package capstone.lip.landinformationportal.entity.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class LandsDetailId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="LandID")
    private Long landId;
	@Column(name="LandsFeatureID")
    private Long landsFeatureId;
	
	public LandsDetailId() {
	}
	
	public LandsDetailId(Long landId, Long landsFeatureId) {
		this.landId = landId;
		this.landsFeatureId = landsFeatureId;
	}

	public Long getLandId() {
		return landId;
	}
	public LandsDetailId setLandId(Long landId) {
		this.landId = landId;
		return this;
	}
	public Long getLandsFeatureId() {
		return landsFeatureId;
	}
	public LandsDetailId setLandsFeatureId(Long landsFeatureId) {
		this.landsFeatureId = landsFeatureId;
		return this;
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
		LandsDetailId other = (LandsDetailId) obj;
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
