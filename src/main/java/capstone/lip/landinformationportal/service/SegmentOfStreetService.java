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
		try {
			List<Street> listStreet = new ArrayList<Street>();
			for (SegmentOfStreet segment : listSegmentOfStreet) {
				Street temp = segment.getStreet();
				if (listStreet.isEmpty() || !listStreet.contains(temp))
					listStreet.add(temp);
			}
			listStreet.stream().distinct().collect(Collectors.toList());
			return listStreet;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

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

    @Override
    public List<SegmentOfStreet> findAll() {
        try {
        	return segmentOfStreetRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
