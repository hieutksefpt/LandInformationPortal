package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReportId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="UserID")
	private Long userId;
	@Column(name="RealEstateID")
	private Long realEstateId;
	public ReportId() {
	}
	
	public ReportId(Long userId, Long realEstateId) {
		this.userId = userId;
		this.realEstateId = realEstateId;
	}


	public Long getUserId() {
		return userId;
	}
	public ReportId setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public Long getRealestateId() {
		return realEstateId;
	}
	public ReportId setRealestateId(Long realestateId) {
		this.realEstateId = realestateId;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((realEstateId == null) ? 0 : realEstateId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		ReportId other = (ReportId) obj;
		if (realEstateId == null) {
			if (other.realEstateId != null)
				return false;
		} else if (!realEstateId.equals(other.realEstateId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
