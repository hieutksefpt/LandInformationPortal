package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.dto.Pagination;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

@Named
@ViewScoped
public class HomepageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICrawledNewsService crawledNewService;
	
	@Autowired
	private IRealEstateService realEstateService;
	
	private Pagination pageNews;
	
	Page<CrawledNews> listNewsPage;
	List<CrawledNews> listNews;
	@PostConstruct
	public void init() {
		pageNews = new Pagination()
				.setTotalRow((int)crawledNewService.countByStatus(StatusCrawledNewsConstant.DISPLAY))
				.setRowsPerPage(5)
				.setPageRange(10)
				.setCurrentPage(0);
		pageNews.setTotalPages(pageNews.getTotalRow()/pageNews.getRowsPerPage());
				
		openPage(0);
		int i= 1;
		i++;
		i--;
	}
	
	public void openPage(int page) {
		pageNews.setCurrentPage(page);
		listNewsPage = crawledNewService.findByCrawledNewsStatus(StatusCrawledNewsConstant.DISPLAY, 
				PageRequest.of(pageNews.getCurrentPage(), pageNews.getRowsPerPage()));
		
		listNews = listNewsPage.stream().map(x->x).collect(Collectors.toList());
	}

	public void firstPage() {
		openPage(0);
	}
	public void lastPage() {
		openPage(pageNews.getTotalPages());
	}
	public void previousPage() {
		if (pageNews.getCurrentPage()!=0) {
			openPage(pageNews.getCurrentPage()-1);
		}
	}
	public void nextPage() {
		if (pageNews.getCurrentPage()!=pageNews.getTotalPages()) {
			openPage(pageNews.getCurrentPage()+1);
		}
	}
	
	public Pagination getPageNews() {
		return pageNews;
	}

	public void setPageNews(Pagination pageNews) {
		this.pageNews = pageNews;
	}

	public List<CrawledNews> getListNews() {
		return listNews;
	}

	public void setListNews(List<CrawledNews> listNews) {
		this.listNews = listNews;
	}


	
}
