package capstone.lip.landinformationportal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.LandRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
//    
//    
//    
//	  @Autowired
//	  private RealEstateRepository reoRepo;
//	  
//	  @Autowired
//	  private LandRepository landRepo;
//	  
//	  @Autowired
//	  private LandsFeatureRepository lfRepo;
//	  
//	  @Override
//	  public void run(String... args) {
//	
//	      System.out.print("hello");
//	      reoRepo.findAll();
////	      List<Land> listLand = landRepo.findAll();
////	      List<LandsFeature> listLf = lfRepo.findAll();
//	      int i = 1;
//	      i++;
//	      i--;
//	  }
}
