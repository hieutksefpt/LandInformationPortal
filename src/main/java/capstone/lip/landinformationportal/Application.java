package capstone.lip.landinformationportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        
    }
//
//    @Autowired
//    private IStreetService streetService;
//    
//    @Override
//    public void run(String... args) {
//    	List<Street> listStreet = streetService.findStreetByDistrictId(7L);
//    	int i = 1; 
//    	i++;
//    }
}
