package capstone.lip.landinformationportal.business.validation;

import org.apache.commons.lang3.StringUtils;

import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.Street;

public class StreetValidation extends StringValidation{
	public String isValidStreet(Street street) {
		String error = isValidName(street.getStreetName());
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
	public String isValidName(String text) {
		if (text == null) {
			return ValidateMessageCommon.NULL;
		}
		if (text.trim().length() == 0) {
			return ValidateMessageCommon.EMPTY;
		}
		if (text.matches(".*[^A-Za-z0-9\\p{L}\\p{M} ].*")) {
			return ValidateMessageCommon.HAS_SPECIAL_CHAR;
		}
		if (StringUtils.isNumeric(text)) {
			return ValidateMessageCommon.NUMERIC;
		}
		return "";
	}
}
