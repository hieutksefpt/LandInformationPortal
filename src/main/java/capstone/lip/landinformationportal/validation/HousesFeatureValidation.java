package capstone.lip.landinformationportal.validation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import capstone.lip.landinformationportal.common.FeatureDataTypeConstant;
import capstone.lip.landinformationportal.entity.HousesFeature;

public class HousesFeatureValidation extends StringValidation{
	public String isValidHousesFeature(HousesFeature housesFeature) {
		String error = isValidText(housesFeature.getHousesFeatureName());
		if (error.isEmpty() && housesFeature.getHousesFeatureUnit()!= null) {
			error = hasNumber(housesFeature.getHousesFeatureUnit());
			if (error.isEmpty()) {
				error = isSpecialCharOnly(housesFeature.getHousesFeatureUnit());
			}
		}	
		if (error.isEmpty() && housesFeature.getHousesFeatureDataType() == null) {
			return error = "Data type is null";
		}
		if (error.isEmpty() 
				&& !housesFeature.getHousesFeatureDataType().equals(FeatureDataTypeConstant.INT) 
				&& !housesFeature.getHousesFeatureDataType().equals(FeatureDataTypeConstant.STR)) {
			return error = "Data type is invalid";
		}
		if (error.isEmpty() && (housesFeature.getHousesFeatureDataType().equals(FeatureDataTypeConstant.INT))) {
			List<String> listRange = housesFeature.getHousesFeatureDataRange();
			if (listRange != null) {
				for (String element:listRange) {
					if (!StringUtils.isNumeric(element)) {
						return error = "Invalid data range";
					}
				}
			}
		}
		if (!error.isEmpty()) return error;
		return "";
	}
}
