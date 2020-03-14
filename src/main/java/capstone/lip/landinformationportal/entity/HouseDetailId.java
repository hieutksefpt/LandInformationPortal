package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

public class HouseDetailId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long houseId;
	
	private Long housesFeatureId;
	
	public HouseDetailId() {
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getHousesFeatureId() {
		return housesFeatureId;
	}

	public void setHousesFeatureId(Long housesFeatureId) {
		this.housesFeatureId = housesFeatureId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((houseId == null) ? 0 : houseId.hashCode());
		result = prime * result + ((housesFeatureId == null) ? 0 : housesFeatureId.hashCode());
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
		HouseDetailId other = (HouseDetailId) obj;
		if (houseId == null) {
			if (other.houseId != null)
				return false;
		} else if (!houseId.equals(other.houseId))
			return false;
		if (housesFeatureId == null) {
			if (other.housesFeatureId != null)
				return false;
		} else if (!housesFeatureId.equals(other.housesFeatureId))
			return false;
		return true;
	}
	
	
}
