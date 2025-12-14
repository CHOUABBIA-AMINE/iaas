/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentTypeRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.core.model.EquipmentType;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM EquipmentType t WHERE t.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM EquipmentType t WHERE t.code = :code AND t.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM EquipmentType t WHERE t.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM EquipmentType t WHERE t.designationFr = :designationFr AND t.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    @Query("SELECT t FROM EquipmentType t WHERE LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%'))"
    									 + " OR LOWER(t.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))"
    									 + " OR LOWER(t.designationEn) LIKE LOWER(CONCAT('%', :search, '%'))"
    									 + " OR LOWER(t.designationFr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<EquipmentType> searchByAnyField(@Param("search") String search, Pageable pageable);
}
