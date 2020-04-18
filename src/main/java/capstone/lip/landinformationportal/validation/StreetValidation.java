package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.Street;

public class StreetValidation extends StringValidation{
	public String isValidStreet(Street street) {
		String error = isValidText(street.getStreetName());
		if (error.compareTo("") != 0) {
			return error;
		}
		
		if (street.getStreetLat().isNaN() || street.getStreetLng().isNaN()) {
			return ValidateMessageCommon.IS_NOT_A_NUMBER; 
		}
		if (street.getStreetLat() <= 0 || street.getStreetLng() <= 0) {
			return ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		return "";
	}
}
