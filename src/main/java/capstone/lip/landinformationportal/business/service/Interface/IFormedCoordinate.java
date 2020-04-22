package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.FormedCoordinate;

public interface IFormedCoordinate {
	FormedCoordinate save(FormedCoordinate formedCoordinate);
	List<FormedCoordinate> saveAll(List<FormedCoordinate> listFormedCoordinate);
	boolean delete(List<FormedCoordinate> listCoordinate);
	FormedCoordinate findById(Long id);
}
