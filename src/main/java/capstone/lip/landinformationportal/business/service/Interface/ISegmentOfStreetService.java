package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

public interface ISegmentOfStreetService {
	
	SegmentOfStreet save(SegmentOfStreet segmentOfStreet);
	boolean delete(List<SegmentOfStreet> listSegment);
	boolean delete(SegmentOfStreet segmentOfStreet);
	SegmentOfStreet findById(Long id);
}
