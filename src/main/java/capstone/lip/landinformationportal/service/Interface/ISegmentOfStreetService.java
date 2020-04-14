package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;

public interface ISegmentOfStreetService {
	
	SegmentOfStreet save(SegmentOfStreet segmentOfStreet);
	boolean delete(List<SegmentOfStreet> listSegment);
	boolean delete(SegmentOfStreet segmentOfStreet);
        
        // need this function
        List<SegmentOfStreet> findAll();
}
