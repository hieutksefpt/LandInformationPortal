package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
	
	private List<RealEstate> listRealEstate;
	
	private LazyDataModel<RealEstate> lazyReo;
	@PostConstruct
	public void init() {
		
		timerCrawl = "";
		timerCrawl = crawlReoService.initCrawlJob();
		listRealEstate = realEstateService.findByRealEstateStatus(String.valueOf(StatusRealEstateConstant.NOT_VERIFIED));
		
		lazyReo = new LazyCrawledRealEstate(realEstateService);
	}
	
	public void setTimerButtonClick() {
		int timer= Integer.valueOf(timerCrawl);
		crawlReoService.setTimeCrawlJob(timer);
	}

	public void turnOffCrawler() {
		crawlReoService.turnOffCrawler();
	}
	public void turnOnCrawler() {
		crawlReoService.turnOnCrawler();
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
		listRealEstate = realEstateService.findByRealEstateStatus(String.valueOf(StatusRealEstateConstant.NOT_VERIFIED));
		int i = 1;
		i++;
	}
	public List<RealEstate> getListRealEstate() {
		return listRealEstate;
	}


	public void setListRealEstate(List<RealEstate> listRealEstate) {
		this.listRealEstate = listRealEstate;
	}

	public LazyDataModel<RealEstate> getLazyReo() {
		return lazyReo;
	}

	public void setLazyReo(LazyDataModel<RealEstate> lazyReo) {
		this.lazyReo = lazyReo;
	}
	
}
