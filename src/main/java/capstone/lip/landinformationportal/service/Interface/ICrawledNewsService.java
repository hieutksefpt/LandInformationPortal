package capstone.lip.landinformationportal.service.Interface;

import java.util.List;
import capstone.lip.landinformationportal.entity.CrawledNews;

public interface ICrawledNewsService{
	List<CrawledNews> findAll();
	List<CrawledNews> findByCrawledNewsStatus(Integer status);
	CrawledNews findByCrawledNewsLink(String link);
	List<CrawledNews> saveNews(List<CrawledNews> listCrawledNews);
	String initCrawlJob();
	void setTimeCrawlJob(int value);
	void turnOnCrawler();
	void turnOffCrawler();
	void crawlNow();
	
}
