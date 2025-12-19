/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: TerminalRepository
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.common.model.Location;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.common.model.Vendor;
import dz.mdn.iaas.network.core.model.Terminal;
import dz.mdn.iaas.network.type.model.StationType;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Terminal t WHERE t.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Terminal t WHERE t.code = :code AND t.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    Optional<Terminal> findByCode(String code);

    @Query("SELECT t FROM Terminal t WHERE LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Terminal> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<Terminal> findByLocation(Location location);

    List<Terminal> findByLocationId(Long locationId);

    List<Terminal> findByVendor(Vendor vendor);

    List<Terminal> findByVendorId(Long vendorId);

    List<Terminal> findByOperationalStatus(OperationalStatus status);

    List<Terminal> findByOperationalStatusId(Long statusId);

    List<Terminal> findByStationType(StationType stationType);

    List<Terminal> findByStationTypeId(Long stationTypeId);

    @Query("SELECT t FROM Terminal t WHERE t.commissioningDate IS NOT NULL ORDER BY t.commissioningDate DESC")
    List<Terminal> findAllOrderByCommissioningDateDesc();
}
