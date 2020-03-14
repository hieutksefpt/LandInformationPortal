package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class LandDetailId implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long landId;
    private Long landsFeatureId;
	public LandDetailId() {
		super();
	}
	public Long getLandId() {
		return landId;
	}
	public void setLandId(Long landId) {
		this.landId = landId;
	}
	public Long getLandsFeatureId() {
		return landsFeatureId;
	}
	public void setLandsFeatureId(Long landsFeatureId) {
		this.landsFeatureId = landsFeatureId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((landId == null) ? 0 : landId.hashCode());
		result = prime * result + ((landsFeatureId == null) ? 0 : landsFeatureId.hashCode());
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
		if (landId == null) {
			if (other.landId != null)
				return false;
		} else if (!landId.equals(other.landId))
			return false;
		if (landsFeatureId == null) {
			if (other.landsFeatureId != null)
				return false;
		} else if (!landsFeatureId.equals(other.landsFeatureId))
			return false;
		return true;
	}
    
    
}
