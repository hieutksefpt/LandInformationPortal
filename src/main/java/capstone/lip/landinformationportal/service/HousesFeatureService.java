/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.service.Interface.IHousesFeatureService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HousesFeatureService implements IHousesFeatureService{
    
    @Autowired
    private HousesFeatureRepository houseFeatureRepository;

    @Override
    public List<HousesFeature> findAll() {
        return houseFeatureRepository.findAll();
    }

    @Override
    public HousesFeature save(HousesFeature housesfeature) {
        return houseFeatureRepository.save(housesfeature);
    }

    @Override
    public void delete(Long housesfeatureID) {
        houseFeatureRepository.deleteById(housesfeatureID);
    }
    
}
