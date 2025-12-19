/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-19-2025
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

import dz.mdn.iaas.network.core.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Equipment e WHERE e.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Equipment e WHERE e.code = :code AND e.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Equipment e WHERE e.serialNumber = :serialNumber")
    boolean existsBySerialNumber(@Param("serialNumber") String serialNumber);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Equipment e WHERE e.serialNumber = :serialNumber AND e.id != :id")
    boolean existsBySerialNumberAndIdNot(@Param("serialNumber") String serialNumber, @Param("id") Long id);

    @Query("SELECT e FROM Equipment e WHERE LOWER(e.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Equipment> searchByAnyField(@Param("search") String search, Pageable pageable);
    
    List<Equipment> findByFacilityId(Long facilityId);
    
    List<Equipment> findByEquipmentTypeId(Long equipmentTypeId);
}
