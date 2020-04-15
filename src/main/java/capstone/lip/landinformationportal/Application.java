package capstone.lip.landinformationportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
    
//    
//    @Autowired
//	private IRealEstateService service;
//    
//    @Override
//    public void run(String... args) {
//    	RealEstate reo = service.findById(3);
//    	List<Report> list = reo.getListReport();
//    	System.out.print(list.size());
//    	int i = 1;
//    	i++;
//    	i--;
//    }
}
