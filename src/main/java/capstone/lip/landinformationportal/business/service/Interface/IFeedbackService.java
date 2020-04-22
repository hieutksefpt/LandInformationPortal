package capstone.lip.landinformationportal.business.service.Interface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.common.entity.Feedback;

public interface IFeedbackService {
	
	Feedback save(Feedback feedback);
	
	boolean delete(Feedback feedback);
	
	Feedback findById(Long id);
	
	long countByFeedbackStatus(String feedbackStatus);
	
	Page<Feedback> findByFeedbackStatus(String feedbackStatus,Pageable page);
	
	boolean sendFeedbackReply(Feedback feedback);
	
}
