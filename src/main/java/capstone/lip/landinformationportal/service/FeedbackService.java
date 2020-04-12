package capstone.lip.landinformationportal.service;

import java.util.List;
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
	public List<Feedback> findAll() {
		return feedbackRepository.findAll();
	}

	@Override
	public List<Feedback> save(List<Feedback> listFeedback) {
		return feedbackRepository.saveAll(listFeedback);
	}

	@Override
	public Feedback save(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	@Override
	public void delete(List<Feedback> listFeedback) throws Exception {
		feedbackRepository.deleteAll(listFeedback);
	}

	@Override
	public void delete(Feedback feedback) throws Exception {
		feedbackRepository.delete(feedback);
	}

	@Override
	public Feedback findById(Long id) {
		Optional<Feedback> temp = feedbackRepository.findById(id);
		if (temp.isPresent()) {
			return temp.get();
		}
		return null;
	}

	@Override
	public long count() {
		return feedbackRepository.count();
	}

	@Override
	public Page<Feedback> findAll(Pageable page) {
		return feedbackRepository.findAll(page);
	}

	@Override
	public void sendFeedbackReply(Feedback feedback) throws Exception {
		feedbackRepository.save(feedback);
		emailSender.sendMailFeedback(feedback);
	}

}
