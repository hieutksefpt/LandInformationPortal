package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;

public interface ISegmentOfStreetService {
	
	SegmentOfStreet save(SegmentOfStreet segmentOfStreet);
	List<Street> getListStreetByListSegment(List<SegmentOfStreet> listSegment);
	void delete(List<SegmentOfStreet> listSegment);
	void delete(SegmentOfStreet segmentOfStreet);
        
        // need this function
        List<SegmentOfStreet> findAll();
}
