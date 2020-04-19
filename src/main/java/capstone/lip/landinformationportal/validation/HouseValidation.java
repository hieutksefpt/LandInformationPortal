package capstone.lip.landinformationportal.validation;

import java.math.BigDecimal;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.House;

public class HouseValidation extends StringValidation{
	public String isValidHouse(House house) {
		String error = "";
		if (house.getRealEstate() == null) return "null";
		if (house.getRealEstate().getRealEstateId() == null) return "null";
		if (house.getHouseName()!=null) {
			error = isNumericOnly(house.getHouseName());
			if (error.isEmpty()) error = isSpecialCharOnly(house.getHouseName());
		}
		
		if (house.getHousePrice() != null && (house.getHousePrice().compareTo(BigDecimal.ZERO)== 0 || house.getHousePrice().compareTo(BigDecimal.ZERO)== -1)) {
			error=ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		
		if (error.compareTo("") != 0) {
			return error;
		}
		return "";
	}
}
