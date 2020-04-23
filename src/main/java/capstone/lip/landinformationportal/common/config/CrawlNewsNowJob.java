package capstone.lip.landinformationportal.common.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import capstone.lip.landinformationportal.business.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.common.constant.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.dto.NewsCrawl;
import capstone.lip.landinformationportal.common.entity.CrawledNews;
@Component
public class CrawlNewsNowJob implements Job {
	@Value("${service.crawl.url}")
	private String URL;	
	
	@Value("${service.crawl.token}")
	private String token;
	@Autowired
	private ICrawledNewsService crawlNewsService;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		System.out.println("crawl news now");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", token);
//		String temp = (String)context.get("crawlnow");
		Map<String, String> map = new HashMap<>();
//		map.put("type", "reo");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("type", "news")
//		        .queryParam("daily", "true")
		        .queryParam("crawlnow","true")
		        ;

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);

		try {
			ResponseEntity<NewsCrawl[]> responseEntity = 
					restTemplate.exchange(builder.toUriString(),HttpMethod.GET,entity,NewsCrawl[].class);
			List<NewsCrawl> listDTO = Arrays.asList(responseEntity.getBody());
			List<CrawledNews> listNews = parseListNewsDTOToListNewsEntity(listDTO);
			crawlNewsService.saveAll(listNews);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	private List<CrawledNews> parseListNewsDTOToListNewsEntity(List<NewsCrawl> listDTO) {
		List<CrawledNews> list = new ArrayList();
		for (NewsCrawl element : listDTO) {
			CrawledNews news = new CrawledNews()
					.setCrawledNewsLink(element.getLink())
					.setCrawledNewsShortDescription(element.getDescription())
					.setCrawledNewsTitle(element.getTitle())
					.setCrawledNewsWebsite(element.getDomain())
					.setCrawledNewsTime(element.getDate())
					.setCrawledNewsImageUrl(element.getImageLink())
					.setCrawledNewsStatus(StatusCrawledNewsConstant.NON_DISPLAY);
			if (crawlNewsService.findByCrawledNewsLink(element.getLink()) == null) {
				list.add(news);
			}
		}
		return list;
	}

}
