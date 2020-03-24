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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RealEstateID")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private RealEstate realEstate;
    
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "land", fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
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

    

    public String getLandName() {
        return landName;
    }

    

    public Double getLandPrice() {
        return landPrice;
    }

    

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public Land setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
        return this;
    }

    public List<LandsDetail> getListLandsDetail() {
        return listLandsDetail;
    }

    public Land setListLandsDetail(List<LandsDetail> listLandsDetail) {
        this.listLandsDetail = listLandsDetail;
        return this;
    }
    
    public Land setLandId(Long landId) {
        this.landId = landId;
        return this;
    }
    
    public Land setLandName(String landName) {
        this.landName = landName;
        return this;
    }
    
    public Land setLandPrice(Double landPrice) {
        this.landPrice = landPrice;
        return this;
    }
    
    public void setListLandsDetailGetOnly(List<LandsDetail> listLandsDetail) {
        this.listLandsDetail = listLandsDetail;
    }
    
    public void setLandIdGetOnly(Long landId) {
        this.landId = landId;
    }
    
    public void setLandNameGetOnly(String landName) {
        this.landName = landName;
    }
    
    public void setLandPriceGetOnly(Double landPrice) {
        this.landPrice = landPrice;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((landId == null) ? 0 : landId.hashCode());
		result = prime * result + ((landName == null) ? 0 : landName.hashCode());
		result = prime * result + ((landPrice == null) ? 0 : landPrice.hashCode());
		result = prime * result + ((listLandsDetail == null) ? 0 : listLandsDetail.hashCode());
		result = prime * result + ((realEstate == null) ? 0 : realEstate.hashCode());
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
		Land other = (Land) obj;
		if (landId == null) {
			if (other.landId != null)
				return false;
		} else if (!landId.equals(other.landId))
			return false;
		if (landName == null) {
			if (other.landName != null)
				return false;
		} else if (!landName.equals(other.landName))
			return false;
		if (landPrice == null) {
			if (other.landPrice != null)
				return false;
		} else if (!landPrice.equals(other.landPrice))
			return false;
		if (listLandsDetail == null) {
			if (other.listLandsDetail != null)
				return false;
		} else if (!listLandsDetail.equals(other.listLandsDetail))
			return false;
		if (realEstate == null) {
			if (other.realEstate != null)
				return false;
		} else if (!realEstate.equals(other.realEstate))
			return false;
		return true;
	}
    
    
    

}
