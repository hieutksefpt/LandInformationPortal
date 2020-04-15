/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesDetailId;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
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

    @Autowired
    private HousesDetailRepository housesDetailRepository;

    @Override
    public List<House> findAll() {
    	try {
    		return houseRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

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


    @Override
    public List<HousesFeature> getListHousesFeature(Long houseId) {
    	try {
			House house = houseRepository.findById(houseId).get();
//          List<HousesFeature> listHousesFeature = house.getListHousesFeatures();   // đhs chỗ này lỗi ?
      		List<HousesFeature> listHousesFeature = new ArrayList<>();
	        List<HousesDetail> listHouseDetail = house.getListHousesDetail();
	        for (HousesDetail housesDetail : listHouseDetail) {
	            listHousesFeature.add(housesDetail.getHousesFeature());
	        }
	        return listHousesFeature;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public House validateHouseInfor(RealEstate newUploadRealEstate, String newHouseName, BigDecimal newHouseMoney, List<HouseFeatureValue> listHouseFeatureValue) {
    	try {
    		RealEstateValidation rev = new RealEstateValidation();
            House tempHouse = new House();
            tempHouse.setHouseName(newHouseName);
            tempHouse.setHousePrice(Double.parseDouble(newHouseMoney.toString()));
            tempHouse.setRealEstate(newUploadRealEstate);
            
            if (rev.checkHouseValidation(tempHouse,listHouseFeatureValue)) {
                return tempHouse;
//                landRepository.save(tempLand);
            } else {
                tempHouse = null;
            }

            return tempHouse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public List<HousesDetail> validateHouseDetailInfor(House tempHouse, List<HouseFeatureValue> listHouseFeatureValue){
    	try {
    		RealEstateValidation rev = new RealEstateValidation();
            ArrayList<HousesDetail> ahd = new ArrayList<>();
            if (rev.checkHouseDetailValidation(tempHouse) && rev.checkHouseValidation(tempHouse,listHouseFeatureValue)) {
                for (int i = 0; i < listHouseFeatureValue.size(); i++) {
                    HousesDetailId tempHDI = new HousesDetailId();
                    tempHDI.setHouseId(tempHouse.getHouseId());
                    tempHDI.setHousesFeatureId(listHouseFeatureValue.get(i).getHousesFeature().getHousesFeatureID());
                    HousesDetail tempHD = new HousesDetail();
                    tempHD.setId(tempHDI)
                            .setValue(listHouseFeatureValue.get(i).getValue());
//                    landsDetailRepository.save(tempLD);
                    ahd.add(tempHD);
                }
            }
            return ahd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }
}
