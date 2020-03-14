package capstone.lip.landinformationportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.repository.HousesDetailRepository;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }

  @Autowired
  private HousesDetailRepository repo;
  
  @Override
  public void run(String... args) {

      System.out.print("hello");
      
  }
}
