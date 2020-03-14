package capstone.lip.landinformationportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.common.HousesFeatureNameConstant;
import capstone.lip.landinformationportal.common.LandsFeatureNameConstant;
import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
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
	
	public void printRandom() {
        System.out.println(ThreadLocalRandom.current().nextInt());
    }
	
	private List<HousesFeature> listHouseFeature;
	private List<LandsFeature> listLandsFeature;
	
	@Override
	public void saveRealEstateCrawl(List<RealEstateObjectCrawl> listReoCrawl) {
		listHouseFeature = housesFeatureRepository.findAll();
		listLandsFeature = landsFeatureRepository.findAll();
		User user = userRepository.findAll().get(0);
		for (RealEstateObjectCrawl reoCrawl : listReoCrawl) {
			RealEstate reo = new RealEstate();
			reo.setRealEstateName(reoCrawl.getTitle())
				.setRealEstateLat(reoCrawl.getLatitude())
				.setRealEstateLng(reoCrawl.getLongitude())
				.setRealEstatePrice(Double.valueOf(reoCrawl.getPrice().toString()))
				.setRealEstateType(reoCrawl.getType())
				.setRealEstateLink(reoCrawl.getLink())
				.setRealEstateAddress(reoCrawl.getAddress())
				.setRealEstateStatus(String.valueOf(StatusRealEstateConstant.NOT_VERIFIED));
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
			List<Land> listLand = new ArrayList<Land>();listLand.add(land);
			reo.setListHouse(listHouse);
			reo.setListLand(listLand);
			reo.setUser(user);
			
			
			housesDetailRepository.saveAll(listHousesDetail);
			landsDetailRepository.saveAll(listLandsDetail);
		}
		int i = 1;
		i++;
		i--;
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
	}
	private List<LandsDetail> parseDataToListLandDetail(RealEstateObjectCrawl reoCrawl, Land land) {
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
	}
}
