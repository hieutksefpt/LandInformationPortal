/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.bean;

import capstone.lip.landinformationportal.business.service.Interface.IHousesFeatureService;
import capstone.lip.landinformationportal.business.service.Interface.ILandsFeatureService;
import capstone.lip.landinformationportal.common.entity.HousesFeature;
import capstone.lip.landinformationportal.common.entity.LandsFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class ManageRealEstateFeatureBean implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private String landsFeatureDataType = "STR";
    private String housesFeatureDataType = "STR";
    private String tempLandsFeatureDataRange;
    private String tempHousesFeatureDataRange;
    private List<String> landsFeatureDataRange;
    private List<String> housesFeatureDataRange;

    private LandsFeature landsFeatureClicked;
    private HousesFeature housesFeatureClicked;

    @PostConstruct
    public void init() {
        processType = "1";
        listLandsFeature = new ArrayList<LandsFeature>();
        listLandsFeature = landsFeatureService.findAll();
        listHousesFeature = new ArrayList<HousesFeature>();
        listHousesFeature = housesFeatureService.findAll();
    }

    public void showAlertLandsFeatureName() {
        PrimeFaces.current().executeScript("showAlertLandsFeatureName()");
    }

    public void showAlertHousesFeatureName() {
        PrimeFaces.current().executeScript("showAlertHousesFeatureName()");
    }

    public void hideEditPopup() {
        PrimeFaces.current().executeScript("hideEditPopup()");
    }

    public void hideEditPopup2() {
        PrimeFaces.current().executeScript("hideEditPopup2()");
    }

    public void hideAddFeaturePopup() {
        PrimeFaces.current().executeScript("hideAddFeaturePopup()");
    }

    public void showAlertOnlyNumber() {
        PrimeFaces.current().executeScript("showAlertOnlyNumber()");
    }

    public List<String> splitAndTrimStringToList(String str) {
        List<String> list = Arrays.asList(str.split("\\|", 0));
        List<String> afterTrim = new ArrayList<>();
        for (String temp : list) {
            afterTrim.add(temp.trim());
        }
        return afterTrim;
    }

    private boolean landsFeatureCheck = true;
    private boolean housesFeatureCheck = true;

    public void saveLandsFeature() {
        landsFeatureCheck = true;
        if (landsFeatureName.isEmpty() || landsFeatureName == null) {
            landsFeatureCheck = false;
            showAlertLandsFeatureName();
        }
        if (landsFeatureDataType == null || landsFeatureDataType.isEmpty()) {
            landsFeatureDataType = "STR";
        }
        if (!tempLandsFeatureDataRange.isEmpty() && tempLandsFeatureDataRange != null) {
            landsFeatureDataRange = splitAndTrimStringToList(tempLandsFeatureDataRange);
            if (landsFeatureDataType.equals("INT")) {
                for (String lfdr : landsFeatureDataRange) {
                    lfdr = lfdr.replaceAll(" ", "");
                    try {
                        Long.parseLong(lfdr);
                    } catch (Exception e) {
                        landsFeatureCheck = false;
                        showAlertOnlyNumber();
                        return;
                    }
                }
            }
        }
        if (landsFeatureCheck) {
            LandsFeature landfeature = new LandsFeature(landsFeatureName, landsFeatureUnit, landsFeatureDataType, landsFeatureDataRange);
            landfeature = landsFeatureService.save(landfeature);
            listLandsFeature = landsFeatureService.findAll();
            hideAddFeaturePopup();
        }
    }

    public void saveHousesFeature() {
        housesFeatureCheck = true;
        if (housesFeatureName.isEmpty() || housesFeatureName == null) {
            housesFeatureCheck = false;
            showAlertHousesFeatureName();
        }
        if (housesFeatureDataType == null || housesFeatureDataType.isEmpty()) {
            housesFeatureDataType = "STR";
        }
        if (!tempHousesFeatureDataRange.isEmpty() && tempHousesFeatureDataRange != null) {
            housesFeatureDataRange = splitAndTrimStringToList(tempHousesFeatureDataRange);
            if (housesFeatureDataType.equals("INT")) {
                for (String hfdr : housesFeatureDataRange) {
                    hfdr = hfdr.replaceAll(" ", "");
                    try {
                        Long.parseLong(hfdr);
                    } catch (Exception e) {
                        housesFeatureCheck = false;
                        showAlertOnlyNumber();
                        return;
                    }
                }
            }
        }
        if (housesFeatureCheck) {
            HousesFeature housesFeature = new HousesFeature(housesFeatureName, housesFeatureUnit, housesFeatureDataType, housesFeatureDataRange);
            housesFeature = housesFeatureService.save(housesFeature);
            listHousesFeature = housesFeatureService.findAll();
            hideAddFeaturePopup();
        }
    }

    public void updateLandsFeature() {
        landsFeatureCheck = true;
        if (landsFeatureNameClicked.isEmpty() || landsFeatureNameClicked == null) {
            landsFeatureCheck = false;
            showAlertLandsFeatureName();
        }
        if (landsFeatureDataTypeClicked == null || landsFeatureDataTypeClicked.isEmpty()) {
            landsFeatureDataTypeClicked = "STR";
        }
        if (!tempLandsFeatureDataRangeClicked.isEmpty() && tempLandsFeatureDataRangeClicked != null) {
            landsFeatureDataRangeClicked = splitAndTrimStringToList(tempLandsFeatureDataRangeClicked);
            if (landsFeatureDataTypeClicked.equals("INT")) {
                for (String lfdr : landsFeatureDataRangeClicked) {
                    lfdr = lfdr.replaceAll(" ", "");
                    try {
                        Long.parseLong(lfdr);
                    } catch (Exception e) {
                        landsFeatureCheck = false;
                        showAlertOnlyNumber();
                        return;
                    }
                }
            }
        }
        if (landsFeatureCheck) {
            landsFeatureClicked.setLandsFeatureName(landsFeatureNameClicked);
            landsFeatureClicked.setLandsFeatureUnit(landsFeatureUnitClicked);
            landsFeatureClicked.setLandsFeatureDataType(landsFeatureDataTypeClicked);
            landsFeatureClicked.setLandsFeatureDataRange(landsFeatureDataRangeClicked);

            landsFeatureService.save(landsFeatureClicked);
            listLandsFeature = landsFeatureService.findAll();
            hideEditPopup();
        }
    }

    public void updateHousesFeature() {
        housesFeatureCheck = true;
        if (housesFeatureNameClicked.isEmpty() || housesFeatureNameClicked == null) {
            housesFeatureCheck = false;
            showAlertHousesFeatureName();
        }
        if (housesFeatureDataTypeClicked == null || housesFeatureDataTypeClicked.isEmpty()) {
            housesFeatureDataTypeClicked = "STR";
        }
        if (!tempHousesFeatureDataRangeClicked.isEmpty() && tempHousesFeatureDataRangeClicked != null) {
            housesFeatureDataRangeClicked = splitAndTrimStringToList(tempHousesFeatureDataRangeClicked);
            if (housesFeatureDataTypeClicked.equals("INT")) {
                for (String hfdr : housesFeatureDataRangeClicked) {
                    hfdr = hfdr.replaceAll(" ", "");
                    try {
                        Long.parseLong(hfdr);
                    } catch (Exception e) {
                        housesFeatureCheck = false;
                        showAlertOnlyNumber();
                        return;
                    }
                }
            }
        }
        if (housesFeatureCheck) {
            housesFeatureClicked.setHousesFeatureName(housesFeatureNameClicked);
            housesFeatureClicked.setHousesFeatureUnit(housesFeatureUnitClicked);
            housesFeatureClicked.setHousesFeatureDataType(housesFeatureDataTypeClicked);
            housesFeatureClicked.setHousesFeatureDataRange(housesFeatureDataRangeClicked);

            housesFeatureService.save(housesFeatureClicked);
            listHousesFeature = housesFeatureService.findAll();
            hideEditPopup2();
        }
    }

    private String landsFeatureNameClicked;
    private String landsFeatureUnitClicked;
    private String landsFeatureDataTypeClicked;
    private List<String> landsFeatureDataRangeClicked;
    private String tempLandsFeatureDataRangeClicked = "";

    public void clickOnButtonRowLandsFeature(String id) {
        landsFeatureClicked = listLandsFeature.stream().filter(x -> x.getLandsFeatureID().equals(Long.parseLong(id))).collect(Collectors.toList()).get(0);
        landsFeatureNameClicked = landsFeatureClicked.getLandsFeatureName();
        landsFeatureUnitClicked = landsFeatureClicked.getLandsFeatureUnit();
        landsFeatureDataTypeClicked = landsFeatureClicked.getLandsFeatureDataType();
        landsFeatureDataRangeClicked = landsFeatureClicked.getLandsFeatureDataRange();
        if (landsFeatureDataRangeClicked != null) {
            if (!landsFeatureDataRangeClicked.isEmpty()) {
                tempLandsFeatureDataRangeClicked = "";
                for (String str : landsFeatureDataRangeClicked) {
                    tempLandsFeatureDataRangeClicked = tempLandsFeatureDataRangeClicked + "|" + str;
                }
                tempLandsFeatureDataRangeClicked = tempLandsFeatureDataRangeClicked.substring(1, tempLandsFeatureDataRangeClicked.length());
            } else {
                tempLandsFeatureDataRangeClicked = "";
            }
        } else {
            tempLandsFeatureDataRangeClicked = "";
        }
    }

    private String housesFeatureNameClicked;
    private String housesFeatureUnitClicked;
    private String housesFeatureDataTypeClicked;
    private List<String> housesFeatureDataRangeClicked;
    private String tempHousesFeatureDataRangeClicked;

    public void clickOnButtonRowHousesFeature(String id) {
        housesFeatureClicked = listHousesFeature.stream().filter(x -> x.getHousesFeatureID().equals(Long.parseLong(id))).collect(Collectors.toList()).get(0);
        housesFeatureNameClicked = housesFeatureClicked.getHousesFeatureName();
        housesFeatureUnitClicked = housesFeatureClicked.getHousesFeatureUnit();
        housesFeatureDataTypeClicked = housesFeatureClicked.getHousesFeatureDataType();
        housesFeatureDataRangeClicked = housesFeatureClicked.getHousesFeatureDataRange();
        if (housesFeatureDataRangeClicked != null) {
            if (!housesFeatureDataRangeClicked.isEmpty()) {
                tempHousesFeatureDataRangeClicked = "";
                for (String str : housesFeatureDataRangeClicked) {
                    tempHousesFeatureDataRangeClicked = tempHousesFeatureDataRangeClicked + "|" + str;
                }
                tempHousesFeatureDataRangeClicked = tempHousesFeatureDataRangeClicked.substring(1, tempHousesFeatureDataRangeClicked.length());
            } else {
                tempHousesFeatureDataRangeClicked = "";
            }
        } else {
            tempHousesFeatureDataRangeClicked = "";
        }
    }

    public void deleteLandsFeature() {
        landsFeatureService.delete(landsFeatureClicked.getLandsFeatureID());
        listLandsFeature = landsFeatureService.findAll();
    }

    public void deleteHousesFeature() {
        housesFeatureService.delete(housesFeatureClicked.getHousesFeatureID());
        listHousesFeature = housesFeatureService.findAll();
    }

    public String getLandsFeatureDataType() {
        return landsFeatureDataType;
    }

    public void setLandsFeatureDataType(String landsFeatureDataType) {
        this.landsFeatureDataType = landsFeatureDataType;
    }

    public String getHousesFeatureDataType() {
        return housesFeatureDataType;
    }

    public void setHousesFeatureDataType(String housesFeatureDataType) {
        this.housesFeatureDataType = housesFeatureDataType;
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

    public HousesFeature getHousesFeatureClicked() {
        return housesFeatureClicked;
    }

    public void setHousesFeatureClicked(HousesFeature housesFeatureClicked) {
        this.housesFeatureClicked = housesFeatureClicked;
    }

    public String getLandsFeatureNameClicked() {
        return landsFeatureNameClicked;
    }

    public void setLandsFeatureNameClicked(String landsFeatureNameClicked) {
        this.landsFeatureNameClicked = landsFeatureNameClicked;
    }

    public String getLandsFeatureUnitClicked() {
        return landsFeatureUnitClicked;
    }

    public void setLandsFeatureUnitClicked(String landsFeatureUnitClicked) {
        this.landsFeatureUnitClicked = landsFeatureUnitClicked;
    }

    public String getLandsFeatureDataTypeClicked() {
        return landsFeatureDataTypeClicked;
    }

    public void setLandsFeatureDataTypeClicked(String landsFeatureDataTypeClicked) {
        this.landsFeatureDataTypeClicked = landsFeatureDataTypeClicked;
    }

    public List<String> getLandsFeatureDataRangeClicked() {
        return landsFeatureDataRangeClicked;
    }

    public void setLandsFeatureDataRangeClicked(List<String> landsFeatureDataRangeClicked) {
        this.landsFeatureDataRangeClicked = landsFeatureDataRangeClicked;
    }

    public String getHousesFeatureNameClicked() {
        return housesFeatureNameClicked;
    }

    public void setHousesFeatureNameClicked(String housesFeatureNameClicked) {
        this.housesFeatureNameClicked = housesFeatureNameClicked;
    }

    public String getHousesFeatureUnitClicked() {
        return housesFeatureUnitClicked;
    }

    public void setHousesFeatureUnitClicked(String housesFeatureUnitClicked) {
        this.housesFeatureUnitClicked = housesFeatureUnitClicked;
    }

    public String getHousesFeatureDataTypeClicked() {
        return housesFeatureDataTypeClicked;
    }

    public void setHousesFeatureDataTypeClicked(String housesFeatureDataTypeClicked) {
        this.housesFeatureDataTypeClicked = housesFeatureDataTypeClicked;
    }

    public List<String> getHousesFeatureDataRangeClicked() {
        return housesFeatureDataRangeClicked;
    }

    public void setHousesFeatureDataRangeClicked(List<String> housesFeatureDataRangeClicked) {
        this.housesFeatureDataRangeClicked = housesFeatureDataRangeClicked;
    }

    public String getTempLandsFeatureDataRange() {
        return tempLandsFeatureDataRange;
    }

    public void setTempLandsFeatureDataRange(String tempLandsFeatureDataRange) {
        this.tempLandsFeatureDataRange = tempLandsFeatureDataRange;
    }

    public String getTempHousesFeatureDataRange() {
        return tempHousesFeatureDataRange;
    }

    public void setTempHousesFeatureDataRange(String tempHousesFeatureDataRange) {
        this.tempHousesFeatureDataRange = tempHousesFeatureDataRange;
    }

    public List<String> getLandsFeatureDataRange() {
        return landsFeatureDataRange;
    }

    public void setLandsFeatureDataRange(List<String> landsFeatureDataRange) {
        this.landsFeatureDataRange = landsFeatureDataRange;
    }

    public List<String> getHousesFeatureDataRange() {
        return housesFeatureDataRange;
    }

    public void setHousesFeatureDataRange(List<String> housesFeatureDataRange) {
        this.housesFeatureDataRange = housesFeatureDataRange;
    }

    public String getTempLandsFeatureDataRangeClicked() {
        return tempLandsFeatureDataRangeClicked;
    }

    public void setTempLandsFeatureDataRangeClicked(String tempLandsFeatureDataRangeClicked) {
        this.tempLandsFeatureDataRangeClicked = tempLandsFeatureDataRangeClicked;
    }

    public String getTempHousesFeatureDataRangeClicked() {
        return tempHousesFeatureDataRangeClicked;
    }

    public void setTempHousesFeatureDataRangeClicked(String tempHousesFeatureDataRangeClicked) {
        this.tempHousesFeatureDataRangeClicked = tempHousesFeatureDataRangeClicked;
    }

}
