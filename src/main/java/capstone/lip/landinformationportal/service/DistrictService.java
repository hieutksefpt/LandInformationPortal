package capstone.lip.landinformationportal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.service.Interface.IStreetService;
import capstone.lip.landinformationportal.validation.DistrictValidation;

@Service
public class DistrictService implements IDistrictService{
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public District save(District district) {
		try {
			DistrictValidation validate = new DistrictValidation();
			String error = validate.isValidDistrict(district);
			if (error.compareTo("") == 0) {
				return districtRepository.save(district);
			}else {
				throw new Exception("Not valid district, "+error);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(List<District> listDistrict) {
		try {
			if (listDistrict == null) throw new Exception("List district is null");
			if (listDistrict.isEmpty()) throw new Exception("List district is empty");
			
			List<SegmentOfStreet> listSegment = listDistrict.stream().map(x->x.getListSegmentOfStreet()).flatMap(List::stream).collect(Collectors.toList());
			segmentService.delete(listSegment);
			districtRepository.deleteAll(listDistrict);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Autowired
	private IStreetService streetService;
	
	@Autowired
	private ISegmentOfStreetService segmentService;
	
	@Override
	public boolean delete(District district) {
		try {
			if (district == null) throw new Exception();
			List<SegmentOfStreet> listSegment = district.getListSegmentOfStreet();
			segmentService.delete(listSegment);	
			districtRepository.delete(district);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public District findById(Long id) {
		try {
			Optional<District> district = districtRepository.findById(id);
			if (district.isPresent()) {
				return district.get();
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
