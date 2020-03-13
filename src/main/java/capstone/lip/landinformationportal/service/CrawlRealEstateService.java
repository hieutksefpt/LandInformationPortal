package capstone.lip.landinformationportal.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.common.HousesFeatureNameConstant;
import capstone.lip.landinformationportal.common.LandsFeatureNameConstant;
import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
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
	
	public void printRandom() {
        System.out.println(ThreadLocalRandom.current().nextInt());
    }
	
	private List<HousesFeature> listHouseFeature = housesFeatureRepository.findAll();
	private List<LandsFeature> listLandsFeature = landsFeatureRepository.findAll();
	
	@Override
	public void saveRealEstateCrawl(List<RealEstateObjectCrawl> listReoCrawl) {
		for (RealEstateObjectCrawl reoCrawl : listReoCrawl) {
			RealEstate reo = new RealEstate();
			reo.setRealEstateName(reoCrawl.getTitle())
				.setRealEstateLat(reoCrawl.getLatitude())
				.setRealEstateLng(reoCrawl.getLongitude())
				.setRealEstatePrice(Double.valueOf(reoCrawl.getPrice().toString()))
				.setRealEstateStatus(String.valueOf(StatusRealEstateConstant.NOT_VERIFIED));
			House house = new House();
			house.setHouseName(reoCrawl.getTitle())
				.setHousePrice(0D);
			Land land = new Land();
			land.setLandName(reoCrawl.getTitle())
				.setLandPrice(0D);
			List<HousesDetail> listHousesDetail = parseDataToListHouseDetail(reoCrawl);
			List<LandsDetail> listLandsDetail = parseDataToListLandDetail(reoCrawl);
			house.setListHousesDetail(listHousesDetail);
			land.setListLandsDetail(listLandsDetail);
			List<House> listHouse = new ArrayList<House>();listHouse.add(house);
			List<Land> listLand = new ArrayList<Land>();listLand.add(land);
			reo.setListHouse(listHouse);
			reo.setListLand(listLand);
			
		}
		
	}
	
	private List<HousesDetail> parseDataToListHouseDetail(RealEstateObjectCrawl reoCrawl) {
		List<HousesDetail> listHouseDetail = new ArrayList<HousesDetail>();
		for (HousesFeature housesFeature: listHouseFeature) {
			switch (housesFeature.getHousesFeatureName()) {
			case HousesFeatureNameConstant.numberFloors:
				listHouseDetail.add(new HousesDetail()
						.setHousesFeature(housesFeature)
						.setValue(reoCrawl.getNumberFloor().toString()));
				break;
			case HousesFeatureNameConstant.numberBedrooms:
				listHouseDetail.add(new HousesDetail()
						.setHousesFeature(housesFeature)
						.setValue(reoCrawl.getNumberBedrooms().toString()));
				break;
			case HousesFeatureNameConstant.homeDirection:
				listHouseDetail.add(new HousesDetail()
						.setHousesFeature(housesFeature)
						.setValue(reoCrawl.getHomeDirection()));
				break;
			case HousesFeatureNameConstant.numberToilets:
				listHouseDetail.add(new HousesDetail()
						.setHousesFeature(housesFeature)
						.setValue(reoCrawl.getNumberToilets().toString()));
				break;
			case HousesFeatureNameConstant.balconyDirection:
				listHouseDetail.add(new HousesDetail()
						.setHousesFeature(housesFeature)
						.setValue(reoCrawl.getBalconyDirection()));
				break;
			case HousesFeatureNameConstant.projectOwner:
				listHouseDetail.add(new HousesDetail()
						.setHousesFeature(housesFeature)
						.setValue(reoCrawl.getProjectOwner()));
				break;
			default:
				break;
			}
		}
		return listHouseDetail;
	}
	private List<LandsDetail> parseDataToListLandDetail(RealEstateObjectCrawl reoCrawl) {
		List<LandsDetail> listLandDetail = new ArrayList<LandsDetail>();
		for(LandsFeature landsFeature:listLandsFeature) {
			switch (landsFeature.getLandsFeatureName()) {
			case LandsFeatureNameConstant.area:
				listLandDetail.add(new LandsDetail()
						.setLandsFeature(landsFeature)
						.setValue(reoCrawl.getArea().toString()));
				break;
			case LandsFeatureNameConstant.wardin:
				listLandDetail.add(new LandsDetail()
						.setLandsFeature(landsFeature)
						.setValue(reoCrawl.getWardin().toString()));
				break;
			default:
				break;
			}
		}
		return listLandDetail;
	}
}
