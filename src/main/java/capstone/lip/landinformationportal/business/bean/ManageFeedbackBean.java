package capstone.lip.landinformationportal.business.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.business.service.Interface.IFeedbackService;
import capstone.lip.landinformationportal.common.constant.FeedbackStatusConstant;
import capstone.lip.landinformationportal.common.dto.LazyFeedback;
import capstone.lip.landinformationportal.common.entity.Feedback;

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
		
		feedbackClick.setFeedbackAdminReply(replyMessage);
		if (feedbackClick.getFeedbackAdminReply()==null || 
				(feedbackClick.getFeedbackAdminReply()!=null && feedbackClick.getFeedbackAdminReply().isEmpty())) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Nội dung phản hồi không được để trống");
			return;
		}
		feedbackClick.setFeedbackStatus(FeedbackStatusConstant.CLOSE);
		boolean isSend = feedbackService.sendFeedbackReply(feedbackClick);
		if (!isSend) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Nội dung phản hồi không phù hợp");
			return;
		}
		setMessage(FacesMessage.SEVERITY_INFO, "Phản hồi thành công");
	}
	public void deleteFeedback() {
		setMessage(FacesMessage.SEVERITY_INFO, "Xóa thành công");
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
