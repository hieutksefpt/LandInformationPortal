package capstone.lip.landinformationportal.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;

@Service
public class CrawlRealEstateService implements ICrawlRealEstateService{
//	@Autowired
//	private RealEstate
	
	public void printRandom() {
        System.out.println(ThreadLocalRandom.current().nextInt());
    }

	@Override
	public void saveRealEstateCrawlToDb(List<RealEstateObjectCrawl> listReoCrawl) {
		// TODO Auto-generated method stub
		
	}
}
