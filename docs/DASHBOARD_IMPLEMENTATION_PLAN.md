# Flow Monitoring Dashboard - Implementation Plan

**Date**: December 27, 2025  
**Project**: IAAS - Infrastructure Asset Management System  
**Module**: Network Flow Monitoring Dashboard

---

## 🎯 Overview

Based on the existing backend structure and your 4-hour interval monitoring workflow, this document outlines the complete implementation plan for the Flow Monitoring Dashboard.

### Current System Analysis

**Existing Entities (Analyzed):**
- `FlowVolume` - Stores volume readings per 4-hour period
- `FlowPressure` - Stores pressure readings per 4-hour period
- `FlowTransported` - Stores daily transported vs estimated volumes
- `MeasurementHour` - Defines the 4-hour reading schedule
- `Pipeline` - Core infrastructure entity

**Database Structure:**
- `T_03_04_02`: Flow Volume (F_01=volume, F_02=date, F_03=hour, F_04=pipeline)
- `T_03_04_03`: Flow Pressure (F_01=pressure, F_02=date, F_03=hour, F_04=pipeline)
- `T_03_04_04`: Flow Transported (F_01=estimated, F_02=transported, F_03=date, F_04=pipeline)

---

## 📄 Phase 1: Backend API Development

### 1.1 DTOs Created ✅

#### DashboardSummaryDTO
```java
public class DashboardSummaryDTO {
    // Infrastructure counts
    private Long totalStations;
    private Long totalTerminals;
    private Long totalFields;
    private Long totalPipelines;
    
    // Today's summary
    private Double totalVolumeToday;
    private Double averagePressureToday;
    private Integer activePipelines;
    private Integer totalReadingsToday;
    
    // Daily transported vs estimated
    private Double totalTransportedToday;
    private Double totalEstimatedToday;
    private Double varianceToday;
    private Double variancePercentToday;
    
    // Status breakdown
    private Integer pipelinesOnTarget;
    private Integer pipelinesBelowTarget;
    private Integer pipelinesAboveTarget;
    private Integer pipelinesOffline;
    
    // Monthly summary
    private Double monthlyTotalTransported;
    private Double monthlyTotalEstimated;
    // ... more fields
}
```

#### PipelineStatusDTO
```java
public class PipelineStatusDTO {
    // Pipeline info
    private Long pipelineId;
    private String pipelineCode;
    private String pipelineName;
    
    // Latest reading
    private Double lastVolume;
    private Double lastPressure;
    private LocalTime lastReadingTime;
    
    // Daily metrics
    private Double dailyVolumeTransported;
    private Double dailyVolumeEstimated;
    private Double dailyVariance;
    private Double dailyVariancePercent;
    private Double dailyProgress;
    
    // Status
    private String volumeStatus;  // ON_TARGET, BELOW_TARGET, ABOVE_TARGET, OFFLINE
    private String pressureStatus;  // NORMAL, LOW, HIGH, OFFLINE
}
```

#### DailyTrendDTO
```java
public class DailyTrendDTO {
    private LocalDate date;
    private Double totalVolumeTransported;
    private Double totalVolumeEstimated;
    private Double variance;
    private Double variancePercent;
    private Double averagePressure;
    private Integer activePipelines;
}
```

### 1.2 Service Layer (To Be Created)

**File**: `src/main/java/dz/mdn/iaas/network/flow/service/DashboardService.java`

```java
@Service
public class DashboardService {
    
    @Autowired
    private FlowVolumeRepository flowVolumeRepository;
    
    @Autowired
    private FlowPressureRepository flowPressureRepository;
    
    @Autowired
    private FlowTransportedRepository flowTransportedRepository;
    
    @Autowired
    private PipelineRepository pipelineRepository;
    
    /**
     * Get dashboard summary for today
     */
    public DashboardSummaryDTO getDashboardSummary() {
        LocalDate today = LocalDate.now();
        
        return DashboardSummaryDTO.builder()
            .totalPipelines(pipelineRepository.count())
            .totalVolumeToday(calculateTotalVolumeToday(today))
            .averagePressureToday(calculateAveragePressureToday(today))
            .totalTransportedToday(getTotalTransportedToday(today))
            .totalEstimatedToday(getTotalEstimatedToday(today))
            // ... calculate all metrics
            .build();
    }
    
    /**
     * Get pipeline status list for today
     */
    public List<PipelineStatusDTO> getPipelineStatuses(LocalDate date) {
        List<Pipeline> pipelines = pipelineRepository.findAll();
        
        return pipelines.stream()
            .map(pipeline -> buildPipelineStatus(pipeline, date))
            .collect(Collectors.toList());
    }
    
    /**
     * Get daily trend data for chart
     */
    public List<DailyTrendDTO> getDailyTrend(LocalDate startDate, LocalDate endDate) {
        List<DailyTrendDTO> trends = new ArrayList<>();
        
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            trends.add(buildDailyTrend(date));
        }
        
        return trends;
    }
    
    /**
     * Calculate total volume for today (sum of all 6 readings)
     */
    private Double calculateTotalVolumeToday(LocalDate date) {
        return flowVolumeRepository.sumVolumeByDate(date);
    }
    
    /**
     * Calculate average pressure for today
     */
    private Double calculateAveragePressureToday(LocalDate date) {
        return flowPressureRepository.avgPressureByDate(date);
    }
    
    /**
     * Build pipeline status from entities
     */
    private PipelineStatusDTO buildPipelineStatus(Pipeline pipeline, LocalDate date) {
        // Get all volume readings for this pipeline today
        List<FlowVolume> volumes = flowVolumeRepository
            .findByPipelineAndDate(pipeline.getId(), date);
        
        // Get all pressure readings
        List<FlowPressure> pressures = flowPressureRepository
            .findByPipelineAndDate(pipeline.getId(), date);
        
        // Get transported vs estimated
        FlowTransported transported = flowTransportedRepository
            .findByPipelineAndDate(pipeline.getId(), date)
            .orElse(null);
        
        // Calculate metrics
        Double dailyVolume = volumes.stream()
            .mapToDouble(FlowVolume::getVolume)
            .sum();
        
        Double avgPressure = pressures.stream()
            .mapToDouble(FlowPressure::getPressure)
            .average()
            .orElse(0.0);
        
        // Latest reading
        FlowVolume latestVolume = volumes.stream()
            .max(Comparator.comparing(v -> v.getMeasurementHour().getHour()))
            .orElse(null);
        
        FlowPressure latestPressure = pressures.stream()
            .max(Comparator.comparing(p -> p.getMeasurementHour().getHour()))
            .orElse(null);
        
        // Determine status
        String volumeStatus = determineVolumeStatus(transported);
        String pressureStatus = determinePressureStatus(avgPressure);
        
        return PipelineStatusDTO.builder()
            .pipelineId(pipeline.getId())
            .pipelineCode(pipeline.getCode())
            .pipelineName(pipeline.getName())
            .lastVolume(latestVolume != null ? latestVolume.getVolume() : null)
            .lastPressure(latestPressure != null ? latestPressure.getPressure() : null)
            .dailyVolumeTransported(dailyVolume)
            .dailyVolumeEstimated(transported != null ? transported.getVolumeEstimated() : null)
            .volumeStatus(volumeStatus)
            .pressureStatus(pressureStatus)
            .readingsCompletedToday(volumes.size())
            .readingsExpectedToday(6)  // 6 readings per day (00:00, 04:00, 08:00, 12:00, 16:00, 20:00)
            .build();
    }
    
    /**
     * Determine volume status based on variance
     */
    private String determineVolumeStatus(FlowTransported transported) {
        if (transported == null || transported.getVolumeTransported() == null) {
            return "OFFLINE";
        }
        
        double variance = ((transported.getVolumeTransported() - transported.getVolumeEstimated()) 
                          / transported.getVolumeEstimated()) * 100;
        
        if (variance >= -5 && variance <= 5) {
            return "ON_TARGET";
        } else if (variance < -5) {
            return "BELOW_TARGET";
        } else {
            return "ABOVE_TARGET";
        }
    }
    
    /**
     * Determine pressure status
     */
    private String determinePressureStatus(Double pressure) {
        if (pressure == null || pressure == 0) {
            return "OFFLINE";
        }
        
        // Assuming normal range is 55-75 bar
        if (pressure >= 55 && pressure <= 75) {
            return "NORMAL";
        } else if (pressure < 55) {
            return "LOW";
        } else {
            return "HIGH";
        }
    }
}
```

### 1.3 Repository Queries (To Be Added)

**Add to FlowVolumeRepository:**
```java
public interface FlowVolumeRepository extends JpaRepository<FlowVolume, Long> {
    
    @Query("SELECT SUM(fv.volume) FROM FlowVolume fv WHERE fv.measurementDate = :date")
    Double sumVolumeByDate(@Param("date") LocalDate date);
    
    @Query("SELECT fv FROM FlowVolume fv WHERE fv.pipeline.id = :pipelineId AND fv.measurementDate = :date ORDER BY fv.measurementHour.hour")
    List<FlowVolume> findByPipelineAndDate(@Param("pipelineId") Long pipelineId, @Param("date") LocalDate date);
    
    @Query("SELECT fv FROM FlowVolume fv WHERE fv.measurementDate BETWEEN :startDate AND :endDate")
    List<FlowVolume> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
```

**Add to FlowPressureRepository:**
```java
public interface FlowPressureRepository extends JpaRepository<FlowPressure, Long> {
    
    @Query("SELECT AVG(fp.pressure) FROM FlowPressure fp WHERE fp.measurementDate = :date")
    Double avgPressureByDate(@Param("date") LocalDate date);
    
    @Query("SELECT fp FROM FlowPressure fp WHERE fp.pipeline.id = :pipelineId AND fp.measurementDate = :date ORDER BY fp.measurementHour.hour")
    List<FlowPressure> findByPipelineAndDate(@Param("pipelineId") Long pipelineId, @Param("date") LocalDate date);
}
```

**Add to FlowTransportedRepository:**
```java
public interface FlowTransportedRepository extends JpaRepository<FlowTransported, Long> {
    
    Optional<FlowTransported> findByPipelineIdAndMeasurementDate(Long pipelineId, LocalDate date);
    
    @Query("SELECT SUM(ft.volumeTransported) FROM FlowTransported ft WHERE ft.measurementDate = :date")
    Double sumTransportedByDate(@Param("date") LocalDate date);
    
    @Query("SELECT SUM(ft.volumeEstimated) FROM FlowTransported ft WHERE ft.measurementDate = :date")
    Double sumEstimatedByDate(@Param("date") LocalDate date);
    
    List<FlowTransported> findByMeasurementDateBetween(LocalDate startDate, LocalDate endDate);
}
```

### 1.4 Controller (To Be Created)

**File**: `src/main/java/dz/mdn/iaas/network/flow/controller/DashboardController.java`

```java
@RestController
@RequestMapping("/network/flow/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    /**
     * GET /network/flow/dashboard/summary
     * Get overall dashboard summary
     */
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> getDashboardSummary() {
        DashboardSummaryDTO summary = dashboardService.getDashboardSummary();
        return ResponseEntity.ok(summary);
    }
    
    /**
     * GET /network/flow/dashboard/pipelines
     * Get all pipeline statuses for today
     */
    @GetMapping("/pipelines")
    public ResponseEntity<List<PipelineStatusDTO>> getPipelineStatuses(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        LocalDate targetDate = date != null ? date : LocalDate.now();
        List<PipelineStatusDTO> statuses = dashboardService.getPipelineStatuses(targetDate);
        return ResponseEntity.ok(statuses);
    }
    
    /**
     * GET /network/flow/dashboard/trends
     * Get daily trend data for charts
     */
    @GetMapping("/trends")
    public ResponseEntity<List<DailyTrendDTO>> getDailyTrends(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        LocalDate start = startDate != null ? startDate : LocalDate.now().minusDays(7);
        LocalDate end = endDate != null ? endDate : LocalDate.now();
        
        List<DailyTrendDTO> trends = dashboardService.getDailyTrend(start, end);
        return ResponseEntity.ok(trends);
    }
    
    /**
     * GET /network/flow/dashboard/pipeline/{id}
     * Get detailed status for a specific pipeline
     */
    @GetMapping("/pipeline/{id}")
    public ResponseEntity<PipelineStatusDTO> getPipelineStatus(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        LocalDate targetDate = date != null ? date : LocalDate.now();
        PipelineStatusDTO status = dashboardService.getPipelineStatus(id, targetDate);
        return ResponseEntity.ok(status);
    }
}
```

---

## 💻 Phase 2: Frontend Implementation (iaas-web)

### 2.1 Directory Structure

```
src/modules/dashboard/
├── components/
│   ├── InfrastructureKPIs.tsx          # Top count cards
│   ├── TodaysSummary.tsx               # Today's metrics panel
│   ├── PipelineStatusTable.tsx         # Main data table
│   ├── ReadingTimeline.tsx             # 4-hour schedule
│   ├── VolumeComparisonChart.tsx       # Bar chart
│   ├── TrendCharts.tsx                 # Line charts
│   ├── TopPerformers.tsx               # Performance lists
│   ├── VarianceAnalysis.tsx            # Status breakdown
│   └── MonthlyOverview.tsx             # Monthly summary
├── hooks/
│   ├── useDashboardData.ts             # Main data hook
│   ├── usePipelineStatuses.ts          # Pipeline list hook
│   └── useTrendData.ts                 # Chart data hook
├── services/
│   └── DashboardService.ts             # API calls
├── types/
│   └── dashboard.types.ts              # TypeScript interfaces
└── pages/
    └── DashboardPage.tsx               # Main page
```

### 2.2 TypeScript Types

**File**: `src/modules/dashboard/types/dashboard.types.ts`

```typescript
export interface DashboardSummary {
  totalStations: number;
  totalTerminals: number;
  totalFields: number;
  totalPipelines: number;
  currentDate: string;
  totalVolumeToday: number;
  averagePressureToday: number;
  activePipelines: number;
  totalReadingsToday: number;
  expectedReadingsToday: number;
  totalTransportedToday: number;
  totalEstimatedToday: number;
  varianceToday: number;
  variancePercentToday: number;
  pipelinesOnTarget: number;
  pipelinesBelowTarget: number;
  pipelinesAboveTarget: number;
  pipelinesOffline: number;
  lastReadingTime: string;
  nextReadingTime: string;
  monthlyTotalTransported: number;
  monthlyTotalEstimated: number;
  monthlyVariance: number;
  monthlyVariancePercent: number;
}

export interface PipelineStatus {
  pipelineId: number;
  pipelineCode: string;
  pipelineName: string;
  measurementDate: string;
  lastReadingTime: string;
  lastVolume: number;
  lastPressure: number;
  dailyVolumeTransported: number;
  dailyVolumeEstimated: number;
  dailyVariance: number;
  dailyVariancePercent: number;
  dailyProgress: number;
  averagePressureToday: number;
  minPressureToday: number;
  maxPressureToday: number;
  volumeStatus: 'ON_TARGET' | 'BELOW_TARGET' | 'ABOVE_TARGET' | 'OFFLINE';
  pressureStatus: 'NORMAL' | 'LOW' | 'HIGH' | 'OFFLINE';
  readingsCompletedToday: number;
  readingsExpectedToday: number;
}

export interface DailyTrend {
  date: string;
  totalVolumeTransported: number;
  totalVolumeEstimated: number;
  variance: number;
  variancePercent: number;
  averagePressure: number;
  activePipelines: number;
}
```

### 2.3 API Service

**File**: `src/modules/dashboard/services/DashboardService.ts`

```typescript
import axiosInstance from '../../../shared/config/axios';
import { DashboardSummary, PipelineStatus, DailyTrend } from '../types/dashboard.types';

class DashboardService {
  private readonly BASE_URL = '/network/flow/dashboard';

  async getSummary(): Promise<DashboardSummary> {
    const response = await axiosInstance.get<DashboardSummary>(
      `${this.BASE_URL}/summary`
    );
    return response.data;
  }

  async getPipelineStatuses(date?: string): Promise<PipelineStatus[]> {
    const response = await axiosInstance.get<PipelineStatus[]>(
      `${this.BASE_URL}/pipelines`,
      { params: { date } }
    );
    return response.data;
  }

  async getTrends(startDate?: string, endDate?: string): Promise<DailyTrend[]> {
    const response = await axiosInstance.get<DailyTrend[]>(
      `${this.BASE_URL}/trends`,
      { params: { startDate, endDate } }
    );
    return response.data;
  }

  async getPipelineStatus(id: number, date?: string): Promise<PipelineStatus> {
    const response = await axiosInstance.get<PipelineStatus>(
      `${this.BASE_URL}/pipeline/${id}`,
      { params: { date } }
    );
    return response.data;
  }
}

export default new DashboardService();
```

---

## 🚀 Implementation Timeline

### Week 1: Backend
- ✅ Day 1: DTOs created
- ⏳ Day 2-3: Repository queries
- ⏳ Day 4-5: DashboardService implementation
- ⏳ Day 6: DashboardController
- ⏳ Day 7: Testing & bug fixes

### Week 2: Frontend
- ⏳ Day 1: Types, services, hooks
- ⏳ Day 2-3: Core components (KPIs, Summary, Table)
- ⏳ Day 4: Charts (Volume, Trends)
- ⏳ Day 5: Additional components (Timeline, Top Performers)
- ⏳ Day 6: Dashboard page layout
- ⏳ Day 7: Styling & responsive design

### Week 3: Integration & Polish
- Day 1-2: End-to-end testing
- Day 3-4: Performance optimization
- Day 5: Documentation
- Day 6-7: User acceptance testing

---

## 📊 Dashboard Features Summary

### Real-Time Metrics
1. Infrastructure counts (Stations, Terminals, Fields, Pipelines)
2. Today's total volume and average pressure
3. Active pipelines count
4. Reading completion status

### Daily Monitoring
1. Transported vs Estimated volume comparison
2. Variance analysis (On Target / Below / Above)
3. Pipeline-by-pipeline status table
4. 4-hour reading timeline

### Trending & Analysis
1. Last 7 days volume comparison chart
2. Pressure trends (24-hour)
3. Top performing pipelines
4. Pipelines requiring attention

### Monthly Overview
1. Month-to-date totals
2. Days on target percentage
3. Average daily volume
4. Best/worst performing days

---

**Status**: ✅ Phase 1 DTOs Completed | ⏳ Next: Service Layer  
**Last Updated**: December 27, 2025
