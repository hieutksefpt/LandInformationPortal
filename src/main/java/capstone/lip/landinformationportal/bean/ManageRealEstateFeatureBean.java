/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.service.IHousesFeatureService;
import capstone.lip.landinformationportal.service.ILandsFetureService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
public class ManageRealEstateFeatureBean {

    @Autowired
    private ILandsFetureService landsfeatureService;

    @Autowired
    private IHousesFeatureService housesfeatureService;

    private List<LandsFeature> listLandsFeature;
    private List<HousesFeature> listHousesFeature;
    private String processType;

    @PostConstruct
    public void init() {
        processType = "1";
        listLandsFeature = new ArrayList<LandsFeature>();
        listLandsFeature = landsfeatureService.findAll();
        listHousesFeature = new ArrayList<HousesFeature>();
        listHousesFeature = housesfeatureService.findAll();
    }
    
    
    
    public void saveLandsFeature(String landsFeatureName, String landsFeatureUnit){
        LandsFeature landfeature = new LandsFeature(landsFeatureName,landsFeatureUnit);
        landsfeatureService.save(landfeature);
    }
    
    public void saveHousesFeature(String housesFeatureName, String housesFeatureUnit){
        HousesFeature housesfeature = new HousesFeature(housesFeatureName, housesFeatureUnit);
        housesfeatureService.save(housesfeature);
    }
    
    public void deleteLandsFeature(Long landfeatureID){
        landsfeatureService.delete(landfeatureID);
    }
    
    public void deleteHousesFeature(Long housesfeatureID){
        housesfeatureService.delete(housesfeatureID);
    }
    
    

    public List<LandsFeature> getListLandsFeature() {
        return listLandsFeature;
    }

    public void setListLandsFeature(List<LandsFeature> listLandsFeature) {
        this.listLandsFeature = listLandsFeature;
    }

    public List<HousesFeature> getListHousesFeature() {
        return listHousesFeature;
    }

    public void setListHousesFeature(List<HousesFeature> listHousesFeature) {
        this.listHousesFeature = listHousesFeature;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

}
