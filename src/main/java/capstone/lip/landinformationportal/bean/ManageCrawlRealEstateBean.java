package capstone.lip.landinformationportal.bean;

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

import capstone.lip.landinformationportal.config.CrawlJob;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;

@Named
@ViewScoped
//@Configuration
//@EnableAutoConfiguration
public class ManageCrawlRealEstateBean{

	private String timerCrawl;
	
//	@Autowired
//	Scheduler scheduler;
	
//	private JobKey jobKey = new JobKey("crawlerJob", "crawler");
	
	@Autowired
	private ICrawlRealEstateService crawlReoService;
	
	private List<RealEstate> listRealEstate;
	
	@PostConstruct
	public void init() {
		
		timerCrawl = "";
		timerCrawl = crawlReoService.initCrawlJob();
//		try {
//			scheduler = new StdSchedulerFactory().getScheduler();
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		}
//		
//		JobDetail jobDetail;
//		try {
//			jobDetail = scheduler.getJobDetail(jobKey);
//			if (jobDetail == null) return;
//			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
//		    for (Trigger trigger : triggers) {
//		    	
//		        SimpleScheduleBuilder scheduleBuilder = (SimpleScheduleBuilder)trigger.getScheduleBuilder();
//		        if (scheduleBuilder != null) {
//		        	
//		        	Field privateStringField = SimpleScheduleBuilder.class.
//		        	            getDeclaredField("interval");
//
//		        	privateStringField.setAccessible(true);
//		        	Long fieldValue = ((Long) privateStringField.get(scheduleBuilder))/1000;
//		        	System.out.println("fieldValue = " + fieldValue);
//		        	timerCrawl = String.valueOf(fieldValue);
//		        }
//		    }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
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

	public String getTimerCrawl() {
		return timerCrawl;
	}

	public void setTimerCrawl(String timerCrawl) {
		this.timerCrawl = timerCrawl;
	}

}
