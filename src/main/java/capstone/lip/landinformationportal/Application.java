package capstone.lip.landinformationportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }

//  private static final Logger log = LoggerFactory.getLogger(Application.class);
//  @Autowired
//  private HousesFeatureRepository repository;
//  
//  @Override
//  public void run(String... args) {
//
//      log.info("StartApplication...");
//
//      System.out.println("\nfindAll()");
//      List<HousesFeature> list = repository.findAll();
//      int i = 1;
//      i++;
//      i--;
//  }
}
