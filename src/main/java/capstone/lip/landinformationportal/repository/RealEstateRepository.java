/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.repository;

import capstone.lip.landinformationportal.entity.RealEstate;

import java.util.List;

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

    @Query("SELECT re FROM RealEstate re WHERE re.realEstateName like \'%Đường%\'")
    List<RealEstate> listFilterRealEstateName(String realEstateName);

    @Query("SELECT re FROM RealEstate re WHERE re.realEstateSource like :source")
    List<RealEstate> listFilterRealEstateSource( @Param("source") String realEstateSource);
    
    @Query("SELECT re FROM RealEstate re WHERE re.realEstateStatus like \'-1\'")
    List<RealEstate> listFilterRealEstateStatus(String realEstateStatus);
    
    Page<RealEstate> findByRealEstateStatus(String status, Pageable page);
    
    
}
