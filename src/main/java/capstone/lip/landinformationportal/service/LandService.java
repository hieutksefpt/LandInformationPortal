/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.dto.LandFeatureValue;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsDetailId;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.validation.RealEstateValidation;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LandService implements ILandService {

    @Autowired
    private LandRepository landRepository;

    @Autowired
    private LandsDetailRepository landsDetailRepository;

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
        List<LandsDetail> listLandDetail = land.getListLandsDetail();
        List<LandsFeature> listLandsFeature = new ArrayList<LandsFeature>();
        for (LandsDetail landDetail : listLandDetail) {
            listLandsFeature.add(landDetail.getLandsFeature());
        }
        return listLandsFeature;
    }

    @Override
    public Land saveLandInfor(RealEstate newUploadRealEstate, String newLandName, BigDecimal newLandMoney, List<LandFeatureValue> listLandFeatureValue) {
        RealEstateValidation rev = new RealEstateValidation();
        Land tempLand = new Land();
        tempLand.setLandName(newLandName);
        tempLand.setLandPrice(Double.parseDouble(newLandMoney.toString()));
        tempLand.setRealEstate(newUploadRealEstate);
        if (rev.checkLandValidation(tempLand,listLandFeatureValue)) {
            tempLand = landRepository.save(tempLand);
        } else {
            tempLand = null;
        }

        if (rev.checkLandDetailValidation(tempLand) && rev.checkLandValidation(tempLand,listLandFeatureValue)) {
            for (int i = 0; i < listLandFeatureValue.size(); i++) {
                LandsDetailId tempLDI = new LandsDetailId();
                tempLDI.setLandId(tempLand.getLandId());
                tempLDI.setLandsFeatureId(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureID());
                LandsDetail tempLD = new LandsDetail();
                tempLD.setId(tempLDI)
                        .setValue(listLandFeatureValue.get(i).getValue());
                landsDetailRepository.save(tempLD);
            }
        }

        return tempLand;
    }

}
