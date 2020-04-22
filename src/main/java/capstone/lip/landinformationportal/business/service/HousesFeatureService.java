/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service;

import capstone.lip.landinformationportal.business.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.business.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.business.service.Interface.IHousesFeatureService;
import capstone.lip.landinformationportal.business.validation.HousesFeatureValidation;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.HousesFeature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HousesFeatureService implements IHousesFeatureService {

    @Autowired
    private HousesFeatureRepository houseFeatureRepository;

    @Autowired
    private IHousesDetailService housesDetailService;

    @Override
    public List<HousesFeature> findAll() {
        try {
            return houseFeatureRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public HousesFeature save(HousesFeature housesfeature) {
        try {
        	HousesFeatureValidation validate = new HousesFeatureValidation();
        	String error = validate.isValidHousesFeature(housesfeature);
        	if (!error.isEmpty()) {
        		throw new Exception(error);
        	}
            return houseFeatureRepository.save(housesfeature);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean delete(Long housesfeatureID) {
        try {
            HousesFeature housesFeature = houseFeatureRepository.findById(housesfeatureID).get();
            List<HousesDetail> list = housesFeature.getListHousesDetail();
            housesDetailService.delete(list);
            houseFeatureRepository.deleteById(housesfeatureID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
