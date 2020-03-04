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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	@Basic(fetch = FetchType.LAZY)
	@ManyToOne(optional = false)
	@JoinColumn(name ="ProvinceID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Province province;

	@Basic(fetch = FetchType.LAZY)
	@OneToMany(mappedBy="district",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<SegmentOfStreet> listSegmentOfStreet;
	
	
	public List<SegmentOfStreet> getListSegmentOfStreet() {
		return listSegmentOfStreet;
	}

	public void setListSegmentOfStreet(List<SegmentOfStreet> listSegmentOfStreet) {
		this.listSegmentOfStreet = listSegmentOfStreet;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Double getDistrictLat() {
		return districtLat;
	}

	public void setDistrictLat(Double districtLat) {
		this.districtLat = districtLat;
	}

	public Double getDistrictLng() {
		return districtLng;
	}

	public void setDistrictLng(Double districtLng) {
		this.districtLng = districtLng;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
    
	
	
}
