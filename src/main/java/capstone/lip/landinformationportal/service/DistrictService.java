package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;

@Service
public class DistrictService implements IDistrictService{
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public List<District> findAll() {
		return districtRepository.findAll();
	}

	@Override
	public District save(District district) {
		return districtRepository.save(district);
	}

	@Override
	public List<SegmentOfStreet> getListSegmentOfStreet(Long streetId) {
		District district = districtRepository.findById(streetId).get();
		List<SegmentOfStreet> list = district.getListSegmentOfStreet();
		return list;
	}

}
