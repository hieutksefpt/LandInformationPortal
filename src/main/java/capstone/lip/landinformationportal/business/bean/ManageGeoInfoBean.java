package capstone.lip.landinformationportal.business.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import capstone.lip.landinformationportal.business.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.business.service.Interface.IFormedCoordinate;
import capstone.lip.landinformationportal.business.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.business.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.business.service.Interface.IStreetService;
import capstone.lip.landinformationportal.business.validation.StreetValidation;
import capstone.lip.landinformationportal.common.dto.Coordinate;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.FormedCoordinate;
import capstone.lip.landinformationportal.common.entity.Province;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;

import java.io.Serializable;

@Named
@ViewScoped
public class ManageGeoInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private String vt1;
	private String vt2;
	private String vt3;
	private String vt4;
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
			Collections.sort(listDistrict, new Comparator<District>() {

				@Override
				public int compare(District o1, District o2) {
					return o1.getDistrictName().compareTo(o2.getDistrictName());
				}
				
			});
			
			PrimeFaces.current().executeScript("focusMap(" + selectedProvince.getProvinceLat() + ", " + selectedProvince.getProvinceLng() + ",15);");
		}else {
			listDistrict = new ArrayList<>();
			listStreet = new ArrayList<>();
			listSegmentOfStreet = new ArrayList<>();
		}
		
		
	}
	District selectedDistrict;
	public void districtChange() {
		if (districtIdSelected != null && !districtIdSelected.equals("")) {
			processType = "2";
			streetIdSelected = "";
			segmentStreetIdSelected = "";
			
			selectedDistrict = listDistrict.stream().filter(x->x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + selectedDistrict.getDistrictLat() + ", " + selectedDistrict.getDistrictLng() + ",17);");
			PrimeFaces.current().executeScript("changeInfo(\""+selectedDistrict.getDistrictName()+"\", "+selectedDistrict.getDistrictLng()+", "+
		    selectedDistrict.getDistrictLat()+")");
				
//			List<SegmentOfStreet>listTemp = selectedDistrict.getListSegmentOfStreet();
//			if (listTemp != null)
//				listStreet = listTemp.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
//			else {
//				listStreet = new ArrayList<>();
//			}
			
			listStreet = streetService.findStreetByDistrictId(selectedDistrict.getDistrictId());
			
			Collections.sort(listStreet, new Comparator<Street>() {

				@Override
				public int compare(Street o1, Street o2) {
					return o1.getStreetName().compareTo(o2.getStreetName());
				}
				
			});
			
			if (selectedStreet != null)
				listStreet.add(selectedStreet);
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
			PrimeFaces.current().executeScript("focusMap(" + selectedStreet.getStreetLat() + ", " + selectedStreet.getStreetLng() + ",19);");
			PrimeFaces.current().executeScript("changeInfo(\""+selectedStreet.getStreetName()+"\", "+selectedStreet.getStreetLng()+", "+
					selectedStreet.getStreetLat()+")");
					
			listSegmentOfStreet = selectedStreet.getListSegmentOfStreet();	
			listSegmentOfStreet = listSegmentOfStreet.stream().filter(x->x.getDistrict().equals(selectedDistrict)).collect(Collectors.toList());
		}else {
			listSegmentOfStreet = new ArrayList<>();
		}
		
	}
	SegmentOfStreet selectedSegmentOfStreet;
	public void segmentStreetChange() {
		if (segmentStreetIdSelected != null && !segmentStreetIdSelected.equals("")) {
			processType = "4";
			selectedSegmentOfStreet = listSegmentOfStreet.stream().filter(x->x.getSegmentId().equals(Long.parseLong(segmentStreetIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + selectedSegmentOfStreet.getSegmentLat() + ", " + selectedSegmentOfStreet.getSegmentLng() + ",19);");
			PrimeFaces.current().executeScript("changeInfo(\""+selectedSegmentOfStreet.getSegmentName()+"\", "+selectedSegmentOfStreet.getSegmentLng()+", "+
					selectedSegmentOfStreet.getSegmentLat()+")");
			vt1 = selectedSegmentOfStreet.getVT1().toString();
			vt2 = selectedSegmentOfStreet.getVT2().toString();
			vt3 = selectedSegmentOfStreet.getVT3().toString();
			vt4 = selectedSegmentOfStreet.getVT4().toString();
			PrimeFaces.current().executeScript("updateDeleteOld()");
			List<FormedCoordinate> listFormedCoordinate = selectedSegmentOfStreet.getListFormedCoordinate();
			List<Coordinate> listCoordinate = listFormedCoordinate.stream()
					.map(x->{
						Coordinate coor = new Coordinate(x.getFormedLng(), x.getFormedLat());
						return coor;
					}).collect(Collectors.toList());
			Gson gson = new Gson();
			jsonMultipleCoordinate = gson.toJson(listCoordinate);
		}else {
			
		}
	}

	
	Street streetTemp;
	
	public void addButtonClick() {
		FacesMessage msg = new FacesMessage();
		String error = findErrorInput("Add");
		PrimeFaces.current().executeScript("renderTable()");
		
		if (!error.equals("")) {
			setMessage(FacesMessage.SEVERITY_ERROR, error);
			return;
		}
		
		
		
		switch (processType) {
			case "1":
				Province province = new Province();
				province.setProvinceName(nameInput).setProvinceLat(Double.valueOf(latSingleCoordinate))
					.setProvinceLng(Double.valueOf(lngSingleCoordinate));
				province = provinceService.save(province);
				if (province == null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra");
					return;
				}
//				listProvince = provinceService.findAll();
				listProvince.add(province);
				break;
			case "2":
				District district = new District();
				if (selectedProvince == null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Phải chọn tỉnh thành trước");
					return;
				}
				district.setDistrictName(nameInput).setDistrictLat(Double.valueOf(latSingleCoordinate))
					.setDistrictLng(Double.valueOf(lngSingleCoordinate)).setProvince(selectedProvince);
				district = districtService.save(district);
				if (district == null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra");
					return;
				}
				if (listDistrict == null) listDistrict = new ArrayList<>();
				listDistrict.add(district);
				
				break;
			case "3":
				if (selectedProvince == null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Phải chọn tỉnh thành trước");
					return;
				}
				Street street = new Street();
				if (selectedDistrict== null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Phải chọn quận huyện trước");
					return;
				}
				street.setStreetName(nameInput).setStreetLat(Double.valueOf(latSingleCoordinate))
					.setStreetLng(Double.valueOf(lngSingleCoordinate));
				streetTemp = street;
				StreetValidation streetValidate = new StreetValidation();
				if (!streetValidate.isValidStreet(street).isEmpty()) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra");
					return;
				}
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg = new FacesMessage("Lưu ý", "Hãy thêm 1 đoạn đường trên đường này");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			case "4":
				street = streetTemp;
				boolean addNewStreet = false;
				if (street != null && !listStreet.contains(street)) {
					addNewStreet = true;
					street = streetService.save(streetTemp);
					if (street == null) {
						setMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra");
						return;
					}
					if (listStreet == null) listStreet = new ArrayList<>();
					listStreet.add(street);
					streetIdSelected = street.getStreetId().toString();
				}else {
					addNewStreet = false;
					street = listStreet.stream().filter(x->x.getStreetId().equals(Long.valueOf(streetIdSelected))).collect(Collectors.toList()).get(0);
				}
				SegmentOfStreet segmentStreet = new SegmentOfStreet();
				segmentStreet.setStreet(street)
					.setDistrict(selectedDistrict)
					.setSegmentName(nameInput)
					.setVT1(Double.valueOf(vt1))
					.setVT2(Double.valueOf(vt2))
					.setVT3(Double.valueOf(vt3))
					.setVT4(Double.valueOf(vt4));
				List<FormedCoordinate> listFormedCoordinate = setListFormedCoordinate(segmentStreet);
				
				segmentStreet = segmentOfStreetService.save(segmentStreet);
				if (segmentStreet == null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra");
					return;
				}
				
				listFormedCoordinate = formedCoordinateService.saveAll(segmentStreet.getListFormedCoordinate());
				if (listFormedCoordinate == null) {
					setMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra");
					return;
				}
				if (addNewStreet) {
					listSegmentOfStreet = new ArrayList();
					listSegmentOfStreet.add(segmentStreet);
				}else {
					listSegmentOfStreet.add(segmentStreet);
				}
				
				PrimeFaces.current().executeScript("drawPath()");
				break;
			default:
				break;
		}
		msg = new FacesMessage("Thành công", "Thêm địa điểm thành công");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private List<FormedCoordinate> setListFormedCoordinate(SegmentOfStreet segmentStreet) {
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonMultipleCoordinate);
		List<FormedCoordinate> listFormedCoordinate = new ArrayList<>();
		
		for (JsonElement jsonElement : jsonArray) {
			JsonObject temp = (JsonObject)jsonElement;
			segmentStreet.setSegmentLat(Double.parseDouble(temp.get("latitude").toString()))
				.setSegmentLng(Double.parseDouble(temp.get("longitude").toString()));
			break;
		}
				
		
		
		for (JsonElement jsonElement : jsonArray) {
			JsonObject temp = (JsonObject)jsonElement;
			FormedCoordinate coordinate = new FormedCoordinate()
					.setFormedLat(Double.parseDouble(temp.get("latitude").toString()))
					.setFormedLng(Double.parseDouble(temp.get("longitude").toString()))
					.setSegmentOfStreet(segmentStreet);
			listFormedCoordinate.add(coordinate);
		}
		
		jsonMultipleCoordinate = "";
		
		segmentStreet.setListFormedCoordinate(listFormedCoordinate);
		
		return listFormedCoordinate;
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
	
	public void deleteButtonClick() {
		switch (processType) {
		case "1":
			try {
				Long selected = Long.parseLong(provinceIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn tỉnh thành");
				return;
			}
			Province province = provinceService.findById(Long.valueOf(provinceIdSelected));	
			provinceService.delete(province);
			this.listProvince = listProvince.stream().filter(x->!x.getProvinceId().equals(Long.valueOf(provinceIdSelected))).collect(Collectors.toList());
			provinceIdSelected = "";
			setMessage(FacesMessage.SEVERITY_INFO, "Xóa thành công");
			break;
		case "2":
			try {
				Long selected = Long.parseLong(districtIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn quận huyện");
				return;
			}
			District district = districtService.findById(Long.parseLong(districtIdSelected));
			districtService.delete(district);
			
			this.listDistrict = this.listDistrict.stream().filter(x->!x.getDistrictId().equals(Long.valueOf(districtIdSelected))).collect(Collectors.toList());
			districtIdSelected = "";
			setMessage(FacesMessage.SEVERITY_INFO, "Xóa thành công");
			break;
		case "3":
			try {
				Long selected = Long.parseLong(streetIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn đường phố");
				return;
			}
			Street street = streetService.findById(Long.parseLong(streetIdSelected));
			streetService.delete(street);
			this.listStreet = this.listStreet.stream().filter(x->!x.getStreetId().equals(Long.parseLong(streetIdSelected))).collect(Collectors.toList());
			streetIdSelected = "";
			break;
		case "4":
			try {
				Long selected = Long.parseLong(segmentStreetIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn đoạn đường");
				return;
			}
			SegmentOfStreet segment = segmentOfStreetService.findById(Long.parseLong(segmentStreetIdSelected));
			Street streetBySegment = segment.getStreet();
			
			segmentOfStreetService.delete(segment);
			
			if (this.listSegmentOfStreet.size() == 1) {
				System.out.println("----------------delete street--------------");
				this.listStreet = this.listStreet.stream().filter(x->!x.getStreetId().equals(streetBySegment.getStreetId())).collect(Collectors.toList());
				streetIdSelected = "";
			}
			
			
			
			this.listSegmentOfStreet = this.listSegmentOfStreet.stream().filter(x->!x.getSegmentId().equals(Long.parseLong(segmentStreetIdSelected))).collect(Collectors.toList());
			segmentStreetIdSelected = "";
			setMessage(FacesMessage.SEVERITY_INFO, "Xóa thành công");
			break;
		default:
			break;
		}
	}
	public void updateButtonClick() {
		
		FacesMessage msg = new FacesMessage();
		String error = findErrorInput("Update");
		PrimeFaces.current().executeScript("renderTable()");
		
		if (!error.equals("")) {
			setMessage(FacesMessage.SEVERITY_ERROR, error);
			return;
		}
		
		switch (processType) {
		case "1":
			try {
				Long selected = Long.parseLong(provinceIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn tỉnh thành");
				return;
			}
			Province province = listProvince.stream().filter(x->x.getProvinceId().equals(Long.valueOf(provinceIdSelected))).collect(Collectors.toList()).get(0);
			province.setProvinceName(nameInput)
				.setProvinceLat(Double.parseDouble(latSingleCoordinate))
				.setProvinceLng(Double.parseDouble(lngSingleCoordinate));
			provinceService.save(province);
			for (Province element:listProvince) {
				if (element.equals(province)) {
					element = province;
					break;
				}
			}
			setMessage(FacesMessage.SEVERITY_INFO, "Cập nhật thành công");
			break;
		case "2":
			try {
				Long selected = Long.parseLong(districtIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn quận huyện");
				return;
			}
			District district = this.listDistrict.stream().filter(x->x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
			district.setDistrictName(nameInput)
				.setDistrictLat(Double.parseDouble(latSingleCoordinate))
				.setDistrictLng(Double.parseDouble(lngSingleCoordinate));
			district = districtService.save(district);
			for (District element:listDistrict) {
				if (element.equals(district)) {
					element = district;
					break;
				}
			}
			setMessage(FacesMessage.SEVERITY_INFO, "Cập nhật thành công");
			break;
		case "3":
			try {
				Long selected = Long.parseLong(streetIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn đường phố");
				return;
			}
			Street street = this.listStreet.stream().filter(x->x.getStreetId().equals(Long.parseLong(streetIdSelected))).collect(Collectors.toList()).get(0);
			street.setStreetName(nameInput)
				.setStreetLat(Double.parseDouble(latSingleCoordinate))
				.setStreetLng(Double.parseDouble(lngSingleCoordinate));
			streetService.save(street);
			for (Street element:listStreet) {
				if (element.equals(street)) {
					element = street;
					break;
				}
			}
			setMessage(FacesMessage.SEVERITY_INFO, "Cập nhật thành công");
			break;
		case "4":
			try {
				Long selected = Long.parseLong(segmentStreetIdSelected);
			}catch(Exception ex){
				setMessage(FacesMessage.SEVERITY_ERROR, "Chưa lựa chọn đoạn đường");
				return;
			}
			SegmentOfStreet segment = this.listSegmentOfStreet.stream().filter(x->x.getSegmentId().equals(Long.parseLong(segmentStreetIdSelected))).collect(Collectors.toList()).get(0);
			segment.setSegmentName(nameInput)
				.setVT1(Double.parseDouble(vt1))
				.setVT2(Double.parseDouble(vt2))
				.setVT3(Double.parseDouble(vt3))
				.setVT4(Double.parseDouble(vt4));
			
			List<FormedCoordinate> listFormed = segment.getListFormedCoordinate();
			formedCoordinateService.delete(listFormed);
			segment.getListFormedCoordinate().clear();
			listFormed = setListFormedCoordinate(segment);
			
			segment = segmentOfStreetService.save(segment);
			listFormed = formedCoordinateService.saveAll(listFormed);
			for (SegmentOfStreet element:listSegmentOfStreet) {
				if (element.equals(segment)) {
					element = segment;
					break;
				}
			}
			PrimeFaces.current().executeScript("drawPath()");
			if (listFormed == null) {
				setMessage(FacesMessage.SEVERITY_ERROR, "Cập nhật không thành công");
			}else {
				setMessage(FacesMessage.SEVERITY_INFO, "Cập nhật thành công");
			}
			break;
		default:
			break;
		}
	}
	public void resetButtonClick() {
		processType = "1";
		nameInput = "";
		lngSingleCoordinate = "";
		latSingleCoordinate = "";
	}
	private String findErrorInput(String action) {
		if (nameInput == null || nameInput.isEmpty()) {
			return "Tên không được để trống";
		}
		if (processType.equals("1")) {
			List<Province> listTempProvince = listProvince.stream().filter(x->!x.equals(selectedProvince)).collect(Collectors.toList());
			
			if (!listProvince.stream().filter(x->x.getProvinceName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Add"))) {
				return "Trùng tên";
			}
			
			if (!listTempProvince.stream().filter(x->x.getProvinceName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Update"))) {
				return "Trùng tên";
			}
		}
		if (processType.equals("2")) {
			List<District> listDistrictTemp = listDistrict.stream().filter(x->!x.equals(selectedDistrict)).collect(Collectors.toList());
			
			if (!listDistrict.stream().filter(x->x.getDistrictName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Add"))) {
				return "Trùng tên";
			}
			
			if (!listDistrictTemp.stream().filter(x->x.getDistrictName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Update"))) {
				return "Trùng tên";
			}
		}
		if (processType.equals("3")) {
			List<Street> listStreetTemp = listStreet.stream().filter(x->!x.equals(selectedStreet)).collect(Collectors.toList());
			
			if (!listStreet.stream().filter(x->x.getStreetName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Add"))) {
				return "Trùng tên";
			}
			
			if (!listStreetTemp.stream().filter(x->x.getStreetName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Update"))) {
				return "Trùng tên";
			}
		}
		if (processType.equals("4")) {
			List<SegmentOfStreet> listSegmentTemp = listSegmentOfStreet.stream().filter(x->!x.equals(selectedSegmentOfStreet)).collect(Collectors.toList());
			
			if (!listSegmentOfStreet.stream().filter(x->x.getSegmentName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Add") )) {
				return "Trùng tên";
			}
			
			if (!listSegmentTemp.stream().filter(x->x.getSegmentName().equalsIgnoreCase(nameInput)).collect(Collectors.toList()).isEmpty()
					&& (action.equals("Update"))) {
				return "Trùng tên";
			}
		}
		if (!processType.equals("4")) {
			if (lngSingleCoordinate == null || latSingleCoordinate ==null 
					|| (lngSingleCoordinate!= null && lngSingleCoordinate.isEmpty()) || (latSingleCoordinate!=null && latSingleCoordinate.isEmpty())) {
				return "Tọa độ không được để trống";
			}
			try {
				Double.parseDouble(lngSingleCoordinate);
				Double.parseDouble(latSingleCoordinate);
			}catch(Exception e) {
				return "Tọa độ không hợp lệ";
			}
		}
		if (processType.equals("4")) {
			if (vt1 == null || vt2 == null || vt3 == null || vt4 == null) {
				return "Bảng giá đất không được để trống";
			}
			try {
				Double.parseDouble(vt1);
				Double.parseDouble(vt2);
				Double.parseDouble(vt3);
				Double.parseDouble(vt4);
			}catch(Exception e) {
				return "Giá trị bảng giá đất không hợp lệ";
			}
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

	public String getVt1() {
		return vt1;
	}

	public void setVt1(String vt1) {
		this.vt1 = vt1;
	}

	public String getVt2() {
		return vt2;
	}

	public void setVt2(String vt2) {
		this.vt2 = vt2;
	}

	public String getVt3() {
		return vt3;
	}

	public void setVt3(String vt3) {
		this.vt3 = vt3;
	}

	public String getVt4() {
		return vt4;
	}

	public void setVt4(String vt4) {
		this.vt4 = vt4;
	}
	
}
