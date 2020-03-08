/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.service.Interface.IHousesFeatureService;
import capstone.lip.landinformationportal.service.Interface.ILandsFeatureService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */

@Named
@ViewScoped
public class ManageRealEstateFeatureBean implements Serializable{

    @Autowired
    private ILandsFeatureService landsFeatureService;

    @Autowired
    private IHousesFeatureService housesFeatureService;

    private List<LandsFeature> listLandsFeature;
    private List<HousesFeature> listHousesFeature;
    private String processType;
    
    private String housesFeatureName;
    private String landsFeatureName;
    private String housesFeatureUnit;
    private String landsFeatureUnit;
    private Long landfeatureID;
    private Long housefeatureID;
    
    private LandsFeature landsFeatureClicked;
   

    @PostConstruct
    public void init() {
        processType = "1";
        listLandsFeature = new ArrayList<LandsFeature>();
        listLandsFeature = landsFeatureService.findAll();
        listHousesFeature = new ArrayList<HousesFeature>();
        listHousesFeature = housesFeatureService.findAll();
    }
    
    
    
    public void saveLandsFeature(){
        LandsFeature landfeature = new LandsFeature(landsFeatureName,landsFeatureUnit);
        landfeature = landsFeatureService.save(landfeature);
        listLandsFeature.add(landfeature);
    }
    public void updateLandsFeature() {
    	landsFeatureService.save(landsFeatureClicked);
    	listLandsFeature = landsFeatureService.findAll();
    	int i = 1;
    	i++;
    }
    public void saveHousesFeature(){
        HousesFeature housesfeature = new HousesFeature(housesFeatureName, housesFeatureUnit);
        housesFeatureService.save(housesfeature);
    }
    
    public void clickOnButtonRow(String id) {
    	landsFeatureClicked = listLandsFeature.stream().filter(x->x.getLandsFeatureID().equals(Long.parseLong(id))).collect(Collectors.toList()).get(0);
    }
    
    public void deleteLandsFeature(){
        landsFeatureService.delete(landsFeatureClicked.getLandsFeatureID());
        listLandsFeature = landsFeatureService.findAll();
    }
    
    public void deleteHousesFeature(){
        housesFeatureService.delete(housefeatureID);
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

	public String getHousesFeatureName() {
		return housesFeatureName;
	}

	public void setHousesFeatureName(String housesFeatureName) {
		this.housesFeatureName = housesFeatureName;
	}

	public String getLandsFeatureName() {
		return landsFeatureName;
	}

	public void setLandsFeatureName(String landsFeatureName) {
		this.landsFeatureName = landsFeatureName;
	}

	public String getHousesFeatureUnit() {
		return housesFeatureUnit;
	}

	public void setHousesFeatureUnit(String housesFeatureUnit) {
		this.housesFeatureUnit = housesFeatureUnit;
	}

	public String getLandsFeatureUnit() {
		return landsFeatureUnit;
	}

	public void setLandsFeatureUnit(String landsFeatureUnit) {
		this.landsFeatureUnit = landsFeatureUnit;
	}

	public Long getLandfeatureID() {
		return landfeatureID;
	}

	public void setLandfeatureID(Long landfeatureID) {
		this.landfeatureID = landfeatureID;
	}

	public Long getHousefeatureID() {
		return housefeatureID;
	}

	public void setHousefeatureID(Long housefeatureID) {
		this.housefeatureID = housefeatureID;
	}

	public LandsFeature getLandsFeatureClicked() {
		return landsFeatureClicked;
	}

	public void setLandsFeatureClicked(LandsFeature landsFeatureClicked) {
		this.landsFeatureClicked = landsFeatureClicked;
	}
    
}
