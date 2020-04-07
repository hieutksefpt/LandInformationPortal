package capstone.lip.landinformationportal.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.config.CrawlNewsNowJob;
import capstone.lip.landinformationportal.config.CrawlNewsScheduleJob;
import capstone.lip.landinformationportal.dto.NewsCrawl;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.repository.CrawledNewsRepository;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;

@Service
public class CrawledNewsService implements ICrawledNewsService{

	private JobKey jobKey = new JobKey("crawlerNewsJob", "crawlerNews");
	private TriggerKey triggerKey = new TriggerKey("crawlerNewsTriggler", "crawlerNews");
	
	@Autowired
	Scheduler scheduler;

	private Trigger trigger;
	private JobDetail job;
	
	@Autowired
	private CrawledNewsRepository crawledNewsRepository;
	
	@Override
	public List<CrawledNews> save(List<NewsCrawl> listCrawledNews) {
		List list = new ArrayList();
		for (NewsCrawl element : listCrawledNews) {
			CrawledNews news = new CrawledNews()
					.setCrawledNewsLink(element.getLink())
					.setCrawledNewsShortDescription(element.getDescription())
					.setCrawledNewsTitle(element.getTitle())
					.setCrawledNewsWebsite(element.getDomain())
					.setCrawledNewsTime(element.getDate())
					.setCrawledNewsImageUrl(element.getImageLink())
					.setCrawledNewsStatus(StatusCrawledNewsConstant.NON_DISPLAY);
			if (crawledNewsRepository.findByCrawledNewsLink(element.getLink()) == null) {
				list.add(news);
			}
		}
		return crawledNewsRepository.saveAll(list);
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

		trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(value).repeatForever()).build();

		job = JobBuilder.newJob(CrawlNewsScheduleJob.class).withIdentity(jobKey).build();
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
				scheduler.clear();
				scheduler.standby();
//				scheduler.shutdown();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void crawlNow() {
		JobKey jobKeyNow = JobKey.jobKey("crawlerNowJob", "crawler");
		JobDetail jobNow = JobBuilder.newJob(CrawlNewsNowJob.class).storeDurably(true).withIdentity(jobKeyNow).build();
		try {
			scheduler.addJob(jobNow, true);
			scheduler.triggerJob(jobKeyNow);
			scheduler.deleteJob(jobKeyNow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CrawledNews> findAll() {
		return crawledNewsRepository.findAll();
	}

	@Override
	public List<CrawledNews> findByCrawledNewsStatus(String status) {
		return crawledNewsRepository.findByCrawledNewsStatus(status);
	}

	@Override
	public CrawledNews findByCrawledNewsLink(String link) {
		return crawledNewsRepository.findByCrawledNewsLink(link);
	}

	@Override
	public void delete(CrawledNews news) {
		crawledNewsRepository.delete(news);
	}

	@Override
	public CrawledNews save(CrawledNews news) {
		return crawledNewsRepository.save(news);
	}

	@Override
	public Page<CrawledNews> findByCrawledNewsStatus(String status, Pageable page) {
		return crawledNewsRepository.findByCrawledNewsStatus(status, page);
	}

	@Override
	public long count() {
		return crawledNewsRepository.count();
	}

	@Override
	public long countByStatus(String status) {
		return crawledNewsRepository.countByCrawledNewsStatus(status);
	}

	@Override
	public void delete(List<CrawledNews> listNews) {
		crawledNewsRepository.deleteAll(listNews);
	}

	@Override
	public List<CrawledNews> saveAll(List<CrawledNews> listNews) {
		return crawledNewsRepository.saveAll(listNews);
	}

	@Override
	public CrawledNews findById(Long newsId) {
		Optional<CrawledNews> news = crawledNewsRepository.findById(newsId);
		if (news.isPresent()) {
			return news.get();
		}
		return null;
	}
}
