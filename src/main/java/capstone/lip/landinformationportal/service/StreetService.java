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
		try {
			return streetRepository.save(street);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(List<Street> listStreet) {
		try {
			streetRepository.deleteInBatch(listStreet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean delete(Street street) {
		try {
			streetRepository.delete(street);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

    @Override
    public List<Street> findAll() {
    	try {
    		return streetRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

}
