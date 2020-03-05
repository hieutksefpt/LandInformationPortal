/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
public class LandsFeatureService implements ILandsFetureService {

    @Autowired
    private LandsFeatureRepository landsfeatureRepository;

    @Override
    public List<LandsFeature> findAll() {
           return landsfeatureRepository.findAll();
    }

    @Override
    public void save(LandsFeature landsfeature) {
          landsfeatureRepository.save(landsfeature);
    }

    @Override
    public void delete(Long landsfeatureID) {
        landsfeatureRepository.deleteById(landsfeatureID);
    }

}
