/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import capstone.lip.landinformationportal.business.service.Interface.IHouseService;
import capstone.lip.landinformationportal.business.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.business.service.Interface.IHousesFeatureService;
import capstone.lip.landinformationportal.business.service.Interface.ILandService;
import capstone.lip.landinformationportal.business.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.business.service.Interface.ILandsFeatureService;
import capstone.lip.landinformationportal.business.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.business.validation.RealEstateValidation;
import capstone.lip.landinformationportal.common.constant.RealEstateTypeConstant;
import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.dto.Coordinate;
import capstone.lip.landinformationportal.common.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.common.dto.LandFeatureValue;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.HousesFeature;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.LandsDetail;
import capstone.lip.landinformationportal.common.entity.LandsFeature;
import capstone.lip.landinformationportal.common.entity.Province;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.entity.compositekey.HousesDetailId;
import capstone.lip.landinformationportal.common.entity.compositekey.LandsDetailId;
import capstone.lip.landinformationportal.common.entity.compositekey.RealEstateAdjacentSegmentId;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.context.ExternalContext;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class ContributeNewRealEstateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IRealEstateService realEstateService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ILandsDetailService landsDetailService;

    @Autowired
    private IHousesDetailService housesDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILandService landService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ILandsFeatureService landFeatureService;

    @Autowired
    private IRealEstateAdjacentSegmentService realEstateAdjacentSegmentService;

    @Autowired
    private IHousesFeatureService housesFeatureService;

    private List<Province> listProvince;
    private List<District> listDistrict;
    private List<SegmentOfStreet> listSegmentOfStreet;
    private List<Street> listStreet;

    private String provinceIdSelected;
    private String districtIdSelected;
    private String streetIdSelected;
    private String segmentStreetIdSelected;
    private String processType;
    private String nameInput;
    private String lngSingleCoordinate;
    private String latSingleCoordinate;
    private List<Coordinate> listCoordinate;
    private String jsonMultipleCoordinate;

    private String landFeatureIdSelected = "";
    private String houseFeatureIdSelected = "";
    private List<LandFeatureValue> listLandFeatureValue = new ArrayList<>();
    private List<HouseFeatureValue> listHouseFeatureValue = new ArrayList<>();

    private List<LandsFeature> listLandsFeature;
    private List<HousesFeature> listHousesFeature;
    private String newLandFeatureValue;
    private String newHouseFeatureValue;
    private String landUnit;
    private String houseUnit;
    private BigDecimal newHouseMoney;
    private BigDecimal newLandMoney;

    // variable to save New RealEstate
    private String realEstateName;
    private String provinceAddress;
    private String districtAddress;
    private String streetAddress;
    private String segmentStreetAddress;
    private String realEstateAddress;
    private Double realEstateLat;
    private Double realEstateLng;
    private BigDecimal realEstatePrice;
    private String realEstateStatus;
    private String realEstateLink;
    private String realEstateType;
    //submit data
    private BigDecimal realEstatePriceSubmit;
    private String realEstateNameSubmit;
    private String newLandName;
    private String newHouseName;
    private String checkLocationLocate;
    private String typeRealEstate;

    @PostConstruct
    public void init() {
        processType = "1";
        provinceIdSelected = "";
        districtIdSelected = "";
        streetIdSelected = "";
        segmentStreetIdSelected = "";
        listCoordinate = new ArrayList();
        listProvince = new ArrayList<Province>();
        listProvince = provinceService.findAll();
        listDistrict = new ArrayList<>();
        listSegmentOfStreet = new ArrayList<>();
        listStreet = new ArrayList<>();
        listLandsFeature = landFeatureService.findAll();
        listHousesFeature = housesFeatureService.findAll();
        realEstateStatus = String.valueOf(StatusRealEstateConstant.NOT_VERIFIED);
        realEstateType = StatusRealEstateConstant.CONTRIBUTOR;
        realEstatePrice = BigDecimal.ZERO;
        newHouseMoney = BigDecimal.ZERO;
        newLandMoney = BigDecimal.ZERO;
        checkLocationLocate = "";
        typeRealEstate = RealEstateTypeConstant.LANDHOUSETYPE;
    }

    public void showModalMandatory() {
        PrimeFaces.current().executeScript("showModalMandatory()");
    }

    public void saveDataUploadToDB() throws IOException {

        nextLocatePoint();
        //save to DB RE
        showModalMandatory();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (auth != null) {
            username = (String) auth.getPrincipal();
        }

        User tempUser = userService.findByUsername(username);
        boolean variableSuccess = false;
        RealEstate newUploadRealEstate = new RealEstate();
        List<LandsDetail> listLandDetail = new ArrayList<>();
        List<HousesDetail> listHousesDetail = new ArrayList<>();
        newUploadRealEstate = validateInfor(realEstateName, realEstateLat, realEstateLng, realEstateAddress, realEstatePrice, realEstateStatus, tempUser);

        RealEstateAdjacentSegment newRealEstateAdjacentSegment = new RealEstateAdjacentSegment();
        // save to Table REAS

        if (typeRealEstate.equals(RealEstateTypeConstant.LANDTYPE)) {
            Land newLand = new Land();
            newLand = validateLandInfor(newUploadRealEstate, newLandName, newLandMoney, listLandFeatureValue);                 // call from Service
            if (newUploadRealEstate != null && newLand != null) {
                saveDataNewLandSigleToDB(newUploadRealEstate, newRealEstateAdjacentSegment, newLand, listLandDetail);
                variableSuccess = true;
            }

        } else if (typeRealEstate.equals(RealEstateTypeConstant.HOUSETYPE)) {
            House newHouse = new House();
            newHouse = validateHouseInfor(newUploadRealEstate, newHouseName, newHouseMoney, listHouseFeatureValue);            // call from Service
            if (newUploadRealEstate != null && newHouse != null) {
                saveDataNewHouseSingleToDB(newUploadRealEstate, newRealEstateAdjacentSegment, newHouse, listHousesDetail);
                variableSuccess = true;
            }
        } else if (typeRealEstate.equals(RealEstateTypeConstant.LANDHOUSETYPE)) {
            Land newLand = new Land();
            newLand = validateLandInfor(newUploadRealEstate, newLandName, newLandMoney, listLandFeatureValue);                 // call from Service
            listLandDetail = validateLandDetailInfor(newLand, listLandFeatureValue);

            House newHouse = new House();
            newHouse = validateHouseInfor(newUploadRealEstate, newHouseName, newHouseMoney, listHouseFeatureValue);            // call from Service
            listHousesDetail = validateHouseDetailInfor(newHouse, listHouseFeatureValue);

            if (newUploadRealEstate != null && newHouse != null && newLand != null) {
                saveDataNewLandHouseTotalToDB(newUploadRealEstate, newRealEstateAdjacentSegment, newLand, listLandDetail, newHouse, listHousesDetail);
                variableSuccess = true;
            }
        }

        if (variableSuccess == true) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/user/listownrealestate.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveDataNewLandSigleToDB(RealEstate newUploadRealEstate, RealEstateAdjacentSegment newRealEstateAdjacentSegment, Land newLand, List<LandsDetail> listLandDetail) {
        newUploadRealEstate = realEstateService.save(newUploadRealEstate);
        RealEstateAdjacentSegmentId realEstateAdjacentSegmentId = new RealEstateAdjacentSegmentId(Long.parseLong(segmentStreetIdSelected), newUploadRealEstate.getRealEstateId());
        newRealEstateAdjacentSegment = validateRealEstateAdjacentInfor(newUploadRealEstate, realEstateAdjacentSegmentId);
        newRealEstateAdjacentSegment = realEstateAdjacentSegmentService.save(newRealEstateAdjacentSegment);                         // save REAS to DB 
        newLand = landService.save(newLand);
        listLandDetail = validateLandDetailInfor(newLand, listLandFeatureValue);
        if (!listLandDetail.isEmpty()) {                                                                                             // save Land to DB 
            for (int i = 0; i < listLandDetail.size(); i++) {
                landsDetailService.save(listLandDetail.get(i));
            }
        }
    }

    public void saveDataNewHouseSingleToDB(RealEstate newUploadRealEstate, RealEstateAdjacentSegment newRealEstateAdjacentSegment, House newHouse, List<HousesDetail> listHousesDetail) {
        newUploadRealEstate = realEstateService.save(newUploadRealEstate);                                                          // save RE to DB 
        RealEstateAdjacentSegmentId realEstateAdjacentSegmentId = new RealEstateAdjacentSegmentId(Long.parseLong(segmentStreetIdSelected), newUploadRealEstate.getRealEstateId());
        newRealEstateAdjacentSegment = validateRealEstateAdjacentInfor(newUploadRealEstate, realEstateAdjacentSegmentId);
        newRealEstateAdjacentSegment = realEstateAdjacentSegmentService.save(newRealEstateAdjacentSegment);                         // save REAS to DB 
        newHouse = houseService.save(newHouse);
        listHousesDetail = validateHouseDetailInfor(newHouse, listHouseFeatureValue);
        if (!listHousesDetail.isEmpty()) {                                                                                             // save Land to DB 
            for (int i = 0; i < listHousesDetail.size(); i++) {
                housesDetailService.save(listHousesDetail.get(i));
            }
        }
    }

    public void saveDataNewLandHouseTotalToDB(RealEstate newUploadRealEstate, RealEstateAdjacentSegment newRealEstateAdjacentSegment, Land newLand, List<LandsDetail> listLandDetail, House newHouse, List<HousesDetail> listHousesDetail) {
        newUploadRealEstate = realEstateService.save(newUploadRealEstate);
        RealEstateAdjacentSegmentId realEstateAdjacentSegmentId = new RealEstateAdjacentSegmentId(Long.parseLong(segmentStreetIdSelected), newUploadRealEstate.getRealEstateId());
        newRealEstateAdjacentSegment = validateRealEstateAdjacentInfor(newUploadRealEstate, realEstateAdjacentSegmentId);
        newRealEstateAdjacentSegment = realEstateAdjacentSegmentService.save(newRealEstateAdjacentSegment);                         // save REAS to DB 
        newLand = landService.save(newLand);
        listLandDetail = validateLandDetailInfor(newLand, listLandFeatureValue);
        if (!listLandDetail.isEmpty()) {                                                                                             // save Land to DB 
            for (int i = 0; i < listLandDetail.size(); i++) {
                landsDetailService.save(listLandDetail.get(i));
            }
        }

        newHouse = houseService.save(newHouse);
        listHousesDetail = validateHouseDetailInfor(newHouse, listHouseFeatureValue);
        if (!listHousesDetail.isEmpty()) {                                                                                             // save Land to DB 
            for (int i = 0; i < listHousesDetail.size(); i++) {
                housesDetailService.save(listHousesDetail.get(i));
            }
        }
    }

    public RealEstate validateInfor(String realEstateName, Double realEstateLat, Double realEstateLng, String realEstateAddress, BigDecimal realEstatePrice, String realEstateStatus, User tempUser) {
        try {
            RealEstateValidation rev = new RealEstateValidation();
            RealEstate newUploadRealEstate = new RealEstate().setRealEstateName(realEstateName)
                    .setRealEstateLat(realEstateLat).setRealEstateLng(realEstateLng)
                    .setRealEstateAddress(realEstateAddress);
            newUploadRealEstate.setRealEstatePrice(realEstatePrice);
            newUploadRealEstate.setRealEstateStatus(realEstateStatus).setRealEstateSource("CONTRIBUTOR").setUser(tempUser);
            if (rev.isRealEstateValid(newUploadRealEstate).equals("")) {
                return newUploadRealEstate;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Land validateLandInfor(RealEstate newUploadRealEstate, String newLandName, BigDecimal newLandMoney, List<LandFeatureValue> listLandFeatureValue) {
        try {
            RealEstateValidation rev = new RealEstateValidation();
            Land tempLand = new Land();
            tempLand.setLandName(newLandName);
            tempLand.setLandPrice(new BigDecimal(newLandMoney.toString()));
            tempLand.setRealEstate(newUploadRealEstate);

            if (rev.checkLandValidation(tempLand, listLandFeatureValue)) {
                return tempLand;
//                landRepository.save(tempLand);
            } else {
                tempLand = null;
            }

            return tempLand;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public House validateHouseInfor(RealEstate newUploadRealEstate, String newHouseName, BigDecimal newHouseMoney, List<HouseFeatureValue> listHouseFeatureValue) {
        try {
            RealEstateValidation rev = new RealEstateValidation();
            House tempHouse = new House();
            tempHouse.setHouseName(newHouseName);
            tempHouse.setHousePrice(new BigDecimal(newHouseMoney.toString()));
            tempHouse.setRealEstate(newUploadRealEstate);

            if (rev.checkHouseValidation(tempHouse, listHouseFeatureValue)) {
                return tempHouse;
//                landRepository.save(tempLand);
            } else {
                tempHouse = null;
            }

            return tempHouse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<HousesDetail> validateHouseDetailInfor(House tempHouse, List<HouseFeatureValue> listHouseFeatureValue) {
        try {
            RealEstateValidation rev = new RealEstateValidation();
            ArrayList<HousesDetail> ahd = new ArrayList<>();
            if (rev.checkHouseDetailValidation(tempHouse) && rev.checkHouseValidation(tempHouse, listHouseFeatureValue)) {
                for (int i = 0; i < listHouseFeatureValue.size(); i++) {
                    HousesDetailId tempHDI = new HousesDetailId();
                    tempHDI.setHouseId(tempHouse.getHouseId());
                    tempHDI.setHousesFeatureId(listHouseFeatureValue.get(i).getHousesFeature().getHousesFeatureID());
                    HousesDetail tempHD = new HousesDetail();
                    tempHD.setId(tempHDI)
                            .setValue(listHouseFeatureValue.get(i).getValue());
//                    landsDetailRepository.save(tempLD);
                    ahd.add(tempHD);
                }
            }
            return ahd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<LandsDetail> validateLandDetailInfor(Land tempLand, List<LandFeatureValue> listLandFeatureValue) {
        try {
            RealEstateValidation rev = new RealEstateValidation();
            ArrayList<LandsDetail> ald = new ArrayList<>();
            if (rev.checkLandDetailValidation(tempLand) && rev.checkLandValidation(tempLand, listLandFeatureValue)) {
                for (int i = 0; i < listLandFeatureValue.size(); i++) {
                    LandsDetailId tempLDI = new LandsDetailId();
                    tempLDI.setLandId(tempLand.getLandId());
                    tempLDI.setLandsFeatureId(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureID());
                    LandsDetail tempLD = new LandsDetail();
                    tempLD.setId(tempLDI)
                            .setValue(listLandFeatureValue.get(i).getValue());
//                    landsDetailRepository.save(tempLD);
                    ald.add(tempLD);
                }
            }
            return ald;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public RealEstateAdjacentSegment validateRealEstateAdjacentInfor(RealEstate newUploadRealEstate, RealEstateAdjacentSegmentId realEstateAdjacentSegmentId) {
        try {
            RealEstateValidation rev = new RealEstateValidation();
            if (rev.checkRealEstateSegmentValidation(newUploadRealEstate)) {
                RealEstateAdjacentSegment newRealEstateAdjacentSegment = new RealEstateAdjacentSegment();
                newRealEstateAdjacentSegment.setRealEstate(newUploadRealEstate);
                newRealEstateAdjacentSegment.setId(realEstateAdjacentSegmentId);
                return newRealEstateAdjacentSegment;
//                        realEstateAdjacentSegmentRepository.save(newRealEstateAdjacentSegment);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // function call when Ajax listener (textbox Price change value)
    public void calculateRealEstateValue() {
        try {
            // when new House Price not null but Total Null (Null is different Zero)
            if (realEstatePrice.equals(BigDecimal.ZERO) && !newHouseMoney.equals(BigDecimal.ZERO) && !newLandMoney.equals(BigDecimal.ZERO)) {
                realEstatePrice = newHouseMoney.add(newLandMoney);
            } // when new Land Price not null but Total Null (Null is different Zero)
            else if (realEstatePrice.equals(BigDecimal.ZERO) && !newHouseMoney.equals(BigDecimal.ZERO) && newLandMoney.equals(BigDecimal.ZERO)) {
                realEstatePrice = newHouseMoney.add(BigDecimal.ZERO);
            } // when new House & Land Price not null but Total Null (Null is different Zero)
            else if (realEstatePrice.equals(BigDecimal.ZERO) && newHouseMoney.equals(BigDecimal.ZERO) && !newLandMoney.equals(BigDecimal.ZERO)) {
                realEstatePrice = newLandMoney.add(BigDecimal.ZERO);
            }
            //khi mà cả 3 trường đều có giá trị 
            try {
                if (realEstatePrice.equals(newHouseMoney.add(newLandMoney)) || realEstatePrice.compareTo(newHouseMoney.add(newLandMoney)) > 0) {
                    //nothing 
                } else {
                    realEstatePrice = newHouseMoney.add(newLandMoney);
                }
            } catch (Exception e) {
                // Math Exception BigDecimal
            }
        } catch (Exception e) {
            // typing number to get Null Pointer Exception
        }

    }

    // delete element in listLandFeatureValue has Name equal Name in Row Delete Selected
    public void deleteLandRowInsert(String landFeatureName) {
        for (int i = 0; i < listLandFeatureValue.size(); i++) {
            if (listLandFeatureValue.get(i).getLandFeature().getLandsFeatureName().equals(landFeatureName)) {
                listLandFeatureValue.remove(listLandFeatureValue.get(i));
            }
        }
    }

    public void deleteHouseRowInsert(String houseFeatureName) {
        for (int i = 0; i < listHouseFeatureValue.size(); i++) {
            if (listHouseFeatureValue.get(i).getHousesFeature().getHousesFeatureName().equals(houseFeatureName)) {
                listHouseFeatureValue.remove(listHouseFeatureValue.get(i));
            }
        }
    }

    public void nextLocatePoint() {
        for (int i = 0; i < listProvince.size(); i++) {
            if (listProvince.get(i).getProvinceId().toString().equals(provinceIdSelected)) {
                provinceAddress = listProvince.get(i).getProvinceName();
            }
        }
        for (int i = 0; i < listDistrict.size(); i++) {
            if (listDistrict.get(i).getDistrictId().toString().equals(districtIdSelected)) {
                districtAddress = listDistrict.get(i).getDistrictName();
            }
        }
        for (int i = 0; i < listStreet.size(); i++) {
            if (listStreet.get(i).getStreetId().toString().equals(streetIdSelected)) {
                streetAddress = listStreet.get(i).getStreetName();
            }
        }
        for (int i = 0; i < listSegmentOfStreet.size(); i++) {
            if (listSegmentOfStreet.get(i).getSegmentId().toString().equals(segmentStreetIdSelected)) {
                segmentStreetAddress = listSegmentOfStreet.get(i).getSegmentName();
            }
        }
        realEstateAddress = segmentStreetAddress + ", " + streetAddress + ", " + districtAddress + ", " + provinceAddress; // Address of Real Estate show just only Street.
        try {
            realEstateLng = Double.parseDouble(lngSingleCoordinate);
            realEstateLat = Double.parseDouble(latSingleCoordinate);
        } catch (Exception e) {

        }
    }

    public void locateSuccess() {
        nextLocatePoint();
        if (checkLocation(provinceIdSelected, districtIdSelected, streetIdSelected, segmentStreetIdSelected).equals("OK") && latSingleCoordinate != null && lngSingleCoordinate != null && !latSingleCoordinate.isEmpty() && !lngSingleCoordinate.isEmpty()) {
            checkLocationLocate = "OK";
        } else if (checkLocation(provinceIdSelected, districtIdSelected, streetIdSelected, segmentStreetIdSelected).equals("OK") && ((latSingleCoordinate == null && lngSingleCoordinate == null) || (latSingleCoordinate.isEmpty() && lngSingleCoordinate.isEmpty()))) {
            checkLocationLocate = "Marker";
        } else if (checkLocation(provinceIdSelected, districtIdSelected, streetIdSelected, segmentStreetIdSelected).equals("TP")) {
            checkLocationLocate = "TP";
        } else if (checkLocation(provinceIdSelected, districtIdSelected, streetIdSelected, segmentStreetIdSelected).equals("QH")) {
            checkLocationLocate = "QH";
        } else if (checkLocation(provinceIdSelected, districtIdSelected, streetIdSelected, segmentStreetIdSelected).equals("DP")) {
            checkLocationLocate = "DP";
        } else if (checkLocation(provinceIdSelected, districtIdSelected, streetIdSelected, segmentStreetIdSelected).equals("DD")) {
            checkLocationLocate = "DD";
        }
        PrimeFaces.current().executeScript("validateMap()");
    }

    private String checkLocation(String provinceIdSelected, String districtIdSelected, String streetIdSelected, String segmentStreetIdSelected) {
        if (provinceIdSelected.equals("")) {
            return "TP";
        } else if (districtIdSelected.equals("")) {
            return "QH";
        } else if (streetIdSelected.equals("")) {
            return "DP";
        } else if (segmentStreetIdSelected.equals("")) {
            return "DD";
        } else {
            return "OK";
        }
    }

    public void onChangeLandUnit() {
        for (int i = 0; i < listLandsFeature.size(); i++) {
            if (listLandsFeature.get(i).getLandsFeatureID().toString().equals(landFeatureIdSelected)) {
                landUnit = listLandsFeature.get(i).getLandsFeatureUnit();
            }
        }
        if (landUnit == null) {
            landUnit = RealEstateTypeConstant.UNIT;
        }
        PrimeFaces.current().executeScript("loadLandUnit('" + landUnit + "')");

    }

    public void onChangeHouseUnit() {
        for (int i = 0; i < listHousesFeature.size(); i++) {
            if (listHousesFeature.get(i).getHousesFeatureID().toString().equals(houseFeatureIdSelected)) {
                houseUnit = listHousesFeature.get(i).getHousesFeatureUnit();
            }
        }
        if (houseUnit == null) {
            houseUnit = RealEstateTypeConstant.UNIT;
        }
        PrimeFaces.current().executeScript("loadHouseUnit('" + houseUnit + "')");
    }

    public void addNewLandFeatureValue() {
        if (!checkLandFeatureExisted(landFeatureIdSelected, listLandFeatureValue)) {
            for (int i = 0; i < listLandsFeature.size(); i++) {

                if (landFeatureIdSelected.equals(listLandsFeature.get(i).getLandsFeatureID().toString())) {
                    listLandFeatureValue.add(new LandFeatureValue(listLandsFeature.get(i), newLandFeatureValue));
                }
            }

        } else {
            // show log 
            PrimeFaces.current().executeScript("landFeatureExisted()");
        }

    }

    public void addNewHousesFeatureValue() {
        if (!checkHouseFeatureExisted(houseFeatureIdSelected, listHouseFeatureValue)) {
            for (int i = 0; i < listHousesFeature.size(); i++) {

                if (houseFeatureIdSelected.equals(listHousesFeature.get(i).getHousesFeatureID().toString())) {
                    listHouseFeatureValue.add(new HouseFeatureValue(listHousesFeature.get(i), newHouseFeatureValue));
                }
            }

        } else {
            PrimeFaces.current().executeScript("houseFeatureExisted()");
        }

    }

    public boolean checkLandFeatureExisted(String landFeatureIdChecking, List<LandFeatureValue> listLandFeatureValue) {
        for (LandFeatureValue i : listLandFeatureValue) {
            if (landFeatureIdChecking.equals(i.getLandFeature().getLandsFeatureID().toString())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHouseFeatureExisted(String houseFeatureIdChecking, List<HouseFeatureValue> listHouseFeatureValue) {
        for (HouseFeatureValue i : listHouseFeatureValue) {
            if (houseFeatureIdChecking.equals(i.getHousesFeature().getHousesFeatureID().toString())) {
                return true;
            }
        }
        return false;
    }

    Province selectedProvince;

    public void provinceChange() {
        if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
            processType = "1";
            selectedProvince = listProvince.stream().filter(x -> x.getProvinceId().equals(Long.parseLong(provinceIdSelected))).collect(Collectors.toList()).get(0);
            listDistrict = selectedProvince.getListDistrict();
            listSegmentOfStreet = new ArrayList();
            listStreet = new ArrayList();
            districtIdSelected = "";
            streetIdSelected = "";
            segmentStreetIdSelected = "";
            nameInput = selectedProvince.getProvinceName();

            PrimeFaces.current().executeScript("focusMap(" + selectedProvince.getProvinceLat() + ", " + selectedProvince.getProvinceLng() + ");");
        } else {
            listDistrict = new ArrayList<>();
            listStreet = new ArrayList<>();
            lngSingleCoordinate = "";
            latSingleCoordinate = "";
        }

    }
    District selectedDistrict;

    public void districtChange() {
        if (districtIdSelected != null && !districtIdSelected.equals("")) {
            processType = "2";
            streetIdSelected = "";
            segmentStreetIdSelected = "";

            selectedDistrict = listDistrict.stream().filter(x -> x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + selectedDistrict.getDistrictLat() + ", " + selectedDistrict.getDistrictLng() + ");");
            PrimeFaces.current().executeScript("changeInfo(\"" + selectedDistrict.getDistrictName() + "\", " + selectedDistrict.getDistrictLng() + ", "
                    + selectedDistrict.getDistrictLat() + ")");
            listSegmentOfStreet = selectedDistrict.getListSegmentOfStreet();
            if (listSegmentOfStreet != null) {
                listStreet = listSegmentOfStreet.stream().map(x -> x.getStreet()).distinct().collect(Collectors.toList());
            }
        } else {
            listStreet = new ArrayList<>();
            listSegmentOfStreet = new ArrayList<>();
            lngSingleCoordinate = "";
            latSingleCoordinate = "";
        }
    }
    Street selectedStreet;

    public void streetChange() {
        if (streetIdSelected != null && !streetIdSelected.equals("")) {
            processType = "3";
            segmentStreetIdSelected = "";

            selectedStreet = listStreet.stream().filter(x -> x.getStreetId().equals((Long.parseLong(streetIdSelected)))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + selectedStreet.getStreetLat() + ", " + selectedStreet.getStreetLng() + ");");
            PrimeFaces.current().executeScript("changeInfo(\"" + selectedStreet.getStreetName() + "\", " + selectedStreet.getStreetLat() + ", "
                    + selectedStreet.getStreetLng() + ")");

            listSegmentOfStreet = selectedStreet.getListSegmentOfStreet();
        } else {
            listSegmentOfStreet = new ArrayList<>();
            lngSingleCoordinate = "";
            latSingleCoordinate = "";
        }

    }
    SegmentOfStreet segmentOfStreet;

    public void segmentStreetChange() {
        if (segmentStreetIdSelected != null && !segmentStreetIdSelected.equals("")) {
            processType = "4";
            segmentOfStreet = listSegmentOfStreet.stream().filter(x -> x.getSegmentId().equals(Long.parseLong(segmentStreetIdSelected))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + segmentOfStreet.getSegmentLat() + ", " + segmentOfStreet.getSegmentLng() + ");");
            PrimeFaces.current().executeScript("changeInfo(\"" + segmentOfStreet.getSegmentName() + "\", " + segmentOfStreet.getSegmentLat() + ", "
                    + segmentOfStreet.getSegmentLng() + ")");

            PrimeFaces.current().executeScript("updateDeleteOld()");
        } else {
            segmentStreetIdSelected = "";
            segmentOfStreet = null;
            lngSingleCoordinate = "";
            latSingleCoordinate = "";
        }
    }

    public void setMessage(FacesMessage.Severity severityType, String message) {

        FacesMessage msg = new FacesMessage();
        if (severityType == FacesMessage.SEVERITY_ERROR) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", message);
        } else if (severityType == FacesMessage.SEVERITY_WARN) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Lưu ý", message);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", message);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void NextButtonClickLocate() {
        try {
            Long provinceSelected = Long.parseLong(provinceIdSelected);
        } catch (Exception ex) {
            setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn Tỉnh/Thành");
            return;
        }
        try {
            Long districtSelected = Long.parseLong(districtIdSelected);
        } catch (Exception ex) {
            setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn Quận/Huyện");
            return;
        }
        try {
            Long streetSelected = Long.parseLong(streetIdSelected);
        } catch (Exception ex) {
            setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn Tuyến Đường");
            return;
        }
        try {
            Long segmentStreetSelected = Long.parseLong(segmentStreetIdSelected);
        } catch (Exception ex) {
            setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn Đoạn đường");
            return;
        }
    }

    public List<Province> getListProvince() {
        return listProvince;
    }

    public void setListProvince(List<Province> listProvince) {
        this.listProvince = listProvince;
    }

    public List<District> getListDistrict() {
        return listDistrict;
    }

    public void setListDistrict(List<District> listDistrict) {
        this.listDistrict = listDistrict;
    }

    public List<SegmentOfStreet> getListSegmentOfStreet() {
        return listSegmentOfStreet;
    }

    public void setListSegmentOfStreet(List<SegmentOfStreet> listSegmentOfStreet) {
        this.listSegmentOfStreet = listSegmentOfStreet;
    }

    public List<Street> getListStreet() {
        return listStreet;
    }

    public String getHouseFeatureIdSelected() {
        return houseFeatureIdSelected;
    }

    public void setHouseFeatureIdSelected(String houseFeatureIdSelected) {
        this.houseFeatureIdSelected = houseFeatureIdSelected;
    }

    public List<HouseFeatureValue> getListHouseFeatureValue() {
        return listHouseFeatureValue;
    }

    public void setListHouseFeatureValue(List<HouseFeatureValue> listHouseFeatureValue) {
        this.listHouseFeatureValue = listHouseFeatureValue;
    }

    public List<HousesFeature> getListHousesFeature() {
        return listHousesFeature;
    }

    public void setListHousesFeature(List<HousesFeature> listHousesFeature) {
        this.listHousesFeature = listHousesFeature;
    }

    public String getNewLandFeatureValue() {
        return newLandFeatureValue;
    }

    public void setNewLandFeatureValue(String newLandFeatureValue) {
        this.newLandFeatureValue = newLandFeatureValue;
    }

    public String getNewHouseFeatureValue() {
        return newHouseFeatureValue;
    }

    public void setNewHouseFeatureValue(String newHouseFeatureValue) {
        this.newHouseFeatureValue = newHouseFeatureValue;
    }

    public void setListStreet(List<Street> listStreet) {
        this.listStreet = listStreet;
    }

    public String getProvinceIdSelected() {
        return provinceIdSelected;
    }

    public void setProvinceIdSelected(String provinceIdSelected) {
        this.provinceIdSelected = provinceIdSelected;
    }

    public String getDistrictIdSelected() {
        return districtIdSelected;
    }

    public void setDistrictIdSelected(String districtIdSelected) {
        this.districtIdSelected = districtIdSelected;
    }

    public String getStreetIdSelected() {
        return streetIdSelected;
    }

    public void setStreetIdSelected(String streetIdSelected) {
        this.streetIdSelected = streetIdSelected;
    }

    public String getSegmentStreetIdSelected() {
        return segmentStreetIdSelected;
    }

    public void setSegmentStreetIdSelected(String segmentStreetIdSelected) {
        this.segmentStreetIdSelected = segmentStreetIdSelected;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public String getLngSingleCoordinate() {
        return lngSingleCoordinate;
    }

    public void setLngSingleCoordinate(String lngSingleCoordinate) {
        this.lngSingleCoordinate = lngSingleCoordinate;
    }

    public String getLatSingleCoordinate() {
        return latSingleCoordinate;
    }

    public void setLatSingleCoordinate(String latSingleCoordinate) {
        this.latSingleCoordinate = latSingleCoordinate;
    }

    public List<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public void setListCoordinate(List<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
    }

    public String getJsonMultipleCoordinate() {
        return jsonMultipleCoordinate;
    }

    public void setJsonMultipleCoordinate(String jsonMultipleCoordinate) {
        this.jsonMultipleCoordinate = jsonMultipleCoordinate;
    }

    public Province getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(Province selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public District getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(District selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

    public Street getSelectedStreet() {
        return selectedStreet;
    }

    public void setSelectedStreet(Street selectedStreet) {
        this.selectedStreet = selectedStreet;
    }

    public SegmentOfStreet getSegmentOfStreet() {
        return segmentOfStreet;
    }

    public void setSegmentOfStreet(SegmentOfStreet segmentOfStreet) {
        this.segmentOfStreet = segmentOfStreet;
    }

    public String getLandFeatureIdSelected() {
        return landFeatureIdSelected;
    }

    public void setLandFeatureIdSelected(String landFeatureIdSelected) {
        this.landFeatureIdSelected = landFeatureIdSelected;
    }

    public List<LandsFeature> getListLandsFeature() {
        return listLandsFeature;
    }

    public void setListLandsFeature(List<LandsFeature> listLandsFeature) {
        this.listLandsFeature = listLandsFeature;
    }

    public List<LandFeatureValue> getListLandFeatureValue() {
        return listLandFeatureValue;
    }

    public void setListLandFeatureValue(List<LandFeatureValue> listLandFeatureValue) {
        this.listLandFeatureValue = listLandFeatureValue;
    }

    public String getLandUnit() {
        return landUnit;
    }

    public void setLandUnit(String landUnit) {
        this.landUnit = landUnit;
    }

    public String getHouseUnit() {
        return houseUnit;
    }

    public void setHouseUnit(String houseUnit) {
        this.houseUnit = houseUnit;
    }

    public String getProvinceAddress() {
        return provinceAddress;
    }

    public void setProvinceAddress(String provinceAddress) {
        this.provinceAddress = provinceAddress;
    }

    public String getDistrictAddress() {
        return districtAddress;
    }

    public void setDistrictAddress(String districtAddress) {
        this.districtAddress = districtAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSegmentStreetAddress() {
        return segmentStreetAddress;
    }

    public void setSegmentStreetAddress(String segmentStreetAddress) {
        this.segmentStreetAddress = segmentStreetAddress;
    }

    public String getRealEstateAddress() {
        return realEstateAddress;
    }

    public void setRealEstateAddress(String realEstateAddress) {
        this.realEstateAddress = realEstateAddress;
    }

    public Double getRealEstateLat() {
        return realEstateLat;
    }

    public void setRealEstateLat(Double realEstateLat) {
        this.realEstateLat = realEstateLat;
    }

    public Double getRealEstateLng() {
        return realEstateLng;
    }

    public void setRealEstateLng(Double realEstateLng) {
        this.realEstateLng = realEstateLng;
    }

    public BigDecimal getRealEstatePrice() {
        return realEstatePrice;
    }

    public void setRealEstatePrice(BigDecimal realEstatePrice) {
        this.realEstatePrice = realEstatePrice;
    }

    public String getRealEstateStatus() {
        return realEstateStatus;
    }

    public void setRealEstateStatus(String realEstateStatus) {
        this.realEstateStatus = realEstateStatus;
    }

    public String getRealEstateLink() {
        return realEstateLink;
    }

    public void setRealEstateLink(String realEstateLink) {
        this.realEstateLink = realEstateLink;
    }

    public String getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(String realEstateType) {
        this.realEstateType = realEstateType;
    }

    public String getRealEstateName() {
        return realEstateName;
    }

    public void setRealEstateName(String realEstateName) {
        this.realEstateName = realEstateName;
    }

    public BigDecimal getRealEstatePriceSubmit() {
        return realEstatePriceSubmit;
    }

    public void setRealEstatePriceSubmit(BigDecimal realEstatePriceSubmit) {
        this.realEstatePriceSubmit = realEstatePriceSubmit;
    }

    public String getRealEstateNameSubmit() {
        return realEstateNameSubmit;
    }

    public void setRealEstateNameSubmit(String realEstateNameSubmit) {
        this.realEstateNameSubmit = realEstateNameSubmit;
    }

    public BigDecimal getNewHouseMoney() {
        return newHouseMoney;
    }

    public void setNewHouseMoney(BigDecimal newHouseMoney) {
        this.newHouseMoney = newHouseMoney;
    }

    public BigDecimal getNewLandMoney() {
        return newLandMoney;
    }

    public void setNewLandMoney(BigDecimal newLandMoney) {
        this.newLandMoney = newLandMoney;
    }

    public String getNewLandName() {
        return newLandName;
    }

    public void setNewLandName(String newLandName) {
        this.newLandName = newLandName;
    }

    public String getNewHouseName() {
        return newHouseName;
    }

    public void setNewHouseName(String newHouseName) {
        this.newHouseName = newHouseName;
    }

    public String getCheckLocationLocate() {
        return checkLocationLocate;
    }

    public void setCheckLocationLocate(String checkLocationLocate) {
        this.checkLocationLocate = checkLocationLocate;
    }

    public String getTypeRealEstate() {
        return typeRealEstate;
    }

    public void setTypeRealEstate(String typeRealEstate) {
        this.typeRealEstate = typeRealEstate;
    }

}
