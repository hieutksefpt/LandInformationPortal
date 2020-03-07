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
@Table(name = "LandsFeature")
public class LandsFeature extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "LandsFeatureID")
    private Long landsfeatureID;
    @Column(name = "LandsFeatureName")
    private String landsfeatureName;
    @Column(name = "LandsFeatureUnit")
    private String landsfeatureUnit;

    public LandsFeature(String landsfeatureName, String landsfeatureUnit) {
        this.landsfeatureName = landsfeatureName;
        this.landsfeatureUnit = landsfeatureUnit;
    }

    public LandsFeature() {
    }

    
    
    public Long getLandsfeatureID() {
        return landsfeatureID;
    }

    public void setLandsfeatureID(Long landsfeatureID) {
        this.landsfeatureID = landsfeatureID;
    }

    public String getLandsfeatureName() {
        return landsfeatureName;
    }

    public void setLandsfeatureName(String landsfeatureName) {
        this.landsfeatureName = landsfeatureName;
    }

    public String getLandsfeatureUnit() {
        return landsfeatureUnit;
    }

    public void setLandsfeatureUnit(String landsfeatureUnit) {
        this.landsfeatureUnit = landsfeatureUnit;
    }

}
