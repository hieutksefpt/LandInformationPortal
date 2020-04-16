package capstone.lip.landinformationportal.service.Interface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.entity.Feedback;

public interface IFeedbackService {
	
	Feedback save(Feedback feedback);
	
	boolean delete(Feedback feedback);
	
	Feedback findById(Long id);
	
	long countByFeedbackStatus(String feedbackStatus);
	
	Page<Feedback> findByFeedbackStatus(String feedbackStatus,Pageable page);
	
	boolean sendFeedbackReply(Feedback feedback);
	
}
