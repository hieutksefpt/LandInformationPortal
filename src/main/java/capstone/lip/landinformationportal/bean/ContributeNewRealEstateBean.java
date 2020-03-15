/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

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
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.service.Interface.IStreetService;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class ContributeNewRealEstateBean implements Serializable {

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
    
    private String realEstateTypeSelected = "";

    

    @PostConstruct
    public void init() {
        processType = "1";
        listCoordinate = new ArrayList<>();
        listProvince = new ArrayList<Province>();
        listProvince = provinceService.findAll();
        listDistrict = new ArrayList<>();
        listSegmentOfStreet = new ArrayList<>();
        listStreet = new ArrayList<>();
    }
    String selectedRealEstateType;

    public void RealEstateTypeChange() {
        if (selectedRealEstateType != null && !provinceIdSelected.equals("")) {
            processType = "5";
            selectedProvince = listProvince.stream().filter(x -> x.getProvinceId().equals(Long.parseLong(provinceIdSelected))).collect(Collectors.toList()).get(0);
            listDistrict = selectedProvince.getListDistrict();
            listSegmentOfStreet = new ArrayList<>();
        }
    }

    Province selectedProvince;

    public void provinceChange() {
        if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
            processType = "1";
            selectedProvince = listProvince.stream().filter(x -> x.getProvinceId().equals(Long.parseLong(provinceIdSelected))).collect(Collectors.toList()).get(0);
            listDistrict = selectedProvince.getListDistrict();
            listSegmentOfStreet = new ArrayList<>();
            listStreet = new ArrayList<>();
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

    public String getRealEstateTypeSelected() {
        return realEstateTypeSelected;
    }

    public void setRealEstateTypeSelected(String realEstateTypeSelected) {
        this.realEstateTypeSelected = realEstateTypeSelected;
    }
    
    
    
}
