/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.UUID;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

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

    public String getValue() {
        return value;
    }

    public LandsDetail setValue(String value) {
        this.value = value;
		return this;
    }

    public Land getLand() {
        return land;
    }

    public LandsDetail setLand(Land land) {
        this.land = land;
		return this;
    }

    public LandsFeature getLandsFeature() {
        return landsFeature;
    }

    public LandsDetail setLandsFeature(LandsFeature landsFeature) {
        this.landsFeature = landsFeature;
		return this;
    }

	public UUID getUuid() {
		return uuid;
	}

	public LandsDetail setUuid(UUID uuid) {
		this.uuid = uuid;
		return this;
	}
    
    
    
}
