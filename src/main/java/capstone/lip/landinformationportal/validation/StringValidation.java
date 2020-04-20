package capstone.lip.landinformationportal.validation;

import org.apache.commons.lang3.StringUtils;

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
	public String isValidNumericString(String text) {
		if (text == null) {
			return ValidateMessageCommon.NULL;
		}
		if (text.trim().length() == 0) {
			return ValidateMessageCommon.EMPTY;
		}
		if (StringUtils.isNumeric(text)) {
			return ValidateMessageCommon.NUMERIC;
		}
		if (text.matches(".*[^A-Za-z0-9\\p{L}\\p{M} ].*")) {
			return ValidateMessageCommon.HAS_SPECIAL_CHAR;
		}
		
		return "";
	}
	
	public String isNumericOnly(String text) {
		if (StringUtils.isNumeric(text)) {
			return ValidateMessageCommon.NUMERIC;
		}
		return "";
	}
	public String isSpecialCharOnly(String text) {
		if (text.matches(".*[^A-Za-z0-9\\p{L}\\p{M} ].*")) {
			return ValidateMessageCommon.HAS_SPECIAL_CHAR;
		}
		return "";
	}
	public String hasNumber(String text) {
		if (text.matches(".*\\d.*")) {
			return ValidateMessageCommon.HAS_NUMBER;
		}
		return "";
	}
}
