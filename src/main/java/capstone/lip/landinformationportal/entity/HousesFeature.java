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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    
    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "housesFeature")
    private List<HousesDetail> listHousesDetail;

    public List<HousesDetail> getListHousesDetail() {
        return listHousesDetail;
    }

    public HousesFeature setListHousesDetail(List<HousesDetail> listHousesDetail) {
        this.listHousesDetail = listHousesDetail;
		return this;
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

    public HousesFeature setHousesFeatureID(Long housesFeatureID) {
        this.housesFeatureID = housesFeatureID;
        return this;
    }

    public String getHousesFeatureName() {
        return housesFeatureName;
    }

    public HousesFeature setHousesFeatureName(String housesFeatureName) {
        this.housesFeatureName = housesFeatureName;
        return this;
    }

    public String getHousesFeatureUnit() {
        return housesFeatureUnit;
    }

    public HousesFeature setHousesFeatureUnit(String housesFeatureUnit) {
        this.housesFeatureUnit = housesFeatureUnit;
        return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((housesFeatureID == null) ? 0 : housesFeatureID.hashCode());
		result = prime * result + ((housesFeatureName == null) ? 0 : housesFeatureName.hashCode());
		result = prime * result + ((housesFeatureUnit == null) ? 0 : housesFeatureUnit.hashCode());
		result = prime * result + ((listHousesDetail == null) ? 0 : listHousesDetail.hashCode());
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
		HousesFeature other = (HousesFeature) obj;
		if (housesFeatureID == null) {
			if (other.housesFeatureID != null)
				return false;
		} else if (!housesFeatureID.equals(other.housesFeatureID))
			return false;
		if (housesFeatureName == null) {
			if (other.housesFeatureName != null)
				return false;
		} else if (!housesFeatureName.equals(other.housesFeatureName))
			return false;
		if (housesFeatureUnit == null) {
			if (other.housesFeatureUnit != null)
				return false;
		} else if (!housesFeatureUnit.equals(other.housesFeatureUnit))
			return false;
		if (listHousesDetail == null) {
			if (other.listHousesDetail != null)
				return false;
		} else if (!listHousesDetail.equals(other.listHousesDetail))
			return false;
		return true;
	}
    
    
    

}
