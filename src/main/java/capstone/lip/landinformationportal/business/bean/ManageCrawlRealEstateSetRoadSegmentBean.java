package capstone.lip.landinformationportal.business.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import capstone.lip.landinformationportal.business.service.Interface.IPredictPriceService;
import capstone.lip.landinformationportal.business.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.business.service.Interface.IStreetService;
import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.dto.Coordinate;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.FormedCoordinate;
import capstone.lip.landinformationportal.common.entity.Province;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;
import capstone.lip.landinformationportal.common.entity.compositekey.RealEstateAdjacentSegmentId;

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
	private boolean coordinateConfuse;
	private boolean segmentConfuse;
	private String singleLat;
	private String singleLng;
	
	@Autowired
	private IRealEstateService realEstateService;
	
	@Autowired 
	private IProvinceService provinceService;
	
	@Autowired
	private IRealEstateAdjacentSegmentService realEstateAdjacentSegmentService;
	
	@Autowired
	private IPredictPriceService predictService;

	@Autowired
	private IStreetService streetService;
	
	public void showPopup(Long realEstateId) {
		realEstate = realEstateService.findById(realEstateId);
		showPopup = true;
		PrimeFaces.current().executeScript("initMap("+realEstate.getRealEstateLat()+", "+realEstate.getRealEstateLng()+")");
		listProvince = provinceService.findAll();
		listDistrict = null;
		listStreet = null;
		listSegment = null;
		listAdjacentSegment = realEstate.getListRealEstateAdjacentSegment();
		provinceIdSelected = "";
		districtIdSelected = "";
		streetIdSelected = "";
		roadSegmentIdSelected = "";
		singleLat = "";
		singleLng = "";
		
		if (realEstate.getListRealEstateAdjacentSegment() != null && !realEstate.getListRealEstateAdjacentSegment().isEmpty()) {
			segmentConfuse = false;
		}else {
			segmentConfuse = true;
		}
		List<RealEstate> listRealEstateByCoordinate = realEstateService.findByRealEstateLatAndRealEstateLng(realEstate.getRealEstateLat(), realEstate.getRealEstateLng());
		if (listRealEstateByCoordinate == null || (listRealEstateByCoordinate!=null && listRealEstateByCoordinate.isEmpty())) {
			coordinateConfuse = false;
		}else {
			if (listRealEstateByCoordinate.size()>1) {
				coordinateConfuse = true;
			}
		}
		
	}
	public void provinceChange() {
		if (provinceIdSelected.isEmpty()) {
			listDistrict = new ArrayList<>();
			listSegment = new ArrayList<>();
			listStreet = new ArrayList<>();
			return;
		}
		Province provinceSelected = listProvince.stream().filter(x->x.getProvinceId().toString().equals(provinceIdSelected))
				.collect(Collectors.toList()).get(0);
		listDistrict = provinceSelected.getListDistrict();
		
		Collections.sort(listDistrict, new Comparator<District>() {

			@Override
			public int compare(District o1, District o2) {
				return o1.getDistrictName().compareTo(o2.getDistrictName());
			}
			
		});
	}
	public void districtChange() {
		if (districtIdSelected.isEmpty()) {
			listSegment = new ArrayList<>();
			listStreet = new ArrayList<>();
			return;
		}
		District districtSelected = listDistrict.stream().filter(x->x.getDistrictId().toString().equals(districtIdSelected))
				.collect(Collectors.toList()).get(0);
		List<SegmentOfStreet> listSegmentByDistrict = districtSelected.getListSegmentOfStreet();
//		listStreet = listSegmentByDistrict.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
		
		listStreet = streetService.findStreetByDistrictId(districtSelected.getDistrictId());
		
		Collections.sort(listStreet, new Comparator<Street>() {

			@Override
			public int compare(Street o1, Street o2) {
				return o1.getStreetName().compareTo(o2.getStreetName());
			}
			
		});
		
	}
	public void streetChange() {
		if (streetIdSelected.isEmpty()) {
			listSegment = new ArrayList<>();
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
		try {
			Double.parseDouble(singleLat);
			Double.parseDouble(singleLng);
		}catch (Exception e) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Tọa độ không hợp lệ");
			return;
		}
		List<RealEstate> listRealEstateByCoordinate = realEstateService.findByRealEstateLatAndRealEstateLng(Double.parseDouble(singleLat), Double.parseDouble(singleLng));
		if (listRealEstateByCoordinate == null || (listRealEstateByCoordinate!=null && listRealEstateByCoordinate.isEmpty())) {
			coordinateConfuse = false;
		}else {
			if (listRealEstateByCoordinate.size()>1) {
				coordinateConfuse = true;
				setMessage(FacesMessage.SEVERITY_ERROR, "Tọa độ bị trùng");
				return;
			}else {
				coordinateConfuse = false;
			}
		}
		
		
		
		if (listAdjacentSegment == null || listAdjacentSegment.isEmpty()) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Chưa chọn đoạn đường");
			return;
		} 
		
		realEstateAdjacentSegmentService.save(listAdjacentSegment);
		
		realEstate = realEstateService.findById(realEstate.getRealEstateId());
		
		SegmentOfStreet segmentAdj = listSegment.stream()
				.filter(x->x.getSegmentId().toString().equals(roadSegmentIdSelected)).collect(Collectors.toList()).get(0);
		Street streetAdj = segmentAdj.getStreet();
    	District districtAdj = segmentAdj.getDistrict();
    	Province provinceAdj = districtAdj.getProvince();
    	String newAddress = segmentAdj.getSegmentName()+", "+streetAdj.getStreetName()+", "+districtAdj.getDistrictName()+", "+provinceAdj.getProvinceName();
    	realEstate.setRealEstateAddress(newAddress);
    	
		realEstate.setRealEstateLng(Double.parseDouble(singleLng));
		realEstate.setRealEstateLat(Double.parseDouble(singleLat));
		realEstate.setRealEstateStatus(String.valueOf(StatusRealEstateConstant.VERIFIED));
		
		realEstate = realEstateService.save(realEstate);
		try {
			predictService.addDataToModel(realEstate);	
		}catch(Exception e) {
			System.out.println("Cannot submit data to predict service");
		}
		
		setMessage(FacesMessage.SEVERITY_INFO, "Chỉnh sửa thành công");
		PrimeFaces.current().executeScript("PF('alert').renderMessage({\"summary\":\"Thành công\",\"detail\":\"Chỉnh sửa thành công\",\"severity\":\"info\"})");
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
			setMessage(FacesMessage.SEVERITY_ERROR, "Chưa chọn đoạn đường");
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
		if (listAdjacentSegment.size()>=1) {
			realEstate.setRealEstateAddress(segment.getSegmentName()+", "+segment.getStreet().getStreetName()+
					", "+segment.getDistrict().getDistrictName()+segment.getDistrict().getProvince().getProvinceName() );
		}
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
	public boolean isCoordinateConfuse() {
		return coordinateConfuse;
	}
	public void setCoordinateConfuse(boolean coordinateConfuse) {
		this.coordinateConfuse = coordinateConfuse;
	}
	public boolean isSegmentConfuse() {
		return segmentConfuse;
	}
	public void setSegmentConfuse(boolean segmentConfuse) {
		this.segmentConfuse = segmentConfuse;
	}
	public String getSingleLat() {
		return singleLat;
	}
	public void setSingleLat(String singleLat) {
		this.singleLat = singleLat;
	}
	public String getSingleLng() {
		return singleLng;
	}
	public void setSingleLng(String singleLng) {
		this.singleLng = singleLng;
	}
	
	
}
