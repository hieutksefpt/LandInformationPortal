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

@Named
@ViewScoped
public class ManageGeoInfoBean {

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
	
	
	@PostConstruct
	public void init() {
		processType = "1";
		listCoordinate = new ArrayList();
		listProvince = new ArrayList<Province>();
		listProvince = provinceService.findAll();
		listDistrict = new ArrayList<>();
		listSegmentOfStreet = new ArrayList<>();
		listStreet = new ArrayList<>();
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
			
			PrimeFaces.current().executeScript("focusMap(" + selectedProvince.getProvinceLat() + ", " + selectedProvince.getProvinceLng() + ");");
			PrimeFaces.current().executeScript("changeInfo(\""+selectedProvince.getProvinceName()+"\", "+selectedProvince.getProvinceLng()+", "+
			selectedProvince.getProvinceLat()+")");
		}else {
			listDistrict = new ArrayList<>();
			listProvince = new ArrayList<>();
			listStreet = new ArrayList<>();
		}
		
		
	}
	District selectedDistrict;
	public void districtChange() {
		if (districtIdSelected != null && !districtIdSelected.equals("")) {
			processType = "2";
			streetIdSelected = "";
			segmentStreetIdSelected = "";
			
			selectedDistrict = listDistrict.stream().filter(x->x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + selectedDistrict.getDistrictLat() + ", " + selectedDistrict.getDistrictLng() + ");");
			PrimeFaces.current().executeScript("changeInfo(\""+selectedDistrict.getDistrictName()+"\", "+selectedDistrict.getDistrictLng()+", "+
		    selectedDistrict.getDistrictLat()+")");
			listSegmentOfStreet = selectedDistrict.getListSegmentOfStreet();
			if (listSegmentOfStreet != null)
			listStreet = listSegmentOfStreet.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
		}else {
			listStreet = new ArrayList<>();
			listSegmentOfStreet = new ArrayList<>();
		}
	}
	Street selectedStreet;
	public void streetChange() {
		if (streetIdSelected != null && !streetIdSelected.equals("")) {
			processType = "3";
			segmentStreetIdSelected = "";
			
			selectedStreet = listStreet.stream().filter(x->x.getStreetId().equals((Long.parseLong(streetIdSelected)))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + selectedStreet.getStreetLat() + ", " + selectedStreet.getStreetLng() + ");");
			PrimeFaces.current().executeScript("changeInfo(\""+selectedDistrict.getDistrictName()+"\", "+selectedStreet.getStreetLat()+", "+
					selectedStreet.getStreetLng()+")");
					
			listSegmentOfStreet = selectedStreet.getListSegmentOfStreet();			
		}else {
			listSegmentOfStreet = new ArrayList<>();
		}
		
	}
	SegmentOfStreet segmentOfStreet;
	public void segmentStreetChange() {
		if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
			processType = "4";
			segmentOfStreet = listSegmentOfStreet.stream().filter(x->x.getSegmentId().equals(Long.parseLong(segmentStreetIdSelected))).collect(Collectors.toList()).get(0);
//			PrimeFaces.current().executeScript("focusMap(" + segmentOfStreet.getSegmentLat() + ", " + segmentOfStreet.getSegmentLng() + ");");
//			PrimeFaces.current().executeScript("changeInfo(\""+segmentOfStreet.getSegmentName()+"\", "+segmentOfStreet.getSegmentLat()+", "+
//					segmentOfStreet.getSegmentLng()+")");
			List<FormedCoordinate> listFormedCoordinate = segmentOfStreet.getListFormedCoordinate();
			List<Coordinate> listCoordinate = listFormedCoordinate.stream()
					.map(x->{
						Coordinate coor = new Coordinate(x.getFormedLng(), x.getFormedLat());
						return coor;
					}).collect(Collectors.toList());
			int i = 1;
			i++;
			Gson gson = new Gson();
			jsonMultipleCoordinate = gson.toJson(listCoordinate);
		}else {
			
		}
	}

	
	Street streetTemp;
	
	public void addButtonClick() {
		FacesMessage msg;
		String error = findErrorInput();
		if (!error.equals("")) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", error);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		
		switch (processType) {
			case "1":
				Province province = new Province();
				province.setProvinceName(nameInput).setProvinceLat(Double.valueOf(latSingleCoordinate))
					.setProvinceLng(Double.valueOf(lngSingleCoordinate));
				province = provinceService.save(province);
				listProvince.add(province);
				break;
			case "2":
				District district = new District();
				district.setDistrictName(nameInput).setDistrictLat(Double.valueOf(latSingleCoordinate))
					.setDistrictLng(Double.valueOf(lngSingleCoordinate)).setProvince(selectedProvince);
				district = districtService.save(district);
				if (listDistrict == null) listDistrict = new ArrayList();
				listDistrict.add(district);
				
				break;
			case "3":
				Street street = new Street();
				street.setStreetName(nameInput).setStreetLat(Double.valueOf(latSingleCoordinate))
					.setStreetLng(Double.valueOf(lngSingleCoordinate));
				streetTemp = street;
				break;
			case "4":
//				if ()
				street = streetTemp;
				if (street != null) {
					street = streetService.save(streetTemp);
					if (listStreet == null) listStreet = new ArrayList();
					listStreet.add(street);
				}else {
					street = listStreet.stream().filter(x->x.getStreetId().equals(Long.valueOf(streetIdSelected))).collect(Collectors.toList()).get(0);
				}
				SegmentOfStreet segmentStreet = new SegmentOfStreet();
				segmentStreet.setStreet(street);
				
				JsonParser jsonParser = new JsonParser();
				JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonMultipleCoordinate);
				List<FormedCoordinate> listFormedCoordinate = new ArrayList();
				
				for (JsonElement jsonElement : jsonArray) {
					JsonObject temp = (JsonObject)jsonElement;
					segmentStreet.setSegmentLat(Double.parseDouble(temp.get("latitude").toString()))
						.setSegmentLng(Double.parseDouble(temp.get("longitude").toString()));
					break;
				}
				
				segmentStreet.setDistrict(selectedDistrict).setStreet(street)
					.setSegmentName(nameInput).setListFormedCoordinate(listFormedCoordinate);
				segmentStreet = segmentOfStreetService.save(segmentStreet);
				
				
				for (JsonElement jsonElement : jsonArray) {
					JsonObject temp = (JsonObject)jsonElement;
					FormedCoordinate coordinate = new FormedCoordinate()
							.setFormedLat(Double.parseDouble(temp.get("latitude").toString()))
							.setFormedLng(Double.parseDouble(temp.get("longitude").toString()))
							.setSegmentOfStreet(segmentStreet);
					listFormedCoordinate.add(coordinate);
				}
				if (listStreet == null) listStreet = new ArrayList<>();
				listStreet.add(street);
				listFormedCoordinate = formedCoordinateService.saveAll(listFormedCoordinate);
				listSegmentOfStreet.add(segmentStreet);
				PrimeFaces.current().executeScript("drawPath()");
				break;
			default:
				break;
		}
		
		msg = new FacesMessage("Thành công", "Thêm địa điểm thành công");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private String findErrorInput() {
		if (nameInput == null || nameInput.isEmpty()) {
			return "Tên không được để trống";
		}
		
		if (processType.equals("4")) {
			if (jsonMultipleCoordinate == null || jsonMultipleCoordinate.isEmpty()) {
				return "Tọa độ không được để trống";
			}
		}else if (lngSingleCoordinate == null || latSingleCoordinate ==null|| lngSingleCoordinate.isEmpty() || latSingleCoordinate.isEmpty()) {
			return "Tọa độ không được để trống";
		}

		return "";
	}
	public void displayMessage() {
		FacesMessage msg;
		msg = new FacesMessage("hello tuan");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void addNewRow() {
		listCoordinate.add(new Coordinate());
	}
	
	public String getNameInput() {
		return nameInput;
	}

	public void setNameInput(String nameInput) {
		this.nameInput = nameInput;
	}

	public List<District> getListDistrict() {
		return listDistrict;
	}

	public void setListDistrict(List<District> listDistrict) {
		this.listDistrict = listDistrict;
	}

	public String getProvinceIdSelected() {
		return provinceIdSelected;
	}

	public void setProvinceIdSelected(String provinceIdSelected) {
		this.provinceIdSelected = provinceIdSelected;
	}

	public List<Province> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<Province> listProvince) {
		this.listProvince = listProvince;
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

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
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
	
}
