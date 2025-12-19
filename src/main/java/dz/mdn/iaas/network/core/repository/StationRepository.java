/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StationRepository
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
import dz.mdn.iaas.network.core.model.Station;
import dz.mdn.iaas.network.type.model.StationType;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Station s WHERE s.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Station s WHERE s.code = :code AND s.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    Optional<Station> findByCode(String code);

    @Query("SELECT s FROM Station s WHERE LOWER(s.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Station> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<Station> findByLocation(Location location);

    List<Station> findByLocationId(Long locationId);

    List<Station> findByVendor(Vendor vendor);

    List<Station> findByVendorId(Long vendorId);

    List<Station> findByOperationalStatus(OperationalStatus status);

    List<Station> findByOperationalStatusId(Long statusId);

    List<Station> findByStationType(StationType stationType);

    List<Station> findByStationTypeId(Long stationTypeId);

    @Query("SELECT s FROM Station s WHERE s.commissioningDate IS NOT NULL ORDER BY s.commissioningDate DESC")
    List<Station> findAllOrderByCommissioningDateDesc();
}
