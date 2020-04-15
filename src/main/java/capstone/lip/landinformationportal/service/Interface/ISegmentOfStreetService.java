package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;

public interface ISegmentOfStreetService {
	
	SegmentOfStreet save(SegmentOfStreet segmentOfStreet);
	boolean delete(List<SegmentOfStreet> listSegment);
	boolean delete(SegmentOfStreet segmentOfStreet);
}
