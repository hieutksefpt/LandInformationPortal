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
    
}
