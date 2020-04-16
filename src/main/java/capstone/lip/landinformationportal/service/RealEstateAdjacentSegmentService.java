/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.compositekey.RealEstateAdjacentSegmentId;
import capstone.lip.landinformationportal.repository.RealEstateAdjacentSegmentRepository;
import capstone.lip.landinformationportal.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.validation.RealEstateValidation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RealEstateAdjacentSegmentService implements IRealEstateAdjacentSegmentService {

    @Autowired
    private RealEstateAdjacentSegmentRepository realEstateAdjacentSegmentRepository;

    

    @Override
    public boolean delete(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment) {
    	try {
    		realEstateAdjacentSegmentRepository.deleteAll(listRealEstateAdjacentSegment);
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
    }

    @Override
    public List<RealEstateAdjacentSegment> save(List<RealEstateAdjacentSegment> listReoAdjacentSegment) {
    	try {
    		return realEstateAdjacentSegmentRepository.saveAll(listReoAdjacentSegment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public RealEstateAdjacentSegment save(RealEstateAdjacentSegment realEstateAdjacentSegment) {
    	try {
    		return realEstateAdjacentSegmentRepository.save(realEstateAdjacentSegment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

}
