package capstone.lip.landinformationportal.common.entity.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RealEstateAdjacentSegmentId implements Serializable{ 
	private static final long serialVersionUID = 1L;
	@Column(name = "SegmentID")
	private Long segmentOfStreetId;
	
	@Column(name="RealEstateID")
	private Long realEstateId;

	
	public RealEstateAdjacentSegmentId() {
		super();
	}

	public RealEstateAdjacentSegmentId(Long segmentOfStreetId, Long realEstateId) {
		super();
		this.segmentOfStreetId = segmentOfStreetId;
		this.realEstateId = realEstateId;
	}

	public Long getSegmentOfStreetId() {
		return segmentOfStreetId;
	}

	public void setSegmentOfStreetId(Long segmentOfStreetId) {
		this.segmentOfStreetId = segmentOfStreetId;
	}

	public Long getRealEstateId() {
		return realEstateId;
	}

	public void setRealEstateId(Long realEstateId) {
		this.realEstateId = realEstateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((realEstateId == null) ? 0 : realEstateId.hashCode());
		result = prime * result + ((segmentOfStreetId == null) ? 0 : segmentOfStreetId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RealEstateAdjacentSegmentId other = (RealEstateAdjacentSegmentId) obj;
		if (realEstateId == null) {
			if (other.realEstateId != null)
				return false;
		} else if (!realEstateId.equals(other.realEstateId))
			return false;
		if (segmentOfStreetId == null) {
			if (other.segmentOfStreetId != null)
				return false;
		} else if (!segmentOfStreetId.equals(other.segmentOfStreetId))
			return false;
		return true;
	}
	
	
}
