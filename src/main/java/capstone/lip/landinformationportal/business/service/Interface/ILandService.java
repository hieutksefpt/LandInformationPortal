/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service.Interface;

import capstone.lip.landinformationportal.common.dto.LandFeatureValue;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.LandsDetail;
import capstone.lip.landinformationportal.common.entity.LandsFeature;
import capstone.lip.landinformationportal.common.entity.RealEstate;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ILandService {

    Land save(Land land);
    
    boolean delete(Land land);

}
