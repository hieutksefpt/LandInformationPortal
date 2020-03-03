package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	
}
