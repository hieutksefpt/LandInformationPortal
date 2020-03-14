package capstone.lip.landinformationportal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.HousesDetailId;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetailId;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.service.LandsDetailService;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
//    
//    
//    @Autowired
//    private LandsDetailRepository landDetailRepo;
//	  @Autowired
//	  private LandRepository landRepo;
//	  @Autowired
//	  private LandsFeatureRepository featureRepo;
//	  
//	  @Autowired
//	  private HousesDetailRepository housesDetailRepo;
//	  @Autowired
//	  private HouseRepository houseRepo;
//	  @Autowired
//	  private HousesFeatureRepository houseFeatureRepo;
//	  
//	  @Override
//	  public void run(String... args) {
//	
//	      System.out.print("hello");
//	      
//	      Land land = landRepo.getOne(56L);
//	      LandsFeature feature = featureRepo.getOne(1L);
//	      
//	      LandsDetail landsDetail = new LandsDetail();
//	      landsDetail.setId(new LandsDetailId(land.getLandId(), feature.getLandsFeatureID()));
//	      landsDetail.setLand(land);
//	      landsDetail.setLandsFeature(feature);
//	      landsDetail.setValue("tuan test");
//	      
//	      land.getListLandsDetail().add(landsDetail);
//	      
//	      landDetailRepo.save(landsDetail);
//	      
//	      HousesFeature houseFeature = houseFeatureRepo.getOne(2L);
//	      House house = houseRepo.getOne(59L);
//	      HousesDetail housesDetail = new HousesDetail();
//	      housesDetail.setId(new HousesDetailId(house.getHouseId(), houseFeature.getHousesFeatureID()));
//	      housesDetail.setHouse(house);
//	      housesDetail.setHousesFeature(houseFeature);
//	      housesDetail.setValue("test12345");
//	      housesDetailRepo.save(housesDetail);
//	      List<HousesDetail> listDetail = house.getListHousesDetail();
//
//	  }
}
