/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "HousesFeature")
public class HousesFeature extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "HousesFeatureID")
    private Long housesFeatureID;
    @Column(name = "HousesFeatureName")
    private String housesFeatureName;
    @Column(name = "HousesFeatureUnit")
    private String housesFeatureUnit;

    @ManyToMany(mappedBy = "landsFeature")
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<House> listHouses;

    public List<House> getListHouses() {
        return listHouses;
    }

    public void setListHouses(List<House> listHouses) {
        this.listHouses = listHouses;
    }
    

    public HousesFeature() {
    }

    public HousesFeature(String housesFeatureName, String housesFeatureUnit) {
        this.housesFeatureName = housesFeatureName;
        this.housesFeatureUnit = housesFeatureUnit;
    }

    public Long getHousesFeatureID() {
        return housesFeatureID;
    }

    public void setHousesFeatureID(Long housesFeatureID) {
        this.housesFeatureID = housesFeatureID;
    }

    public String getHousesFeatureName() {
        return housesFeatureName;
    }

    public void setHousesFeatureName(String housesFeatureName) {
        this.housesFeatureName = housesFeatureName;
    }

    public String getHousesFeatureUnit() {
        return housesFeatureUnit;
    }

    public void setHousesFeatureUnit(String housesFeatureUnit) {
        this.housesFeatureUnit = housesFeatureUnit;
    }
    
    
    

}
