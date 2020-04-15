package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.entity.Feedback;

public interface IFeedbackService {
	List<Feedback> findAll();
	
	Page<Feedback> findAll(Pageable page);
	
	List<Feedback> save(List<Feedback> listFeedback);
	
	Feedback save(Feedback feedback);
	
	boolean delete(List<Feedback> listFeedback);
	
	boolean delete(Feedback feedback);
	
	Feedback findById(Long id);
	
	long count();
	
	boolean sendFeedbackReply(Feedback feedback);
}
