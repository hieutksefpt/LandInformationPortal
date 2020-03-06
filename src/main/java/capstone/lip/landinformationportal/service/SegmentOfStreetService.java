package capstone.lip.landinformationportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.repository.SegmentOfStreetRepository;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;

@Service
public class SegmentOfStreetService implements ISegmentOfStreetService {

	@Autowired
	SegmentOfStreetRepository segmentOfStreetRepository;
	
	@Override
	public List<Street> getListStreetByListSegment(List<SegmentOfStreet> listSegmentOfStreet) {
		List<Street> listStreet = new ArrayList<Street>();
		for (SegmentOfStreet segment : listSegmentOfStreet) {
			Street temp = segment.getStreet();
			if (listStreet.isEmpty() || !listStreet.contains(temp))
				listStreet.add(temp);
		}
		listStreet.stream().distinct().collect(Collectors.toList());
		return listStreet;
	}

	@Override
	public SegmentOfStreet save(SegmentOfStreet segmentOfStreet) {
		return segmentOfStreetRepository.save(segmentOfStreet);
	}


}
