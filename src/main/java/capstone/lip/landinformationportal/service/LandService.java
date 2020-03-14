/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LandService implements ILandService{
    @Autowired
    private LandRepository landRepository;

    @Override
    public List<Land> findAll() {
        return landRepository.findAll();
    }

    @Override
    public Land save(Land land) {
        return landRepository.save(land);
    }

    @Override
    public void delete(Land land) {
        landRepository.delete(land);
    }

    @Override
    public void deleteById(Long landId) {
        landRepository.deleteById(landId);
    }
    
    @Override
    public List<LandsFeature> getListLandsFeature(Long landId) {
        Land land = landRepository.findById(landId).get();
        List<LandsFeature> listLandsFeature = land.getListLandsFeatures();
        return listLandsFeature;
    }
}
