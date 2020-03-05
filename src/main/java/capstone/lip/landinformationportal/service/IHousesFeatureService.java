/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.LandsFeature;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHousesFeatureService {
        public List<HousesFeature> findAll();

        public void save(HousesFeature housesfeature);
        
        public void delete(Long housesfeatureID);
}
