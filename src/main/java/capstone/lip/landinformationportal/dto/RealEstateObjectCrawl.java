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
    private Timestamp startDatePost;
    private Timestamp endDatePost;
    
    
}