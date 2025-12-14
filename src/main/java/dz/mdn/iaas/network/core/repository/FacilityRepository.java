/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.common.model.Location;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.core.model.Facility;
import dz.mdn.iaas.network.core.model.FacilityType;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Facility f WHERE f.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Facility f WHERE f.code = :code AND f.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Facility t WHERE t.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Facility t WHERE t.designationFr = :designationFr AND t.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    @Query("SELECT f FROM Facility f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(f.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Facility> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<Facility> findByOperationalStatus(OperationalStatus status);

    List<Facility> findByLocation(Location location);

    List<Facility> findByFacilityType(FacilityType type);

    List<Facility> findByLocationId(Long locationId);

    List<Facility> findByFacilityTypeId(Long typeId);
}
