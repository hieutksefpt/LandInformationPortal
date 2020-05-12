package capstone.lip.landinformationportal.business.validation;

import java.util.List;
import java.util.Optional;
import capstone.lip.landinformationportal.business.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.HousesFeature;

public class HousesDetailValidation {

	public String isValidHouseDetail(List<HousesDetail> listHousesDetail, HousesFeatureRepository housesFeatureRepository) {

		if (listHousesDetail != null) {
			for (HousesDetail hd : listHousesDetail) {
				Optional<HousesFeature> hfTemp = housesFeatureRepository
						.findById(hd.getId().getHousesFeatureId());
				if (!hfTemp.isPresent()) {
					return ValidateMessageCommon.HOUSE_FEATURE_NOT_EXIST;
				}
			}
		}

		return "";
	}
}
