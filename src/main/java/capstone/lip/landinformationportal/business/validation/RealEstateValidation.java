/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.validation;

import capstone.lip.landinformationportal.common.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.common.dto.LandFeatureValue;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.RealEstate;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RealEstateValidation {

    public String isRealEstateValid(RealEstate newUploadRealEstate) {
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
        return "";
    }

    public boolean checkLandValidation(Land tempLand, List<LandFeatureValue> listLandFeatureValue) {                     // check Land if Null RealState Condition
        if (tempLand.getRealEstate() == null || tempLand.getLandName().equals("") || tempLand.getLandPrice() == BigDecimal.ZERO) {
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
        if (tempHouse.getRealEstate() == null || tempHouse.getHouseName().equals("") || tempHouse.getHousePrice() == BigDecimal.ZERO) {
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