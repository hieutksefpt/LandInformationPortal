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
    
    
//    @Autowired
//	private ICrawlRealEstateService service;
//    
//    @Override
//    public void run(String... args) {
//    	RealEstateObjectCrawl reo = new RealEstateObjectCrawl();
//    	reo.setAddress("tuan");
//    	reo.setArea(new BigDecimal(-1));
//    	reo.setBalconyDirection("Đông");
//    	reo.setCodePost(123L);
//    	reo.setDomain("tuan");
//    	reo.setEmail("tuan");
//    	reo.setHomeDirection("dasb");
//    	reo.setLatitude(1.0);
//    	reo.setLongitude(1.0);
//    	reo.setPrice(new BigDecimal(-1));
//    	reo.setLink("tuan");
//    	reo.setTitle("tuan");
//    	reo.setArea(new BigDecimal("99"));
//    	reo.setNumberBedrooms(-1);
//    	reo.setNumberFloor(-1);
//    	reo.setNumberToilets(8);
//    	reo.setProjectOwner("tuan");
//    	reo.setAddress("Hà đông, hà nội, an hòa");
////    	reo.setStartDatePost(new Timestamp(23300000));
//    	ArrayList<RealEstateObjectCrawl> listReoCrawl = new ArrayList<>();
//    	listReoCrawl.add(reo);
//    	service.saveRealEstateCrawl(listReoCrawl);
//    	int i = 1;
//    	i++;
//    }
}
