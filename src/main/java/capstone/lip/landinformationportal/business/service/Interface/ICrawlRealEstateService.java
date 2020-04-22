package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;

public interface ICrawlRealEstateService {
	boolean saveRealEstateCrawl(List<RealEstateObjectCrawl> listReoCrawl);
	String initCrawlJob();
	boolean setTimeCrawlJob(int value);
	boolean turnOnCrawler();
	boolean turnOffCrawler();
	boolean crawlNow();
}
