package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;

public class StringValidation {
	public String isValidText(String text) {
		if (text == null) {
			return ValidateMessageCommon.NULL;
		}
		if (text.trim().length() == 0) {
			return ValidateMessageCommon.EMPTY;
		}
		if (text.matches(".*\\d.*")) {
			return ValidateMessageCommon.HAS_NUMBER;
		}
		if (text.matches(".*[^A-Za-z0-9\\p{L}\\p{M} ].*")) {
			return ValidateMessageCommon.HAS_SPECIAL_CHAR;
		}
		
		return "";
	}
	public String isValidDouble(String text) {
		try {
			Double.parseDouble(text);
			return "";
		}catch (Exception e) {
			return ValidateMessageCommon.IS_NOT_DOUBLE;
		}
	}
}
