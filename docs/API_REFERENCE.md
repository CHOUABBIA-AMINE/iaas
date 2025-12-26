# Dashboard API Reference

**Base URL**: `http://localhost:8080/iaas/api`

---

## Endpoints

### 1. Get Dashboard Summary

**Endpoint**: `GET /network/flow/dashboard/summary`

**Description**: Retrieve comprehensive dashboard metrics including infrastructure counts, today's metrics, status breakdown, and monthly summary.

**Request**:
```http
GET /iaas/api/network/flow/dashboard/summary HTTP/1.1
Host: localhost:8080
```

**Response** (200 OK):
```json
{
  "totalStations": 45,
  "totalTerminals": 32,
  "totalFields": 78,
  "totalPipelines": 90,
  "currentDate": "2025-12-27",
  "totalVolumeToday": 58450.0,
  "averagePressureToday": 64.5,
  "activePipelines": 87,
  "totalReadingsToday": 522,
  "expectedReadingsToday": 540,
  "totalTransportedToday": 58450.0,
  "totalEstimatedToday": 65000.0,
  "varianceToday": -6550.0,
  "variancePercentToday": -10.08,
  "pipelinesOnTarget": 65,
  "pipelinesBelowTarget": 18,
  "pipelinesAboveTarget": 4,
  "pipelinesOffline": 3,
  "lastReadingTime": "20:00",
  "nextReadingTime": "00:00",
  "currentDayOfMonth": 27,
  "monthlyTotalTransported": 1574200.0,
  "monthlyTotalEstimated": 1755000.0,
  "monthlyVariance": -180800.0,
  "monthlyVariancePercent": -10.3,
  "daysOnTargetThisMonth": 18
}
```

---

### 2. Get Pipeline Statuses

**Endpoint**: `GET /network/flow/dashboard/pipelines`

**Description**: Retrieve status for all pipelines for a specific date.

**Query Parameters**:
- `date` (optional): Date in format `yyyy-MM-dd`. Defaults to today.

**Request Examples**:
```http
# Get today's statuses
GET /iaas/api/network/flow/dashboard/pipelines HTTP/1.1

# Get statuses for specific date
GET /iaas/api/network/flow/dashboard/pipelines?date=2025-12-27 HTTP/1.1
```

**Response** (200 OK):
```json
[
  {
    "pipelineId": 1,
    "pipelineCode": "PL-001",
    "pipelineName": "Main Transfer Line",
    "measurementDate": "2025-12-27",
    "lastReadingTime": "20:00",
    "lastVolume": 1250.0,
    "lastPressure": 65.3,
    "dailyVolumeTransported": 7500.0,
    "dailyVolumeEstimated": 8000.0,
    "dailyVariance": -500.0,
    "dailyVariancePercent": -6.25,
    "dailyProgress": 93.75,
    "averagePressureToday": 64.8,
    "minPressureToday": 62.1,
    "maxPressureToday": 67.2,
    "volumeStatus": "BELOW_TARGET",
    "pressureStatus": "NORMAL",
    "readingsCompletedToday": 6,
    "readingsExpectedToday": 6
  },
  {
    "pipelineId": 2,
    "pipelineCode": "PL-002",
    "pipelineName": "Secondary Line A",
    "measurementDate": "2025-12-27",
    "lastReadingTime": "20:00",
    "lastVolume": 950.0,
    "lastPressure": 63.7,
    "dailyVolumeTransported": 5800.0,
    "dailyVolumeEstimated": 5500.0,
    "dailyVariance": 300.0,
    "dailyVariancePercent": 5.45,
    "dailyProgress": 105.45,
    "averagePressureToday": 63.5,
    "minPressureToday": 61.8,
    "maxPressureToday": 65.1,
    "volumeStatus": "ABOVE_TARGET",
    "pressureStatus": "NORMAL",
    "readingsCompletedToday": 6,
    "readingsExpectedToday": 6
  }
]
```

**Volume Status Values**:
- `ON_TARGET`: Variance within ±5%
- `BELOW_TARGET`: Variance < -5%
- `ABOVE_TARGET`: Variance > +5%
- `OFFLINE`: No data available

**Pressure Status Values**:
- `NORMAL`: 55-75 bar
- `LOW`: < 55 bar
- `HIGH`: > 75 bar
- `OFFLINE`: No data available

---

### 3. Get Specific Pipeline Status

**Endpoint**: `GET /network/flow/dashboard/pipeline/{id}`

**Description**: Retrieve detailed status for a specific pipeline.

**Path Parameters**:
- `id`: Pipeline ID (Long)

**Query Parameters**:
- `date` (optional): Date in format `yyyy-MM-dd`. Defaults to today.

**Request Examples**:
```http
# Get today's status for pipeline 1
GET /iaas/api/network/flow/dashboard/pipeline/1 HTTP/1.1

# Get status for specific date
GET /iaas/api/network/flow/dashboard/pipeline/1?date=2025-12-27 HTTP/1.1
```

**Response** (200 OK):
```json
{
  "pipelineId": 1,
  "pipelineCode": "PL-001",
  "pipelineName": "Main Transfer Line",
  "measurementDate": "2025-12-27",
  "lastReadingTime": "20:00",
  "lastVolume": 1250.0,
  "lastPressure": 65.3,
  "dailyVolumeTransported": 7500.0,
  "dailyVolumeEstimated": 8000.0,
  "dailyVariance": -500.0,
  "dailyVariancePercent": -6.25,
  "dailyProgress": 93.75,
  "averagePressureToday": 64.8,
  "minPressureToday": 62.1,
  "maxPressureToday": 67.2,
  "volumeStatus": "BELOW_TARGET",
  "pressureStatus": "NORMAL",
  "readingsCompletedToday": 6,
  "readingsExpectedToday": 6
}
```

**Error Response** (404 Not Found):
```json
{
  "timestamp": "2025-12-27T00:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Pipeline not found with id: 999",
  "path": "/iaas/api/network/flow/dashboard/pipeline/999"
}
```

---

### 4. Get Daily Trends

**Endpoint**: `GET /network/flow/dashboard/trends`

**Description**: Retrieve daily trend data for charts (volume comparison, pressure trends).

**Query Parameters**:
- `startDate` (optional): Start date in format `yyyy-MM-dd`. Defaults to 7 days ago.
- `endDate` (optional): End date in format `yyyy-MM-dd`. Defaults to today.

**Request Examples**:
```http
# Get last 7 days (default)
GET /iaas/api/network/flow/dashboard/trends HTTP/1.1

# Get last 30 days
GET /iaas/api/network/flow/dashboard/trends?startDate=2025-11-27&endDate=2025-12-27 HTTP/1.1

# Get specific date range
GET /iaas/api/network/flow/dashboard/trends?startDate=2025-12-20&endDate=2025-12-27 HTTP/1.1
```

**Response** (200 OK):
```json
[
  {
    "date": "2025-12-20",
    "totalVolumeTransported": 62300.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -2700.0,
    "variancePercent": -4.15,
    "averagePressure": 63.8,
    "activePipelines": 88
  },
  {
    "date": "2025-12-21",
    "totalVolumeTransported": 59800.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -5200.0,
    "variancePercent": -8.0,
    "averagePressure": 64.2,
    "activePipelines": 86
  },
  {
    "date": "2025-12-22",
    "totalVolumeTransported": 63500.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -1500.0,
    "variancePercent": -2.31,
    "averagePressure": 64.7,
    "activePipelines": 89
  },
  {
    "date": "2025-12-23",
    "totalVolumeTransported": 64200.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -800.0,
    "variancePercent": -1.23,
    "averagePressure": 63.9,
    "activePipelines": 90
  },
  {
    "date": "2025-12-24",
    "totalVolumeTransported": 61500.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -3500.0,
    "variancePercent": -5.38,
    "averagePressure": 64.1,
    "activePipelines": 87
  },
  {
    "date": "2025-12-25",
    "totalVolumeTransported": 58900.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -6100.0,
    "variancePercent": -9.38,
    "averagePressure": 63.6,
    "activePipelines": 85
  },
  {
    "date": "2025-12-26",
    "totalVolumeTransported": 60200.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -4800.0,
    "variancePercent": -7.38,
    "averagePressure": 64.3,
    "activePipelines": 87
  },
  {
    "date": "2025-12-27",
    "totalVolumeTransported": 58450.0,
    "totalVolumeEstimated": 65000.0,
    "variance": -6550.0,
    "variancePercent": -10.08,
    "averagePressure": 64.5,
    "activePipelines": 87
  }
]
```

---

## HTTP Status Codes

| Code | Description |
|------|-------------|
| 200  | OK - Request successful |
| 404  | Not Found - Pipeline ID doesn't exist |
| 500  | Internal Server Error - Server error occurred |

---

## CORS Configuration

All endpoints support CORS with:
- **Allowed Origins**: `*` (all origins)
- **Allowed Methods**: `GET`
- **Allowed Headers**: All headers

---

## Testing with curl

```bash
# Dashboard Summary
curl -X GET "http://localhost:8080/iaas/api/network/flow/dashboard/summary" \
  -H "Accept: application/json"

# All Pipeline Statuses (Today)
curl -X GET "http://localhost:8080/iaas/api/network/flow/dashboard/pipelines" \
  -H "Accept: application/json"

# All Pipeline Statuses (Specific Date)
curl -X GET "http://localhost:8080/iaas/api/network/flow/dashboard/pipelines?date=2025-12-27" \
  -H "Accept: application/json"

# Specific Pipeline Status
curl -X GET "http://localhost:8080/iaas/api/network/flow/dashboard/pipeline/1" \
  -H "Accept: application/json"

# Daily Trends (Last 7 Days)
curl -X GET "http://localhost:8080/iaas/api/network/flow/dashboard/trends" \
  -H "Accept: application/json"

# Daily Trends (Custom Range)
curl -X GET "http://localhost:8080/iaas/api/network/flow/dashboard/trends?startDate=2025-12-20&endDate=2025-12-27" \
  -H "Accept: application/json"
```

---

## Testing with JavaScript/Fetch

```javascript
// Dashboard Summary
fetch('http://localhost:8080/iaas/api/network/flow/dashboard/summary')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));

// Pipeline Statuses with Date
const date = '2025-12-27';
fetch(`http://localhost:8080/iaas/api/network/flow/dashboard/pipelines?date=${date}`)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));

// Specific Pipeline
const pipelineId = 1;
fetch(`http://localhost:8080/iaas/api/network/flow/dashboard/pipeline/${pipelineId}`)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));

// Daily Trends
const startDate = '2025-12-20';
const endDate = '2025-12-27';
fetch(`http://localhost:8080/iaas/api/network/flow/dashboard/trends?startDate=${startDate}&endDate=${endDate}`)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));
```

---

## Swagger/OpenAPI Documentation

Interactive API documentation is available at:

```
http://localhost:8080/swagger-ui.html
```

Navigate to the **Dashboard** tag to explore and test all endpoints.

---

**Last Updated**: December 27, 2025
