package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.District;

public interface IDistrictService {
	District save(District district);
    boolean delete(List<District> listDistrict);
	boolean delete(District district);
	District findById(Long id);
}
