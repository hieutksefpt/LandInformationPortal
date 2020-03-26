/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.repository;

import capstone.lip.landinformationportal.dto.MaxMinAvg;
import capstone.lip.landinformationportal.entity.RealEstate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Admin
 */
public interface RealEstateRepository extends JpaRepository<RealEstate, Long>, JpaSpecificationExecutor<RealEstate>{

    RealEstate findByRealEstateLink(String link);

    List<RealEstate> findByRealEstateStatus(String status);

    @Query("SELECT DISTINCT(re.realEstateSource) FROM RealEstate re")
    List<String> listRealEstateSource();

    Page<RealEstate> findByRealEstateStatus(String status, Pageable page);
    
	long countByRealEstateStatus(String status);
	
	long countByRealEstateSource(String source);
	
	long countByRealEstateSourceNot(String source);
	
	long countByRealEstateAddress(String address);
	
	Page<RealEstate> findByRealEstateSource(String source, Pageable page);
	
	Page<RealEstate> findByRealEstateSourceNot(String source, Pageable page);
	
	Page<RealEstate> findAll(Pageable page);
	
	@Query("SELECT new capstone.lip.landinformationportal.dto.MaxMinAvg(MAX(re.realEstatePrice), MIN(re.realEstatePrice), AVG(re.realEstatePrice) ) FROM RealEstate re where "
			+ "(LOWER(re.realEstateName) LIKE CONCAT('%',LOWER(:address),'%') or LOWER(re.realEstateAddress) LIKE CONCAT('%',LOWER(:address),'%') ) "
			+ "and (re.realEstatePrice != -1)" )
	MaxMinAvg getMaxMinAvg(@Param("address")String address);
	
	@Query("SELECT DATE_TRUNK(re.createdDate) FROM RealEstate re where "
			+ "(LOWER(re.realEstateName) LIKE CONCAT('%',LOWER(:address),'%') or LOWER(re.realEstateAddress) LIKE CONCAT('%',LOWER(:address),'%') ) "
			+ "and (re.realEstatePrice != -1) GROUP BY TRUNC(re.createdDate)" )
//	@Query(value="select date_trunc('day', re.\"createdDate\"), max(re.\"RealEstatePrice\"), min(re.\"RealEstatePrice\"), avg(re.\"RealEstatePrice\")  from \"RealEstate\" re " + 
//			"where (lower(re.\"RealEstateName\") like CONCAT('%',lower(:address),'%') or lower(re.\"RealEstateAddress\") like CONCAT('%',lower(:address),'%')) and (re.\"RealEstatePrice\" != -1) " + 
//			"group by date_trunc('day', re.\"createdDate\")", nativeQuery=true )
	List<Timestamp> getGroupTimeAndPrice(@Param("address")String address);
}
