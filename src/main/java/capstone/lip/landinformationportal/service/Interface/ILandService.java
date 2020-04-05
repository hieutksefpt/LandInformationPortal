/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.dto.LandFeatureValue;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ILandService {
    
    List<Land> findAll();

    Land save(Land land);
    
    void delete(Land land);

    void deleteById(Long landID);

    List<LandsFeature> getListLandsFeature(Long landId);
    
    void saveLandInfor(RealEstate newUploadRealEstate, String newLandName, BigDecimal newLandMoney,List<LandFeatureValue> listLandFeatureValue);
}
