package capstone.lip.landinformationportal.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.repository.ProvinceRepository;

@Service
public class ProvinceService implements IProvinceService{
	@Autowired 
	ProvinceRepository provinceRepository;
	
//	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
	
	public List<Province> findAll(){
		return provinceRepository.findAll();
	}
	
	public void save(Province province) {
		provinceRepository.save(province);
	}
	
	public void delete(Long id) {
		provinceRepository.deleteById(id);
	}
	
	@Override
	public List<District> getListDistrictByProvinceId(Long provinceId) {
		Province province = provinceRepository.findById(provinceId).get();
		List<District> list = province.getListDistrict();
		return list;
	}

}
