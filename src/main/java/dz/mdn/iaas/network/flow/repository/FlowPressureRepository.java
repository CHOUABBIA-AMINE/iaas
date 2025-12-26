/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FlowPressureRepository
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Core
 *
 **/

package dz.mdn.iaas.network.flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.flow.model.FlowPressure;

@Repository
public interface FlowPressureRepository extends JpaRepository<FlowPressure, Long> {


}
