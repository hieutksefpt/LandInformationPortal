/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HousesDetailService implements IHousesDetailService {

    @Autowired
    private HousesDetailRepository housesDetailRepository;

    @Override
    public HousesDetail save(HousesDetail housesDetail) {
    	try {
    		return housesDetailRepository.save(housesDetail); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public boolean delete(List<HousesDetail> listHousesDetail) {
    	try {
    		housesDetailRepository.deleteAll(listHousesDetail);
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
    }

}
