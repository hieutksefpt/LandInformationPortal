package capstone.lip.landinformationportal.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

@Named
@ViewScoped
//@SessionScoped
public class ManageCrawlRealEstateSetRoadSegment implements Serializable{
	private static final long serialVersionUID = 1L;

	private RealEstate realEstate;
	private boolean showPopup;
	
	@Autowired
	private IRealEstateService realEstateService;
	
	public void showPopup(Long realEstateId) {
		realEstate = realEstateService.findById(realEstateId);
		showPopup = true;
		PrimeFaces.current().executeScript("initMap("+realEstate.getRealEstateLat()+", "+realEstate.getRealEstateLng()+")");
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
	
	
}
