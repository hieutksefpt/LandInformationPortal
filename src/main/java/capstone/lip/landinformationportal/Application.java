package capstone.lip.landinformationportal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.dto.MaxMinAvg;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.specification.RealEstateSpecifications;
import capstone.lip.landinformationportal.specification.SearchCriteria;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
    
    
    @Autowired
    private RealEstateRepository repo;
    
	  @Override
	  public void run(String... args) {
	
	      System.out.print("hello");
	      MaxMinAvg max = repo.getMaxMinAvg("hà đông");
	      List<Timestamp> list = repo.getGroupTimeAndPrice("hà đông");
	      int i = 1;
	      i++;
	      i--;
	  }
}
