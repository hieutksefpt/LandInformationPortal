/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service.Interface;

import capstone.lip.landinformationportal.common.dto.GroupByDateMaxMin;
import capstone.lip.landinformationportal.common.dto.MaxMinAvg;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Admin
 */
public interface IRealEstateService {

    RealEstate findById(long realEstateId);

    RealEstate save(RealEstate realEstate);
    
    Land getLand(Long realEstateId);

    List<House> getListHouse(Long realEstateId);
 
    boolean delete(long realEstateId);

    Page<RealEstate> findByRealEstateStatus(String status, Pageable page);
    
    long count();
    
    //Search man hinh listallrealestate.xhtml
    List<String> listRealEstateSource();
    
    List<RealEstate> listFilterRealEstate(String realEstateName, String realEstateSource, String realEstateStatus);
    //
    
    long countByRealEstateStatus(String status);
    
    Page<RealEstate> listFilterRealEstateByAddress(String realEstateAddress, Pageable page);
    
    long countByRealEstateSource(String realEstateAddress, String realEstateSource);
    
    long countByRealEstateSourceNot(String realEstateAddress, String realEstateSource);
    
    Page<RealEstate> findAll(Pageable page);
    
    Page<RealEstate> listFilterRealEstateByAddressAndSource(String realEstateAddress, String realEstateSource, Pageable page);
    
    Page<RealEstate> listFilterRealEstateByAddressAndSourceNot(String realEstateAddress, String realEstateSource, Pageable page);
    
    long countByRealEstateAddress(String address);
    
//    @Cacheable(value="listMaxMinAvg", key="{#address}", cacheManager="cacheManager1Hour")
    MaxMinAvg listMaxMinAvg(String location);
    
//    @Cacheable(value="listGroupByDateAndValue", key="{#address}", cacheManager="cacheManager1Hour")
    List<GroupByDateMaxMin> listGroupByDateAndValue(String location);
    
    List<RealEstate> findByRealEstateLatAndRealEstateLng(Double RealEstateLat, Double RealEstateLng);

	Page<RealEstate> findAllBySourceAndStatus(String source, String status, Pageable page);
	
	long countBySourceAndStatus(String source, String status);
	
	Page<RealEstate> findAllByAttribute(Map<String, Object> listAttribute, Pageable page);
	
	long countByAttribute(Map<String, Object> listAttribute);
}
