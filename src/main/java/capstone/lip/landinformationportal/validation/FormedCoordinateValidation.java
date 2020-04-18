package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.FormedCoordinate;

public class FormedCoordinateValidation{
	public String isValidFormedCoordinate(FormedCoordinate coordinate) {
		if (coordinate.getFormedLat().isNaN() || coordinate.getFormedLng().isNaN()) {
			return ValidateMessageCommon.IS_NOT_A_NUMBER; 
		}
		if (coordinate.getFormedLat() <= 0 || coordinate.getFormedLng() <= 0) {
			return ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		return "";
	}
}
