package capstone.lip.landinformationportal.business.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import capstone.lip.landinformationportal.business.service.Interface.IPredictPriceService;

@Service
public class PredictPriceService implements IPredictPriceService{

	@Value("${service.predict.price.url}")
	private String URL;	
	
	@Value("${service.crawl.token}")
	private String token;
	
	@Override
	public String getPredictPrice(String numberToilets, String numberBedrooms, String area, String latitude,
			String longitude) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", token);
		Map<String, String> map = new HashMap<>();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("numberToilets", numberToilets)
		        .queryParam("numberBedrooms", numberBedrooms)
		        .queryParam("area", area)
		        .queryParam("latitude", latitude)
		        .queryParam("longitude", longitude)
		        ;
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);
		try {
			ResponseEntity<String> value = restTemplate.exchange(builder.toUriString(),HttpMethod.GET,entity,String.class);
			return value.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
