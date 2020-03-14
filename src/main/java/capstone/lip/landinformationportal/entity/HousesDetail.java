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
@Table(name="HousesDetail")
@IdClass(HouseDetailId.class)
public class HousesDetail extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
//    @Id
//    @Column(columnDefinition = "BINARY(16)")
//    private UUID uuid;
    
    @Column(name = "Value")
    private String value;
    
    @Id
    @Column(name="HouseID", insertable=false, updatable=false)
    private Long houseId;
    
    @Id
    @Column(name="HousesFeatureID", insertable=false, updatable=false)
    private Long housesFeatureId;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "HouseID")
    private House house;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "HousesFeatureID")
    private HousesFeature housesFeature;

    public HousesDetail() {
    }

    
    public String getValue() {
        return value;
    }

    public HousesDetail setValue(String value) {
        this.value = value;
		return this;
    }

    public House getHouse() {
        return house;
    }

    public HousesDetail setHouse(House house) {
        this.house = house;
		return this;
    }

    public HousesFeature getHousesFeature() {
        return housesFeature;
    }

    public HousesDetail setHousesFeature(HousesFeature housesFeature) {
        this.housesFeature = housesFeature;
		return this;
    }

	public Long getHouseId() {
		return houseId;
	}


	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}


	public Long getHousesFeatureId() {
		return housesFeatureId;
	}


	public void setHousesFeatureId(Long housesFeatureId) {
		this.housesFeatureId = housesFeatureId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((houseId == null) ? 0 : houseId.hashCode());
		result = prime * result + ((housesFeatureId == null) ? 0 : housesFeatureId.hashCode());
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
		HousesDetail other = (HousesDetail) obj;
		if (houseId == null) {
			if (other.houseId != null)
				return false;
		} else if (!houseId.equals(other.houseId))
			return false;
		if (housesFeatureId == null) {
			if (other.housesFeatureId != null)
				return false;
		} else if (!housesFeatureId.equals(other.housesFeatureId))
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
//	public HousesDetail setUuid(UUID uuid) {
//		this.uuid = uuid;
//		return this;
//	}
    
    
}
