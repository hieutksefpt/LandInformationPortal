package capstone.lip.landinformationportal.common.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import capstone.lip.landinformationportal.common.entity.audit.AuditAbstract;
import capstone.lip.landinformationportal.common.entity.compositekey.ReportId;

@Entity
@Table(name = "Report")
public class Report extends AuditAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ReportId id;

    @Basic(fetch = FetchType.LAZY)
    @OneToOne
    @MapsId("UserID")
    @JoinColumn(name = "UserID")
    private User user;

    @Basic(fetch = FetchType.LAZY)
    @OneToOne
    @MapsId("RealEstateID")
    @JoinColumn(name = "RealEstateID")
    private RealEstate realEstate;

    public Report() {
        super();
    }

    public ReportId getId() {
        return id;
    }

    public Report setId(ReportId id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Report setUser(User user) {
        this.user = user;
        return this;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public Report setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
        return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}
