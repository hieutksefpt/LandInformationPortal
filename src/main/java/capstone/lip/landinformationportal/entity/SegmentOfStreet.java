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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import capstone.lip.landinformationportal.entity.audit.AuditAbstract;

@Entity
@Table(name = "SegmentOfStreet")
public class SegmentOfStreet extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SegmentID")
    private Long segmentId;
    @Column(name = "SegmentName")
    private String segmentName;
    @Column(name = "SegmentLat")
    private Double segmentLat;
    @Column(name = "SegmentLng")
    private Double segmentLng;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DistrictID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private District district;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StreetID")
    private Street street;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "segmentOfStreet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<FormedCoordinate> listFormedCoordinate;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "segmentOfStreet", fetch = FetchType.LAZY)
    private List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment;

    @Column(name = "VT1")
    private Double VT1;
    @Column(name = "VT2")
    private Double VT2;
    @Column(name = "VT3")
    private Double VT3;
    @Column(name = "VT4")
    private Double VT4;
    
    public Long getSegmentId() {
        return segmentId;
    }

    public SegmentOfStreet setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
        return this;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public SegmentOfStreet setSegmentName(String segmentName) {
        this.segmentName = segmentName;
        return this;
    }

    public Double getSegmentLat() {
        return segmentLat;
    }

    public SegmentOfStreet setSegmentLat(Double segmentLat) {
        this.segmentLat = segmentLat;
        return this;
    }

    public Double getSegmentLng() {
        return segmentLng;
    }

    public SegmentOfStreet setSegmentLng(Double segmentLng) {
        this.segmentLng = segmentLng;
        return this;
    }

    public District getDistrict() {
        return district;
    }

    public SegmentOfStreet setDistrict(District district) {
        this.district = district;
        return this;
    }

    public Street getStreet() {
        return street;
    }

    public SegmentOfStreet setStreet(Street street) {
        this.street = street;
        return this;
    }

    public List<FormedCoordinate> getListFormedCoordinate() {
        return listFormedCoordinate;
    }

    public SegmentOfStreet setListFormedCoordinate(List<FormedCoordinate> listFormedCoordinate) {
        this.listFormedCoordinate = listFormedCoordinate;
        return this;
    }

    public List<RealEstateAdjacentSegment> getListRealEstateAdjacentSegment() {
        return listRealEstateAdjacentSegment;
    }

    public void setListRealEstateAdjacentSegment(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment) {
        this.listRealEstateAdjacentSegment = listRealEstateAdjacentSegment;
    }

	public Double getVT1() {
		return VT1;
	}

	public SegmentOfStreet setVT1(Double vT1) {
		VT1 = vT1;
		return this;
	}

	public Double getVT2() {
		return VT2;
	}

	public SegmentOfStreet setVT2(Double vT2) {
		VT2 = vT2;
		return this;
	}

	public Double getVT3() {
		return VT3;
	}

	public SegmentOfStreet setVT3(Double vT3) {
		VT3 = vT3;
		return this;
	}

	public Double getVT4() {
		return VT4;
	}

	public SegmentOfStreet setVT4(Double vT4) {
		VT4 = vT4;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((VT1 == null) ? 0 : VT1.hashCode());
		result = prime * result + ((VT2 == null) ? 0 : VT2.hashCode());
		result = prime * result + ((VT3 == null) ? 0 : VT3.hashCode());
		result = prime * result + ((VT4 == null) ? 0 : VT4.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((listFormedCoordinate == null) ? 0 : listFormedCoordinate.hashCode());
		result = prime * result
				+ ((listRealEstateAdjacentSegment == null) ? 0 : listRealEstateAdjacentSegment.hashCode());
		result = prime * result + ((segmentId == null) ? 0 : segmentId.hashCode());
		result = prime * result + ((segmentLat == null) ? 0 : segmentLat.hashCode());
		result = prime * result + ((segmentLng == null) ? 0 : segmentLng.hashCode());
		result = prime * result + ((segmentName == null) ? 0 : segmentName.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		SegmentOfStreet other = (SegmentOfStreet) obj;
		if (VT1 == null) {
			if (other.VT1 != null)
				return false;
		} else if (!VT1.equals(other.VT1))
			return false;
		if (VT2 == null) {
			if (other.VT2 != null)
				return false;
		} else if (!VT2.equals(other.VT2))
			return false;
		if (VT3 == null) {
			if (other.VT3 != null)
				return false;
		} else if (!VT3.equals(other.VT3))
			return false;
		if (VT4 == null) {
			if (other.VT4 != null)
				return false;
		} else if (!VT4.equals(other.VT4))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (listFormedCoordinate == null) {
			if (other.listFormedCoordinate != null)
				return false;
		} else if (!listFormedCoordinate.equals(other.listFormedCoordinate))
			return false;
		if (listRealEstateAdjacentSegment == null) {
			if (other.listRealEstateAdjacentSegment != null)
				return false;
		} else if (!listRealEstateAdjacentSegment.equals(other.listRealEstateAdjacentSegment))
			return false;
		if (segmentId == null) {
			if (other.segmentId != null)
				return false;
		} else if (!segmentId.equals(other.segmentId))
			return false;
		if (segmentLat == null) {
			if (other.segmentLat != null)
				return false;
		} else if (!segmentLat.equals(other.segmentLat))
			return false;
		if (segmentLng == null) {
			if (other.segmentLng != null)
				return false;
		} else if (!segmentLng.equals(other.segmentLng))
			return false;
		if (segmentName == null) {
			if (other.segmentName != null)
				return false;
		} else if (!segmentName.equals(other.segmentName))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
    
    
}
