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
    private Long housesfeatureID;
    @Column(name = "HousesFeatureName")
    private String housesfeatureName;
    @Column(name = "HousesFeatureUnit")
    private String housesfeatureUnit;

    public HousesFeature(String housesfeatureName, String housesfeatureUnit) {
        this.housesfeatureName = housesfeatureName;
        this.housesfeatureUnit = housesfeatureUnit;
    }

    public HousesFeature() {
    }
    
    
    public Long getHousesfeatureID() {
        return housesfeatureID;
    }

    public void setHousesfeatureID(Long housesfeatureID) {
        this.housesfeatureID = housesfeatureID;
    }

    public String getHousesfeatureName() {
        return housesfeatureName;
    }

    public void setHousesfeatureName(String housesfeatureName) {
        this.housesfeatureName = housesfeatureName;
    }

    public String getHousesfeatureUnit() {
        return housesfeatureUnit;
    }

    public void setHousesfeatureUnit(String housesfeatureUnit) {
        this.housesfeatureUnit = housesfeatureUnit;
    }

}
