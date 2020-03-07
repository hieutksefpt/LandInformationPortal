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
		return formerCoordinateRepository.save(formedCoordinate);  
	}

	@Override
	public List<FormedCoordinate> saveAll(List<FormedCoordinate> listFormedCoordinate) {
		return formerCoordinateRepository.saveAll(listFormedCoordinate);
	}

	@Override
	public void delete(List<FormedCoordinate> listCoordinate) {
		formerCoordinateRepository.deleteInBatch(listCoordinate);
	}
	
	

}
