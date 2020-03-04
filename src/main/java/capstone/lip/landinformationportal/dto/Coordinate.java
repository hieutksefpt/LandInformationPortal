package capstone.lip.landinformationportal.dto;

import java.io.Serializable;

public class Coordinate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391393586416050465L;
	private Double longitude;
	private Double latitude;
	
	
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
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
}
