/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.service.Interface.IHousesFeatureService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HousesDetailService implements IHousesDetailService {

    @Autowired
    private HousesDetailRepository housesDetailRepository;

    @Autowired
    private HouseRepository houseRepository;
    
    @Autowired
    private HousesFeatureRepository housesFeatureRepository;
    
    @Override
    public HousesDetail save(HousesDetail housesDetail) {
    	try {
    		if (!houseRepository.findById(housesDetail.getId().getHouseId()).isPresent() 
    				|| !housesFeatureRepository.findById(housesDetail.getId().getHousesFeatureId()).isPresent()) {
    			throw new Exception("Id not found");
    		}
    		return housesDetailRepository.save(housesDetail); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public boolean delete(List<HousesDetail> listHousesDetail) {
    	try {
    		if (listHousesDetail== null) throw new Exception("null");
    		if (listHousesDetail.isEmpty()) throw new Exception("empty");
    		for (HousesDetail element:listHousesDetail) {
    			if (housesDetailRepository.findByIdHouseIdAndIdHousesFeatureId(element.getId().getHouseId(),
    					element.getId().getHousesFeatureId())==null) {
    				throw new Exception("Id not found");
    			}
    		}
    		housesDetailRepository.deleteAll(listHousesDetail);
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
    }

}
