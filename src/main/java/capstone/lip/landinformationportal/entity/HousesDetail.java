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
    @Column(name="HouseID")
    private Long houseId;
    
    @Id
    @Column(name="HousesFeatureID")
    private Long housesFeatureId;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "HouseID")
    private House house;
    
    @Id
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + ((housesFeature == null) ? 0 : housesFeature.hashCode());
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
