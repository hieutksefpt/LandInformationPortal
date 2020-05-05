package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Coordinate implements Serializable {

    private static final long serialVersionUID = 6391393586416050465L;
    private Double longitude;
    private Double latitude;
    private Long id;
    private String source;
    private String status;
    private BigDecimal price;
    
    public Coordinate() {
        super();
    }

    public Coordinate(Double longitude, Double latitude) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    

    public Coordinate(Double longitude, Double latitude, BigDecimal price) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.price = price;
	}

	public String getStatus() {
        return status;
    }

    public Coordinate setStatus(String status) {
        this.status = status;
        return this;
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

	public BigDecimal getPrice() {
		return price;
	}

	public Coordinate setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Coordinate other = (Coordinate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
    
}
