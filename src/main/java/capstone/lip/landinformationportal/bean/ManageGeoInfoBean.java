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
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.service.Interface.IDistrictService;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.service.Interface.ISegmentOfStreetService;

@Named
@ViewScoped
public class ManageGeoInfoBean {

	@Autowired
	private IProvinceService provinceService;

	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private ISegmentOfStreetService segmentOfStreetService;
	
	
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
	@PostConstruct
	public void init() {
		processType = "1";
		listProvince = new ArrayList<Province>();
		listProvince = provinceService.findAll();
	}

	public void provinceChange() {
		if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
			Province selectedProvince = listProvince.stream().filter(x -> x.getProvinceId().equals(Long.parseLong(provinceIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + selectedProvince.getProvinceLat() + ", " + selectedProvince.getProvinceLng() + ");");
			
			// listDistrict = provinceService.getListDistrictByProvinceId(Long.parseLong(provinceIdSelected));
			listDistrict = selectedProvince.getListDistrict();
		}else {
			listDistrict = new ArrayList<>();
			listProvince = new ArrayList<>();
			listStreet = new ArrayList<>();
		}
	}
	public void districtChange() {
		if (districtIdSelected != null && !districtIdSelected.equals("")) {
			District selectedDistrict = listDistrict.stream().filter(x->x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + selectedDistrict.getDistrictLat() + ", " + selectedDistrict.getDistrictLng() + ");");
			
			listSegmentOfStreet = selectedDistrict.getListSegmentOfStreet();
			listStreet = segmentOfStreetService.getListStreetByListSegment(listSegmentOfStreet);
		}else {
			listStreet = new ArrayList<>();
			listSegmentOfStreet = new ArrayList<>();
		}
	}
	public void streetChange() {
		if (streetIdSelected != null && !streetIdSelected.equals("")) {
			Street streetSelected = listStreet.stream().filter(x->x.getStreetId().equals((Long.parseLong(streetIdSelected)))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + streetSelected.getStreetLat() + ", " + streetSelected.getStreetLng() + ");");
			
			listSegmentOfStreet = streetSelected.getListSegmentOfStreet();			
		}else {
			listSegmentOfStreet = new ArrayList<>();
		}
		
	}
	public void segmentStreetChange() {
//		if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
//			listDistrict = provinceService.getListDistrictByProvinceId(Long.parseLong(provinceIdSelected));
//		}else {
//			listDistrict = new ArrayList<>();
//		}
	}
	public void addButtonClick() {
		switch (processType) {
			case "1":
				Province province = new Province();
				province.setProvinceName(nameInput);
				
				provinceService.save(province);
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;

		default:
			break;
		}
	}
	public void displayLocation() {
		FacesMessage msg;
		msg = new FacesMessage("hello tuan");
//		if (city != null && country != null)
//			msg = new FacesMessage("Selected", city + " of " + country);
//		else
//			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");

		FacesContext.getCurrentInstance().addMessage(null, msg);
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
	
}
