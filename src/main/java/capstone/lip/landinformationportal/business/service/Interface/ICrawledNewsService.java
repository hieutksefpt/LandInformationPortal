package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import capstone.lip.landinformationportal.common.entity.CrawledNews;

public interface ICrawledNewsService{
	
	String initCrawlJob();
	
	boolean setTimeCrawlJob(int value);
	
	boolean turnOnCrawler();
	
	boolean turnOffCrawler();
	
	boolean crawlNow();
	
	boolean delete(CrawledNews news);
	
	boolean delete(List<CrawledNews> listNews);
	
	List<CrawledNews> saveAll(List<CrawledNews> listNews);
	
	Page<CrawledNews> findByCrawledNewsStatus(String status, Pageable page);
	
	long countByStatus(String status);
	
	CrawledNews findById(Long newsId);
	
	CrawledNews findByCrawledNewsLink(String link);
	
}
