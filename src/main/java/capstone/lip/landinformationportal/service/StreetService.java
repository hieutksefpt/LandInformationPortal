package capstone.lip.landinformationportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.repository.StreetRepository;
import capstone.lip.landinformationportal.service.Interface.IStreetService;

@Service
public class StreetService implements IStreetService{
	@Autowired
	StreetRepository streetRepository;
	
	@Override
	public Street save(Street street) {
		return streetRepository.save(street);
	}

	@Override
	public void delete(List<Street> listStreet) {
		streetRepository.deleteInBatch(listStreet);
		
	}

}
