package capstone.lip.landinformationportal.config;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CrawlJob implements Job {

	static final String URL = "http://127.0.0.1:8000/realestateobject/";
	RestTemplate restTemplate = new RestTemplate();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("crawling");
		
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", "application/json");
		header.set("type", "reo");
		
//		not work in get 
		Map<String, String> map = new HashMap<>();
//		map.put("type", "reo");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("type", "reo");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);
		System.out.println(restTemplate.toString());
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, entity, String.class);
		System.out.print(response.getBody());
	}

}
