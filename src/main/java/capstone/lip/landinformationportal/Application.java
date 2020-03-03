package capstone.lip.landinformationportal;

import java.util.List;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.repository.ProvinceRepository;
import capstone.lip.landinformationportal.repository.UserRepository;
import capstone.lip.landinformationportal.service.IProvinceService;

@EnableJpaAuditing
@SpringBootApplication
public class Application implements CommandLineRunner{

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  
  private static final Logger log = LoggerFactory.getLogger(Application.class);

//  @Autowired
//  private UserRepository repository;
  
//  @Autowired
//  private ProvinceRepository provinceRepo;
//  
//  @Autowired
//  private DistrictRepository districtRepo;
  
  
  @Autowired
  private IProvinceService provinceService;
  
  @Override
  public void run(String... args) {

      log.info("StartApplication...");

      System.out.println("\nprovince()");
      provinceService.findAll().forEach(x -> System.out.println(x.getProvinceName()));
      Province province = provinceService.findAll().get(0);
      
      System.out.print(province.getProvinceName());
      province.getListDistrict().get(0).getDistrictName();
      
      List<District> list = provinceService.getListDistrictByProvinceId(1L);
      for (District district:list) {
    	  System.out.println(district.getDistrictName());
      }
      
      
      
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
      
      

  }
}