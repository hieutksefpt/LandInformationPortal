package capstone.lip.landinformationportal.business.validation;

import java.util.List;
import java.util.Optional;

import capstone.lip.landinformationportal.business.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.LandsDetail;
import capstone.lip.landinformationportal.common.entity.LandsFeature;

public class LandsDetailValidation {

	public String isValidLandDetail(List<LandsDetail> listLandsDetail, LandsFeatureRepository landsFeatureRepository) {

		if (listLandsDetail != null) {
			for (LandsDetail ld : listLandsDetail) {
				Optional<LandsFeature> lfTemp = landsFeatureRepository
						.findById(ld.getId().getLandsFeatureId());
				if (!lfTemp.isPresent()) {
					return ValidateMessageCommon.LAND_FEATURE_NOT_EXIST;
				}
			}
		}

		return "";
	}
}
