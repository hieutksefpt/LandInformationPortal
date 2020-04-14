package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;

public interface ICrawlRealEstateService {
	boolean saveRealEstateCrawl(List<RealEstateObjectCrawl> listReoCrawl);
	String initCrawlJob();
	boolean setTimeCrawlJob(int value);
	boolean turnOnCrawler();
	boolean turnOffCrawler();
	boolean crawlNow();
}
