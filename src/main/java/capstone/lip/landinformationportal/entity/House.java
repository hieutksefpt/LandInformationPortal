/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "House")
public class House extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HouseID")
    private Long houseId;
    @Column(name = "HouseName")
    private String houseName;
    @Column(name = "HousePrice")
    private Double housePrice;

    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "RealEstateID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RealEstate realEstate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "HousesDetail", joinColumns = @JoinColumn(name = "HouseID"),
            inverseJoinColumns = @JoinColumn(name = "HousesFeatureID"))
    private List<HousesFeature> listHousesFeatures;

    public List<HousesFeature> getListHousesFeatures() {
        return listHousesFeatures;
    }

    public void setListHousesFeatures(List<HousesFeature> listHousesFeatures) {
        this.listHousesFeatures = listHousesFeatures;
    }

    public House() {
    }

    public House(String houseName, Double housePrice, RealEstate realEstate) {
        this.houseName = houseName;
        this.housePrice = housePrice;
        this.realEstate = realEstate;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

}
