package capstone.lip.landinformationportal.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.util.UriComponentsBuilder;

import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.service.CrawlRealEstateService;
import capstone.lip.landinformationportal.service.Interface.ICrawlRealEstateService;

@Component
public class CrawlRealEstateScheduleJob implements Job {

	static final String URL = "http://127.0.0.1:8000/realestateobject/";
	
	@Autowired
	private ICrawlRealEstateService crawlReoService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		System.out.println("crawling");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", "Token f992ddf15c9d3d30dac1358e918a5693d85d174c");

		Map<String, String> map = new HashMap<>();
		map.put("type", "reo");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("type", "reo")
//		        .queryParam("daily", "true")
		        ;

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);
		System.out.println(restTemplate.toString());

		// code done here
		ResponseEntity<RealEstateObjectCrawl[]> responseEntity = 
				restTemplate.exchange(builder.toUriString(),HttpMethod.GET,entity,RealEstateObjectCrawl[].class);
		List<RealEstateObjectCrawl> listCrawl = Arrays.asList(responseEntity.getBody());
		// temp comment
		crawlReoService.saveRealEstateCrawl(listCrawl);
	}

}
