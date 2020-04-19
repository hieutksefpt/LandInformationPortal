/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.service.Interface.ILandsFeatureService;
import capstone.lip.landinformationportal.validation.LandsFeatureValidation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LandsFeatureService implements ILandsFeatureService {

    @Autowired
    private LandsFeatureRepository landsFeatureRepository;

    @Autowired
    private ILandsDetailService landsDetailService;

    @Override
    public List<LandsFeature> findAll() {
        try {
            return landsFeatureRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LandsFeature save(LandsFeature landsFeature) {
        try {
        	LandsFeatureValidation validate = new LandsFeatureValidation();
        	String error = validate.isValidLandsFeature(landsFeature);
        	if (!error.isEmpty()) {
        		throw new Exception(error);
        	}
            return landsFeatureRepository.save(landsFeature);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Long landsFeatureId) {
        try {
            LandsFeature landsFeature = landsFeatureRepository.findById(landsFeatureId).get();
            List<LandsDetail> list = landsFeature.getListLandsDetail();
            landsDetailService.delete(list);
            landsFeatureRepository.deleteById(landsFeatureId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
