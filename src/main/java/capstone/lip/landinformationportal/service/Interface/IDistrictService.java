package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;

public interface IDistrictService {
	List<District> findAll();
	District save(District district);
        void delete(List<District> listDistrict);
	List<SegmentOfStreet> getListSegmentOfStreet(Long streetId);
	
}
