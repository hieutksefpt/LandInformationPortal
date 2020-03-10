package capstone.lip.landinformationportal.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class CrawlRealEstateService {
	public void printRandom() {
        System.out.println(ThreadLocalRandom.current().nextInt());
    }
}
