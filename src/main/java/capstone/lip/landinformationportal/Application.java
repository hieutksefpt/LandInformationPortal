package capstone.lip.landinformationportal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired
  private UserRepository repository;
  
  @Override
  public void run(String... args) {

      log.info("StartApplication...");

//      repository.save(new User("test2","test2"));
//      repository.save(new User("test3","test3"));
//      
      System.out.println("\nfindAll()");
      repository.findAll().forEach(x -> System.out.println(x));

//      System.out.println("\nfindById(1L)");
//      repository.findById(1l).ifPresent(x -> System.out.println(x));
//
//      System.out.println("\nfindByName('Node')");
//      repository.findByName("Node").forEach(x -> System.out.println(x));

  }
}