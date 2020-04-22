package capstone.lip.landinformationportal.common.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import capstone.lip.landinformationportal.common.entity.audit.AuditAbstract;

@Entity
@Table(name="District")
public class District extends AuditAbstract  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4375895236315181232L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DistrictID")
	private Long districtId;
	@Column(name="DistrictName")
	private String districtName;
	@Column(name="DistrictLat")
	private Double districtLat;
	@Column(name="DistrictLng")
	private Double districtLng;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ProvinceID")
//	@OnDelete(action = OnDeleteAction.CASCADE)
	private Province province;

	@LazyToOne(LazyToOneOption.NO_PROXY)
	@NotFound(action = NotFoundAction.IGNORE)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="district", fetch = FetchType.LAZY)
	private List<SegmentOfStreet> listSegmentOfStreet;
	
	
	public List<SegmentOfStreet> getListSegmentOfStreet() {
		return listSegmentOfStreet;
	}

	public District setListSegmentOfStreet(List<SegmentOfStreet> listSegmentOfStreet) {
		this.listSegmentOfStreet = listSegmentOfStreet;
		return this;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public District setDistrictId(Long districtId) {
		this.districtId = districtId;
		return this;
	}

	public String getDistrictName() {
		return districtName;
	}

	public District setDistrictName(String districtName) {
		this.districtName = districtName;
		return this;
	}

	public Double getDistrictLat() {
		return districtLat;
	}

	public District setDistrictLat(Double districtLat) {
		this.districtLat = districtLat;
		return this;
	}

	public Double getDistrictLng() {
		return districtLng;
	}

	public District setDistrictLng(Double districtLng) {
		this.districtLng = districtLng;
		return this;
	}

	public Province getProvince() {
		return province;
	}

	public District setProvince(Province province) {
		this.province = province;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((districtId == null) ? 0 : districtId.hashCode());
		result = prime * result + ((districtLat == null) ? 0 : districtLat.hashCode());
		result = prime * result + ((districtLng == null) ? 0 : districtLng.hashCode());
		result = prime * result + ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
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
		District other = (District) obj;
		if (districtId == null) {
			if (other.districtId != null)
				return false;
		} else if (!districtId.equals(other.districtId))
			return false;
		if (districtLat == null) {
			if (other.districtLat != null)
				return false;
		} else if (!districtLat.equals(other.districtLat))
			return false;
		if (districtLng == null) {
			if (other.districtLng != null)
				return false;
		} else if (!districtLng.equals(other.districtLng))
			return false;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		return true;
	}

}
