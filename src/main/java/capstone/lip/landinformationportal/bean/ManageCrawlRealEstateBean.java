package capstone.lip.landinformationportal.bean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.service.CrawlRealEstateService;

@Named
@ViewScoped
public class ManageCrawlRealEstateBean extends QuartzJobBean {
	
	@Autowired
	private CrawlRealEstateService crawlRealEstateService;
	
	private String timerCrawl;
	
	@PostConstruct
	public void init() {
		timerCrawl = "";
	}
	
	public void setTimerButtonClick() {
		
	}
	
	public void changeCrawlerStatus() {
		
	}
	
	public String getTimerCrawl() {
		return timerCrawl;
	}

	public void setTimerCrawl(String timerCrawl) {
		this.timerCrawl = timerCrawl;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		crawlRealEstateService.printRandom();
	}
	
}
