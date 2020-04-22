/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service;

import capstone.lip.landinformationportal.business.repository.RealEstateAdjacentSegmentRepository;
import capstone.lip.landinformationportal.business.repository.RealEstateRepository;
import capstone.lip.landinformationportal.business.repository.SegmentOfStreetRepository;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;

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
    @Autowired
    private RealEstateRepository realEstateRepoditory;
    @Autowired
    private SegmentOfStreetRepository segmentOfStreetRepository;
    
    

    @Override
    public boolean delete(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment) {
    	try {
    		if (listRealEstateAdjacentSegment == null) throw new Exception("null");
    		if (listRealEstateAdjacentSegment.isEmpty()) throw new Exception("empty");
    		for (RealEstateAdjacentSegment element: listRealEstateAdjacentSegment) {
    			if (realEstateAdjacentSegmentRepository.findByIdSegmentOfStreetIdAndIdRealEstateId(
    					element.getId().getSegmentOfStreetId(), element.getId().getRealEstateId())==null) {
    				throw new Exception("Id not found");
    			}
    		}
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
    		if (listReoAdjacentSegment == null) throw new Exception("null");
    		if (listReoAdjacentSegment.isEmpty()) throw new Exception("empty");
    		for (RealEstateAdjacentSegment element: listReoAdjacentSegment) {
    			if (!realEstateRepoditory.existsById(element.getId().getRealEstateId())) {
    				throw new Exception("Realestate id not found");
    			}
    			if (!segmentOfStreetRepository.existsById(element.getId().getSegmentOfStreetId())) {
    				throw new Exception("Segment id not found");
    			}
    		}
    		return realEstateAdjacentSegmentRepository.saveAll(listReoAdjacentSegment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public RealEstateAdjacentSegment save(RealEstateAdjacentSegment realEstateAdjacentSegment) {
    	try {
    		if (!realEstateRepoditory.existsById(realEstateAdjacentSegment.getId().getRealEstateId())) {
				throw new Exception("Realestate id not found");
			}
			if (!segmentOfStreetRepository.existsById(realEstateAdjacentSegment.getId().getSegmentOfStreetId())) {
				throw new Exception("Segment id not found");
			}
    		return realEstateAdjacentSegmentRepository.save(realEstateAdjacentSegment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

}
