package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.dto.NewsCrawl;
import capstone.lip.landinformationportal.entity.CrawledNews;

public interface ICrawledNewsService{
	List<CrawledNews> findAll();
	
	CrawledNews findByCrawledNewsLink(String link);
	
	List<CrawledNews> save(List<NewsCrawl> listCrawledNews);
	
	String initCrawlJob();
	
	void setTimeCrawlJob(int value);
	
	void turnOnCrawler();
	
	void turnOffCrawler();
	
	void crawlNow();
	
	void delete(CrawledNews news);
	
	CrawledNews save(CrawledNews news);
	
	List<CrawledNews> findByCrawledNewsStatus(String status);
	
	Page<CrawledNews> findByCrawledNewsStatus(String status, Pageable page);
	
	long count();
	
	long countByStatus(String status);
}
