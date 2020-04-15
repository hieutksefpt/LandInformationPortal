package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;

public interface IProvinceService {
	List<Province> findAll();
	Province save(Province province);
	boolean delete(Province province);
}
