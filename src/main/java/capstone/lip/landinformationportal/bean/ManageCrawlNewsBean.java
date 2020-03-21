package capstone.lip.landinformationportal.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.dto.LazyCrawledNew;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;

@Named
@ViewScoped
public class ManageCrawlNewsBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String timerCrawl;

	@Autowired 
	private ICrawledNewsService crawledNewService;
	
	private LazyDataModel<CrawledNews> lazyNews;
	
	private boolean statusCrawlSchedule;
	
	@PostConstruct
	public void init() {
		timerCrawl = "";
		timerCrawl = crawledNewService.initCrawlJob();
		if (timerCrawl.isEmpty()) {
			statusCrawlSchedule = false;
		}
		else {
			statusCrawlSchedule = true;
		}
		lazyNews = new LazyCrawledNew(crawledNewService);
	}
	
	public void setTimerButtonClick() {
		int timer= Integer.valueOf(timerCrawl);
		crawledNewService.setTimeCrawlJob(timer);
	}

	public void turnOffCrawler() {
		crawledNewService.turnOffCrawler();
		statusCrawlSchedule = false;
	}
	public void turnOnCrawler() {
		if (timerCrawl.isEmpty()) {
			setMessage(FacesMessage.SEVERITY_ERROR, "Chưa cài đặt thời gian");
			return;
		}
		crawledNewService.initCrawlJob();
		crawledNewService.turnOnCrawler();
		statusCrawlSchedule = true;
	}
	public void setMessage(FacesMessage.Severity severityType, String message) {
		
		FacesMessage msg = new FacesMessage();
		if (severityType == FacesMessage.SEVERITY_ERROR) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", message);
		} else if (severityType == FacesMessage.SEVERITY_WARN) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Lưu ý", message);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", message);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
		lazyNews = new LazyCrawledNew(crawledNewService);
	}

	CrawledNews newsClick;
	public void setNews(CrawledNews news) {
		newsClick = news;
	}
	
	public void acceptNews() {
		CrawledNews newsTemp = newsClick;
		if (newsTemp == null) return;
		newsClick.setCrawledNewsStatus(StatusCrawledNewsConstant.DISPLAY);
		crawledNewService.save(newsClick);
		refreshData();
	}
	public void deleteNews() {
		CrawledNews newsTemp = newsClick;
		if (newsTemp == null) return;
		crawledNewService.delete(newsTemp);
		refreshData();
	}

	public LazyDataModel<CrawledNews> getLazyNews() {
		return lazyNews;
	}

	public boolean isStatusCrawlSchedule() {
		return statusCrawlSchedule;
	}

	public void setStatusCrawlSchedule(boolean statusCrawlSchedule) {
		this.statusCrawlSchedule = statusCrawlSchedule;
	}

	public void setLazyNews(LazyDataModel<CrawledNews> lazyNews) {
		this.lazyNews = lazyNews;
	}
	
}
