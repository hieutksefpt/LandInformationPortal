package capstone.lip.landinformationportal.business.validation;

import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.Province;

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
