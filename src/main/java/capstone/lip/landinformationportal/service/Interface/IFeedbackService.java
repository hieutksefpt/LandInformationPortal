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
	
	void delete(List<Feedback> listFeedback) throws Exception;
	
	void delete(Feedback feedback) throws Exception;
	
	Feedback findById(Long id);
	
	long count();
}
