/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "LandsFeature")
public class LandsFeature extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LandsFeatureID")
    private Long landsFeatureID;
    @Column(name = "LandsFeatureName")
    private String landsFeatureName;
    @Column(name = "LandsFeatureUnit")
    private String landsFeatureUnit;

    @ManyToMany(mappedBy = "listLandsFeatures")
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Land> listLands;

    public List<Land> getListLands() {
        return listLands;
    }

    public void setListLands(List<Land> listLands) {
        this.listLands = listLands;
    }
    
    

    public LandsFeature(String landsFeatureName, String landsFeatureUnit) {
        this.landsFeatureName = landsFeatureName;
        this.landsFeatureUnit = landsFeatureUnit;
    }

    public LandsFeature() {
    }

    public Long getLandsFeatureID() {
        return landsFeatureID;
    }

    public void setLandsFeatureID(Long landsFeatureID) {
        this.landsFeatureID = landsFeatureID;
    }

    public String getLandsFeatureName() {
        return landsFeatureName;
    }

    public void setLandsFeatureName(String landsFeatureName) {
        this.landsFeatureName = landsFeatureName;
    }

    public String getLandsFeatureUnit() {
        return landsFeatureUnit;
    }

    public void setLandsFeatureUnit(String landsFeatureUnit) {
        this.landsFeatureUnit = landsFeatureUnit;
    }

}
