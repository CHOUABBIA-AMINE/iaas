/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: VendorRepository
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.configuration.reference.model.Country;
import dz.mdn.iaas.network.common.model.Vendor;
import dz.mdn.iaas.network.type.model.VendorType;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByShortName(String shortName);

    @Query("SELECT v FROM Vendor v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(v.shortName) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Vendor> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<Vendor> findByVendorType(VendorType vendorType);

    List<Vendor> findByVendorTypeId(Long vendorTypeId);

    List<Vendor> findByCountry(Country country);

    List<Vendor> findByCountryId(Long countryId);

    @Query("SELECT v FROM Vendor v ORDER BY v.name ASC")
    List<Vendor> findAllOrderByNameAsc();

    @Query("SELECT v FROM Vendor v WHERE v.shortName IS NOT NULL ORDER BY v.shortName ASC")
    List<Vendor> findAllOrderByShortNameAsc();
}
