/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HousesFeatureService implements IHousesFeatureService{
    
    private HousesFeatureRepository housefeatureRepository;

    @Override
    public List<HousesFeature> findAll() {
        return housefeatureRepository.findAll();
    }

    @Override
    public void save(HousesFeature housesfeature) {
        housefeatureRepository.save(housesfeature);
    }

    @Override
    public void delete(Long housesfeatureID) {
        housefeatureRepository.deleteById(housesfeatureID);
    }
    
}
