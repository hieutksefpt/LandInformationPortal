package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;

public interface IProvinceService {
	List<Province> findAll();
	Province save(Province province);
	void deleteById(Long id);
	List<District> getListDistrictByProvinceId(Long provinceId);
	void delete(Province province);
}
