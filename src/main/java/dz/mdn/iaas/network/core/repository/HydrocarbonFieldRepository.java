/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: HydrocarbonFieldRepository
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
import dz.mdn.iaas.network.core.model.HydrocarbonField;
import dz.mdn.iaas.network.type.model.StationType;

@Repository
public interface HydrocarbonFieldRepository extends JpaRepository<HydrocarbonField, Long> {

    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HydrocarbonField h WHERE h.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HydrocarbonField h WHERE h.code = :code AND h.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    Optional<HydrocarbonField> findByCode(String code);

    @Query("SELECT h FROM HydrocarbonField h WHERE LOWER(h.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(h.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<HydrocarbonField> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<HydrocarbonField> findByLocation(Location location);

    List<HydrocarbonField> findByLocationId(Long locationId);

    List<HydrocarbonField> findByVendor(Vendor vendor);

    List<HydrocarbonField> findByVendorId(Long vendorId);

    List<HydrocarbonField> findByOperationalStatus(OperationalStatus status);

    List<HydrocarbonField> findByOperationalStatusId(Long statusId);

    List<HydrocarbonField> findByStationType(StationType stationType);

    List<HydrocarbonField> findByStationTypeId(Long stationTypeId);

    @Query("SELECT h FROM HydrocarbonField h WHERE h.commissioningDate IS NOT NULL ORDER BY h.commissioningDate DESC")
    List<HydrocarbonField> findAllOrderByCommissioningDateDesc();
}
