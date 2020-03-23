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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name="Street")
public class Street extends AuditAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy="street",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<SegmentOfStreet> listSegmentOfStreet;
	
	
	public List<SegmentOfStreet> getListSegmentOfStreet() {
		return listSegmentOfStreet;
	}
	public Street setListSegmentOfStreet(List<SegmentOfStreet> listSegmentOfStreet) {
		this.listSegmentOfStreet = listSegmentOfStreet;
		return this;
	}
	public Street setStreetId(Long streetId) {
		this.streetId = streetId;
		return this;
	}
	public String getStreetName() {
		return streetName;
	}
	public Street setStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}
	public Double getStreetLat() {
		return streetLat;
	}
	public Street setStreetLat(Double streetLat) {
		this.streetLat = streetLat;
		return this;
	}
	public Double getStreetLng() {
		return streetLng;
	}
	public Street setStreetLng(Double streetLng) {
		this.streetLng = streetLng;
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Street temp = (Street) obj;
		return temp.streetId.equals(this.streetId);
	}
	
	

}
