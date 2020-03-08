/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "HousesFeature")
public class HousesFeature extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "HousesFeatureID")
    private Long housesFeatureID;
    @Column(name = "HousesFeatureName")
    private String housesFeatureName;
    @Column(name = "HousesFeatureUnit")
    private String housesFeatureUnit;


    public HousesFeature() {
    }

    public HousesFeature(String housesFeatureName, String housesFeatureUnit) {
        this.housesFeatureName = housesFeatureName;
        this.housesFeatureUnit = housesFeatureUnit;
    }

    public Long getHousesFeatureID() {
        return housesFeatureID;
    }

    public void setHousesFeatureID(Long housesFeatureID) {
        this.housesFeatureID = housesFeatureID;
    }

    public String getHousesFeatureName() {
        return housesFeatureName;
    }

    public void setHousesFeatureName(String housesFeatureName) {
        this.housesFeatureName = housesFeatureName;
    }

    public String getHousesFeatureUnit() {
        return housesFeatureUnit;
    }

    public void setHousesFeatureUnit(String housesFeatureUnit) {
        this.housesFeatureUnit = housesFeatureUnit;
    }
    
    
    

}
