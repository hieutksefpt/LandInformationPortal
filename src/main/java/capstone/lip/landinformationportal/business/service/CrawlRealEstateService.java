package capstone.lip.landinformationportal.business.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.business.repository.HouseRepository;
import capstone.lip.landinformationportal.business.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.business.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.business.repository.LandRepository;
import capstone.lip.landinformationportal.business.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.business.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.business.repository.ProvinceRepository;
import capstone.lip.landinformationportal.business.repository.RealEstateAdjacentSegmentRepository;
import capstone.lip.landinformationportal.business.repository.RealEstateRepository;
import capstone.lip.landinformationportal.business.repository.UserRepository;
import capstone.lip.landinformationportal.business.service.Interface.ICrawlRealEstateService;
import capstone.lip.landinformationportal.business.service.Interface.IPredictPriceService;
import capstone.lip.landinformationportal.business.service.Interface.ISegmentOfStreetService;
import capstone.lip.landinformationportal.business.validation.CrawlRealEstateValidation;
import capstone.lip.landinformationportal.common.config.CrawlRealEstateNowJob;
import capstone.lip.landinformationportal.common.config.CrawlRealEstateScheduleJob;
import capstone.lip.landinformationportal.common.constant.HousesFeatureNameConstant;
import capstone.lip.landinformationportal.common.constant.LandsFeatureNameConstant;
import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.HousesFeature;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.LandsDetail;
import capstone.lip.landinformationportal.common.entity.LandsFeature;
import capstone.lip.landinformationportal.common.entity.Province;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.entity.compositekey.HousesDetailId;
import capstone.lip.landinformationportal.common.entity.compositekey.LandsDetailId;
import capstone.lip.landinformationportal.common.entity.compositekey.RealEstateAdjacentSegmentId;

@Service
public class CrawlRealEstateService implements ICrawlRealEstateService {

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
    private IPredictPriceService predictService;

    @Autowired
    private ISegmentOfStreetService segmentService;
    
    @Autowired
    private RealEstateAdjacentSegmentRepository adjRepository;

    private JobKey jobKey = new JobKey("crawlerJob", "crawler");
    private TriggerKey triggerKey = new TriggerKey("crawlerTriggler", "crawler");
    private JobKey jobKeyNow = new JobKey("crawlerNowJob", "crawler");

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
            User user = userRepository.findByUsername("admin");
            int i = 1;
            for (RealEstateObjectCrawl reoCrawl : listReoCrawl) {
                CrawlRealEstateValidation validReo = new CrawlRealEstateValidation();
                RealEstate reo = new RealEstate();
                String link = reoCrawl.getLink();
                String title = reoCrawl.getTitle().trim();
                RealEstate reoSearch = realEstateRepository.findByRealEstateLink(link);
                System.out.println(i + " " + link);
                i++;
                if ((reoCrawl.getPrice().compareTo(BigDecimal.ZERO) <= 0)) {
                	reoCrawl.setPrice(BigDecimal.ONE);
                }
                if (reoSearch != null) {
                    continue;
                } else if (validReo.isValidCrawlRealEstate(reoCrawl)) {

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
                    Date date= new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    if (reo.getCreatedDate()== null || reo.getCreatedDate().after(ts)) {
                    	reo.setCreateDate(ts);
                    }
                    
                    if (!validateNumber(getStringCheckNull(reoCrawl.getPrice().toString()))) {
                        reo.setRealEstatePrice(BigDecimal.ZERO);
                    } else {
                        reo.setRealEstatePrice(reoCrawl.getPrice());
                    }
                    reo.setCreateDate(reoCrawl.getStartDatePost());
                    reo = realEstateRepository.save(reo);

                    RealEstateAdjacentSegment adj = mappingRealEstateToLocation(reo);
                    if (adj != null) {
                        adj = adjRepository.save(adj);
                        adj = adjRepository.findByIdSegmentOfStreetIdAndIdRealEstateId(adj.getId().getSegmentOfStreetId(), adj.getId().getRealEstateId());
                        
                    	SegmentOfStreet segmentAdj = adj.getSegmentOfStreet();
                    	Street streetAdj = segmentAdj.getStreet();
                    	District districtAdj = segmentAdj.getDistrict();
                    	Province provinceAdj = districtAdj.getProvince();
                    	String newAddress = segmentAdj.getSegmentName()+", "+streetAdj.getStreetName()+", "+districtAdj.getDistrictName()+", "+provinceAdj.getProvinceName();
                    	reo.setRealEstateAddress(newAddress);
                        List<RealEstate> listRealEstateByCoordinate = realEstateRepository.findByRealEstateLatAndRealEstateLng(reo.getRealEstateLat(), reo.getRealEstateLng());
                        if (listRealEstateByCoordinate == null || (listRealEstateByCoordinate != null && listRealEstateByCoordinate.isEmpty())) {
                            reo.setRealEstateStatus(String.valueOf(StatusRealEstateConstant.VERIFIED));
                            reo = realEstateRepository.save(reo);
                        }
                        
//                        predictService.addDataToModel(reo);	
                    }

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
                    List<House> listHouse = new ArrayList<House>();
                    listHouse.add(house);

                    reo.setListHouse(listHouse);
                    reo.setLand(land);

                    housesDetailRepository.saveAll(listHousesDetail);
                    landsDetailRepository.saveAll(listLandsDetail);
                } else {
                    throw new Exception();

                }

            }
            return true;
        } catch (Exception e) {
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
            if (number.compareTo(new BigDecimal(0)) == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private List<HousesDetail> parseDataToListHouseDetail(RealEstateObjectCrawl reoCrawl, House house) {
        try {
            List<HousesDetail> listHouseDetail = new ArrayList<HousesDetail>();
            for (HousesFeature housesFeature : listHouseFeature) {
                switch (housesFeature.getHousesFeatureName()) {
                    case HousesFeatureNameConstant.NUMBERFLOORS:
                        if (!validateNumber(getStringCheckNull(reoCrawl.getNumberFloor().toString()))) {
                            continue;
                        }
                        listHouseDetail.add(new HousesDetail()
                                .setHouse(house)
                                .setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
                                .setHousesFeature(housesFeature)
                                .setValue(getStringCheckNull(reoCrawl.getNumberFloor().toString())));
                        break;
                    case HousesFeatureNameConstant.NUMBERBEDROOMS:
                        if (!validateNumber(getStringCheckNull(reoCrawl.getNumberBedrooms().toString()))) {
                            continue;
                        }
                        listHouseDetail.add(new HousesDetail()
                                .setHouse(house)
                                .setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
                                .setHousesFeature(housesFeature)
                                .setValue(getStringCheckNull(reoCrawl.getNumberBedrooms().toString())));
                        break;
                    case HousesFeatureNameConstant.HOMEDIRECTION:
                        listHouseDetail.add(new HousesDetail()
                                .setHouse(house)
                                .setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
                                .setHousesFeature(housesFeature)
                                .setValue(getStringCheckNull(reoCrawl.getHomeDirection())));
                        break;
                    case HousesFeatureNameConstant.NUMBERTOILETS:
                        if (!validateNumber(getStringCheckNull(reoCrawl.getNumberToilets().toString()))) {
                            continue;
                        }
                        listHouseDetail.add(new HousesDetail()
                                .setHouse(house)
                                .setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
                                .setHousesFeature(housesFeature)
                                .setValue(getStringCheckNull(reoCrawl.getNumberToilets().toString())));
                        break;
                    case HousesFeatureNameConstant.BALCONYDIRECTION:
                        listHouseDetail.add(new HousesDetail()
                                .setHouse(house)
                                .setId(new HousesDetailId().setHouseId(house.getHouseId()).setHousesFeatureId(housesFeature.getHousesFeatureID()))
                                .setHousesFeature(housesFeature)
                                .setValue(getStringCheckNull(reoCrawl.getBalconyDirection())));
                        break;
                    case HousesFeatureNameConstant.PROJECTOWNER:
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<LandsDetail> parseDataToListLandDetail(RealEstateObjectCrawl reoCrawl, Land land) {
        try {
            List<LandsDetail> listLandDetail = new ArrayList<LandsDetail>();
            for (LandsFeature landsFeature : listLandsFeature) {
                switch (landsFeature.getLandsFeatureName()) {
                    case LandsFeatureNameConstant.AREA:
                        listLandDetail.add(new LandsDetail()
                                .setLand(land)
                                .setId(new LandsDetailId().setLandId(land.getLandId()).setLandsFeatureId(landsFeature.getLandsFeatureID()))
                                .setLandsFeature(landsFeature)
                                .setValue(getStringCheckNull(reoCrawl.getArea().toString())));
                        break;
                    case LandsFeatureNameConstant.WARDIN:
                        listLandDetail.add(new LandsDetail()
                                .setLand(land)
                                .setId(new LandsDetailId().setLandId(land.getLandId()).setLandsFeatureId(landsFeature.getLandsFeatureID()))
                                .setLandsFeature(landsFeature)
                                .setValue(getStringCheckNull(reoCrawl.getWardin())));
                        break;
                    case LandsFeatureNameConstant.SIZEFRONT:
                        listLandDetail.add(new LandsDetail()
                                .setLand(land)
                                .setId(new LandsDetailId().setLandId(land.getLandId()).setLandsFeatureId(landsFeature.getLandsFeatureID()))
                                .setLandsFeature(landsFeature)
                                .setValue(getStringCheckNull(reoCrawl.getSizeFront())));
                        break;  
                    default:
                        break;
                }
            }
            return listLandDetail;
        } catch (Exception e) {
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
                if (jobDetail == null) {
                    return timeCrawl;
                }
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
                for (Trigger trigger : triggers) {

                    SimpleScheduleBuilder scheduleBuilder = (SimpleScheduleBuilder) trigger.getScheduleBuilder();
                    if (scheduleBuilder != null) {

                        Field privateStringField = SimpleScheduleBuilder.class.
                                getDeclaredField("interval");

                        privateStringField.setAccessible(true);
                        Long fieldValue = ((Long) privateStringField.get(scheduleBuilder)) / 3600000;
                        System.out.println("fieldValue = " + fieldValue);
                        timeCrawl = String.valueOf(fieldValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return timeCrawl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setTimeCrawlJob(int value) {
        try {
            if (value == 0) {
                return false;
            } else {
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(value).repeatForever()).build();
                job = JobBuilder.newJob(CrawlRealEstateScheduleJob.class).withIdentity(jobKey).build();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean turnOffCrawler() {
        try {
            if (scheduler != null) {
                scheduler.deleteJob(jobKey);
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

    public boolean crawlNow() {
        try {
            if (scheduler != null) {
                if (!scheduler.isStarted()) {
                    scheduler.start();
                }
            }
            JobDetail jobNow = JobBuilder.newJob(CrawlRealEstateNowJob.class).storeDurably(true).withIdentity(jobKeyNow).build();
            scheduler.addJob(jobNow, true);
            scheduler.triggerJob(jobKeyNow);
            scheduler.deleteJob(jobKeyNow);
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
        if (provinceSearch == null) {
            return null;
        }
        List<District> listDistrict = provinceSearch.getListDistrict();
        District districtSearch = listDistrict.stream().filter(element -> address.contains(element.getDistrictName().toLowerCase())).findAny().orElse(null);
        if (districtSearch == null) {
            return null;
        }
        List<SegmentOfStreet> listSegment = districtSearch.getListSegmentOfStreet();
        List<Street> listStreet = listSegment.stream().map(x -> x.getStreet()).distinct().collect(Collectors.toList());
        Street streetSearch = listStreet.stream().filter(element -> address.contains(element.getStreetName().toLowerCase())).findAny().orElse(null);
        if (streetSearch == null) {
            return null;
        }
        listSegment = streetSearch.getListSegmentOfStreet();

        SegmentOfStreet segmentSelected = null;
        Double distance = null;
        for (SegmentOfStreet element : listSegment) {
            if (segmentSelected == null) {
                segmentSelected = element;
                distance = Math.sqrt(Math.pow(element.getSegmentLat() - realestate.getRealEstateLat(), 2) + Math.pow(element.getSegmentLng() - realestate.getRealEstateLng(), 2));
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
