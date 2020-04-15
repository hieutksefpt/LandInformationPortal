package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.dto.LazyCrawledRealEstate;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

@Named
@ViewScoped
public class ManageCrawlRealEstateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String timerCrawl;

	@Autowired
	private IRealEstateService realEstateService;
	
	@Autowired
	private ICrawlRealEstateService crawlReoService;
	
	private LazyDataModel<RealEstate> lazyReo;
	
	private boolean statusCrawlSchedule;
	@PostConstruct
	public void init() {
		
		timerCrawl = "";
		timerCrawl = crawlReoService.initCrawlJob();
		if (timerCrawl.isEmpty()) {
			statusCrawlSchedule = false;
		}
		else {
			statusCrawlSchedule = true;
		}
		
		lazyReo = new LazyCrawledRealEstate(realEstateService);
	}
	
	public void setTimerButtonClick() {
		try {
			int timer= Integer.valueOf(timerCrawl);
			crawlReoService.setTimeCrawlJob(timer*60*60);
		}catch(Exception e) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Thời gian không phù hợp");
		}
	}

	public void turnOffCrawler() {
		crawlReoService.turnOffCrawler();
		statusCrawlSchedule = false;
		
	}
	public void turnOnCrawler() {
		if (timerCrawl.isEmpty()) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Chưa cài đặt thời gian");
			return;
		}
		crawlReoService.initCrawlJob();
		crawlReoService.turnOnCrawler();
		statusCrawlSchedule = true; 
		int i= 1;
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
	public void crawlNow() {
		crawlReoService.crawlNow();
	}

	public String getTimerCrawl() {
		return timerCrawl;
	}

	public void setTimerCrawl(String timerCrawl) {
		this.timerCrawl = timerCrawl;
	}

	public void refreshData() {
		lazyReo = new LazyCrawledRealEstate(realEstateService);
	}
	
	public LazyDataModel<RealEstate> getLazyReo() {
		return lazyReo;
	}

	public void setLazyReo(LazyDataModel<RealEstate> lazyReo) {
		this.lazyReo = lazyReo;
	}

	public boolean isStatusCrawlSchedule() {
		return statusCrawlSchedule;
	}

	public void setStatusCrawlSchedule(boolean statusCrawlSchedule) {
		this.statusCrawlSchedule = statusCrawlSchedule;
	}

}
