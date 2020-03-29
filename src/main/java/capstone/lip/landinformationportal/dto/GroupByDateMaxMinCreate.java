package capstone.lip.landinformationportal.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class GroupByDateMaxMinCreate {
	private Timestamp dateCreated;
	private MaxMinAvg maxMinAvg;
	
	public GroupByDateMaxMinCreate(Date dateCreated, BigDecimal max, BigDecimal min, Double avg) {
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
