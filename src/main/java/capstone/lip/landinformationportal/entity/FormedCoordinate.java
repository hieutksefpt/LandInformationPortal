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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import capstone.lip.landinformationportal.entity.audit.AuditAbstract;
@Entity
@Table(name="FormedCoordinate")
public class FormedCoordinate extends AuditAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FormedCoordinateID")
	private Long formedCoordinateId;
	@Column(name="FormedLat")
	private Double formedLat;
	@Column(name="FormedLng")
	private Double formedLng;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SegmentID")
//	@OnDelete(action = OnDeleteAction.CASCADE)
	private SegmentOfStreet segmentOfStreet;

	public Long getFormedCoordinateId() {
		return formedCoordinateId;
	}

	public FormedCoordinate setFormedCoordinateId(Long formedCoordinateId) {
		this.formedCoordinateId = formedCoordinateId;
		return this;
	}

	public Double getFormedLat() {
		return formedLat;
	}

	public FormedCoordinate setFormedLat(Double formedLat) {
		this.formedLat = formedLat;
		return this;
	}

	public Double getFormedLng() {
		return formedLng;
	}

	public FormedCoordinate setFormedLng(Double formedLng) {
		this.formedLng = formedLng;
		return this;
	}

	public SegmentOfStreet getSegmentOfStreet() {
		return segmentOfStreet;
	}

	public FormedCoordinate setSegmentOfStreet(SegmentOfStreet segmentOfStreet) {
		this.segmentOfStreet = segmentOfStreet;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((formedCoordinateId == null) ? 0 : formedCoordinateId.hashCode());
		result = prime * result + ((formedLat == null) ? 0 : formedLat.hashCode());
		result = prime * result + ((formedLng == null) ? 0 : formedLng.hashCode());
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
		FormedCoordinate other = (FormedCoordinate) obj;
		if (formedCoordinateId == null) {
			if (other.formedCoordinateId != null)
				return false;
		} else if (!formedCoordinateId.equals(other.formedCoordinateId))
			return false;
		if (formedLat == null) {
			if (other.formedLat != null)
				return false;
		} else if (!formedLat.equals(other.formedLat))
			return false;
		if (formedLng == null) {
			if (other.formedLng != null)
				return false;
		} else if (!formedLng.equals(other.formedLng))
			return false;
		return true;
	}

}
