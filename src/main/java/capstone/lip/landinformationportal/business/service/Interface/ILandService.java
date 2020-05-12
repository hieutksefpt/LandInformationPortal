/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service.Interface;

import capstone.lip.landinformationportal.common.entity.Land;

/**
 *
 * @author Admin
 */
public interface ILandService {

    Land save(Land land);
    
    boolean delete(Land land);

}
