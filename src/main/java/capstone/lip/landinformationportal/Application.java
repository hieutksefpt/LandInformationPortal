package capstone.lip.landinformationportal;

import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.repository.ProvinceRepository;
import capstone.lip.landinformationportal.repository.UserRepository;

@EnableJpaAuditing
@SpringBootApplication
public class Application implements CommandLineRunner{

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  
  private static final Logger log = LoggerFactory.getLogger(Application.class);

//  @Autowired
//  private UserRepository repository;
  
  @Autowired
  private ProvinceRepository provinceRepo;
  
  @Autowired
  private DistrictRepository districtRepo;
  
  @Override
  public void run(String... args) {

      log.info("StartApplication...");

      System.out.println("\nprovince()");
      provinceRepo.findAll().forEach(x -> System.out.println(x.getProvinceName()));
      
      System.out.println("\nprovince()");
      List<District> list = districtRepo.findAll();
      int i = 1;
      i++;
      System.out.print(list.get(0).getProvince().getProvinceName());
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