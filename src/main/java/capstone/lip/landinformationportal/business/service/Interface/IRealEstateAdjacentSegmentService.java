/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.common.entity.compositekey.RealEstateAdjacentSegmentId;

/**
 *
 * @author Admin
 */
public interface IRealEstateAdjacentSegmentService {
    
    RealEstateAdjacentSegment save(RealEstateAdjacentSegment realEstateAdjacentSegment);
    
    boolean delete(List<RealEstateAdjacentSegment> listRealEstateAdjacentSegment);
    
    List<RealEstateAdjacentSegment> save(List<RealEstateAdjacentSegment> listReoAdjacentSegment);
}
