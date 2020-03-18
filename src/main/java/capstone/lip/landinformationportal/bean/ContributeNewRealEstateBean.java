/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import static capstone.lip.landinformationportal.common.StatusRealEstateConstant.CONFUSED;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.dto.LandFeatureValue;
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsDetailId;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.service.Interface.IHousesFeatureService;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.service.Interface.ILandsFeatureService;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.service.Interface.IStreetService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class ContributeNewRealEstateBean implements Serializable, StatusRealEstateConstant {

    @Autowired
    private IRealEstateService realEstateService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private IDistrictService districtService;
    
    @Autowired
    private ILandsDetailService landsDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILandService landService;

    @Autowired
    private ISegmentOfStreetService segmentOfStreetService;

    @Autowired
    private IStreetService streetService;

    @Autowired
    private IFormedCoordinate formedCoordinateService;

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
    private String userId;
    //submit data
    private BigDecimal realEstatePriceSubmit;
    private String realEstateNameSubmit;
    private String newLandName;
    private String newHouseName;

    @PostConstruct
    public void init() {
        processType = "1";
        listCoordinate = new ArrayList();
        listProvince = new ArrayList<Province>();
        listProvince = provinceService.findAll();
        listDistrict = new ArrayList<>();
        listSegmentOfStreet = new ArrayList<>();
        listStreet = new ArrayList<>();
        listLandsFeature = landFeatureService.findAll();
        listHousesFeature = housesFeatureService.findAll();
        realEstateStatus = String.valueOf(CONFUSED);
        realEstateType = CONTRIBUTOR;
        userId = "1";
        realEstatePrice = BigDecimal.ZERO;
        newHouseMoney = BigDecimal.ZERO;
        newLandMoney = BigDecimal.ZERO;

    }

    public void saveDataUploadToDB() {
        //save to DB RE
        User tempUser = new User();
        List<User> userListAll = userService.findAll();
        for (int i = 0; i < userListAll.size(); i++) {
            if (userListAll.get(i).getUserId().equals(userId)) {
                tempUser.setUserId(userListAll.get(i).getUserId());
                tempUser.setUsername(userListAll.get(i).getUsername());
                tempUser.setPassword(userListAll.get(i).getPassword());
                tempUser.setFullName(userListAll.get(i).getFullName());
                tempUser.setRole(userListAll.get(i).getRole());
                tempUser.setEmail(userListAll.get(i).getEmail());
                tempUser.setPhone(userListAll.get(i).getPhone());
            }
        }
        RealEstate newUploadRealEstate = new RealEstate().setRealEstateName(realEstateName)
                .setRealEstateLat(realEstateLat).setRealEstateLng(realEstateLng)
                .setRealEstateAddress(realEstateAddress);
        newUploadRealEstate.setRealEstatePrice(realEstatePrice);
//        newUploadRealEstate.set                               Land 
        newUploadRealEstate.setRealEstateStatus(realEstateStatus).setRealEstateType(realEstateType).setUser(tempUser);
        realEstateService.save(newUploadRealEstate);

        // save to Table REAS
        RealEstateAdjacentSegment newRealEstateAdjacentSegment = new RealEstateAdjacentSegment();
        newRealEstateAdjacentSegment.setRealEstate(newUploadRealEstate);
        SegmentOfStreet tempSos = new SegmentOfStreet();
        List<SegmentOfStreet> segmentOfStreetListAll = segmentOfStreetService.findAll();
        for (int i = 0; i < segmentOfStreetListAll.size(); i++) {
            if (segmentOfStreetListAll.get(i).getSegmentId().equals(segmentStreetIdSelected)) {
                tempSos.setSegmentName(segmentOfStreetListAll.get(i).getSegmentName())
                        .setSegmentLat(segmentOfStreetListAll.get(i).getSegmentLng()).setSegmentLng(segmentOfStreetListAll.get(i).getSegmentLng())
                        .setDistrict(selectedDistrict).setStreet(selectedStreet);
            }
        }

        newRealEstateAdjacentSegment.setSegmentOfStreet(tempSos);
        newRealEstateAdjacentSegment.setRealEstate(newUploadRealEstate);
        realEstateAdjacentSegmentService.save(newRealEstateAdjacentSegment);

        // save to Table Land & save to Table LandsDetail
        if (newLandMoney.compareTo(BigDecimal.ZERO) != 0) {
            Land tempLand = new Land();
            tempLand.setLandName(realEstateName);
            tempLand.setLandPrice(Double.parseDouble(newLandMoney.toString()));
            tempLand.setRealEstate(newUploadRealEstate);

//            landService.save(tempLand);
            // 
            for (int i = 0; i < listLandFeatureValue.size(); i++) {
                LandsDetailId tempLDI = new LandsDetailId();
                tempLDI.setLandId(tempLand.getLandId());
                tempLDI.setLandsFeatureId(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureID());
                LandsDetail tempLD = new LandsDetail();
                LandsFeature lf = new LandsFeature();
                lf.setLandsFeatureID(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureID());
                lf.setLandsFeatureName(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureName());
                lf.setLandsFeatureUnit(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureUnit());
       //        lf.setListLandsDetail();           // set list Detail ở đây thế nào khi 
                tempLD.setId(tempLDI).setLand(tempLand).setLandsFeature(lf);
                landsDetailService.save(tempLD);
            }

        }

        // save to Table House & House Detail tương tự Land :(( 
        
    }

    // function call when Ajax listener (textbox Price change value)
    public void calculateRealEstateValue() {
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
        realEstateAddress = streetAddress + districtAddress + provinceAddress; // Address of Real Estate show just only Street.
        try {
            realEstateLng = Double.parseDouble(lngSingleCoordinate);
            realEstateLat = Double.parseDouble(latSingleCoordinate);
        } catch (Exception e) {

        }
//        if(provinceAddress.equals("") || districtAddress.equals("") 
//                || streetAddress.equals("") || segmentStreetAddress.equals("") 
//                || realEstateLng.equals("") || realEstateLat.equals("")){
//                // this case will show for user to warning about empty address of Combobox 
//        }
    }


    public void onChangeLandUnit() {
        for (int i = 0; i < listLandsFeature.size(); i++) {
            if (listLandsFeature.get(i).getLandsFeatureID().toString().equals(landFeatureIdSelected)) {
                landUnit = listLandsFeature.get(i).getLandsFeatureUnit();
            }
        }
        PrimeFaces.current().executeScript("loadLandUnit('" + landUnit + "')");

    }

    public void onChangeHouseUnit() {
        for (int i = 0; i < listHousesFeature.size(); i++) {
            if (listHousesFeature.get(i).getHousesFeatureID().toString().equals(houseFeatureIdSelected)) {
                houseUnit = listHousesFeature.get(i).getHousesFeatureUnit();
            }
        }
        PrimeFaces.current().executeScript("loadHouseUnit('" + houseUnit + "')");
    }

    public void addNewLandFeatureValue() {
        for (int i = 0; i < listLandsFeature.size(); i++) {
            if (landFeatureIdSelected.equals(listLandsFeature.get(i).getLandsFeatureID().toString())) {
                listLandFeatureValue.add(new LandFeatureValue(listLandsFeature.get(i), newLandFeatureValue));
            }
        }

    }

    public void addNewHousesFeatureValue() {
        for (int i = 0; i < listHousesFeature.size(); i++) {
            if (houseFeatureIdSelected.equals(listHousesFeature.get(i).getHousesFeatureID().toString())) {
                listHouseFeatureValue.add(new HouseFeatureValue(listHousesFeature.get(i), newHouseFeatureValue));
            }
        }

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
            latSingleCoordinate = selectedProvince.getProvinceLat().toString();
            lngSingleCoordinate = selectedProvince.getProvinceLng().toString();

            PrimeFaces.current().executeScript("focusMap(" + selectedProvince.getProvinceLat() + ", " + selectedProvince.getProvinceLng() + ");");
        } else {
            listDistrict = new ArrayList<>();
            listStreet = new ArrayList<>();
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
        }

    }
    SegmentOfStreet segmentOfStreet;

    public void segmentStreetChange() {
        if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
            processType = "4";
            segmentOfStreet = listSegmentOfStreet.stream().filter(x -> x.getSegmentId().equals(Long.parseLong(segmentStreetIdSelected))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + segmentOfStreet.getSegmentLat() + ", " + segmentOfStreet.getSegmentLng() + ");");
            PrimeFaces.current().executeScript("changeInfo(\"" + segmentOfStreet.getSegmentName() + "\", " + segmentOfStreet.getSegmentLat() + ", "
                    + segmentOfStreet.getSegmentLng() + ")");

            PrimeFaces.current().executeScript("updateDeleteOld()");
            List<FormedCoordinate> listFormedCoordinate = segmentOfStreet.getListFormedCoordinate();
            List<Coordinate> listCoordinate = listFormedCoordinate.stream()
                    .map(x -> {
                        Coordinate coor = new Coordinate(x.getFormedLng(), x.getFormedLat());
                        return coor;
                    }).collect(Collectors.toList());
            int i = 1;
            i++;
            Gson gson = new Gson();
            jsonMultipleCoordinate = gson.toJson(listCoordinate);
        } else {

        }
    }

    private String findErrorInput() {
        if (nameInput == null || nameInput.isEmpty()) {
            return "Tên không được để trống";
        }
        if (processType.equals("1")) {
            if (!listProvince.stream().filter(x -> x.getProvinceName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()) {
                return "Trùng tên";
            }
        }
        if (processType.equals("4")) {
            if (jsonMultipleCoordinate == null || jsonMultipleCoordinate.isEmpty()) {
                return "Tọa độ không được để trống";
            }
        } else if (lngSingleCoordinate == null || latSingleCoordinate == null || lngSingleCoordinate.isEmpty() || latSingleCoordinate.isEmpty()) {
            return "Tọa độ không được để trống";
        }

        return "";
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

}
