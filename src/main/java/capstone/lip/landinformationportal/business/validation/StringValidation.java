package capstone.lip.landinformationportal.business.validation;

import org.apache.commons.lang3.StringUtils;

import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;

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
        
        public String isValidText2(String text) {
		if (text == null) {
			return ValidateMessageCommon.NULL;
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
	public String isEmptyString(String text) {
		if (text.trim().isEmpty()) {
			return ValidateMessageCommon.EMPTY;
		}
		return "";
	}
	public String isValidEmail(String text) {
		if (!text.matches("^[a-z][a-z0-9_\\.]{2,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
			return ValidateMessageCommon.EMAIL;
		}
		return "";
	}
	public String containSpace(String text) {
		if (text.contains(" ")) {
			return ValidateMessageCommon.SPACE;
		}
		return "";
	}
	public String containUTF8(String text) {
		if (text.matches(".*[^A-Za-z0-9!@#$%&*()^~`\\-_+=|<>?{}\\[\\\\\\]\\/.].*")) {
			return ValidateMessageCommon.UTF8;
		}
		return "";
	}
}
