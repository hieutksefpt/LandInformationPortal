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
    
	
	
}
