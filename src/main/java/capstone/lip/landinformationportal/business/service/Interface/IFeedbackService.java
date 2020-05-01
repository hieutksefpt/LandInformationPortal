package capstone.lip.landinformationportal.business.service.Interface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.common.entity.Feedback;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import java.util.Map;

public interface IFeedbackService {

    Feedback save(Feedback feedback);

    boolean delete(Feedback feedback);

    Feedback findById(Long id);

    long countByFeedbackStatus(String feedbackStatus);

    Page<Feedback> findByFeedbackStatus(String feedbackStatus, Pageable page);

    boolean sendFeedbackReply(Feedback feedback);

    Page<Feedback> findAllByAttribute(Map<String, Object> listAttribute, Pageable page);

    long countByAttribute(Map<String, Object> listAttribute);

}
