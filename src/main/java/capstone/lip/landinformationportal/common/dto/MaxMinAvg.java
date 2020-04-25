package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.max);
        hash = 89 * hash + Objects.hashCode(this.min);
        hash = 89 * hash + Objects.hashCode(this.avg);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaxMinAvg other = (MaxMinAvg) obj;
        if (!Objects.equals(this.max, other.max)) {
            return false;
        }
        if (!Objects.equals(this.min, other.min)) {
            return false;
        }
        if (!Objects.equals(this.avg, other.avg)) {
            return false;
        }
        return true;
    }
	
        
}
