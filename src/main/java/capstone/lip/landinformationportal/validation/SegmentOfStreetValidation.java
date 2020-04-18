package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;;

public class SegmentOfStreetValidation extends StringValidation{
	public String isValidSegment(SegmentOfStreet segment) {
		String error = isValidText(segment.getSegmentName());
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
