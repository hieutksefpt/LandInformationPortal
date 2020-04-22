package capstone.lip.landinformationportal.business.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import capstone.lip.landinformationportal.business.service.SegmentOfStreetService;
import capstone.lip.landinformationportal.business.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.common.constant.ValidateMessageCommon;
import capstone.lip.landinformationportal.common.entity.FormedCoordinate;

@Component
public class FormedCoordinateValidation {

	private ISegmentOfStreetService segmentService;
	
	public FormedCoordinateValidation(ISegmentOfStreetService segmentService) {
		this.segmentService = segmentService;
	}

	public String isValidFormedCoordinate(FormedCoordinate coordinate) {
		if (coordinate.getSegmentOfStreet() == null) {
			return "Segment not found";
		}
		if (segmentService.findById(coordinate.getSegmentOfStreet().getSegmentId()) == null) {
			return "Segment not found";
		}
		if (coordinate.getFormedLat().isNaN() || coordinate.getFormedLng().isNaN()) {
			return ValidateMessageCommon.IS_NOT_A_NUMBER;
		}
		if (coordinate.getFormedLat() <= 0 || coordinate.getFormedLng() <= 0) {
			return ValidateMessageCommon.IS_NEGATIVE_NUMBER;
		}
		return "";
	}
}
