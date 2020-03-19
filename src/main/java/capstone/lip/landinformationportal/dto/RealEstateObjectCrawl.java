package capstone.lip.landinformationportal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class RealEstateObjectCrawl implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String idCrawlerJob;

    private Long codePost;
    private String typePost;
    private String title;
    private BigDecimal price;
    private BigDecimal area;
    private String type;
    private Timestamp date;
    private String address;
    private Integer numberBedrooms;
    private Integer numberToilets;
    private Double sizeFront;
    private Integer numberFloor;
    private Double wardin;
    private String homeDirection;
    private String balconyDirection;
    private String interior;
    private Double longitude;
    private Double latitude;
    private String nameOwner;
    private String mobile;
    private String email;
    private String link;
    private String projectName;
    private String projectSize;
    private String projectOwner;
    private String source;
    private Timestamp startDatePost;
    private Timestamp endDatePost;
	public String getIdCrawlerJob() {
		return idCrawlerJob;
	}
	public void setIdCrawlerJob(String idCrawlerJob) {
		this.idCrawlerJob = idCrawlerJob;
	}
	public Long getCodePost() {
		return codePost;
	}
	public void setCodePost(Long codePost) {
		this.codePost = codePost;
	}
	public String getTypePost() {
		return typePost;
	}
	public void setTypePost(String typePost) {
		this.typePost = typePost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getNumberBedrooms() {
		return numberBedrooms;
	}
	public void setNumberBedrooms(Integer numberBedrooms) {
		this.numberBedrooms = numberBedrooms;
	}
	public Integer getNumberToilets() {
		return numberToilets;
	}
	public void setNumberToilets(Integer numberToilets) {
		this.numberToilets = numberToilets;
	}
	public Double getSizeFront() {
		return sizeFront;
	}
	public void setSizeFront(Double sizeFront) {
		this.sizeFront = sizeFront;
	}
	public Integer getNumberFloor() {
		return numberFloor;
	}
	public void setNumberFloor(Integer numberFloor) {
		this.numberFloor = numberFloor;
	}
	public Double getWardin() {
		return wardin;
	}
	public void setWardin(Double wardin) {
		this.wardin = wardin;
	}
	public String getHomeDirection() {
		return homeDirection;
	}
	public void setHomeDirection(String homeDirection) {
		this.homeDirection = homeDirection;
	}
	public String getBalconyDirection() {
		return balconyDirection;
	}
	public void setBalconyDirection(String balconyDirection) {
		this.balconyDirection = balconyDirection;
	}
	public String getInterior() {
		return interior;
	}
	public void setInterior(String interior) {
		this.interior = interior;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getNameOwner() {
		return nameOwner;
	}
	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectSize() {
		return projectSize;
	}
	public void setProjectSize(String projectSize) {
		this.projectSize = projectSize;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	public Timestamp getStartDatePost() {
		return startDatePost;
	}
	public void setStartDatePost(Timestamp startDatePost) {
		this.startDatePost = startDatePost;
	}
	public Timestamp getEndDatePost() {
		return endDatePost;
	}
	public void setEndDatePost(Timestamp endDatePost) {
		this.endDatePost = endDatePost;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((balconyDirection == null) ? 0 : balconyDirection.hashCode());
		result = prime * result + ((codePost == null) ? 0 : codePost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endDatePost == null) ? 0 : endDatePost.hashCode());
		result = prime * result + ((homeDirection == null) ? 0 : homeDirection.hashCode());
		result = prime * result + ((idCrawlerJob == null) ? 0 : idCrawlerJob.hashCode());
		result = prime * result + ((interior == null) ? 0 : interior.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((nameOwner == null) ? 0 : nameOwner.hashCode());
		result = prime * result + ((numberBedrooms == null) ? 0 : numberBedrooms.hashCode());
		result = prime * result + ((numberFloor == null) ? 0 : numberFloor.hashCode());
		result = prime * result + ((numberToilets == null) ? 0 : numberToilets.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((projectOwner == null) ? 0 : projectOwner.hashCode());
		result = prime * result + ((projectSize == null) ? 0 : projectSize.hashCode());
		result = prime * result + ((sizeFront == null) ? 0 : sizeFront.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((startDatePost == null) ? 0 : startDatePost.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((typePost == null) ? 0 : typePost.hashCode());
		result = prime * result + ((wardin == null) ? 0 : wardin.hashCode());
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
		RealEstateObjectCrawl other = (RealEstateObjectCrawl) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (balconyDirection == null) {
			if (other.balconyDirection != null)
				return false;
		} else if (!balconyDirection.equals(other.balconyDirection))
			return false;
		if (codePost == null) {
			if (other.codePost != null)
				return false;
		} else if (!codePost.equals(other.codePost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endDatePost == null) {
			if (other.endDatePost != null)
				return false;
		} else if (!endDatePost.equals(other.endDatePost))
			return false;
		if (homeDirection == null) {
			if (other.homeDirection != null)
				return false;
		} else if (!homeDirection.equals(other.homeDirection))
			return false;
		if (idCrawlerJob == null) {
			if (other.idCrawlerJob != null)
				return false;
		} else if (!idCrawlerJob.equals(other.idCrawlerJob))
			return false;
		if (interior == null) {
			if (other.interior != null)
				return false;
		} else if (!interior.equals(other.interior))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (nameOwner == null) {
			if (other.nameOwner != null)
				return false;
		} else if (!nameOwner.equals(other.nameOwner))
			return false;
		if (numberBedrooms == null) {
			if (other.numberBedrooms != null)
				return false;
		} else if (!numberBedrooms.equals(other.numberBedrooms))
			return false;
		if (numberFloor == null) {
			if (other.numberFloor != null)
				return false;
		} else if (!numberFloor.equals(other.numberFloor))
			return false;
		if (numberToilets == null) {
			if (other.numberToilets != null)
				return false;
		} else if (!numberToilets.equals(other.numberToilets))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (projectOwner == null) {
			if (other.projectOwner != null)
				return false;
		} else if (!projectOwner.equals(other.projectOwner))
			return false;
		if (projectSize == null) {
			if (other.projectSize != null)
				return false;
		} else if (!projectSize.equals(other.projectSize))
			return false;
		if (sizeFront == null) {
			if (other.sizeFront != null)
				return false;
		} else if (!sizeFront.equals(other.sizeFront))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (startDatePost == null) {
			if (other.startDatePost != null)
				return false;
		} else if (!startDatePost.equals(other.startDatePost))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typePost == null) {
			if (other.typePost != null)
				return false;
		} else if (!typePost.equals(other.typePost))
			return false;
		if (wardin == null) {
			if (other.wardin != null)
				return false;
		} else if (!wardin.equals(other.wardin))
			return false;
		return true;
	}
	
    
    
}