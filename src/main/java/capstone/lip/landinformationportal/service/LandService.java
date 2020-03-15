/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
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
    
    @Autowired
    private RealEstateRepository realEstateRepository;

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
    public Land findByRealEstateId(long realEstateId) {
        RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
        return realEstate.getLand();
    }

}
