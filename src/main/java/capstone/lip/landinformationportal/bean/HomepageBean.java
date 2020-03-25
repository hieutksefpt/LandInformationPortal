package capstone.lip.landinformationportal.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.faces.component.UICommand;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.google.gson.Gson;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.dto.Pagination;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.FormedCoordinate;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

@Named
@ViewScoped
public class HomepageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICrawledNewsService crawledNewService;	
	@Autowired
	private IRealEstateService realEstateService;
	@Autowired
	private IProvinceService provinceService;
	
	private Pagination pageNews;
	private Pagination pageReo;
	
	private List<CrawledNews> listNews;
	private List<Province> listProvince;
	private List<District> listDistrict;
	private List<SegmentOfStreet> listSegmentOfStreet;
	private List<Street> listStreet;
	private String provinceIdSelected;
	private String districtIdSelected;
	private String streetIdSelected;
	private String segmentIdSelected;
	
	private Province provinceSelected;
	private District districtSelected;
	private SegmentOfStreet segmentSelected;
	private Street streetSelected;
	
	private List<RealEstate> listRealEstate;
	private String typeReo;
	private boolean isDisplayReoPanel;
	@PostConstruct
	public void init() {
		pageNews = new Pagination()
				.setTotalRow((int)crawledNewService.countByStatus(StatusCrawledNewsConstant.DISPLAY))
				.setRowsPerPage(10)
				.setPageRange(3)
				.setCurrentPage(0);
		pageNews.setTotalPages(pageNews.getTotalRow()/pageNews.getRowsPerPage());
		
		openPageNews(0);

		isDisplayReoPanel = false;
		districtIdSelected = provinceIdSelected = segmentIdSelected = streetIdSelected = "";
		listProvince = provinceService.findAll();
	}
	
	private void openPageNews(int page) {
		pageNews.setCurrentPage(page);
		Page<CrawledNews> listNewsPage = crawledNewService.findByCrawledNewsStatus(StatusCrawledNewsConstant.DISPLAY, 
				PageRequest.of(pageNews.getCurrentPage(), pageNews.getRowsPerPage()));

		listNews = listNewsPage.stream().map(x->x).collect(Collectors.toList());
	}
	
	public void openPageReo(int page) {
		isDisplayReoPanel = true;
		if (typeReo == null) {
			setTypeReo("0");
		}
		pageReo.setCurrentPage(page);
		Page<RealEstate> listPageReo = null;
		String address = districtSelected.getDistrictName();
		if (streetSelected != null) {
			address = streetSelected.getStreetName();
		}
		if (segmentSelected != null) {
			address = segmentSelected.getSegmentName();
		}
		switch (typeReo) {
		case "0":
			listPageReo = realEstateService.listFilterRealEstateByAddress(address, 
					PageRequest.of(pageReo.getCurrentPage(), pageReo.getRowsPerPage()));
			break;
		case "1":
			listPageReo = realEstateService.listFilterRealEstateByAddressAndSource(address, 
					StatusRealEstateConstant.CONTRIBUTOR,
					PageRequest.of(pageReo.getCurrentPage(), pageReo.getRowsPerPage()));
			break;
		case "2":
			listPageReo = realEstateService.listFilterRealEstateByAddressAndSourceNot(address, 
					StatusRealEstateConstant.CONTRIBUTOR,
					PageRequest.of(pageReo.getCurrentPage(), pageReo.getRowsPerPage()));
			break;
		default:
			break;
		}
		if (listPageReo != null)
			listRealEstate = listPageReo.stream().map(x->x).collect(Collectors.toList());
		List<Coordinate> listCoordinate = listRealEstate.stream().map(x->{
			return new Coordinate().setId(x.getRealEstateId())
					.setLatitude(x.getRealEstateLat())
					.setLongitude(x.getRealEstateLng())
					.setSource(x.getRealEstateSource());
		}).collect(Collectors.toList());
		Gson gson = new Gson();
		PrimeFaces.current().executeScript("drawListMarker("+gson.toJson(listCoordinate)+")");
	}

	public void provinceChange() {
		listDistrict = provinceSelected.getListDistrict();
		districtIdSelected = "";
		listSegmentOfStreet= new ArrayList();
		segmentIdSelected = "";
		listStreet = new ArrayList();
		streetIdSelected = "";
	}
	public void districtChange() {
		List<SegmentOfStreet>listTemp = districtSelected.getListSegmentOfStreet();
		listStreet = listTemp.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
		
		streetIdSelected = "";
		listSegmentOfStreet = new ArrayList();
		segmentIdSelected = "";
				
		openPageReo(0);
	}
	public void streetChange() {
		listSegmentOfStreet = streetSelected.getListSegmentOfStreet();
		segmentIdSelected = "";
		openPageReo(0);
	}
	public void segmentOfStreetChange() {
//		TODO
	}
	public void goToDetails(long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/viewrealestatedetail.xhtml?realEstateId=" + realEstateId);
    }
	public void firstPageNews() {
		openPageNews(0);
	}
	public void lastPageNews() {
		openPageNews(pageNews.getTotalPages()-1);
	}

	public void pageNewsAt(ActionEvent event) {
		openPageNews(((Integer) ((UICommand) event.getComponent()).getValue() - 1));
	}
	
	public void pageReoAt(ActionEvent event) {
		openPageReo(((Integer) ((UICommand) event.getComponent()).getValue() - 1));
	}
	public void firstPageReo() {
		openPageReo(0);
	}
	public void lastPageReo() {
		openPageReo(pageReo.getTotalPages()-1);
	}
	
	public Pagination getPageNews() {
		return pageNews;
	}

	public void setPageNews(Pagination pageNews) {
		this.pageNews = pageNews;
	}
	
	public List<CrawledNews> getListNews() {
		return listNews;
	}

	public void setListNews(List<CrawledNews> listNews) {
		this.listNews = listNews;
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

	public Province getProvinceSelected() {
		return provinceSelected;
	}

	public void setProvinceSelected(Province provinceSelected) {
		this.provinceSelected = provinceSelected;
	}

	public String getProvinceIdSelected() {
		return provinceIdSelected;
	}

	public void setProvinceIdSelected(String provinceIdSelected) {
		this.provinceIdSelected = provinceIdSelected;
		if (provinceIdSelected != null && !provinceIdSelected.isEmpty()) {
			provinceSelected = listProvince.stream()
				.filter(x -> x.getProvinceId().equals(Long.parseLong(provinceIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + provinceSelected.getProvinceLat() + ", " + provinceSelected.getProvinceLng() + ");");
			
		}
	}

	public String getDistrictIdSelected() {
		return districtIdSelected;
	}

	public void setDistrictIdSelected(String districtIdSelected) {
		this.districtIdSelected = districtIdSelected;
		if (districtIdSelected != null && !districtIdSelected.isEmpty()) {
			districtSelected = listDistrict.stream()
				.filter(x -> x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + districtSelected.getDistrictLat() + ", " + districtSelected.getDistrictLng() + ");");
		}
	}

	public String getStreetIdSelected() {
		return streetIdSelected;
	}

	public void setStreetIdSelected(String streetIdSelected) {
		this.streetIdSelected = streetIdSelected;
		if (streetIdSelected != null && !streetIdSelected.isEmpty()) {
			streetSelected = listStreet.stream()
				.filter(x -> x.getStreetId().equals(Long.parseLong(streetIdSelected))).collect(Collectors.toList()).get(0);
			PrimeFaces.current().executeScript("focusMap(" + streetSelected.getStreetLat() + ", " + streetSelected.getStreetLng() + ");");
		}
	}

	public String getSegmentIdSelected() {
		return segmentIdSelected;
	}

	public void setSegmentIdSelected(String segmentIdSelected) {
		this.segmentIdSelected = segmentIdSelected;
		if (segmentIdSelected != null && !segmentIdSelected.isEmpty()) {
			segmentSelected = listSegmentOfStreet.stream()
				.filter(x -> x.getSegmentId().equals(Long.parseLong(segmentIdSelected))).collect(Collectors.toList()).get(0);
			List<FormedCoordinate> listFormedCoordinate = segmentSelected.getListFormedCoordinate();
			List<Coordinate> listCoordinate = listFormedCoordinate.stream().map(x->{
				return new Coordinate(x.getFormedLng(), x.getFormedLat());
			}).collect(Collectors.toList());
			Gson gson = new Gson();
			PrimeFaces.current().executeScript("drawPath(" + gson.toJson(listCoordinate) + ");");
			
		}
	}

	public District getDistrictSelected() {
		return districtSelected;
	}

	public void setDistrictSelected(District districtSelected) {
		this.districtSelected = districtSelected;
	}

	public SegmentOfStreet getSegmentSelected() {
		return segmentSelected;
	}

	public void setSegmentSelected(SegmentOfStreet segmentSelected) {
		this.segmentSelected = segmentSelected;
	}

	public Street getStreetSelected() {
		return streetSelected;
	}

	public void setStreetSelected(Street streetSelected) {
		this.streetSelected = streetSelected;
	}

	public List<RealEstate> getListRealEstate() {
		return listRealEstate;
	}

	public void setListRealEstate(List<RealEstate> listRealEstate) {
		this.listRealEstate = listRealEstate;
	}

	public String getTypeReo() {
		return typeReo;
	}

	public void setTypeReo(String typeReo) {
		if (typeReo == null) typeReo = "0";
		this.typeReo = typeReo;
		switch (typeReo) {
		case "0":
			pageReo = new Pagination()
					.setTotalRow((int)realEstateService.countByRealEstateAddress(districtSelected.getDistrictName()))
					.setRowsPerPage(10)
					.setPageRange(3)
					.setCurrentPage(0);
			pageReo.setTotalPages(pageReo.getTotalRow() / pageReo.getRowsPerPage());
			break;
		case "1":
			pageReo = new Pagination()
					.setTotalRow((int)realEstateService.countByRealEstateSource(districtSelected.getDistrictName(),StatusRealEstateConstant.CONTRIBUTOR))
					.setRowsPerPage(10)
					.setPageRange(3)
					.setCurrentPage(0);
			pageReo.setTotalPages(pageReo.getTotalRow() / pageReo.getRowsPerPage());
			break;
		case "2":
			pageReo = new Pagination()
					.setTotalRow((int)realEstateService.countByRealEstateSourceNot(districtSelected.getDistrictName(),StatusRealEstateConstant.CONTRIBUTOR))
					.setRowsPerPage(10)
					.setPageRange(3)
					.setCurrentPage(0);
			pageReo.setTotalPages(pageReo.getTotalRow() / pageReo.getRowsPerPage());
			break;
		default:
			break;
		}
	}

	public Pagination getPageReo() {
		return pageReo;
	}

	public void setPageReo(Pagination pageReo) {
		this.pageReo = pageReo;
	}

	public boolean isDisplayReoPanel() {
		return isDisplayReoPanel;
	}

	public void setDisplayReoPanel(boolean isDisplayReoPanel) {
		this.isDisplayReoPanel = isDisplayReoPanel;
	}
	
	
	
	
}
