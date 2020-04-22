package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.Province;

public interface IProvinceService {
	List<Province> findAll();
	Province findById(Long id);
	Province save(Province province);
	boolean delete(Province province);
	
}
