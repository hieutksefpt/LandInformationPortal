/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import capstone.lip.landinformationportal.entity.audit.AuditAbstract;
import capstone.lip.landinformationportal.entity.audit.CustomAuditModify;
import capstone.lip.landinformationportal.entity.audit.CustomAuditableListener;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "RealEstate")
@EntityListeners(CustomAuditableListener.class)
public class RealEstate implements Serializable, CustomAuditModify{

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
    private BigDecimal realEstatePrice;
    @Column(name = "RealEstateStatus")
    private String realEstateStatus;
    @Column(name = "RealEstateLink")
    private String realEstateLink;
    @Column(name = "RealEstateSource")
    private String realEstateSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private User user;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(mappedBy = "realEstate", fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Land land;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "realEstate", fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private List<House> listHouse;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "realEstate", fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "realEstate", fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private List<Report> listReport;

    
    public RealEstate() {
    }

    public RealEstate(String realEstateName, Double realEstateLat, Double realEstateLng, String realEstateAddress, BigDecimal realEstatePrice, String realEstateStatus, String realEstateLink, String realEstateSource, User user, Land land, List<House> listHouse) {
        this.realEstateName = realEstateName;
        this.realEstateLat = realEstateLat;
        this.realEstateLng = realEstateLng;
        this.realEstateAddress = realEstateAddress;
        this.realEstatePrice = realEstatePrice;
        this.realEstateStatus = realEstateStatus;
        this.realEstateLink = realEstateLink;
        this.realEstateSource = realEstateSource;
        this.user = user;
        this.land = land;
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

    public String getRealEstateNameGetOnly() {
        return this.realEstateName;
    }

    public void setRealEstateNameGetOnly(String realEstateName) {
         realEstateName = realEstateName;
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

    public BigDecimal getRealEstatePrice() {
        return realEstatePrice;
    }

    public RealEstate setRealEstatePrice(BigDecimal realEstatePrice) {
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

    public String getRealEstateSource() {
        return realEstateSource;
    }

    public RealEstate setRealEstateSource(String realEstateSource) {
        this.realEstateSource = realEstateSource;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RealEstate setUser(User user) {
        this.user = user;
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

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }
    

	private Timestamp createdDate;
	private Timestamp modifiedDate;


	@Override
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	@Override
	public void setCreateDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	@Override
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Report> getListReport() {
		return listReport;
	}

	public void setListReport(List<Report> listReport) {
		this.listReport = listReport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((listHouse == null) ? 0 : listHouse.hashCode());
		result = prime * result
				+ ((listRealEstateAdjacentSegment == null) ? 0 : listRealEstateAdjacentSegment.hashCode());
		result = prime * result + ((listReport == null) ? 0 : listReport.hashCode());
		result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result + ((realEstateAddress == null) ? 0 : realEstateAddress.hashCode());
		result = prime * result + ((realEstateId == null) ? 0 : realEstateId.hashCode());
		result = prime * result + ((realEstateLat == null) ? 0 : realEstateLat.hashCode());
		result = prime * result + ((realEstateLink == null) ? 0 : realEstateLink.hashCode());
		result = prime * result + ((realEstateLng == null) ? 0 : realEstateLng.hashCode());
		result = prime * result + ((realEstateName == null) ? 0 : realEstateName.hashCode());
		result = prime * result + ((realEstatePrice == null) ? 0 : realEstatePrice.hashCode());
		result = prime * result + ((realEstateSource == null) ? 0 : realEstateSource.hashCode());
		result = prime * result + ((realEstateStatus == null) ? 0 : realEstateStatus.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		RealEstate other = (RealEstate) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (listHouse == null) {
			if (other.listHouse != null)
				return false;
		} else if (!listHouse.equals(other.listHouse))
			return false;
		if (listRealEstateAdjacentSegment == null) {
			if (other.listRealEstateAdjacentSegment != null)
				return false;
		} else if (!listRealEstateAdjacentSegment.equals(other.listRealEstateAdjacentSegment))
			return false;
		if (listReport == null) {
			if (other.listReport != null)
				return false;
		} else if (!listReport.equals(other.listReport))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (realEstateAddress == null) {
			if (other.realEstateAddress != null)
				return false;
		} else if (!realEstateAddress.equals(other.realEstateAddress))
			return false;
		if (realEstateId == null) {
			if (other.realEstateId != null)
				return false;
		} else if (!realEstateId.equals(other.realEstateId))
			return false;
		if (realEstateLat == null) {
			if (other.realEstateLat != null)
				return false;
		} else if (!realEstateLat.equals(other.realEstateLat))
			return false;
		if (realEstateLink == null) {
			if (other.realEstateLink != null)
				return false;
		} else if (!realEstateLink.equals(other.realEstateLink))
			return false;
		if (realEstateLng == null) {
			if (other.realEstateLng != null)
				return false;
		} else if (!realEstateLng.equals(other.realEstateLng))
			return false;
		if (realEstateName == null) {
			if (other.realEstateName != null)
				return false;
		} else if (!realEstateName.equals(other.realEstateName))
			return false;
		if (realEstatePrice == null) {
			if (other.realEstatePrice != null)
				return false;
		} else if (!realEstatePrice.equals(other.realEstatePrice))
			return false;
		if (realEstateSource == null) {
			if (other.realEstateSource != null)
				return false;
		} else if (!realEstateSource.equals(other.realEstateSource))
			return false;
		if (realEstateStatus == null) {
			if (other.realEstateStatus != null)
				return false;
		} else if (!realEstateStatus.equals(other.realEstateStatus))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
