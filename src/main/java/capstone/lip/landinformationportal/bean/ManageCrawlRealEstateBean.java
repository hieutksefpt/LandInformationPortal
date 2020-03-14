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

@Named
@ViewScoped
@Configuration
@EnableAutoConfiguration
public class ManageCrawlRealEstateBean{

	private String timerCrawl;
	@Autowired
	Scheduler scheduler;
	
	private JobKey jobKey = new JobKey("crawlerJob", "crawler");
	
	@PostConstruct
	public void init() {
		
		timerCrawl = "";
//		try {
//			scheduler = new StdSchedulerFactory().getScheduler();
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		}
		
		JobDetail jobDetail;
		try {
			jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail == null) return;
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
		    for (Trigger trigger : triggers) {
		    	
		        SimpleScheduleBuilder scheduleBuilder = (SimpleScheduleBuilder)trigger.getScheduleBuilder();
		        if (scheduleBuilder != null) {
		        	
		        	Field privateStringField = SimpleScheduleBuilder.class.
		        	            getDeclaredField("interval");

		        	privateStringField.setAccessible(true);
		        	Long fieldValue = (Long) privateStringField.get(scheduleBuilder);
		        	System.out.println("fieldValue = " + fieldValue);
		        	timerCrawl = String.valueOf(fieldValue);
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	Trigger trigger;
	JobDetail job;
	
	public void setTimerButtonClick() {
		
		int timer= Integer.valueOf(timerCrawl);
		
		trigger = TriggerBuilder.newTrigger().withIdentity("crawlerTriggler", "crawler")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(timer).repeatForever()).build();

		job = JobBuilder.newJob(CrawlJob.class).withIdentity("crawlerJob", "crawler").build();
	}

	public void turnOffCrawler() {
		try {
			if (scheduler!= null) {
				scheduler.standby();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void turnOnCrawler() {
		try {
			if (scheduler!= null) {
				scheduler.clear();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTimerCrawl() {
		return timerCrawl;
	}

	public void setTimerCrawl(String timerCrawl) {
		this.timerCrawl = timerCrawl;
	}

}
