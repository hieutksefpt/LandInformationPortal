/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "Land")
public class Land extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LandID")
    private Long landId;
    @Column(name = "LandName")
    private String landName;
    @Column(name = "LandPrice")
    private Double landPrice;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "RealEstateID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RealEstate realEstate;
    
    @Basic(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "land")
    private List<LandsDetail> listLandsDetail;

    public Land() {
    }

    public Land( String landName, Double landPrice, RealEstate realEstate, List<LandsDetail> listLandsDetail) {
        this.landName = landName;
        this.landPrice = landPrice;
        this.realEstate = realEstate;
        this.listLandsDetail = listLandsDetail;
    }

    public Long getLandId() {
        return landId;
    }

    public void setLandId(Long landId) {
        this.landId = landId;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public Double getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(Double landPrice) {
        this.landPrice = landPrice;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public List<LandsDetail> getListLandsDetail() {
        return listLandsDetail;
    }

    public void setListLandsDetail(List<LandsDetail> listLandsDetail) {
        this.listLandsDetail = listLandsDetail;
    }
    
    
    

}
