/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="LandsDetail")
public class LandsDetail extends AuditAbstract implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LandID")
    private Long landId;
    @Id
    @Column(name = "LandsFeatureID")
    private Long landsFeatureId;
    @Column(name = "Value")
    private String value;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "LandID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Land land;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "LandsFeatureID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LandsFeature landsFeature;

    public LandsDetail() {
    }

    public LandsDetail(Long landId, Long landsFeatureId, String value, Land land, LandsFeature landsFeature) {
        this.landId = landId;
        this.landsFeatureId = landsFeatureId;
        this.value = value;
        this.land = land;
        this.landsFeature = landsFeature;
    }

    public Long getLandId() {
        return landId;
    }

    public void setLandId(Long landId) {
        this.landId = landId;
    }

    public Long getLandsFeatureId() {
        return landsFeatureId;
    }

    public void setLandsFeatureId(Long landsFeatureId) {
        this.landsFeatureId = landsFeatureId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public LandsFeature getLandsFeature() {
        return landsFeature;
    }

    public void setLandsFeature(LandsFeature landsFeature) {
        this.landsFeature = landsFeature;
    }
    
    
    
}
