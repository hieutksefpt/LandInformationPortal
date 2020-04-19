package capstone.lip.landinformationportal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.repository.SegmentOfStreetRepository;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.validation.SegmentOfStreetValidation;

@Service
public class SegmentOfStreetService implements ISegmentOfStreetService {

	@Autowired
	private SegmentOfStreetRepository segmentOfStreetRepository;
	
	@Autowired
	private IFormedCoordinate coordinateService;
	
	@Autowired
	private IRealEstateAdjacentSegmentService adjService;
	
	@Override
	public SegmentOfStreet save(SegmentOfStreet segmentOfStreet) {
		try {
			SegmentOfStreetValidation validate = new SegmentOfStreetValidation();
			String error = validate.isValidSegment(segmentOfStreet);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			return segmentOfStreetRepository.save(segmentOfStreet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean delete(List<SegmentOfStreet> listSegment) {
		try {
			if (listSegment == null) throw new Exception("List segment is null");
			if (listSegment.isEmpty()) throw new Exception("List segment is empty");
			for (SegmentOfStreet element: listSegment) {
				if (findById(element.getSegmentId())==null) {
					throw new Exception("Segment not found");
				}
			}
			List<FormedCoordinate> listCoordinate = listSegment.stream().map(x->x.getListFormedCoordinate()).flatMap(List::stream).collect(Collectors.toList());
			coordinateService.delete(listCoordinate);
			List<RealEstateAdjacentSegment> listAdj = listSegment.stream().map(x->x.getListRealEstateAdjacentSegment()).flatMap(List::stream).collect(Collectors.toList());
			adjService.delete(listAdj);
			segmentOfStreetRepository.deleteAll(listSegment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean delete(SegmentOfStreet segmentOfStreet) {
		
		try {
			if (segmentOfStreet == null) throw new Exception("Segment is null");
			if (findById(segmentOfStreet.getSegmentId())==null) {
				throw new Exception("Segment not found");
			}
			List<FormedCoordinate> listCoordinate = segmentOfStreet.getListFormedCoordinate();
			coordinateService.delete(listCoordinate);
			List<RealEstateAdjacentSegment> listAdj = segmentOfStreet.getListRealEstateAdjacentSegment();
			adjService.delete(listAdj);
			
			segmentOfStreetRepository.delete(segmentOfStreet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public SegmentOfStreet findById(Long id) {
		try {
			if (id==null) throw new Exception();
			Optional<SegmentOfStreet> segment = segmentOfStreetRepository.findById(id);
			if (segment.isPresent()) {
				return segment.get();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
