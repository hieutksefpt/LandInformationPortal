package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.config.CrawlRealEstateScheduleJob;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

@Named
@ViewScoped
//@Configuration
//@EnableAutoConfiguration
public class ManageCrawlRealEstateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String timerCrawl;

	@Autowired
	private IRealEstateService realEstateService;
	
	@Autowired
	private ICrawlRealEstateService crawlReoService;
	
	private List<RealEstate> listRealEstate;
	
	@PostConstruct
	public void init() {
		
		timerCrawl = "";
		timerCrawl = crawlReoService.initCrawlJob();
		listRealEstate = realEstateService.findByRealEstateStatus(String.valueOf(StatusRealEstateConstant.NOT_VERIFIED));
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
	
}
