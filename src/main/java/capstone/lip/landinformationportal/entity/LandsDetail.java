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
import javax.persistence.IdClass;
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
@IdClass(LandDetailId.class)
public class LandsDetail extends AuditAbstract implements Serializable{
    private static final long serialVersionUID = 1L;
//    @Id
//    @Column(columnDefinition = "BINARY(16)")
//    private UUID uuid;

    @Column(name = "Value")
    private String value;
    
    @Id
    @Column(name="LandID", insertable=false, updatable=false)
    private Long landId;
    
    @Id
    @Column(name="LandsFeatureID", insertable=false, updatable=false)
    private Long landsFeatureId;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "LandID")
    private Land land;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "LandsFeatureID")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((landId == null) ? 0 : landId.hashCode());
		result = prime * result + ((landsFeatureId == null) ? 0 : landsFeatureId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LandsDetail other = (LandsDetail) obj;
		if (landId == null) {
			if (other.landId != null)
				return false;
		} else if (!landId.equals(other.landId))
			return false;
		if (landsFeatureId == null) {
			if (other.landsFeatureId != null)
				return false;
		} else if (!landsFeatureId.equals(other.landsFeatureId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

//	public UUID getUuid() {
//		return uuid;
//	}
//
//	public LandsDetail setUuid(UUID uuid) {
//		this.uuid = uuid;
//		return this;
//	}
//    
    
    
}
