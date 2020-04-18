package capstone.lip.landinformationportal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import capstone.lip.landinformationportal.common.ValidateMessageCommon;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.service.SegmentOfStreetService;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;

@Component
public class FormedCoordinateValidation{
	
	@Autowired
	private ISegmentOfStreetService segmentService;
	
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
