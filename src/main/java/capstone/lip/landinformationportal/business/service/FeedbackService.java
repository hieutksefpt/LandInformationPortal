package capstone.lip.landinformationportal.business.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.business.repository.FeedbackRepository;
import capstone.lip.landinformationportal.business.service.Interface.IFeedbackService;
import capstone.lip.landinformationportal.business.validation.FeedbackValidation;
import capstone.lip.landinformationportal.common.entity.Feedback;
import capstone.lip.landinformationportal.common.utils.EmailSender;

@Service
public class FeedbackService implements IFeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public Feedback save(Feedback feedback) {
		try {
			FeedbackValidation validate = new FeedbackValidation();
			String error = validate.isValidFeedback(feedback);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			return feedbackRepository.save(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean delete(Feedback feedback){
		try {
			if (findById(feedback.getFeedBackID())==null) {
				throw new Exception("Id not found");
			}
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
			FeedbackValidation validate = new FeedbackValidation();
			String error = validate.isValidFeedback(feedback);
			if (error.isEmpty()) error = validate.isValidFeedbackReply(feedback);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
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
			FeedbackValidation validate = new FeedbackValidation();
			String error = validate.isValidStatus(new Feedback().setFeedbackStatus(feedbackStatus));
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			return feedbackRepository.findByFeedbackStatus(feedbackStatus, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
