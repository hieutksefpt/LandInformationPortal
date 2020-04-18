package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.District;

public class DistrictValidation extends StringValidation{
	public String isValidDistrict(District district) {
		String error = isValidText(district.getDistrictName());
		if (error.compareTo("") != 0) {
			return error;
		}
		
		if (district.getDistrictLat().isNaN() || district.getDistrictLng().isNaN()) {
			return ValidateMessageCommon.IS_NOT_A_NUMBER; 
		}
		if (district.getDistrictLat() <= 0 || district.getDistrictLng() <= 0) {
			return ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		return "";
	}
}
