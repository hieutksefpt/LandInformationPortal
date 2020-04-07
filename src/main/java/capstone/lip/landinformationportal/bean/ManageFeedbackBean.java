package capstone.lip.landinformationportal.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	@PostConstruct
	public void init() {
		lazyFeedback = new LazyFeedback(feedbackService);
	}

	private Feedback feedbackClick;
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

	
	
	
}
