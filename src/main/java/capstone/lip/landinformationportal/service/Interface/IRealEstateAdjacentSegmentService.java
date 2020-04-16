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
    
    RealEstateAdjacentSegment save(RealEstateAdjacentSegment realEstateAdjacentSegment);
    
    boolean delete(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment);
    
    List<RealEstateAdjacentSegment> save(List<RealEstateAdjacentSegment> listReoAdjacentSegment);
}
