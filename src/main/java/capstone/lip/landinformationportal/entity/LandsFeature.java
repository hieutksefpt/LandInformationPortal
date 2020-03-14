/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LandsFeatureID")
    private Long landsFeatureID;
    @Column(name = "LandsFeatureName")
    private String landsFeatureName;
    @Column(name = "LandsFeatureUnit")
    private String landsFeatureUnit;
    
//    @Basic(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "landsFeature", cascade=CascadeType.ALL)
    private List<LandsDetail> listLandsDetail;
    
    public LandsFeature() {
    }
    
    
    
	public LandsFeature(Long landsFeatureID, String landsFeatureName, String landsFeatureUnit,
			List<LandsDetail> listLandsDetail) {
		this.landsFeatureID = landsFeatureID;
		this.landsFeatureName = landsFeatureName;
		this.landsFeatureUnit = landsFeatureUnit;
		this.listLandsDetail = listLandsDetail;
	}



	public List<LandsDetail> getListLandsDetail() {
        return listLandsDetail;
    }

    public void setListLandsDetail(List<LandsDetail> listLandsDetail) {
        this.listLandsDetail = listLandsDetail;
    }
    
    

    public LandsFeature(String landsFeatureName, String landsFeatureUnit) {
        this.landsFeatureName = landsFeatureName;
        this.landsFeatureUnit = landsFeatureUnit;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((landsFeatureID == null) ? 0 : landsFeatureID.hashCode());
		result = prime * result + ((landsFeatureName == null) ? 0 : landsFeatureName.hashCode());
		result = prime * result + ((landsFeatureUnit == null) ? 0 : landsFeatureUnit.hashCode());
		result = prime * result + ((listLandsDetail == null) ? 0 : listLandsDetail.hashCode());
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
		LandsFeature other = (LandsFeature) obj;
		if (landsFeatureID == null) {
			if (other.landsFeatureID != null)
				return false;
		} else if (!landsFeatureID.equals(other.landsFeatureID))
			return false;
		if (landsFeatureName == null) {
			if (other.landsFeatureName != null)
				return false;
		} else if (!landsFeatureName.equals(other.landsFeatureName))
			return false;
		if (landsFeatureUnit == null) {
			if (other.landsFeatureUnit != null)
				return false;
		} else if (!landsFeatureUnit.equals(other.landsFeatureUnit))
			return false;
		if (listLandsDetail == null) {
			if (other.listLandsDetail != null)
				return false;
		} else if (!listLandsDetail.equals(other.listLandsDetail))
			return false;
		return true;
	}

    
    
    
}
