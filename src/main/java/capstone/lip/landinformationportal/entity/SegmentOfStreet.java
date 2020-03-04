package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name="SegmentOfStreet")
public class SegmentOfStreet extends AuditAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SegmentID")
	private Long segmentId;
	@Column(name="SegmentName")
	private String segmentName;
	@Column(name="SegmentLat")
	private Double segmentLat;
	@Column(name="SegmentLng")
	private Double segmentLng;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name ="DistrictID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private District district;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name ="StreetID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Street street;

	public Long getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(Long segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public Double getSegmentLat() {
		return segmentLat;
	}

	public void setSegmentLat(Double segmentLat) {
		this.segmentLat = segmentLat;
	}

	public Double getSegmentLng() {
		return segmentLng;
	}

	public void setSegmentLng(Double segmentLng) {
		this.segmentLng = segmentLng;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}
	
	
}
