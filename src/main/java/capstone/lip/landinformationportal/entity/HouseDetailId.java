package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

public class HouseDetailId implements Serializable {

	private static final long serialVersionUID = 1L;

	private House house;
	private HousesFeature housesFeature;
	
	public HouseDetailId() {
	}

	public HouseDetailId(House house, HousesFeature housesFeature) {
		this.house = house;
		this.housesFeature = housesFeature;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public HousesFeature getHousesFeature() {
		return housesFeature;
	}

	public void setHousesFeature(HousesFeature housesFeature) {
		this.housesFeature = housesFeature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + ((housesFeature == null) ? 0 : housesFeature.hashCode());
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
		if (house == null) {
			if (other.house != null)
				return false;
		} else if (!house.equals(other.house))
			return false;
		if (housesFeature == null) {
			if (other.housesFeature != null)
				return false;
		} else if (!housesFeature.equals(other.housesFeature))
			return false;
		return true;
	}
	
}
