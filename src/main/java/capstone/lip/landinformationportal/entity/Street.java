package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Street")
public class Street extends AuditAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="StreetID")
	private Long streetId;
	@Column(name="StreetName")
	private String streetName;
	@Column(name="StreetLat")
	private Double streetLat;
	@Column(name="StreetLng")
	private Double streetLng;
	public Long getStreetId() {
		return streetId;
	}
	
	@Basic(fetch = FetchType.LAZY)
	@OneToMany(mappedBy="street",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<SegmentOfStreet> listSegmentOfStreet;
	
	
	public List<SegmentOfStreet> getListSegmentOfStreet() {
		return listSegmentOfStreet;
	}
	public void setListSegmentOfStreet(List<SegmentOfStreet> listSegmentOfStreet) {
		this.listSegmentOfStreet = listSegmentOfStreet;
	}
	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public Double getStreetLat() {
		return streetLat;
	}
	public void setStreetLat(Double streetLat) {
		this.streetLat = streetLat;
	}
	public Double getStreetLng() {
		return streetLng;
	}
	public void setStreetLng(Double streetLng) {
		this.streetLng = streetLng;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Street temp = (Street) obj;
		return temp.streetId.equals(this.streetId);
	}
	
	

}
