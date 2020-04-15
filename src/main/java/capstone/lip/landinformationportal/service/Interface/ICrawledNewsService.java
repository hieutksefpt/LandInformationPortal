package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.dto.NewsCrawl;
import capstone.lip.landinformationportal.entity.CrawledNews;

public interface ICrawledNewsService{

	List<CrawledNews> save(List<NewsCrawl> listCrawledNews);
	
	String initCrawlJob();
	
	boolean setTimeCrawlJob(int value);
	
	boolean turnOnCrawler();
	
	boolean turnOffCrawler();
	
	boolean crawlNow();
	
	boolean delete(CrawledNews news);
	
	boolean delete(List<CrawledNews> listNews);
	
	CrawledNews save(CrawledNews news);
	
	List<CrawledNews> saveAll(List<CrawledNews> listNews);
	
	List<CrawledNews> findByCrawledNewsStatus(String status);
	
	Page<CrawledNews> findByCrawledNewsStatus(String status, Pageable page);
	
	long count();
	
	long countByStatus(String status);
	
	CrawledNews findById(Long newsId);
}
