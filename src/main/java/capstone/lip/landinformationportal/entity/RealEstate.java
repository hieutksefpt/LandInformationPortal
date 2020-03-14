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
import javax.persistence.OneToOne;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Basic(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(mappedBy = "realEstate")
    private Land land;
    
    @Basic(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "realEstate")
    private List<House> listHouse;
    
    @Basic(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "realEstate")
    private List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment;

    

    public RealEstate() {
    }

    public RealEstate(String realEstateName, Double realEstateLat, Double realEstateLng, String realEstateAddress, Double realEstatePrice, String realEstateStatus, String realEstateLink, String realEstateType, User user, Land land, List<House> listHouse) {
        this.realEstateName = realEstateName;
        this.realEstateLat = realEstateLat;
        this.realEstateLng = realEstateLng;
        this.realEstateAddress = realEstateAddress;
        this.realEstatePrice = realEstatePrice;
        this.realEstateStatus = realEstateStatus;
        this.realEstateLink = realEstateLink;
        this.realEstateType = realEstateType;
        this.user = user;
        this.land = land;
        this.listHouse = listHouse;
    }

    public Long getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Long realEstateId) {
        this.realEstateId = realEstateId;
    }

    public String getRealEstateName() {
        return realEstateName;
    }

    public void setRealEstateName(String realEstateName) {
        this.realEstateName = realEstateName;
    }

    public Double getRealEstateLat() {
        return realEstateLat;
    }

    public void setRealEstateLat(Double realEstateLat) {
        this.realEstateLat = realEstateLat;
    }

    public Double getRealEstateLng() {
        return realEstateLng;
    }

    public void setRealEstateLng(Double realEstateLng) {
        this.realEstateLng = realEstateLng;
    }

    public String getRealEstateAddress() {
        return realEstateAddress;
    }

    public void setRealEstateAddress(String realEstateAddress) {
        this.realEstateAddress = realEstateAddress;
    }

    public Double getRealEstatePrice() {
        return realEstatePrice;
    }

    public void setRealEstatePrice(Double realEstatePrice) {
        this.realEstatePrice = realEstatePrice;
    }

    public String getRealEstateStatus() {
        return realEstateStatus;
    }

    public void setRealEstateStatus(String realEstateStatus) {
        this.realEstateStatus = realEstateStatus;
    }

    public String getRealEstateLink() {
        return realEstateLink;
    }

    public void setRealEstateLink(String realEstateLink) {
        this.realEstateLink = realEstateLink;
    }

    public String getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(String realEstateType) {
        this.realEstateType = realEstateType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }


    public List<House> getListHouse() {
        return listHouse;
    }

    public void setListHouse(List<House> listHouse) {
        this.listHouse = listHouse;
    }

    public List<RealEstateAdjacentSegment> getListRealEstateAdjacentSegment() {
        return listRealEstateAdjacentSegment;
    }

    public void setListRealEstateAdjacentSegment(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment) {
        this.listRealEstateAdjacentSegment = listRealEstateAdjacentSegment;
    }
    
    
}
