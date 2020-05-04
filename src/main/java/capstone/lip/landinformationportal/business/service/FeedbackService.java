package capstone.lip.landinformationportal.business.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.business.repository.FeedbackRepository;
import capstone.lip.landinformationportal.business.service.Interface.IFeedbackService;
import capstone.lip.landinformationportal.business.specification.FeedbackSpecifications;
import capstone.lip.landinformationportal.business.specification.RealEstateSpecifications;
import capstone.lip.landinformationportal.business.specification.SearchCriteria;
import capstone.lip.landinformationportal.business.validation.FeedbackValidation;
import capstone.lip.landinformationportal.common.constant.FeedbackStatusConstant;
import capstone.lip.landinformationportal.common.entity.Feedback;
import capstone.lip.landinformationportal.common.utils.EmailSender;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

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
    public boolean delete(Feedback feedback) {
        try {
            if (findById(feedback.getFeedBackID()) == null) {
//		throw new Exception("Id not found");
                return false;
            } else {
                feedbackRepository.delete(feedback);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Feedback findById(Long id) {
        try {
            if (id == null) {
                return null;
            }
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
            if (!feedbackStatus.equals(FeedbackStatusConstant.OPEN) && !feedbackStatus.equals(FeedbackStatusConstant.CLOSE)) {
                return -1;
            } else {
                return feedbackRepository.countByFeedbackStatus(feedbackStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean sendFeedbackReply(Feedback feedback) {
        try {
            FeedbackValidation validate = new FeedbackValidation();
            if (!feedbackRepository.existsById(feedback.getFeedBackID())) {
                throw new Exception();
            }
            String error = validate.isValidFeedback(feedback);
            if (error.isEmpty()) {
                error = validate.isValidFeedbackReply(feedback);
            }
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
            Page<Feedback> tempPage = feedbackRepository.findByFeedbackStatus(feedbackStatus, page);
            List<Feedback> listTemp = tempPage.stream().map(x -> x).collect(Collectors.toList());
//            List<Feedback> listTemp = (List<Feedback>) tempPage;
            if (listTemp.isEmpty() || listTemp == null) {
                throw new Exception();
            } else {
                return tempPage;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Feedback> findAllByAttribute(Map<String, Object> listAttribute, Pageable page) {
        try {
        	if(listAttribute == null) {
        		throw new Exception();
        	}
            List<FeedbackSpecifications> listSpec = new ArrayList();
            if (listAttribute != null) {
                for (Map.Entry meta : listAttribute.entrySet()) {
                    String key = (String) meta.getKey();
                    String value = (String) meta.getValue();
                    if (key.equals("feedbackStatus")) {
                        listSpec.add(new FeedbackSpecifications(new SearchCriteria(key, ":=", value)));
                    } 
                }
            }
            if (!listSpec.isEmpty()) {
                return feedbackRepository.findAll(createSpecification(listSpec), page);
            }
            return feedbackRepository.findAll(page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long countByAttribute(Map<String, Object> listAttribute) {
        try {
        	if(listAttribute == null) {
        		throw new Exception();
        	}
            List<FeedbackSpecifications> listSpec = new ArrayList();
            if (listAttribute != null) {
                for (Map.Entry meta : listAttribute.entrySet()) {
                    String key = (String) meta.getKey();
                    String value = (String) meta.getValue();
                    if (key.equals("feedbackStatus")) {
                        listSpec.add(new FeedbackSpecifications(new SearchCriteria(key, ":=", value)));
                    } 
                }
            }
            if (!listSpec.isEmpty()) {
                return feedbackRepository.count(createSpecification(listSpec));
            }
            return feedbackRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private Specification<Feedback> createSpecification(List<FeedbackSpecifications> listSpec) {
        if (listSpec == null) {
            return null;
        }
        Specification<Feedback> spec = Specification.where(listSpec.get(0));
        for (int i = 1; i < listSpec.size(); i++) {
            spec = spec.and(listSpec.get(i));
        }
        return spec;
    }

}
