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

public class CrawlJob implements Job {

	static final String URL = "http://127.0.0.1:8000/realestateobject/";
	RestTemplate restTemplate = new RestTemplate();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("crawling");
		
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", "application/json");
		Map<String, Object> map = new HashMap<>();
		map.put("type", "reo");
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, header);
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
		System.out.print(response.getBody());
	}

}
