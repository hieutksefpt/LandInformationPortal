package capstone.lip.landinformationportal.config;

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

import capstone.lip.landinformationportal.dto.NewsCrawl;
import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;
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
		System.out.println("crawling");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", token);
		String temp = (String)context.get("crawlnow");
		Map<String, String> map = new HashMap<>();
//		map.put("type", "reo");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("type", "news")
//		        .queryParam("daily", "true")
		        .queryParam("crawlnow","true")
		        ;

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);
		System.out.println(restTemplate.toString());

		// code done here
		ResponseEntity<NewsCrawl[]> responseEntity = 
				restTemplate.exchange(builder.toUriString(),HttpMethod.GET,entity,NewsCrawl[].class);
		List<NewsCrawl> listCrawl = Arrays.asList(responseEntity.getBody());
		// temp comment
		crawlNewsService.save(listCrawl);
	}

}
