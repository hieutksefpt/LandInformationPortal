package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;

@Named
@ViewScoped
public class ManageCrawlNewsBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String timerCrawl;

	@Autowired 
	private ICrawledNewsService crawledNewService;
	
	private List<CrawledNews> listCrawledNews;
	@PostConstruct
	public void init() {
		timerCrawl = "";
		timerCrawl = crawledNewService.initCrawlJob();
		listCrawledNews = crawledNewService.findAll();
	}
	
	public void setTimerButtonClick() {
		int timer= Integer.valueOf(timerCrawl);
		crawledNewService.setTimeCrawlJob(timer);
	}

	public void turnOffCrawler() {
		crawledNewService.turnOffCrawler();
	}
	public void turnOnCrawler() {
		crawledNewService.turnOnCrawler();
	}
	
	public void crawlNow() {
		crawledNewService.crawlNow();
	}

	public String getTimerCrawl() {
		return timerCrawl;
	}

	public void setTimerCrawl(String timerCrawl) {
		this.timerCrawl = timerCrawl;
	}

	public void refreshData() {
		listCrawledNews = crawledNewService.findByCrawledNewsStatus(StatusCrawledNewsConstant.NON_DISPLAY);
	}

	public List<CrawledNews> getListCrawledNews() {
		return listCrawledNews;
	}

	public void setListCrawledNews(List<CrawledNews> listCrawledNews) {
		this.listCrawledNews = listCrawledNews;
	}
	
}
