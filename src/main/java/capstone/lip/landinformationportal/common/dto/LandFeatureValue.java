/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;

import capstone.lip.landinformationportal.common.entity.LandsFeature;

/**
 *
 * @author Admin
 */
public class LandFeatureValue implements Serializable{

	private static final long serialVersionUID = 1L;
	private LandsFeature landFeature;
    private String value;

    public LandFeatureValue(LandsFeature landFeature, String value) {
        this.landFeature = landFeature;
        this.value = value;
    }

    public LandsFeature getLandFeature() {
        return landFeature;
    }

    public void setLandFeature(LandsFeature landFeature) {
        this.landFeature = landFeature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
