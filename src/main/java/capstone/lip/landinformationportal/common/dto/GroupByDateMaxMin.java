package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class GroupByDateMaxMin implements Serializable{

	private static final long serialVersionUID = 1L;
	private Timestamp dateCreated;
	private MaxMinAvg maxMinAvg;
	
	public GroupByDateMaxMin(Date dateCreated, BigDecimal max, BigDecimal min, Double avg) {
		this.dateCreated = new Timestamp(dateCreated.getTime());
		this.maxMinAvg = new MaxMinAvg(max, min, avg);
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public MaxMinAvg getMaxMinAvg() {
		return maxMinAvg;
	}

	public void setMaxMinAvg(MaxMinAvg maxMinAvg) {
		this.maxMinAvg = maxMinAvg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((maxMinAvg == null) ? 0 : maxMinAvg.hashCode());
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
		GroupByDateMaxMin other = (GroupByDateMaxMin) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (maxMinAvg == null) {
			if (other.maxMinAvg != null)
				return false;
		} else if (!maxMinAvg.equals(other.maxMinAvg))
			return false;
		return true;
	}
	
}
