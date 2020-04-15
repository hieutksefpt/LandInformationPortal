package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.repository.FormedCoordinateRepository;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;

@Service
public class FormedCoordinateService implements IFormedCoordinate{

	@Autowired
	FormedCoordinateRepository formerCoordinateRepository;
	
	@Override
	public FormedCoordinate save(FormedCoordinate formedCoordinate) {
		
		try {
			return formerCoordinateRepository.save(formedCoordinate);  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FormedCoordinate> saveAll(List<FormedCoordinate> listFormedCoordinate) {
		try {
			return formerCoordinateRepository.saveAll(listFormedCoordinate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(List<FormedCoordinate> listCoordinate) {
		try {
			formerCoordinateRepository.deleteAll(listCoordinate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
