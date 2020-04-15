package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.Street;

public interface IStreetService {
	Street save(Street street);
	boolean delete(List<Street> listStreet);
	boolean delete(Street street);
}
