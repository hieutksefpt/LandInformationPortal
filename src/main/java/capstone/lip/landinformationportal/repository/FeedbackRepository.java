package capstone.lip.landinformationportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import capstone.lip.landinformationportal.entity.Feedback;

@Repository
public interface FeedbackRepository  extends JpaRepository<Feedback, Long>{
	long countByFeedbackStatus(String feedbackStatus);
	
	Page<Feedback> findByFeedbackStatus(String feedbackStatus, Pageable page);
}
