package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;

public interface ISegmentOfStreetService {
	
	public List<Street> getListStreetByListSegment(List<SegmentOfStreet> listSegment);
	
}