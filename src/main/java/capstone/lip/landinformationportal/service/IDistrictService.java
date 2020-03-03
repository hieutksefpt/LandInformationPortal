package capstone.lip.landinformationportal.service;

import java.util.List;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;

public interface IDistrictService {
	public List<District> findAll();
	public void save(District district);
	public List<SegmentOfStreet> getListSegmentOfStreet(Long streetId);
}
