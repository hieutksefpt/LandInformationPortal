/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegmentId;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IRealEstateAdjacentSegmentService {
    List<RealEstateAdjacentSegment> findAll();

    RealEstateAdjacentSegment save(RealEstate newUploadRealEstate, RealEstateAdjacentSegmentId realEstateAdjacentSegmentId);
    
    RealEstateAdjacentSegment save(RealEstateAdjacentSegment realEstateAdjacentSegment);
    void delete(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment);

    void deleteById(Long realEstateAdjacentSegmentId);
    
    List<RealEstateAdjacentSegment> save(List<RealEstateAdjacentSegment> listReoAdjacentSegment);
}
