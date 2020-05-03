package capstone.lip.landinformationportal.common.config;

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

import capstone.lip.landinformationportal.business.service.Interface.ICrawlRealEstateService;
import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
@Component
public class CrawlRealEstateNowJob implements Job {

	@Value("${service.crawl.url}")
	private String URL;	
	
	@Value("${service.token}")
	private String token;
	
		
	@Autowired
	private ICrawlRealEstateService crawlReoService;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("crawling real estate now");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", token);

		Map<String, String> map = new HashMap<>();
//		map.put("type", "reo");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("type", "reo")
//		        .queryParam("daily", "true")
		        .queryParam("crawlnow","true")
		        ;

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);

		try {
			ResponseEntity<RealEstateObjectCrawl[]> responseEntity = 
					restTemplate.exchange(builder.toUriString(),HttpMethod.GET,entity,RealEstateObjectCrawl[].class);
			List<RealEstateObjectCrawl> listCrawl = Arrays.asList(responseEntity.getBody());
			crawlReoService.saveRealEstateCrawl(listCrawl);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
