package capstone.lip.landinformationportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.repository.FormedCoordinateRepository;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.validation.FormedCoordinateValidation;

@Service
public class FormedCoordinateService implements IFormedCoordinate{

	@Autowired
	FormedCoordinateRepository formerCoordinateRepository;
	
	@Override
	public FormedCoordinate save(FormedCoordinate formedCoordinate) {
		
		try {
			if (formedCoordinate == null) {
				throw new Exception("null");
			}
			FormedCoordinateValidation validate = new FormedCoordinateValidation();
			String error = validate.isValidFormedCoordinate(formedCoordinate);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			return formerCoordinateRepository.save(formedCoordinate);  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FormedCoordinate> saveAll(List<FormedCoordinate> listFormedCoordinate) {
		try {
			if (listFormedCoordinate == null) throw new Exception("null");
			if (listFormedCoordinate.isEmpty()) throw new Exception("empty");
			for (FormedCoordinate element: listFormedCoordinate) {
				if (element.getFormedCoordinateId()==null || findById(element.getFormedCoordinateId())==null) {
					throw new Exception("Coordinate not found");
				}
			}
			return formerCoordinateRepository.saveAll(listFormedCoordinate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(List<FormedCoordinate> listCoordinate) {
		try {
			if (listCoordinate == null) throw new Exception("List coordinate is null");
			if (listCoordinate.isEmpty()) throw new Exception("List coordinate is empty");
			for (FormedCoordinate element: listCoordinate) {
				if (element.getFormedCoordinateId()==null || findById(element.getFormedCoordinateId())==null) {
					throw new Exception("Coordinate not found");
				}
			}
			formerCoordinateRepository.deleteAll(listCoordinate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public FormedCoordinate findById(Long id) {
		try {
			Optional<FormedCoordinate> coordinate = formerCoordinateRepository.findById(id);
			if (coordinate.isPresent()) {
				return coordinate.get();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
