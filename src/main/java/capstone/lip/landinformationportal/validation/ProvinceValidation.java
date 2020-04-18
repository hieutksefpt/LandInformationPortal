package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.Province;

public class ProvinceValidation extends StringValidation{
	public String isValidProvince(Province province) {
		String error = isValidText(province.getProvinceName());
		if (error.compareTo("") != 0) {
			return error;
		}
		
		if (province.getProvinceLat().isNaN() || province.getProvinceLng().isNaN()) {
			return ValidateMessageCommon.IS_NOT_A_NUMBER; 
		}
		if (province.getProvinceLat() <= 0 || province.getProvinceLng() <= 0) {
			return ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		return "";
	}
}
