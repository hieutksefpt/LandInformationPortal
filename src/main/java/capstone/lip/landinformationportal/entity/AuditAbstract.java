package capstone.lip.landinformationportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdDate", "modifiedDate"},
        allowGetters = true
)
public abstract class AuditAbstract implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    @Column(name = "createdDate", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdDate;


    @Column(name = "modifiedDate", nullable = false)
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
    
    
    
}