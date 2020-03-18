package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="CrawledNews")
public class CrawledNews extends AuditAbstract  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CrawledNewsID")
	private Long crawledNewsID;
	@Column(name="CrawledNewsTitle")
	private String crawledNewsTitle;
	@Column(name="CrawledNewsLink")
	private String crawledNewsLink;
	@Column(name="CrawledNewsWebsite")
	private String crawledNewsWebsite;
	@Column(name="CrawledNewsShortDescription")
	private String crawledNewsShortDescription;
	@Column(name="CrawledNewsStatus")
	private Integer crawledNewsStatus;
	@Column(name = "CrawledNewsTime")
    private Timestamp crawledNewsTime;
	
	
	
	public CrawledNews() {
		super();
	}
	public Long getCrawledNewsID() {
		return crawledNewsID;
	}
	public CrawledNews setCrawledNewsID(Long crawledNewsID) {
		this.crawledNewsID = crawledNewsID;
		return this;
	}
	public String getCrawledNewsTitle() {
		return crawledNewsTitle;
	}
	public CrawledNews setCrawledNewsTitle(String crawledNewsTitle) {
		this.crawledNewsTitle = crawledNewsTitle;
		return this;
	}
	public String getCrawledNewsLink() {
		return crawledNewsLink;
	}
	public CrawledNews setCrawledNewsLink(String crawledNewsLink) {
		this.crawledNewsLink = crawledNewsLink;
		return this;
	}
	public String getCrawledNewsWebsite() {
		return crawledNewsWebsite;
	}
	public CrawledNews setCrawledNewsWebsite(String crawledNewsWebsite) {
		this.crawledNewsWebsite = crawledNewsWebsite;
		return this;
	}
	public String getCrawledNewsShortDescription() {
		return crawledNewsShortDescription;
	}
	public CrawledNews setCrawledNewsShortDescription(String crawledNewsShortDescription) {
		this.crawledNewsShortDescription = crawledNewsShortDescription;
		return this;
	}
	public Integer getCrawledNewsStatus() {
		return crawledNewsStatus;
	}
	public CrawledNews setCrawledNewsStatus(Integer crawledNewsStatus) {
		this.crawledNewsStatus = crawledNewsStatus;
		return this;
	}
	public Timestamp getCrawledNewsTime() {
		return crawledNewsTime;
	}
	public CrawledNews setCrawledNewsTime(Timestamp crawledNewsTime) {
		this.crawledNewsTime = crawledNewsTime;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crawledNewsID == null) ? 0 : crawledNewsID.hashCode());
		result = prime * result + ((crawledNewsLink == null) ? 0 : crawledNewsLink.hashCode());
		result = prime * result + ((crawledNewsShortDescription == null) ? 0 : crawledNewsShortDescription.hashCode());
		result = prime * result + ((crawledNewsStatus == null) ? 0 : crawledNewsStatus.hashCode());
		result = prime * result + ((crawledNewsTime == null) ? 0 : crawledNewsTime.hashCode());
		result = prime * result + ((crawledNewsTitle == null) ? 0 : crawledNewsTitle.hashCode());
		result = prime * result + ((crawledNewsWebsite == null) ? 0 : crawledNewsWebsite.hashCode());
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
		CrawledNews other = (CrawledNews) obj;
		if (crawledNewsID == null) {
			if (other.crawledNewsID != null)
				return false;
		} else if (!crawledNewsID.equals(other.crawledNewsID))
			return false;
		if (crawledNewsLink == null) {
			if (other.crawledNewsLink != null)
				return false;
		} else if (!crawledNewsLink.equals(other.crawledNewsLink))
			return false;
		if (crawledNewsShortDescription == null) {
			if (other.crawledNewsShortDescription != null)
				return false;
		} else if (!crawledNewsShortDescription.equals(other.crawledNewsShortDescription))
			return false;
		if (crawledNewsStatus == null) {
			if (other.crawledNewsStatus != null)
				return false;
		} else if (!crawledNewsStatus.equals(other.crawledNewsStatus))
			return false;
		if (crawledNewsTime == null) {
			if (other.crawledNewsTime != null)
				return false;
		} else if (!crawledNewsTime.equals(other.crawledNewsTime))
			return false;
		if (crawledNewsTitle == null) {
			if (other.crawledNewsTitle != null)
				return false;
		} else if (!crawledNewsTitle.equals(other.crawledNewsTitle))
			return false;
		if (crawledNewsWebsite == null) {
			if (other.crawledNewsWebsite != null)
				return false;
		} else if (!crawledNewsWebsite.equals(other.crawledNewsWebsite))
			return false;
		return true;
	}
	
	
}
