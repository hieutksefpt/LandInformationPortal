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
@Table(name="HousesDetail")
public class HousesDetail extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "HouseID")
    private Long houseId;
    @Id
    @Column(name = "HousesFeatureID")
    private Long housesFeatureId;
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

    public HousesDetail(Long houseId, Long housesFeatureId, String value, House house, HousesFeature housesFeature) {
        this.houseId = houseId;
        this.housesFeatureId = housesFeatureId;
        this.value = value;
        this.house = house;
        this.housesFeature = housesFeature;
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
    
    
}
