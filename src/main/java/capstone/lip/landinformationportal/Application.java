package capstone.lip.landinformationportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.business.service.Interface.IPredictPriceService;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        
    }
    
//    
//    @Autowired
//	private IPredictPriceService service;
//    
//    @Override
//    public void run(String... args) {
//    	String value = service.getPredictPrice("2", "2", "123", "27.908759224806225", "105.80231678186296");
//    	int i = 1;
//    	i++;
//    }
}
