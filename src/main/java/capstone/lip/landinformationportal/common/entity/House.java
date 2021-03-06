/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common.entity;
import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import capstone.lip.landinformationportal.common.entity.audit.AuditAbstract;

/**
 *
 * @author Admin
 */

@Entity
@Table(name="House")
public class House extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HouseID")
    private Long houseId;
    @Column(name = "HouseName")
    private String houseName;
    @Column(name = "HousePrice")
    private BigDecimal housePrice;
    
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "RealEstateID")
    private RealEstate realEstate;
    
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "house",fetch = FetchType.LAZY)
    private List<HousesDetail> listHousesDetail;

    public House() {
    }

    public House(String houseName, BigDecimal housePrice, RealEstate realEstate, List<HousesDetail> listHousesDetail) {
        this.houseName = houseName;
        this.housePrice = housePrice;
        this.realEstate = realEstate;
        this.listHousesDetail = listHousesDetail;
    }

    public Long getHouseId() {
        return houseId;
    }

    

    public String getHouseName() {
        return houseName;
    }

    

    public BigDecimal getHousePrice() {
        return housePrice;
    }

    public House setHousePrice(BigDecimal housePrice) {
        this.housePrice = housePrice;
        return this;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    

    public List<HousesDetail> getListHousesDetail() {
        return listHousesDetail;
    }

    public House setListHousesDetail(List<HousesDetail> listHousesDetail) {
        this.listHousesDetail = listHousesDetail;
        return this;
    }
    
    public House setHouseId(Long houseId) {
        this.houseId = houseId;
        return this;
    }
    public House setHouseName(String houseName) {
        this.houseName = houseName;
        return this;
    }
    public House setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
        return this;
    }
    
    public void setListHousesDetailGetOnly(List<HousesDetail> listHousesDetail) {
        this.listHousesDetail = listHousesDetail;
    }
    
    public void setHouseIdGetOnly(Long houseId) {
        this.houseId = houseId;
    }
    public void setHouseNameGetOnly(String houseName) {
        this.houseName = houseName;
    }
    public void setRealEstateGetOnly(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((houseId == null) ? 0 : houseId.hashCode());
		result = prime * result + ((houseName == null) ? 0 : houseName.hashCode());
		result = prime * result + ((housePrice == null) ? 0 : housePrice.hashCode());
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
		House other = (House) obj;
		if (houseId == null) {
			if (other.houseId != null)
				return false;
		} else if (!houseId.equals(other.houseId))
			return false;
		if (houseName == null) {
			if (other.houseName != null)
				return false;
		} else if (!houseName.equals(other.houseName))
			return false;
		if (housePrice == null) {
			if (other.housePrice != null)
				return false;
		} else if (!housePrice.equals(other.housePrice))
			return false;
		if (realEstate == null) {
			if (other.realEstate != null)
				return false;
		} else if (!realEstate.equals(other.realEstate))
			return false;
		return true;
	}
	
}
