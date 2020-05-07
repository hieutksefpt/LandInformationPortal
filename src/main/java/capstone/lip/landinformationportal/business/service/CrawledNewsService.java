package capstone.lip.landinformationportal.business.service;

import java.lang.reflect.Field;
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

import capstone.lip.landinformationportal.business.repository.CrawledNewsRepository;
import capstone.lip.landinformationportal.business.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.business.specification.CrawledNewsSpecifications;
import capstone.lip.landinformationportal.business.specification.SearchCriteria;
import capstone.lip.landinformationportal.business.validation.CrawledNewsValidation;
import capstone.lip.landinformationportal.common.config.CrawlNewsNowJob;
import capstone.lip.landinformationportal.common.config.CrawlNewsScheduleJob;
import capstone.lip.landinformationportal.common.constant.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.entity.CrawledNews;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

@Service
public class CrawledNewsService implements ICrawledNewsService {

	private JobKey jobKey = new JobKey("crawlerNewsJob", "crawlerNews");
	private TriggerKey triggerKey = new TriggerKey("crawlerNewsTriggler", "crawlerNews");
	private JobKey jobKeyNow = new JobKey("crawlerNewsNowJob", "crawlerNews");

	@Autowired
	Scheduler scheduler;

	private Trigger trigger;
	private JobDetail job;

	@Autowired
	private CrawledNewsRepository crawledNewsRepository;

	@Override
	public String initCrawlJob() {
		try {
			String timeCrawl = "";
			JobDetail jobDetail;
			// find current job if exist
			jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail == null)
				return timeCrawl;
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
			for (Trigger trigger : triggers) {

				SimpleScheduleBuilder scheduleBuilder = (SimpleScheduleBuilder) trigger.getScheduleBuilder();
				if (scheduleBuilder != null) {

					Field privateStringField = SimpleScheduleBuilder.class.getDeclaredField("interval");

					privateStringField.setAccessible(true);
					Long fieldValue = ((Long) privateStringField.get(scheduleBuilder)) / 3600000;
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
			if (value == 0) {
				throw new Exception("time crawl job can not be zero");
			}
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(value).repeatForever())
					.build();

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
			if (scheduler != null) {
				if (!scheduler.isStarted()) {
					scheduler.start();
				}
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
			if (scheduler != null) {
				scheduler.deleteJob(jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean crawlNow() {
		try {
			if (scheduler != null) {
				if (!scheduler.isStarted()) {
					scheduler.start();
				}
			}
			JobDetail jobNow = JobBuilder.newJob(CrawlNewsNowJob.class).storeDurably(true).withIdentity(jobKeyNow)
					.build();
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
	public boolean delete(CrawledNews news) {
		try {
			CrawledNewsValidation crawledNewsValidation = new CrawledNewsValidation();
			String error = crawledNewsValidation.isValidCrawledNewsOne(news);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			crawledNewsRepository.delete(news);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<CrawledNews> findByCrawledNewsStatus(String status, Pageable page) {
		try {
			if (status == null || (status != null && status.isEmpty())) {
				throw new Exception("Status can not be null or empty");
			}
			if (status.equals(StatusCrawledNewsConstant.DISPLAY)
					|| status.equals(StatusCrawledNewsConstant.NON_DISPLAY)) {

			} else {
				throw new Exception("Status must be display or non display");
			}
			return crawledNewsRepository.findByCrawledNewsStatus(status, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long countByStatus(String status) {
		try {
			if (status == null || (status != null && status.isEmpty())) {
				throw new Exception("Status can not be null or empty");
			}
			if (status.equals(StatusCrawledNewsConstant.DISPLAY)
					|| status.equals(StatusCrawledNewsConstant.NON_DISPLAY)) {

			} else {
				throw new Exception("Status must be display or non display");
			}
			return crawledNewsRepository.countByCrawledNewsStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean delete(List<CrawledNews> listNews) {
		try {
			CrawledNewsValidation crawledNewsValidation = new CrawledNewsValidation();
			String error = crawledNewsValidation.isValidCrawledNews(listNews);
			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			for (CrawledNews news : listNews) {
				Optional<CrawledNews> newsTemp = crawledNewsRepository.findById(news.getCrawledNewsID());
				if (!newsTemp.isPresent()) {
					throw new Exception("can't delete if news ID is not exist");
				}
			}
			crawledNewsRepository.deleteAll(listNews);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CrawledNews> saveAll(List<CrawledNews> listNews) {
		try {
			CrawledNewsValidation crawledNewsValidation = new CrawledNewsValidation();
			String error = crawledNewsValidation.isValidCrawledNews(listNews);

			if (!error.isEmpty()) {
				throw new Exception(error);
			}
			return crawledNewsRepository.saveAll(listNews);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrawledNews findById(Long newsId) {
		try {
			if (newsId == null) {
				throw new Exception("userId can not be null");
			}
			Optional<CrawledNews> news = crawledNewsRepository.findById(newsId);
			if (!news.isPresent()) {
				return null;
			}
			return news.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrawledNews findByCrawledNewsLink(String link) {
		try {
			CrawledNews news = crawledNewsRepository.findByCrawledNewsLink(link);
			return news;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<CrawledNews> findAllByAttribute(Map<String, Object> listAttribute, Pageable page) {
		try {
			if (listAttribute == null) {
				throw new Exception();
			}
			List<CrawledNewsSpecifications> listSpec = new ArrayList();
			if (listAttribute != null) {
				for (Map.Entry meta : listAttribute.entrySet()) {
					String key = (String) meta.getKey();
					String value = (String) meta.getValue();
					if (key.equals("crawledNewsTitle")) {
						listSpec.add(new CrawledNewsSpecifications(new SearchCriteria(key, ":", value)));
					}else {
						listSpec.add(new CrawledNewsSpecifications(new SearchCriteria(key, ":=", value)));;
					}
				}
			}
			if (!listSpec.isEmpty()) {
				Page<CrawledNews> temp = crawledNewsRepository.findAll(createSpecification(listSpec), page);
				if (temp == null) {
					throw new Exception();
				}
				return temp;
			}
			return crawledNewsRepository.findAll(page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long countByAttribute(Map<String, Object> listAttribute) {
		try {
			if (listAttribute == null) {
				throw new Exception();
			}
			List<CrawledNewsSpecifications> listSpec = new ArrayList();
			if (listAttribute != null) {
				for (Map.Entry meta : listAttribute.entrySet()) {
					String key = (String) meta.getKey();
					String value = (String) meta.getValue();
					if (key.equals("crawledNewsTitle")) {
						listSpec.add(new CrawledNewsSpecifications(new SearchCriteria(key, ":", value)));
					}else {
						listSpec.add(new CrawledNewsSpecifications(new SearchCriteria(key, ":=", value)));;
					}
				}
			}
			if (!listSpec.isEmpty()) {
				return crawledNewsRepository.count(createSpecification(listSpec));
			}
			return crawledNewsRepository.count();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private Specification<CrawledNews> createSpecification(List<CrawledNewsSpecifications> listSpec) {
		if (listSpec == null) {
			return null;
		}
		Specification<CrawledNews> spec = Specification.where(listSpec.get(0));
		for (int i = 1; i < listSpec.size(); i++) {
			spec = spec.and(listSpec.get(i));
		}
		return spec;
	}

}
