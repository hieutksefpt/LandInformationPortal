package capstone.lip.landinformationportal.business.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.business.repository.DistrictRepository;
import capstone.lip.landinformationportal.business.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.business.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.business.validation.DistrictValidation;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

@Service
public class DistrictService implements IDistrictService{
	
	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private ISegmentOfStreetService segmentService;
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
			for (District element: listDistrict) {
				if (findById(element.getDistrictId())==null) {
					throw new Exception("District not found");
				}
			}
			List<SegmentOfStreet> listSegment = listDistrict.stream().map(x->x.getListSegmentOfStreet()).flatMap(List::stream).collect(Collectors.toList());
			segmentService.delete(listSegment);
			districtRepository.deleteAll(listDistrict);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(District district) {
		try {
			if (district == null) throw new Exception("null");
			if (findById(district.getDistrictId())==null) throw new Exception("district not found");
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
			if (id == null) throw new Exception();
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
