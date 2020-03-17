/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.repository.RealEstateAdjacentSegmentRepository;
import capstone.lip.landinformationportal.service.Interface.IRealEstateAdjacentSegmentService;
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
    public List<RealEstateAdjacentSegment> findAll() {
        return realEstateAdjacentSegmentRepository.findAll();
    }

    @Override
    public RealEstateAdjacentSegment save(RealEstateAdjacentSegment realEstateAdjacentSegment) {
        return realEstateAdjacentSegmentRepository.save(realEstateAdjacentSegment);
    }

    @Override
    public void delete(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment) {
        realEstateAdjacentSegmentRepository.deleteInBatch(listRealEstateAdjacentSegment);
    }

    @Override
    public void deleteById(Long realEstateAdjacentSegmentId) {
        realEstateAdjacentSegmentRepository.deleteById(realEstateAdjacentSegmentId);
    }

	@Override
	public List<RealEstateAdjacentSegment> save(List<RealEstateAdjacentSegment> listReoAdjacentSegment) {
		return realEstateAdjacentSegmentRepository.saveAll(listReoAdjacentSegment);
	}

}
