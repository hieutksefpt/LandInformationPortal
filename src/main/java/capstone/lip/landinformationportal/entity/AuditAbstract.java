package capstone.lip.landinformationportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import capstone.lip.landinformationportal.config.CustomAuditConfig;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(CustomAuditConfig.class)
@JsonIgnoreProperties(
        value = {"createdDate", "modifiedDate"},
        allowGetters = true,
        allowSetters = true
)
public abstract class AuditAbstract implements Serializable {

	private static final long serialVersionUID = 1L;


    @Column(name = "createdDate", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdDate;


    @Column(name = "modifiedDate", nullable = false, updatable = true)
    @LastModifiedDate
    private Timestamp modifiedDate;

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreateDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
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
		AuditAbstract other = (AuditAbstract) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		return true;
	}
    
    
    
}