package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.Street;

public interface IStreetService {
	Street save(Street street);
	boolean delete(List<Street> listStreet);
	boolean delete(Street street);
	Street findById(Long id);
	
	List<Street> findStreetByDistrictId(Long districtId);
}
