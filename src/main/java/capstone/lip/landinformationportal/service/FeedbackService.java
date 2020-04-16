package capstone.lip.landinformationportal.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import capstone.lip.landinformationportal.entity.Feedback;
import capstone.lip.landinformationportal.repository.FeedbackRepository;
import capstone.lip.landinformationportal.service.Interface.IFeedbackService;
import capstone.lip.landinformationportal.utils.EmailSender;

@Service
public class FeedbackService implements IFeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public Feedback save(Feedback feedback) {
		try {
			return feedbackRepository.save(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean delete(Feedback feedback){
		try {
			feedbackRepository.delete(feedback);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Feedback findById(Long id) {
		try {
			Optional<Feedback> temp = feedbackRepository.findById(id);
			if (temp.isPresent()) {
				return temp.get();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public long countByFeedbackStatus(String feedbackStatus) {
		try {
			return feedbackRepository.countByFeedbackStatus(feedbackStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public boolean sendFeedbackReply(Feedback feedback){
		try {
			feedbackRepository.save(feedback);
			emailSender.sendMailFeedback(feedback);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Page<Feedback> findByFeedbackStatus(String feedbackStatus, Pageable page) {
		try {
			return feedbackRepository.findByFeedbackStatus(feedbackStatus, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
