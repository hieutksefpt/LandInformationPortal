package capstone.lip.landinformationportal.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.Feedback;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface FeedbackRepository  extends JpaRepository<Feedback, Long>, JpaSpecificationExecutor<Feedback>{
	long countByFeedbackStatus(String feedbackStatus);
	
	Page<Feedback> findByFeedbackStatus(String feedbackStatus, Pageable page);
}
