/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service;

import capstone.lip.landinformationportal.business.repository.HouseRepository;
import capstone.lip.landinformationportal.business.service.Interface.IHouseService;
import capstone.lip.landinformationportal.business.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.business.validation.HouseValidation;
import capstone.lip.landinformationportal.business.validation.RealEstateValidation;
import capstone.lip.landinformationportal.common.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.compositekey.HousesDetailId;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HouseService implements IHouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private IHousesDetailService housesDetailService;
    
    @Override
    public House save(House house) {
    	try {
    		HouseValidation validate = new HouseValidation();
    		String error = validate.isValidHouse(house);
    		if (!error.isEmpty()) {
    			throw new Exception(error);
    		}
    		return houseRepository.save(house);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public boolean delete(List<House> listHouse) {
    	try {
    		if (listHouse == null) throw new Exception("null");
    		if (listHouse.isEmpty()) throw new Exception("empty");   		
    		List<HousesDetail> listHouseDetail = listHouse.stream().map(x->x.getListHousesDetail()).flatMap(List::stream).collect(Collectors.toList());
    		housesDetailService.delete(listHouseDetail);
    		houseRepository.deleteAll(listHouse);
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    

    
}
