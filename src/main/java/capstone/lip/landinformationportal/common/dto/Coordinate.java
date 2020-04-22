package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;

public class Coordinate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391393586416050465L;
	private Double longitude;
	private Double latitude;
	private Long id;
	private String source;
	public Coordinate() {
		super();
	}
	public Coordinate(Double longitude, Double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Coordinate setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Coordinate setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	public Long getId() {
		return id;
	}
	public Coordinate setId(Long id) {
		this.id = id;
		return this;
	}
	public String getSource() {
		return source;
	}
	public Coordinate setSource(String source) {
		this.source = source;
		return this;
	}
	
}
