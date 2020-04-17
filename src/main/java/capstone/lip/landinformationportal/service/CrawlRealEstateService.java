package capstone.lip.landinformationportal.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.entity.compositekey.HousesDetailId;
import capstone.lip.landinformationportal.entity.compositekey.LandsDetailId;
import capstone.lip.landinformationportal.entity.compositekey.RealEstateAdjacentSegmentId;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.repository.ProvinceRepository;
import capstone.lip.landinformationportal.repository.RealEstateAdjacentSegmentRepository;
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
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private RealEstateAdjacentSegmentRepository adjRepository;
	
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
			User user = userRepository.findByUsername("tuan");
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
				
				if (!validateNumber(getStringCheckNull(reoCrawl.getPrice().toString()))) {
					reo.setRealEstatePrice(BigDecimal.ZERO);
				}else {
					reo.setRealEstatePrice(reoCrawl.getPrice());
				}
				
				
				reo.setCreateDate(reoCrawl.getStartDatePost());
				reo = realEstateRepository.save(reo);
				House house = new House();
				house.setRealEstate(reo)
					.setHouseName(reoCrawl.getTitle())
					.setHousePrice(BigDecimal.ZERO);
				Land land = new Land();
				land.setRealEstate(reo)
					.setLandName(reoCrawl.getTitle())
					.setLandPrice(BigDecimal.ZERO);
				
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
				
				RealEstateAdjacentSegment adj = mappingRealEstateToLocation(reo);
				if (adj != null) {
					adjRepository.save(adj);
				}
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
	private boolean validateNumber(String value) {
		try {
			BigDecimal number = new BigDecimal(value);
			if (number.compareTo(new BigDecimal(0)) == 1){
				return true;
			}
			return false;
		}catch(Exception e) {
			return false;
		}
	}
	private List<HousesDetail> parseDataToListHouseDetail(RealEstateObjectCrawl reoCrawl, House house) {
		try {
			List<HousesDetail> listHouseDetail = new ArrayList<HousesDetail>();
			for (HousesFeature housesFeature: listHouseFeature) {
				switch (housesFeature.getHousesFeatureName()) {
				case HousesFeatureNameConstant.numberFloors:
					if (!validateNumber(getStringCheckNull(reoCrawl.getNumberFloor().toString()))) {
						continue;
					}
					listHouseDetail.add(new HousesDetail()
							.setHouse(house)
							.setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
							.setHousesFeature(housesFeature)
							.setValue(getStringCheckNull(reoCrawl.getNumberFloor().toString())));
					break;
				case HousesFeatureNameConstant.numberBedrooms:
					if (!validateNumber(getStringCheckNull(reoCrawl.getNumberBedrooms().toString()))) {
						continue;
					}
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
					if (!validateNumber(getStringCheckNull(reoCrawl.getNumberToilets().toString()))) {
						continue;
					}
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
	
	private RealEstateAdjacentSegment mappingRealEstateToLocation(RealEstate realestate) {
		List<Province> listProvince = provinceRepository.findAll();
		String address = realestate.getRealEstateAddress().toLowerCase();
		Province provinceSearch = listProvince.stream().filter(element -> address.contains(element.getProvinceName().toLowerCase())).findAny().orElse(null);
		if (provinceSearch == null) return null;
		List<District> listDistrict = provinceSearch.getListDistrict();
		District districtSearch = listDistrict.stream().filter(element -> address.contains(element.getDistrictName().toLowerCase())).findAny().orElse(null);
		if (districtSearch == null) return null;
		List<SegmentOfStreet> listSegment = districtSearch.getListSegmentOfStreet();
		List<Street> listStreet = listSegment.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
		Street streetSearch = listStreet.stream().filter(element -> address.contains(element.getStreetName().toLowerCase())).findAny().orElse(null);
		if (streetSearch == null) return null;
		listSegment = streetSearch.getListSegmentOfStreet();
		
		SegmentOfStreet segmentSelected = null;
		Double distance = null;
		for (SegmentOfStreet element: listSegment) {
			if (segmentSelected == null) {
				segmentSelected = element;
				distance = Math.sqrt( Math.pow(element.getSegmentLat() - realestate.getRealEstateLat(), 2) + Math.pow(element.getSegmentLng() - realestate.getRealEstateLng(), 2));
				continue;
			}
			Double currentDistance = Math.sqrt(Math.pow(element.getSegmentLat() - realestate.getRealEstateLat(), 2) + Math.pow(element.getSegmentLng() - realestate.getRealEstateLng(), 2));
			if (currentDistance < distance) {
				distance = currentDistance;
				segmentSelected = element;
			}
		}
		RealEstateAdjacentSegment adj = null;
		if (segmentSelected != null) {
			adj = new RealEstateAdjacentSegment().setId(new RealEstateAdjacentSegmentId(segmentSelected.getSegmentId(), realestate.getRealEstateId()));
		}
		
		return adj;
	}
}
