package capstone.lip.landinformationportal.business.validation;

import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;;

public class SegmentOfStreetValidation extends StringValidation{
	public String isValidSegment(SegmentOfStreet segment) {
		String error = isValidNumericString(segment.getSegmentName());
		if (error.compareTo("") != 0) {
			return error;
		}
		
		if (segment.getSegmentLat().isNaN() || segment.getSegmentLng().isNaN()) {
			return ValidateMessageCommon.IS_NOT_A_NUMBER; 
		}
		if (segment.getSegmentLat() <= 0 || segment.getSegmentLng() <= 0) {
			return ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		return "";
	}
	
}
