package capstone.lip.landinformationportal.service;

import java.lang.reflect.Field;

import java.util.ArrayList;
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

import capstone.lip.landinformationportal.common.HousesFeatureNameConstant;
import capstone.lip.landinformationportal.common.LandsFeatureNameConstant;
import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.config.CrawlRealEstateNowJob;
import capstone.lip.landinformationportal.config.CrawlRealEstateScheduleJob;
import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesDetailId;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsDetailId;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.repository.UserRepository;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;

@Service
public class CrawlRealEstateService implements ICrawlRealEstateService{
	
	@Autowired
	private RealEstateRepository realEstateRepository;
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private LandRepository landRepository;
	
	@Autowired
	private HousesFeatureRepository housesFeatureRepository;
	
	@Autowired
	private LandsFeatureRepository landsFeatureRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HousesDetailRepository housesDetailRepository;
	
	@Autowired
	private LandsDetailRepository landsDetailRepository;
	
	private JobKey jobKey = new JobKey("crawlerJob", "crawler");
	@Autowired
	Scheduler scheduler;
	
	private List<HousesFeature> listHouseFeature;
	private List<LandsFeature> listLandsFeature;
	
	private Trigger trigger;
	private JobDetail job;
	
	@Override
	public boolean saveRealEstateCrawl(List<RealEstateObjectCrawl> listReoCrawl) {
		try {
			listHouseFeature = housesFeatureRepository.findAll();
			listLandsFeature = landsFeatureRepository.findAll();
			User user = userRepository.findAll().get(0);
			int i = 1;
			for (RealEstateObjectCrawl reoCrawl : listReoCrawl) {
				RealEstate reo = new RealEstate();
				String link = reoCrawl.getLink();
				RealEstate reoSearch = realEstateRepository.findByRealEstateLink(link);
				System.out.println(i+" "+link);
				i++;
				if (reoSearch != null) {
					continue;
				}
	
				reo.setRealEstateName(reoCrawl.getTitle())
					.setRealEstateLat(reoCrawl.getLatitude())
					.setRealEstateLng(reoCrawl.getLongitude())
					.setRealEstatePrice(reoCrawl.getPrice())
					.setRealEstateSource(reoCrawl.getDomain())
					.setRealEstateLink(reoCrawl.getLink())
					.setRealEstateAddress(reoCrawl.getAddress())
					.setUser(user)
					.setRealEstateStatus(String.valueOf(StatusRealEstateConstant.CONFUSED));
				reo.setCreateDate(reoCrawl.getStartDatePost());
				reo = realEstateRepository.save(reo);
				House house = new House();
				house.setRealEstate(reo)
					.setHouseName(reoCrawl.getTitle())
					.setHousePrice(0D);
				Land land = new Land();
				land.setRealEstate(reo)
					.setLandName(reoCrawl.getTitle())
					.setLandPrice(0D);
				
				house = houseRepository.save(house);
				land = landRepository.save(land);
				
				List<HousesDetail> listHousesDetail = parseDataToListHouseDetail(reoCrawl, house);
				List<LandsDetail> listLandsDetail = parseDataToListLandDetail(reoCrawl, land);
				house.setListHousesDetail(listHousesDetail);
				land.setListLandsDetail(listLandsDetail);
				List<House> listHouse = new ArrayList<House>();listHouse.add(house);
				
				reo.setListHouse(listHouse);
				reo.setLand(land);
				
	
				housesDetailRepository.saveAll(listHousesDetail);
				landsDetailRepository.saveAll(listLandsDetail);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private String getStringCheckNull(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
	private String getStringCheckNull(Double string) {
		if (string == null) {
			return "";
		}
		return string.toString();
	}
	private List<HousesDetail> parseDataToListHouseDetail(RealEstateObjectCrawl reoCrawl, House house) {
		try {
			List<HousesDetail> listHouseDetail = new ArrayList<HousesDetail>();
			for (HousesFeature housesFeature: listHouseFeature) {
				switch (housesFeature.getHousesFeatureName()) {
				case HousesFeatureNameConstant.numberFloors:
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getNumberFloor().toString())));
					break;
				case HousesFeatureNameConstant.numberBedrooms:
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getNumberBedrooms().toString())));
					break;
				case HousesFeatureNameConstant.homeDirection:
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getHomeDirection())));
					break;
				case HousesFeatureNameConstant.numberToilets:
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getNumberToilets().toString())));
					break;
				case HousesFeatureNameConstant.balconyDirection:
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getBalconyDirection())));
					break;
				case HousesFeatureNameConstant.projectOwner:
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getProjectOwner())));
					break;
				default:
					break;
				}
			}
			return listHouseDetail;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private List<LandsDetail> parseDataToListLandDetail(RealEstateObjectCrawl reoCrawl, Land land) {
		try {
			List<LandsDetail> listLandDetail = new ArrayList<LandsDetail>();
			for(LandsFeature landsFeature:listLandsFeature) {
				switch (landsFeature.getLandsFeatureName()) {
				case LandsFeatureNameConstant.area:
					listLandDetail.add(new LandsDetail()
							.setLand(land)
							.setId(new LandsDetailId().setLandId(land.getLandId()).setLandsFeatureId(landsFeature.getLandsFeatureID()))
							.setLandsFeature(landsFeature)
							.setValue(getStringCheckNull(reoCrawl.getArea().toString())));
					break;
				case LandsFeatureNameConstant.wardin:
					listLandDetail.add(new LandsDetail()
							.setLand(land)
							.setId(new LandsDetailId().setLandId(land.getLandId()).setLandsFeatureId(landsFeature.getLandsFeatureID()))
							.setLandsFeature(landsFeature)
							.setValue(getStringCheckNull(reoCrawl.getWardin())));
					break;
				default:
					break;
				}
			}
			return listLandDetail;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String initCrawlJob() {
		try {
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
			        	Long fieldValue = ((Long) privateStringField.get(scheduleBuilder))/3600000;
			        	System.out.println("fieldValue = " + fieldValue);
			        	timeCrawl = String.valueOf(fieldValue);
			        }
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return timeCrawl;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean setTimeCrawlJob(int value) {
		try {
			trigger = TriggerBuilder.newTrigger().withIdentity("crawlerTriggler", "crawler")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(value).repeatForever()).build();
	
			job = JobBuilder.newJob(CrawlRealEstateScheduleJob.class).withIdentity("crawlerJob", "crawler").build();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
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
	public boolean crawlNow() {
		try {
			JobKey jobKeyNow = JobKey.jobKey("crawlerNowJob", "crawler");
			JobDetail jobNow = JobBuilder.newJob(CrawlRealEstateNowJob.class).storeDurably(true).withIdentity("crawlerNowJob", "crawler").build();
			scheduler.addJob(jobNow, true);
			scheduler.getContext().put("crawlnow", "true");
			scheduler.triggerJob(jobKeyNow);
			scheduler.deleteJob(jobKeyNow);
			scheduler.getContext().remove("crawlnow");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
