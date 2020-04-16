/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.dto.LandFeatureValue;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsDetailId;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.validation.RealEstateValidation;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LandService implements ILandService {

    @Autowired
    private LandRepository landRepository;

    @Override
    public Land save(Land land) {
    	try {
    		return landRepository.save(land);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public boolean delete(Land land) {
    	try {
    		landRepository.delete(land);
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
    }

    
}
