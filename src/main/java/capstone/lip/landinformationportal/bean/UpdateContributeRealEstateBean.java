/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.dto.HouseFeatureValue;
import capstone.lip.landinformationportal.dto.LandFeatureValue;
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesDetailId;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsDetailId;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegmentId;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
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
import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class UpdateContributeRealEstateBean implements Serializable {

    private RealEstate realEstateClicked;
    private String jsonCoordinate;
    private Land currentLand;
    private List<House> currentListHouse;

    @Autowired
    private IRealEstateService realEstateService;

    @Autowired
    private ILandService landService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ILandsDetailService landsDetailService;

    @Autowired
    private IHousesDetailService housesDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private IDistrictService districtService;

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
    private String realEstateSource;
    //submit data
    private BigDecimal realEstatePriceSubmit;
    private String realEstateNameSubmit;
    private String newLandName;
    private String newHouseName;

    private long tempRealEstateId;
    private House tempHouse;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long realEstateId = Long.parseLong(params.get("realEstateId"));
        listProvince = provinceService.findAll();
        listDistrict = new ArrayList<>();
        listSegmentOfStreet = new ArrayList<>();
        listStreet = new ArrayList<>();
        listLandsFeature = landFeatureService.findAll();
        listHousesFeature = housesFeatureService.findAll();
        realEstateClicked = realEstateService.findById(realEstateId);
        currentLand = realEstateService.getLand(realEstateId);
        currentListHouse = realEstateService.getListHouse(realEstateId);
        tempHouse = currentListHouse.get(0);

        lngSingleCoordinate = realEstateClicked.getRealEstateLng().toString();
        latSingleCoordinate = realEstateClicked.getRealEstateLat().toString();

        realEstateStatus = realEstateClicked.getRealEstateStatus();
        realEstateLink = realEstateClicked.getRealEstateLink();
        realEstateSource = realEstateClicked.getRealEstateSource();
//        PrimeFaces.current().executeScript("focusMap(" + latSingleCoordinate + ", " + lngSingleCoordinate + ");");

        realEstateName = realEstateClicked.getRealEstateName();
        realEstatePrice = realEstateClicked.getRealEstatePrice();
        newLandName = currentLand.getLandName();
        newLandMoney = new BigDecimal(currentLand.getLandPrice());
        List<LandsDetail> landsDetailContribute = currentLand.getListLandsDetail();
        for (int i = 0; i < landsDetailContribute.size(); i++) {
            listLandFeatureValue.add(new LandFeatureValue(landsDetailContribute.get(i).getLandsFeature(), landsDetailContribute.get(i).getValue()));
        }

        newHouseName = currentListHouse.get(0).getHouseName();
        newHouseMoney = new BigDecimal(currentListHouse.get(0).getHousePrice());
        List<HousesDetail> housesDetailContribute = currentListHouse.get(0).getListHousesDetail();
        for (int i = 0; i < housesDetailContribute.size(); i++) {
            listHouseFeatureValue.add(new HouseFeatureValue(housesDetailContribute.get(i).getHousesFeature(), housesDetailContribute.get(i).getValue()));
        }

        realEstateStatus = "0";   // Set tạm
        
        segmentStreetIdSelected = realEstateClicked.getListRealEstateAdjacentSegment().get(0).getSegmentOfStreet().getSegmentId().toString();
        districtIdSelected = realEstateClicked.getListRealEstateAdjacentSegment().get(0).getSegmentOfStreet().getDistrict().getDistrictId().toString();
        streetIdSelected = realEstateClicked.getListRealEstateAdjacentSegment().get(0).getSegmentOfStreet().getStreet().getStreetId().toString();
        provinceIdSelected = realEstateClicked.getListRealEstateAdjacentSegment().get(0).getSegmentOfStreet().getDistrict().getProvince().getProvinceId().toString();
    }

    public void updateDataUploadToDB() {

        if (!segmentStreetIdSelected.equals("") && !provinceIdSelected.equals("") && !districtIdSelected.equals("") && !streetIdSelected.equals("")) {
            nextLocatePoint();
        } else {
            realEstateAddress = realEstateClicked.getRealEstateAddress();
            realEstateLat = Double.parseDouble(latSingleCoordinate);
            realEstateLng = Double.parseDouble(lngSingleCoordinate);
        }
        //Update to DB RE
       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username="";
		if (auth!= null) {
			username = (String)auth.getPrincipal();
		}
    	
        User tempUser = userService.findByUsername(username);
        
        realEstateClicked.setRealEstateLat(realEstateLat).setRealEstateLng(realEstateLng)
                .setRealEstateAddress(realEstateAddress);
        realEstateClicked.setRealEstatePrice(realEstatePrice);
        realEstateClicked.setRealEstateStatus(realEstateStatus).setRealEstateSource("CONTRIBUTOR").setUser(tempUser);
        realEstateClicked = realEstateService.save(realEstateClicked);

        // Update to Table REAS if combobox value of Map != Null
        if (!segmentStreetIdSelected.equals("") && !provinceIdSelected.equals("") && !districtIdSelected.equals("") && !streetIdSelected.equals("")) {
            RealEstateAdjacentSegment newRealEstateAdjacentSegment = new RealEstateAdjacentSegment();

            newRealEstateAdjacentSegment.setRealEstate(realEstateClicked);

            Long segmentID = Long.parseLong(segmentStreetIdSelected);
            newRealEstateAdjacentSegment.setId(new RealEstateAdjacentSegmentId(segmentID, realEstateClicked.getRealEstateId()));
            realEstateAdjacentSegmentService.save(newRealEstateAdjacentSegment);
        }

        // Update to Table Land & Update to Table LandsDetail
        if (currentLand.getLandId() != null || !currentLand.getLandId().equals("")) {
            currentLand.setLandName(newLandName);
            currentLand.setLandPrice(Double.parseDouble(newLandMoney.toString()));
            currentLand.setRealEstate(realEstateClicked);

            currentLand = landService.save(currentLand);

            for (int i = 0; i < listLandFeatureValue.size(); i++) {
                LandsDetail tempLD = currentLand.getListLandsDetail().get(i);
                LandsDetailId tempLDI = tempLD.getId();
                tempLDI.setLandId(currentLand.getLandId());
                tempLDI.setLandsFeatureId(listLandFeatureValue.get(i).getLandFeature().getLandsFeatureID());
                tempLD.setId(tempLDI)
                        .setValue(listLandFeatureValue.get(i).getValue());
                landsDetailService.save(tempLD);
            }
        }
        // Update to Table House & House Detail tương tự Land 
        if (currentListHouse.get(0).getHouseId() != null || !currentListHouse.get(0).getHouseId().equals("")) {
            currentListHouse.get(0).setHouseName(newHouseName);
            currentListHouse.get(0).setHouseName(realEstateName);
            currentListHouse.get(0).setHousePrice(Double.parseDouble(newHouseMoney.toString()));
            currentListHouse.get(0).setRealEstate(realEstateClicked);

            for (int i = 0; i < listHouseFeatureValue.size(); i++) {
                HousesDetail tempHD = currentListHouse.get(0).getListHousesDetail().get(i);
                HousesDetailId tempHDI = tempHD.getId();
                tempHDI.setHouseId(currentListHouse.get(0).getHouseId());
                tempHDI.setHousesFeatureId(listHouseFeatureValue.get(i).getHousesFeature().getHousesFeatureID());
                tempHD.setId(tempHDI)
                        .setValue(listHouseFeatureValue.get(i).getValue());
                housesDetailService.save(tempHD);
            }

        }
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
        realEstateAddress = streetAddress + ", " + districtAddress + ", " + provinceAddress; // Address of Real Estate show just only Street.
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

    public RealEstate getRealEstateClicked() {
        return realEstateClicked;
    }

    public void setRealEstateClicked(RealEstate realEstateClicked) {
        this.realEstateClicked = realEstateClicked;
    }

    public String getJsonCoordinate() {
        return jsonCoordinate;
    }

    public void setJsonCoordinate(String jsonCoordinate) {
        this.jsonCoordinate = jsonCoordinate;
    }

    public Land getCurrentLand() {
        return currentLand;
    }

    public void setCurrentLand(Land currentLand) {
        this.currentLand = currentLand;
    }

    public List<House> getCurrentListHouse() {
        return currentListHouse;
    }

    public void setCurrentListHouse(List<House> currentListHouse) {
        this.currentListHouse = currentListHouse;
    }

    public ILandService getLandService() {
        return landService;
    }

    public void setLandService(ILandService landService) {
        this.landService = landService;
    }

    public IHouseService getHouseService() {
        return houseService;
    }

    public void setHouseService(IHouseService houseService) {
        this.houseService = houseService;
    }

    public ILandsDetailService getLandsDetailService() {
        return landsDetailService;
    }

    public void setLandsDetailService(ILandsDetailService landsDetailService) {
        this.landsDetailService = landsDetailService;
    }

    public IHousesDetailService getHousesDetailService() {
        return housesDetailService;
    }

    public void setHousesDetailService(IHousesDetailService housesDetailService) {
        this.housesDetailService = housesDetailService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IFormedCoordinate getFormedCoordinateService() {
        return formedCoordinateService;
    }

    public void setFormedCoordinateService(IFormedCoordinate formedCoordinateService) {
        this.formedCoordinateService = formedCoordinateService;
    }

    public ILandsFeatureService getLandFeatureService() {
        return landFeatureService;
    }

    public void setLandFeatureService(ILandsFeatureService landFeatureService) {
        this.landFeatureService = landFeatureService;
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

    public String getLandFeatureIdSelected() {
        return landFeatureIdSelected;
    }

    public void setLandFeatureIdSelected(String landFeatureIdSelected) {
        this.landFeatureIdSelected = landFeatureIdSelected;
    }

    public String getHouseFeatureIdSelected() {
        return houseFeatureIdSelected;
    }

    public void setHouseFeatureIdSelected(String houseFeatureIdSelected) {
        this.houseFeatureIdSelected = houseFeatureIdSelected;
    }

    public List<LandFeatureValue> getListLandFeatureValue() {
        return listLandFeatureValue;
    }

    public void setListLandFeatureValue(List<LandFeatureValue> listLandFeatureValue) {
        this.listLandFeatureValue = listLandFeatureValue;
    }

    public List<HouseFeatureValue> getListHouseFeatureValue() {
        return listHouseFeatureValue;
    }

    public void setListHouseFeatureValue(List<HouseFeatureValue> listHouseFeatureValue) {
        this.listHouseFeatureValue = listHouseFeatureValue;
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

    public String getRealEstateName() {
        return realEstateName;
    }

    public void setRealEstateName(String realEstateName) {
        this.realEstateName = realEstateName;
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
        return realEstateSource;
    }

    public void setRealEstateType(String realEstateSource) {
        this.realEstateSource = realEstateSource;
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

    public long getTempRealEstateId() {
        return tempRealEstateId;
    }

    public void setTempRealEstateId(long tempRealEstateId) {
        this.tempRealEstateId = tempRealEstateId;
    }

    public House getTempHouse() {
        return tempHouse;
    }

    public void setTempHouse(House tempHouse) {
        this.tempHouse = tempHouse;
    }

}
