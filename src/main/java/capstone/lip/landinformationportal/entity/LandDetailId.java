package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

public class LandDetailId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Land land;

    private LandsFeature landsFeature;

	public LandDetailId() {
		super();
	}
	
	public LandDetailId(Land land, LandsFeature landsFeature) {
		super();
		this.land = land;
		this.landsFeature = landsFeature;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public LandsFeature getLandsFeature() {
		return landsFeature;
	}

	public void setLandsFeature(LandsFeature landsFeature) {
		this.landsFeature = landsFeature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((landsFeature == null) ? 0 : landsFeature.hashCode());
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
		LandDetailId other = (LandDetailId) obj;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (landsFeature == null) {
			if (other.landsFeature != null)
				return false;
		} else if (!landsFeature.equals(other.landsFeature))
			return false;
		return true;
	}
    
}
