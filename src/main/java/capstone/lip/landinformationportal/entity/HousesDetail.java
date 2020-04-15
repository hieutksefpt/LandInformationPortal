/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import capstone.lip.landinformationportal.entity.audit.AuditAbstract;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="HousesDetail")
public class HousesDetail extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private HousesDetailId id;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @MapsId("HouseId")
    @JoinColumn(name = "HouseID")
    private House house;
    
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("HousesFeatureID")
    @JoinColumn(name = "HousesFeatureID")
    private HousesFeature housesFeature;


    @Column(name = "Value")
    private String value;
    
    
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

	public HousesDetailId getId() {
		return id;
	}

	public HousesDetail setId(HousesDetailId id) {
		this.id = id;
		return this;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + ((housesFeature == null) ? 0 : housesFeature.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (house == null) {
			if (other.house != null)
				return false;
		} else if (!house.equals(other.house))
			return false;
		if (housesFeature == null) {
			if (other.housesFeature != null)
				return false;
		} else if (!housesFeature.equals(other.housesFeature))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
    
}
