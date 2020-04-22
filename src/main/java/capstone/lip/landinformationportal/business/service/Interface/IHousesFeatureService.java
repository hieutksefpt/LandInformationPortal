/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.entity.HousesFeature;

/**
 *
 * @author Admin
 */
public interface IHousesFeatureService {
        public List<HousesFeature> findAll();

        public HousesFeature save(HousesFeature housesfeature);
        
        public boolean delete(Long housesfeatureID);
}
