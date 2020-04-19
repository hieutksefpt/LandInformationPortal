package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.entity.HousesFeature;

public class HousesFeatureValidation  extends StringValidation{
	public String isValidHousesFeature(HousesFeature housesFeature) {
		String error = isValidText(housesFeature.getHousesFeatureName());
		if (error.isEmpty() && housesFeature.getHousesFeatureUnit()!= null) {
			error = isNumericOnly(housesFeature.getHousesFeatureUnit());
			if (error.isEmpty()) {
				error = isSpecialCharOnly(housesFeature.getHousesFeatureUnit());
			}
		}	
		if (!error.isEmpty()) return error;
		return "";
	}
}
