package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;

@Service
public class DistrictService implements IDistrictService{
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public District save(District district) {
		try {
			return districtRepository.save(district);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(List<District> listDistrict) {
		try {
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
			districtRepository.delete(district);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
