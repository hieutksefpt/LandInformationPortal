package capstone.lip.landinformationportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.business.service.Interface.IPredictPriceService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.common.entity.RealEstate;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        
    }
//    
//    
//    @Autowired
//	private IPredictPriceService predictService;
//    
//    @Autowired
//    private IRealEstateService reoService;
//    
//    @Override
//    public void run(String... args) {
//    	RealEstate reo = reoService.findById(20839);
//    	String value = predictService.getPredictPrice(reo);
//    	System.out.print(value);
////    	predictService.addDataToModel(reo);
//    	int i = 1; 
//    	i++;
//    }
}
