/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHouseService {
    List<House> findAll();

    House save(House house);

    void delete(List<House> listHouse);

    void deleteById(Long houseID);
    
    List<HousesFeature> getListHousesFeature(Long houseId);
    
    House validateHouseInfor(RealEstate newUploadRealEstate, String newLandName, BigDecimal newLandMoney,List<HouseFeatureValue> listLandFeatureValue);
    
    List<HousesDetail> validateHouseDetailInfor(House tempHouse, List<HouseFeatureValue> listHouseFeatureValue);
}
