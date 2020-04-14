package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.entity.FormedCoordinate;

public interface IFormedCoordinate {
	FormedCoordinate save(FormedCoordinate formedCoordinate);
	List<FormedCoordinate> saveAll(List<FormedCoordinate> listFormedCoordinate);
	boolean delete(List<FormedCoordinate> listCoordinate);
	
}
