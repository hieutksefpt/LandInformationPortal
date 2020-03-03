package capstone.lip.landinformationportal.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.service.IDistrictService;
import capstone.lip.landinformationportal.service.IProvinceService;

@Named
@ViewScoped
public class ManageGeoInfoBean {

	@Autowired
	private IProvinceService provinceService;

	@Autowired
	private IDistrictService districtService;
	
	private List<Province> listProvince;
	private List<District> listDistrict;
	private List<SegmentOfStreet> listSegmentOfStreet;
	private List<Street> listStreet;
	
	
	private String provinceIdSelected;
	private String districtIdSelected;
	private String streetIdSelected;
	private String segmentStreetIdSelected;
	private String processType;
	@PostConstruct
	public void init() {
		processType = "1";
		listProvince = new ArrayList<Province>();
		listProvince = provinceService.findAll();
	}

	public void provinceChange() {
		if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
			listDistrict = provinceService.getListDistrictByProvinceId(Long.parseLong(provinceIdSelected));
		}else {
			listDistrict = new ArrayList<>();
		}
	}
	public void districtChange() {
		if (districtIdSelected != null && !districtIdSelected.equals("")) {
			listSegmentOfStreet = districtService.getListSegmentOfStreet(Long.parseLong(districtIdSelected));
		}else {
			listSegmentOfStreet = new ArrayList<>();
		}
	}
	public void streetChange() {
		if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
			listDistrict = provinceService.getListDistrictByProvinceId(Long.parseLong(provinceIdSelected));
		}else {
			listDistrict = new ArrayList<>();
		}
	}
	public void segmentStreetChange() {
		if (provinceIdSelected != null && !provinceIdSelected.equals("")) {
			listDistrict = provinceService.getListDistrictByProvinceId(Long.parseLong(provinceIdSelected));
		}else {
			listDistrict = new ArrayList<>();
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
