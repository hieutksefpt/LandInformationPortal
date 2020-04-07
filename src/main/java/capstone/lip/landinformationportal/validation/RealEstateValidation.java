/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.dto.LandFeatureValue;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.RealEstate;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RealEstateValidation {

    public String checkRealEstateValidation(RealEstate newUploadRealEstate) {
        if (newUploadRealEstate.getRealEstateName().equals("")) {
            return "NameEmpty";
        } else if (newUploadRealEstate.getRealEstateAddress().equals("")) {
            return "AddressEmpty";
        } else if (newUploadRealEstate.getRealEstateLat().equals("")) {
            return "LatEmpty";
        } else if (newUploadRealEstate.getRealEstateLng().equals("")) {
            return "LngEmpty";
        } else if (newUploadRealEstate.getRealEstatePrice().equals(BigDecimal.ZERO) || newUploadRealEstate.getRealEstatePrice() == null) {
            return "ZeroPrice";
        } else if (newUploadRealEstate.getUser() == null) {
            return "UserError";
        }
        return "AcceptRealEstate";
    }

    public boolean checkLandValidation(Land tempLand, List<LandFeatureValue> listLandFeatureValue) {                     // check Land if Null RealState Condition
        if (tempLand.getRealEstate() == null || (tempLand.getLandName().equals("") && tempLand.getLandPrice() == 0 && listLandFeatureValue.isEmpty())) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkLandDetailValidation(Land tempLand){
        if(tempLand == null)
            return false;
        return true;
    }
    
    public boolean checkHouseValidation(House tempHouse, List<HouseFeatureValue> listHouseFeatureValue) {
        if (tempHouse.getRealEstate() == null || (tempHouse.getHouseName().equals("") && tempHouse.getHousePrice() == 0 && listHouseFeatureValue.isEmpty())) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkHouseDetailValidation(House tempHouse){
        if(tempHouse == null)
            return false;
        return true;
    }
    
    public boolean checkRealEstateSegmentValidation(RealEstate newUploadRealEstate) {                     // check Land if Null RealState Condition
        if (newUploadRealEstate == null) {
            return false;
        } else {
            return true;
        }
    }
}
