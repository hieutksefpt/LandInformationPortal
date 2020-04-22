package capstone.lip.landinformationportal.business.validation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import capstone.lip.landinformationportal.common.constant.FeatureDataTypeConstant;
import capstone.lip.landinformationportal.common.entity.LandsFeature;

public class LandsFeatureValidation extends StringValidation{
	public String isValidLandsFeature(LandsFeature landsFeature) {
		String error = isValidText(landsFeature.getLandsFeatureName());
		if (error.isEmpty() && landsFeature.getLandsFeatureUnit()!= null) {
			error = hasNumber(landsFeature.getLandsFeatureUnit());
			if (error.isEmpty()) {
				error = isSpecialCharOnly(landsFeature.getLandsFeatureUnit());
			}
		}	
		if (error.isEmpty() && landsFeature.getLandsFeatureDataType() == null) {
			return error = "Data type is null";
		}
		if (error.isEmpty() 
				&& !landsFeature.getLandsFeatureDataType().equals(FeatureDataTypeConstant.INT) 
				&& !landsFeature.getLandsFeatureDataType().equals(FeatureDataTypeConstant.STR)) {
			return error = "Data type is invalid";
		}
		if (error.isEmpty() && (landsFeature.getLandsFeatureDataType().equals(FeatureDataTypeConstant.INT))) {
			List<String> listRange = landsFeature.getLandsFeatureDataRange();
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
