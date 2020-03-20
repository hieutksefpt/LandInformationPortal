package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class HousesDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "HouseID")
    private Long houseId;

    @Column(name = "HousesFeatureID")
    private Long housesFeatureId;

    public HousesDetailId() {
    }

    public HousesDetailId(Long houseId, Long housesFeatureId) {
        super();
        this.houseId = houseId;
        this.housesFeatureId = housesFeatureId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public HousesDetailId setHouseId(Long houseId) {
        this.houseId = houseId;
        return this;
    }

    public Long getHousesFeatureId() {
        return housesFeatureId;
    }

    public HousesDetailId setHousesFeatureId(Long housesFeatureId) {
        this.housesFeatureId = housesFeatureId;
        return this;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HousesDetailId other = (HousesDetailId) obj;
        if (houseId == null) {
            if (other.houseId != null) {
                return false;
            }
        } else if (!houseId.equals(other.houseId)) {
            return false;
        }
        if (housesFeatureId == null) {
            if (other.housesFeatureId != null) {
                return false;
            }
        } else if (!housesFeatureId.equals(other.housesFeatureId)) {
            return false;
        }
        return true;
    }

}
