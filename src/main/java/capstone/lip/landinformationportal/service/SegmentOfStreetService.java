package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.repository.SegmentOfStreetRepository;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;

@Service
public class SegmentOfStreetService implements ISegmentOfStreetService {

	@Autowired
	SegmentOfStreetRepository segmentOfStreetRepository;

	@Override
	public SegmentOfStreet save(SegmentOfStreet segmentOfStreet) {
		try {
			return segmentOfStreetRepository.save(segmentOfStreet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean delete(List<SegmentOfStreet> listSegment) {
		try {
			segmentOfStreetRepository.deleteInBatch(listSegment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(SegmentOfStreet segmentOfStreet) {
		
		try {
			segmentOfStreetRepository.delete(segmentOfStreet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
