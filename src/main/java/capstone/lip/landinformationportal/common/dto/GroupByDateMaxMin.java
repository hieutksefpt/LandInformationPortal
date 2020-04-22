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
	
}
