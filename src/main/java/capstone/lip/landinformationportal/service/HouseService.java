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
        return houseRepository.findAll();
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void delete(List<House> listHouse) {
        houseRepository.deleteInBatch(listHouse);
    }

    @Override
    public void deleteById(Long houseId) {
        houseRepository.deleteById(houseId);
    }

    @Override
    public List<HousesFeature> getListHousesFeature(Long houseId) {
        House house = houseRepository.findById(houseId).get();
//        List<HousesFeature> listHousesFeature = house.getListHousesFeatures();   // đhs chỗ này lỗi ?
        List<HousesFeature> listHousesFeature = new ArrayList<>();
        List<HousesDetail> listHouseDetail = house.getListHousesDetail();
        for (HousesDetail housesDetail : listHouseDetail) {
            listHousesFeature.add(housesDetail.getHousesFeature());
        }
        return listHousesFeature;
    }

    @Override
    public House saveHouseInfor(RealEstate newUploadRealEstate, String newHouseName, BigDecimal newHouseMoney, List<HouseFeatureValue> listHouseFeatureValue) {
        RealEstateValidation rev = new RealEstateValidation();
        House tempHouse = new House();
        tempHouse.setHouseName(newHouseName);
        tempHouse.setHousePrice(Double.parseDouble(newHouseMoney.toString()));
        tempHouse.setRealEstate(newUploadRealEstate);

        if (rev.checkHouseValidation(tempHouse,listHouseFeatureValue)) {
            tempHouse = houseRepository.save(tempHouse);
        } else {
            tempHouse = null;
        }

        if (rev.checkHouseDetailValidation(tempHouse) && rev.checkHouseValidation(tempHouse,listHouseFeatureValue)) {
            for (int i = 0; i < listHouseFeatureValue.size(); i++) {
                HousesDetailId tempHDI = new HousesDetailId();
                tempHDI.setHouseId(tempHouse.getHouseId());
                tempHDI.setHousesFeatureId(listHouseFeatureValue.get(i).getHousesFeature().getHousesFeatureID());
                HousesDetail tempHD = new HousesDetail();
                tempHD.setId(tempHDI)
                        .setValue(listHouseFeatureValue.get(i).getValue());
                housesDetailRepository.save(tempHD);
            }
        }
        return tempHouse;
    }
}
