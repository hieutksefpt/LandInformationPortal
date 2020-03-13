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
    private Double housePrice;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "RealEstateID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RealEstate realEstate;
    
    @Basic(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "house")
    private List<HousesDetail> listHousesDetail;

    public House() {
    }

    public House(String houseName, Double housePrice, RealEstate realEstate, List<HousesDetail> listHousesDetail) {
        this.houseName = houseName;
        this.housePrice = housePrice;
        this.realEstate = realEstate;
        this.listHousesDetail = listHousesDetail;
    }

    public Long getHouseId() {
        return houseId;
    }

    public House setHouseId(Long houseId) {
        this.houseId = houseId;
        return this;
    }

    public String getHouseName() {
        return houseName;
    }

    public House setHouseName(String houseName) {
        this.houseName = houseName;
        return this;
    }

    public Double getHousePrice() {
        return housePrice;
    }

    public House setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
        return this;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public House setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
        return this;
    }

    public List<HousesDetail> getListHousesDetail() {
        return listHousesDetail;
    }

    public House setListHousesDetail(List<HousesDetail> listHousesDetail) {
        this.listHousesDetail = listHousesDetail;
        return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((houseId == null) ? 0 : houseId.hashCode());
		result = prime * result + ((houseName == null) ? 0 : houseName.hashCode());
		result = prime * result + ((housePrice == null) ? 0 : housePrice.hashCode());
		result = prime * result + ((listHousesDetail == null) ? 0 : listHousesDetail.hashCode());
		result = prime * result + ((realEstate == null) ? 0 : realEstate.hashCode());
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
		if (listHousesDetail == null) {
			if (other.listHousesDetail != null)
				return false;
		} else if (!listHousesDetail.equals(other.listHousesDetail))
			return false;
		if (realEstate == null) {
			if (other.realEstate != null)
				return false;
		} else if (!realEstate.equals(other.realEstate))
			return false;
		return true;
	}
    
    
    
}
