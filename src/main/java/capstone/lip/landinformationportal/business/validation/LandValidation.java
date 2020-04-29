package capstone.lip.landinformationportal.business.validation;

import java.math.BigDecimal;

import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.Land;

public class LandValidation extends StringValidation{
	public String isValidLand(Land land) {
		String error = "";
		if (land.getRealEstate() == null) return "null";
		if (land.getRealEstate().getRealEstateId() == null) return "null";
		if (land.getLandName()!=null) {
			error = isNumericOnly(land.getLandName());
			if (error.isEmpty()) error = isSpecialCharOnly(land.getLandName());
		}
		
		if (land.getLandPrice() != null && (land.getLandPrice().compareTo(BigDecimal.ZERO)== -1)) {
			error=ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		
		if (error.compareTo("") != 0) {
			return error;
		}
		return "";
	}
}
