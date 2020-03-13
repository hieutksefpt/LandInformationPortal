/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "RealEstate")
public class RealEstate extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = -7177630011940526932L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RealEstateID")
    private Long realEstateId;
    @Column(name = "RealEstateName")
    private String realEstateName;
    @Column(name = "RealEstateLat")
    private Double realEstateLat;
    @Column(name = "RealEstateLng")
    private Double realEstateLng;
    @Column(name = "RealEstateAddress")
    private String realEstateAddress;
    @Column(name = "RealEstatePrice")
    private Double realEstatePrice;
    @Column(name = "RealEstateStatus")
    private String realEstateStatus;
    @Column(name = "RealEstateLink")
    private String realEstateLink;
    @Column(name = "RealEstateType")
    private String realEstateType;

    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Basic(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "realEstate")
    private List<Land> listLand;
    
    @Basic(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "realEstate")
    private List<House> listHouse;
    
    @Basic(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "realEstate")
    private List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment;

    

    public RealEstate() {
    }

    public RealEstate(String realEstateName, Double realEstateLat, Double realEstateLng, String realEstateAddress, Double realEstatePrice, String realEstateStatus, String realEstateLink, String realEstateType, User user, List<Land> listLand, List<House> listHouse) {
        this.realEstateName = realEstateName;
        this.realEstateLat = realEstateLat;
        this.realEstateLng = realEstateLng;
        this.realEstateAddress = realEstateAddress;
        this.realEstatePrice = realEstatePrice;
        this.realEstateStatus = realEstateStatus;
        this.realEstateLink = realEstateLink;
        this.realEstateType = realEstateType;
        this.user = user;
        this.listLand = listLand;
        this.listHouse = listHouse;
    }

    public Long getRealEstateId() {
        return realEstateId;
    }

    public RealEstate setRealEstateId(Long realEstateId) {
        this.realEstateId = realEstateId;
        return this;
    }

    public String getRealEstateName() {
        return realEstateName;
    }

    public RealEstate setRealEstateName(String realEstateName) {
        this.realEstateName = realEstateName;
        return this;
    }

    public Double getRealEstateLat() {
        return realEstateLat;
    }

    public RealEstate setRealEstateLat(Double realEstateLat) {
        this.realEstateLat = realEstateLat;
        return this;
    }

    public Double getRealEstateLng() {
        return realEstateLng;
    }

    public RealEstate setRealEstateLng(Double realEstateLng) {
        this.realEstateLng = realEstateLng;
        return this;
    }

    public String getRealEstateAddress() {
        return realEstateAddress;
    }

    public RealEstate setRealEstateAddress(String realEstateAddress) {
        this.realEstateAddress = realEstateAddress;
        return this;
    }

    public Double getRealEstatePrice() {
        return realEstatePrice;
    }

    public RealEstate setRealEstatePrice(Double realEstatePrice) {
        this.realEstatePrice = realEstatePrice;
        return this;
    }

    public String getRealEstateStatus() {
        return realEstateStatus;
    }

    public RealEstate setRealEstateStatus(String realEstateStatus) {
        this.realEstateStatus = realEstateStatus;
        return this;
    }

    public String getRealEstateLink() {
        return realEstateLink;
    }

    public RealEstate setRealEstateLink(String realEstateLink) {
        this.realEstateLink = realEstateLink;
        return this;
    }

    public String getRealEstateType() {
        return realEstateType;
    }

    public RealEstate setRealEstateType(String realEstateType) {
        this.realEstateType = realEstateType;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RealEstate setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Land> getListLand() {
        return listLand;
    }

    public RealEstate setListLand(List<Land> listLand) {
        this.listLand = listLand;
        return this;
    }

    public List<House> getListHouse() {
        return listHouse;
    }

    public RealEstate setListHouse(List<House> listHouse) {
        this.listHouse = listHouse;
        return this;
    }

    public List<RealEstateAdjacentSegment> getListRealEstateAdjacentSegment() {
        return listRealEstateAdjacentSegment;
    }

    public RealEstate setListRealEstateAdjacentSegment(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment) {
        this.listRealEstateAdjacentSegment = listRealEstateAdjacentSegment;
        return this;
    }
    
    
}
