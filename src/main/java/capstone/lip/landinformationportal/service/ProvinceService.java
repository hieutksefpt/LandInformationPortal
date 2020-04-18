package capstone.lip.landinformationportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.repository.ProvinceRepository;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;

@Service
public class ProvinceService implements IProvinceService{
	
	@Autowired 
	private ProvinceRepository provinceRepository;

	@Autowired
	private IDistrictService districtService;
	
	public List<Province> findAll(){
		try {
			return provinceRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Province save(Province province) {
		
		try {
			return provinceRepository.save(province);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Province province) {
		
		try {
			
			if (province == null) throw new Exception("Province is null");
			List<District> listDistrict = province.getListDistrict();
			
			districtService.delete(listDistrict);
			
			provinceRepository.delete(province);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Province findById(Long id) {
		try {
			Optional<Province> province = provinceRepository.findById(id);
			if (province.isPresent()) {
				return province.get();
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
