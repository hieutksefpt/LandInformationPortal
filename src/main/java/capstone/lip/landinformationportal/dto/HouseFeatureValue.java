/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.dto;

import capstone.lip.landinformationportal.entity.HousesFeature;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class HouseFeatureValue implements Serializable{
    private HousesFeature housesFeature;
    private String value;

    public HouseFeatureValue() {
    }

    
    public HouseFeatureValue(HousesFeature housesFeature, String value) {
        this.housesFeature = housesFeature;
        this.value = value;
    }

    public HousesFeature getHousesFeature() {
        return housesFeature;
    }

    public void setHousesFeature(HousesFeature housesFeature) {
        this.housesFeature = housesFeature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    

}
