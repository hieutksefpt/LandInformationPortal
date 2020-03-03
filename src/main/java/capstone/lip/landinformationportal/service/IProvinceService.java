package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;

public interface IProvinceService {
	public List<Province> findAll();
	public void save(Province province);
	public List<District> getListDistrictByProvinceId(Long provinceId);
}
