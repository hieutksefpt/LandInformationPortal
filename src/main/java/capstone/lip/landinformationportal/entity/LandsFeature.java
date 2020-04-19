/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import capstone.lip.landinformationportal.entity.audit.AuditAbstract;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "LandsFeature")
@TypeDefs({
    @TypeDef(name = "list-array", typeClass = ListArrayType.class)})
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

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToMany(mappedBy = "landsFeature", fetch = FetchType.LAZY)
    private List<LandsDetail> listLandsDetail;

    @Column(name = "LandsFeatureDataType")
    private String landsFeatureDataType;

    @Type(type = "list-array")
    @Column(name = "LandsFeatureDataRange", columnDefinition = "text[]")
    private List<String> landsFeatureDataRange;

    public List<LandsDetail> getListLandsDetail() {
        return listLandsDetail;
    }

    public void setListLandsDetail(List<LandsDetail> listLandsDetail) {
        this.listLandsDetail = listLandsDetail;
    }

    public LandsFeature() {
    }

    public LandsFeature(String landsFeatureName, String landsFeatureUnit, String landsFeatureDataType, List<String> landsFeatureDataRange) {
        this.landsFeatureName = landsFeatureName;
        this.landsFeatureUnit = landsFeatureUnit;
        this.landsFeatureDataType = landsFeatureDataType;
        this.landsFeatureDataRange = landsFeatureDataRange;
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

    public String getLandsFeatureDataType() {
        return landsFeatureDataType;
    }

    public void setLandsFeatureDataType(String landsFeatureDataType) {
        this.landsFeatureDataType = landsFeatureDataType;
    }

    public List<String> getLandsFeatureDataRange() {
        return landsFeatureDataRange;
    }

    public void setLandsFeatureDataRange(List<String> landsFeatureDataRange) {
        this.landsFeatureDataRange = landsFeatureDataRange;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((landsFeatureDataRange == null) ? 0 : landsFeatureDataRange.hashCode());
		result = prime * result + ((landsFeatureDataType == null) ? 0 : landsFeatureDataType.hashCode());
		result = prime * result + ((landsFeatureID == null) ? 0 : landsFeatureID.hashCode());
		result = prime * result + ((landsFeatureName == null) ? 0 : landsFeatureName.hashCode());
		result = prime * result + ((landsFeatureUnit == null) ? 0 : landsFeatureUnit.hashCode());
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
		if (landsFeatureDataRange == null) {
			if (other.landsFeatureDataRange != null)
				return false;
		} else if (!landsFeatureDataRange.equals(other.landsFeatureDataRange))
			return false;
		if (landsFeatureDataType == null) {
			if (other.landsFeatureDataType != null)
				return false;
		} else if (!landsFeatureDataType.equals(other.landsFeatureDataType))
			return false;
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
		return true;
	}

    
}
