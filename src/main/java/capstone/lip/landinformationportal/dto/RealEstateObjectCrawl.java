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
	
    
    
}