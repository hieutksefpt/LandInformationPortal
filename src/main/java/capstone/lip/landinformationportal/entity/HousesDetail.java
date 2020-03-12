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
@Table(name="HousesDetail")
public class HousesDetail extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;
    
    @Column(name = "Value")
    private String value;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "HouseID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private House house;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "HousesFeatureID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HousesFeature housesFeature;

    public HousesDetail() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public HousesFeature getHousesFeature() {
        return housesFeature;
    }

    public void setHousesFeature(HousesFeature housesFeature) {
        this.housesFeature = housesFeature;
    }

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
    
    
}