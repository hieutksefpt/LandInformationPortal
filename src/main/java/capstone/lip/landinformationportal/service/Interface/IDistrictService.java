package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;

public interface IDistrictService {
	District save(District district);
    boolean delete(List<District> listDistrict);
	boolean delete(District district);
}
