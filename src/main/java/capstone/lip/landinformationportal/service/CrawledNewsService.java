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
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String initCrawlJob() {
		try {
			String timeCrawl = "";
			JobDetail jobDetail;
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
		        	Long fieldValue = ((Long) privateStringField.get(scheduleBuilder))/3600000;
		        	System.out.println("fieldValue = " + fieldValue);
		        	timeCrawl = String.valueOf(fieldValue);
		        }
		    }
		    return timeCrawl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean setTimeCrawlJob(int value) {
		try {
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(value).repeatForever()).build();
	
			job = JobBuilder.newJob(CrawlNewsScheduleJob.class).withIdentity(jobKey).build();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean turnOnCrawler() {
		try {
			if (scheduler!= null) {
				scheduler.clear();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean turnOffCrawler() {
		try {
			if (scheduler!= null) {
				scheduler.clear();
				scheduler.standby();
//				scheduler.shutdown();
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean crawlNow() {
		JobKey jobKeyNow = JobKey.jobKey("crawlerNowJob", "crawler");
		JobDetail jobNow = JobBuilder.newJob(CrawlNewsNowJob.class).storeDurably(true).withIdentity(jobKeyNow).build();
		try {
			scheduler.addJob(jobNow, true);
			scheduler.triggerJob(jobKeyNow);
			scheduler.deleteJob(jobKeyNow);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CrawledNews> findAll() {
		try {
			return crawledNewsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CrawledNews> findByCrawledNewsStatus(String status) {
		try {
			return crawledNewsRepository.findByCrawledNewsStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrawledNews findByCrawledNewsLink(String link) {
		try {
			return crawledNewsRepository.findByCrawledNewsLink(link);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(CrawledNews news) {
		try {
			crawledNewsRepository.delete(news);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CrawledNews save(CrawledNews news) {
		try {
			return crawledNewsRepository.save(news);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<CrawledNews> findByCrawledNewsStatus(String status, Pageable page) {	
		try {
			return crawledNewsRepository.findByCrawledNewsStatus(status, page);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long count() {
		try {
			return crawledNewsRepository.count();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long countByStatus(String status) {
		try {
			return crawledNewsRepository.countByCrawledNewsStatus(status);
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public boolean delete(List<CrawledNews> listNews) {
		try {
			crawledNewsRepository.deleteAll(listNews);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CrawledNews> saveAll(List<CrawledNews> listNews) {
		try {
			return crawledNewsRepository.saveAll(listNews);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
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
