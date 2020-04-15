package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.repository.ProvinceRepository;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;

@Service
public class ProvinceService implements IProvinceService{
	
	@Autowired 
	ProvinceRepository provinceRepository;

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
			provinceRepository.delete(province);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
