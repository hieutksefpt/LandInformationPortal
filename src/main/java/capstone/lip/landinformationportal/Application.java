package capstone.lip.landinformationportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
//@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }

//  private static final Logger log = LoggerFactory.getLogger(Application.class);
//  @Autowired
//  private UserRepository repository;
//  @Autowired
//  private ProvinceRepository provinceRepo;
//  
//  @Autowired
//  private DistrictRepository districtRepo;
//  @Autowired
//  private IProvinceService provinceService;
//  
//  @Override
//  public void run(String... args) {
//
//      log.info("StartApplication...");
//
//      System.out.println("\nprovince()");
//      provinceService.findAll().forEach(x -> System.out.println(x.getProvinceName()));
//      Province province = provinceService.findAll().get(0);
//      
//      System.out.print(province.getProvinceName());
//      System.out.print(province.getListDistrict().get(0).getDistrictName());
//      List<District> list = provinceService.getListDistrictByProvinceId(1L);
//      for (District district:list) {
//    	  System.out.println(district.getDistrictName());
//      }
//      List<District> set = province.getListDistrict();
//      Hibernate.initialize(set.size());
//      System.out.println("\nprovince()");
//      List<District> list = districtRepo.findAll();
//      int i = 1;
//      i++;
//      System.out.print(list.get(0).getProvince());
//      repository.save(new User("test2","test2"));
//      repository.save(new User("test3","test3"));
//      
//      System.out.println("\nfindAll()");
//      repository.findAll().forEach(x -> System.out.println(x));
//
//      System.out.println("\nfindById(1L)");
//      repository.findById(1l).ifPresent(x -> System.out.println(x));
//
//      System.out.println("\nfindByName('Node')");
//      repository.findByName("Node").forEach(x -> System.out.println(x));
//  }
}
