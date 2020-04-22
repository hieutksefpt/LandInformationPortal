package capstone.lip.landinformationportal.business.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.business.repository.StreetRepository;
import capstone.lip.landinformationportal.business.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.business.service.Interface.IStreetService;
import capstone.lip.landinformationportal.business.validation.StreetValidation;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;

@Service
public class StreetService implements IStreetService{
	@Autowired
	private StreetRepository streetRepository;
	
	@Autowired
	private ISegmentOfStreetService segmentService;
	
	@Override
	public Street save(Street street) {
		try {
			StreetValidation validate = new StreetValidation();
			String error = validate.isValidStreet(street);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			return streetRepository.save(street);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(List<Street> listStreet) {
		try {
			if (listStreet == null) throw new Exception("List street is null");
			if (listStreet.isEmpty()) throw new Exception("List street is empty");
			for (Street element:listStreet) {
				if (findById(element.getStreetId())==null) {
					throw new Exception("Street not found");
				}
			}
			List<SegmentOfStreet> listSegment = listStreet.stream().map(x->x.getListSegmentOfStreet()).flatMap(List::stream).collect(Collectors.toList());
			segmentService.delete(listSegment);
			
			streetRepository.deleteAll(listStreet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean delete(Street street) {
		try {
			if (street == null) throw new Exception("Street is null");
			if (findById(street.getStreetId())==null) {
				throw new Exception("Street not found");
			}
			List<SegmentOfStreet> listSegment = street.getListSegmentOfStreet();
			segmentService.delete(listSegment);
			
			streetRepository.delete(street);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Street findById(Long id) {
		try {
			if (id==null) throw new Exception();
			Optional<Street> street = streetRepository.findById(id);
			if (street.isPresent()) {
				return street.get();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
