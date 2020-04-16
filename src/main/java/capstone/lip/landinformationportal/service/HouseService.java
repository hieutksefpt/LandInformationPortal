/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.compositekey.HousesDetailId;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.validation.RealEstateValidation;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public House save(House house) {
    	try {
    		return houseRepository.save(house);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public boolean delete(List<House> listHouse) {
    	try {
    		houseRepository.deleteAll(listHouse);
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    

    
}
