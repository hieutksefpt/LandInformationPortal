package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MaxMinAvg implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigDecimal max;
	private BigDecimal min;
	private BigDecimal avg;
	
	public MaxMinAvg(BigDecimal max, BigDecimal min, Double avg) {
		super();
		this.max = max;
		this.min = min;
		if (avg != null)
			this.avg = new BigDecimal(avg);
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getAvg() {
		return avg;
	}
	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}
	
}
