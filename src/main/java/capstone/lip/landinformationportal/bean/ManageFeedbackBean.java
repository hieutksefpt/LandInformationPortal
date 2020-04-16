package capstone.lip.landinformationportal.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.common.FeedbackStatusConstant;
import capstone.lip.landinformationportal.dto.LazyFeedback;
import capstone.lip.landinformationportal.entity.Feedback;
import capstone.lip.landinformationportal.service.Interface.IFeedbackService;

@Named
@ViewScoped
public class ManageFeedbackBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IFeedbackService feedbackService;
	
	private LazyDataModel<Feedback> lazyFeedback;
	private String replyMessage;
	private Feedback feedbackClick;
	
	@PostConstruct
	public void init() {
		lazyFeedback = new LazyFeedback(feedbackService);
	}
	public void replyFeedbackClick() {
		setMessage(FacesMessage.SEVERITY_INFO, "Phản hồi thành công");
		feedbackClick.setFeedbackAdminReply(replyMessage);
		feedbackClick.setFeedbackStatus(FeedbackStatusConstant.CLOSE);
		try {
			feedbackService.sendFeedbackReply(feedbackClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteFeedback() {
		setMessage(FacesMessage.SEVERITY_WARN, "Xóa thành công");
		try {
			feedbackService.delete(feedbackClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lazyFeedback = new LazyFeedback(feedbackService);
	}
	
	public void setMessage(FacesMessage.Severity severityType, String message) {
		
		FacesMessage msg = new FacesMessage();
		if (severityType == FacesMessage.SEVERITY_ERROR) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", message);
		} else if (severityType == FacesMessage.SEVERITY_WARN) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Lưu ý", message);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", message);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void setFeedback(Feedback feedback) {
		feedbackClick = feedback;
	}
	
	public LazyDataModel<Feedback> getLazyFeedback() {
		return lazyFeedback;
	}

	public void setLazyFeedback(LazyDataModel<Feedback> lazyFeedback) {
		this.lazyFeedback = lazyFeedback;
	}

	public Feedback getFeedbackClick() {
		return feedbackClick;
	}

	public void setFeedbackClick(Feedback feedbackClick) {
		this.feedbackClick = feedbackClick;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

	
	
	
}
