/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.dto.GroupByDateMaxMinCreate;
import capstone.lip.landinformationportal.dto.MaxMinAvg;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;

import java.math.BigDecimal;
import java.util.List;

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
    
    MaxMinAvg listMaxMinAvg(String address);
    
    List<GroupByDateMaxMinCreate> listGroupByDateAndValue(String address);
}
