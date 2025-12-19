/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AuditLogRepository
 *	@CreatedOn	: 10-27-2025
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: System / Audit
 *
 **/

package dz.mdn.iaas.system.audit.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.system.audit.model.Audited;
import dz.mdn.iaas.system.audit.model.Audited.AuditAction;
import dz.mdn.iaas.system.audit.model.Audited.AuditStatus;

@Repository
public interface AuditedRepository extends JpaRepository<Audited, Long> {

    /**
     * Find audit logs by entity name and ID
     */
    List<Audited> findByEntityNameAndEntityIdOrderByTimestampDesc(String entityName, Long entityId);

    /**
     * Find audit logs by username
     */
    Page<Audited> findByUsernameOrderByTimestampDesc(String username, Pageable pageable);

    /**
     * Find audit logs by action type
     */
    Page<Audited> findByActionOrderByTimestampDesc(AuditAction action, Pageable pageable);

    /**
     * Find audit logs by date range
     */
    @Query("SELECT a FROM Audited a WHERE a.timestamp BETWEEN :startDate AND :endDate ORDER BY a.timestamp DESC")
    Page<Audited> findByTimestampBetween(@Param("startDate") Date startDate, 
                                         @Param("endDate") Date endDate, 
                                         Pageable pageable);

    /**
     * Find audit logs by module
     */
    Page<Audited> findByModuleOrderByTimestampDesc(String module, Pageable pageable);

    /**
     * Find failed operations
     */
    Page<Audited> findByStatusOrderByTimestampDesc(AuditStatus status, Pageable pageable);

    /**
     * Count operations by user
     */
    @Query("SELECT COUNT(a) FROM AuditLog a WHERE a.username = :username AND a.timestamp >= :since")
    long countByUsernameAndTimestampAfter(@Param("username") String username, @Param("since") Date since);

    /**
     * Get user activity summary
     */
    @Query("SELECT a.action, COUNT(a) FROM AuditLog a WHERE a.username = :username " +
           "AND a.timestamp >= :since GROUP BY a.action")
    List<Object[]> getUserActivitySummary(@Param("username") String username, @Param("since") Date since);

    /**
     * Get system activity statistics
     */
    @Query("SELECT a.entityName, a.action, COUNT(a) FROM AuditLog a " +
           "WHERE a.timestamp >= :since GROUP BY a.entityName, a.action")
    List<Object[]> getSystemActivityStatistics(@Param("since") Date since);
}
