package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegmentId;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.service.RealEstateService;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

@Named
@ViewScoped
//@SessionScoped
public class ManageCrawlRealEstateSetRoadSegmentBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private RealEstate realEstate;
	private boolean showPopup;
	private String provinceIdSelected;
	private String districtIdSelected;
	private String streetIdSelected;
	private String roadSegmentIdSelected;
	private List<Province> listProvince;
	private List<District> listDistrict;
	private List<Street> listStreet;
	private List<SegmentOfStreet> listSegment;
	private List<RealEstateAdjacentSegment> listAdjacentSegment;
	@Autowired
	private IRealEstateService realEstateService;
	
	@Autowired 
	private IProvinceService provinceService;
	
	@Autowired
	private IRealEstateAdjacentSegmentService realEstateAdjacentSegmentService;

	public void showPopup(Long realEstateId) {
		realEstate = realEstateService.findById(realEstateId);
		showPopup = true;
		PrimeFaces.current().executeScript("initMap("+realEstate.getRealEstateLat()+", "+realEstate.getRealEstateLng()+")");
		listProvince = provinceService.findAll();
		listDistrict = null;
		listStreet = null;
		listSegment = null;
		listAdjacentSegment = null;
		provinceIdSelected = "";
		districtIdSelected = "";
		streetIdSelected = "";
		roadSegmentIdSelected = "";
	}
	public void provinceChange() {
		if (provinceIdSelected.isEmpty()) {
			return;
		}
		Province provinceSelected = listProvince.stream().filter(x->x.getProvinceId().toString().equals(provinceIdSelected))
				.collect(Collectors.toList()).get(0);
		listDistrict = provinceSelected.getListDistrict();
	}
	public void districtChange() {
		if (districtIdSelected.isEmpty()) {
			return;
		}
		District districtSelected = listDistrict.stream().filter(x->x.getDistrictId().toString().equals(districtIdSelected))
				.collect(Collectors.toList()).get(0);
		List<SegmentOfStreet> listSegmentByDistrict = districtSelected.getListSegmentOfStreet();
		listStreet = listSegmentByDistrict.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
	}
	public void streetChange() {
		if (streetIdSelected.isEmpty()) {
			return;
		}
		Street streetSelected = listStreet.stream().filter(x->x.getStreetId().toString().equals(streetIdSelected))
				.collect(Collectors.toList()).get(0);
		listSegment = streetSelected.getListSegmentOfStreet();
	}
	public void segmentStreetChange() {
		if (roadSegmentIdSelected.isEmpty()) {
			return;
		}
		SegmentOfStreet segment = listSegment.stream()
				.filter(x->x.getSegmentId().toString().equals(roadSegmentIdSelected)).collect(Collectors.toList()).get(0);
		List<FormedCoordinate> listFormedCoordinate = segment.getListFormedCoordinate();
		List<Coordinate> listCoordinate = listFormedCoordinate.stream()
				.map(x->{
					Coordinate coor = new Coordinate(x.getFormedLng(), x.getFormedLat());
					return coor;
				}).collect(Collectors.toList());
		Gson gson = new Gson();
		String json = gson.toJson(listCoordinate);
		PrimeFaces.current().executeScript("drawPath('"+json+"')");
	}
	public void saveSegmentToReo() {
		if (listAdjacentSegment == null || listAdjacentSegment.isEmpty()) {
			return;
		} 
		
		realEstateAdjacentSegmentService.save(listAdjacentSegment);
		realEstate.setRealEstateStatus(String.valueOf(StatusRealEstateConstant.VERIFIED));
		realEstateService.save(realEstate);
		setMessage(FacesMessage.SEVERITY_INFO, "Chỉnh sửa thành công");
		showPopup = false;
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
	public void addSegment() {
		if (roadSegmentIdSelected.isEmpty()) {
			return;
		} 
		if (listAdjacentSegment == null) {
			listAdjacentSegment = new ArrayList<RealEstateAdjacentSegment>();
		}
		SegmentOfStreet segment = listSegment.stream()
				.filter(x->x.getSegmentId().toString().equals(roadSegmentIdSelected)).collect(Collectors.toList()).get(0);
		
		RealEstateAdjacentSegment reoAdj = new RealEstateAdjacentSegment()
				.setId(new RealEstateAdjacentSegmentId(segment.getSegmentId(), realEstate.getRealEstateId()))
				.setRealEstate(realEstate)
				.setSegmentOfStreet(segment);
		if (!listAdjacentSegment.contains(reoAdj)) {
			listAdjacentSegment.add(reoAdj);
		}
	}
	public void hidePopup() {
		showPopup = false;
	}
	
	public RealEstate getRealEstate() {
		return realEstate;
	}
	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}
	public boolean isShowPopup() {
		return showPopup;
	}
	public void setShowPopup(boolean showPopup) {
		this.showPopup = showPopup;
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
	public String getRoadSegmentIdSelected() {
		return roadSegmentIdSelected;
	}
	public void setRoadSegmentIdSelected(String roadSegmentIdSelected) {
		this.roadSegmentIdSelected = roadSegmentIdSelected;
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
	public List<Street> getListStreet() {
		return listStreet;
	}
	public void setListStreet(List<Street> listStreet) {
		this.listStreet = listStreet;
	}
	public List<SegmentOfStreet> getListSegment() {
		return listSegment;
	}
	public void setListSegment(List<SegmentOfStreet> listSegment) {
		this.listSegment = listSegment;
	}
	public List<RealEstateAdjacentSegment> getListAdjacentSegment() {
		return listAdjacentSegment;
	}
	public void setListAdjacentSegment(List<RealEstateAdjacentSegment> listAdjacentSegment) {
		this.listAdjacentSegment = listAdjacentSegment;
	}
	
	
}
