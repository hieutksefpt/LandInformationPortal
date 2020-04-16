package capstone.lip.landinformationportal.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import capstone.lip.landinformationportal.common.FeedbackStatusConstant;
import capstone.lip.landinformationportal.entity.Feedback;
import capstone.lip.landinformationportal.service.Interface.IFeedbackService;

public class LazyFeedback extends LazyDataModel<Feedback> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IFeedbackService feedbackService;
	
	public LazyFeedback() {
		this.setRowCount((int)feedbackService.countByFeedbackStatus(FeedbackStatusConstant.OPEN));
	}

	public LazyFeedback(IFeedbackService feedbackService) {
		this.feedbackService = feedbackService;
		this.setRowCount((int)feedbackService.countByFeedbackStatus(FeedbackStatusConstant.OPEN));
	}
	
	@Override
    public List<Feedback> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        Page<Feedback> list
                = feedbackService.findByFeedbackStatus(FeedbackStatusConstant.OPEN, PageRequest.of(first / pageSize, pageSize));
        List<Feedback> list1 = list.stream().map(x -> x).collect(Collectors.toList());
        return list1;
    }

    @Override
    public Feedback getRowData(String rowKey) {
        return feedbackService.findById(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(Feedback feedback) {
        return feedback.getFeedBackID();
    }
	
}
