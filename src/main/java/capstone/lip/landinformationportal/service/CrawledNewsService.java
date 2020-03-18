package capstone.lip.landinformationportal.service;

import java.lang.reflect.Field;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.config.CrawlRealEstateNowJob;
import capstone.lip.landinformationportal.config.CrawlRealEstateScheduleJob;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.repository.CrawledNewsRepository;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;

@Service
public class CrawledNewsService implements ICrawledNewsService{

	private JobKey jobKey = new JobKey("crawlerJob", "crawler");
	
	@Autowired
	Scheduler scheduler;

	private Trigger trigger;
	private JobDetail job;
	
	@Autowired
	private CrawledNewsRepository crawledNewsRepository;
	
	@Override
	public List<CrawledNews> saveNews(List<CrawledNews> listCrawledNews) {
		return crawledNewsRepository.saveAll(listCrawledNews);
	}

	@Override
	public String initCrawlJob() {
		String timeCrawl = "";
		JobDetail jobDetail;
		try {
			//find current job if exist
			jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail == null) return timeCrawl;
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
		    for (Trigger trigger : triggers) {
		    	
		        SimpleScheduleBuilder scheduleBuilder = (SimpleScheduleBuilder)trigger.getScheduleBuilder();
		        if (scheduleBuilder != null) {
		        	
		        	Field privateStringField = SimpleScheduleBuilder.class.
		        	            getDeclaredField("interval");

		        	privateStringField.setAccessible(true);
		        	Long fieldValue = ((Long) privateStringField.get(scheduleBuilder))/1000;
		        	System.out.println("fieldValue = " + fieldValue);
		        	timeCrawl = String.valueOf(fieldValue);
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeCrawl;
	}

	@Override
	public void setTimeCrawlJob(int value) {

		trigger = TriggerBuilder.newTrigger().withIdentity("crawlerTriggler", "crawler")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(value).repeatForever()).build();

		job = JobBuilder.newJob(CrawlRealEstateScheduleJob.class).withIdentity("crawlerJob", "crawler").build();
	}

	@Override
	public void turnOnCrawler() {
		try {
			if (scheduler!= null) {
				scheduler.clear();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void turnOffCrawler() {
		try {
			if (scheduler!= null) {
				scheduler.standby();
				scheduler.shutdown();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void crawlNow() {
		JobKey jobKeyNow = JobKey.jobKey("crawlerNowJob", "crawler");
		JobDetail jobNow = JobBuilder.newJob(CrawlRealEstateNowJob.class).storeDurably(true).withIdentity("crawlerNowJob", "crawler").build();
		try {
			scheduler.addJob(jobNow, true);
			scheduler.getContext().put("crawlnow", "true");
			scheduler.triggerJob(jobKeyNow);
			scheduler.deleteJob(jobKeyNow);
			scheduler.getContext().remove("crawlnow");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CrawledNews> findAll() {
		return crawledNewsRepository.findAll();
	}

	@Override
	public List<CrawledNews> findByCrawledNewsStatus(Integer status) {
		return crawledNewsRepository.findByCrawledNewsStatus(status);
	}

	@Override
	public CrawledNews findByCrawledNewsLink(String link) {
		return crawledNewsRepository.findByCrawledNewsLink(link);
	}
}
