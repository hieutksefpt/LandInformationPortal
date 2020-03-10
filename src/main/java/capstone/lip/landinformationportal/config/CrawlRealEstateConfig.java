package capstone.lip.landinformationportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableScheduling
//@Configuration
//@ConditionalOnProperty(name = "spring.enable.scheduling")
public class CrawlRealEstateConfig {
	private static final Logger LOG = LoggerFactory.getLogger(CrawlRealEstateConfig.class);

//    @Scheduled(fixedRateString = "${crawljob}")
    public void sayHello(){
    	System.out.println("hello tuan");
        LOG.info("Hello from our simple scheduled method");
    }
//    
//    @Scheduled(fixedRateString = "${sample.schedule.string}")
//    public void scheduleTaskWithFixedRate() throws InterruptedException {
//        task1();
//        task2();
//    }
//
//    public void task1() throws InterruptedException {
//        logger.info("Task 1 starts" + Thread.currentThread());
//        Thread.sleep(1000);
//        logger.info("Task 1 ends" + Thread.currentThread());
//    }
//
//    public void task2() throws InterruptedException {
//        logger.info("Task 2 starts" + Thread.currentThread());
//        Thread.sleep(1000);
//        logger.info("Task 2 ends" + Thread.currentThread());
//    }
}
