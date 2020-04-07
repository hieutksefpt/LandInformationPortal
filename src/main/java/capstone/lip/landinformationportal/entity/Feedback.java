package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name="Feedback")
public class Feedback extends AuditAbstract implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackID")
    private Long feedBackID;
    @Column(name = "FeedbackTitle")
    private String feedbackTitle;
    @Column(name = "FeedbackContent")
    private String feedbackContent;
    @Column(name = "FeedbackStatus")
    private String feedbackStatus;
    @Column(name = "FeedbackAdminReply")
    private String feedbackAdminReply;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private User user;
	public Long getFeedBackID() {
		return feedBackID;
	}
	public Feedback setFeedBackID(Long feedBackID) {
		this.feedBackID = feedBackID;
		return this;
	}
	public String getFeedbackTitle() {
		return feedbackTitle;
	}
	public Feedback setFeedbackTitle(String feedbackTitle) {
		this.feedbackTitle = feedbackTitle;
		return this;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}
	public Feedback setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
		return this;
	}
	public String getFeedbackStatus() {
		return feedbackStatus;
	}
	public Feedback setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
		return this;
	}
	public String getFeedbackAdminReply() {
		return feedbackAdminReply;
	}
	public Feedback setFeedbackAdminReply(String feedbackAdminReply) {
		this.feedbackAdminReply = feedbackAdminReply;
		return this;
	}
	public User getUser() {
		return user;
	}
	public Feedback setUser(User user) {
		this.user = user;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((feedBackID == null) ? 0 : feedBackID.hashCode());
		result = prime * result + ((feedbackAdminReply == null) ? 0 : feedbackAdminReply.hashCode());
		result = prime * result + ((feedbackContent == null) ? 0 : feedbackContent.hashCode());
		result = prime * result + ((feedbackStatus == null) ? 0 : feedbackStatus.hashCode());
		result = prime * result + ((feedbackTitle == null) ? 0 : feedbackTitle.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Feedback other = (Feedback) obj;
		if (feedBackID == null) {
			if (other.feedBackID != null)
				return false;
		} else if (!feedBackID.equals(other.feedBackID))
			return false;
		if (feedbackAdminReply == null) {
			if (other.feedbackAdminReply != null)
				return false;
		} else if (!feedbackAdminReply.equals(other.feedbackAdminReply))
			return false;
		if (feedbackContent == null) {
			if (other.feedbackContent != null)
				return false;
		} else if (!feedbackContent.equals(other.feedbackContent))
			return false;
		if (feedbackStatus == null) {
			if (other.feedbackStatus != null)
				return false;
		} else if (!feedbackStatus.equals(other.feedbackStatus))
			return false;
		if (feedbackTitle == null) {
			if (other.feedbackTitle != null)
				return false;
		} else if (!feedbackTitle.equals(other.feedbackTitle))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
    
    
    
}
