package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.Feedback;

public class FeedbackValidation extends StringValidation{
	public String isValidFeedback(Feedback feedback) {
		if (feedback.getFeedbackAdminReply() == null) {
			return ValidateMessageCommon.NULL; 
		}
		if (feedback.getFeedbackAdminReply().isEmpty()) {
			return ValidateMessageCommon.EMPTY;
		}
		return "";
	}
}
