package capstone.lip.landinformationportal.business.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import capstone.lip.landinformationportal.business.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.business.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.business.service.Interface.IHousesFeatureService;
import capstone.lip.landinformationportal.business.service.Interface.ILandsFeatureService;
import capstone.lip.landinformationportal.business.service.Interface.IPredictPriceService;
import capstone.lip.landinformationportal.common.constant.HousesFeatureNameConstant;
import capstone.lip.landinformationportal.common.constant.LandsFeatureNameConstant;
import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.HousesFeature;
import capstone.lip.landinformationportal.common.entity.LandsDetail;
import capstone.lip.landinformationportal.common.entity.LandsFeature;
import capstone.lip.landinformationportal.common.entity.RealEstate;

@Service
public class PredictPriceService implements IPredictPriceService{

	@Value("${service.predict.price.url}")
	private String URL;	
	
	@Value("${service.crawl.token}")
	private String token;
	
	@Override
	public String getPredictPrice(RealEstate realEstate) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", token);
		
		RealEstateObjectCrawl reoDto = convertRealEstateToRealEstateObjectCrawl(realEstate);
		
		Map<String, String> map = new HashMap<>();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
		        .queryParam("numberToilets", reoDto.getNumberToilets()==null? "" : reoDto.getNumberToilets().toString())
		        .queryParam("numberBedrooms", reoDto.getNumberBedrooms()==null? "" : reoDto.getNumberBedrooms().toString())
		        .queryParam("area", reoDto.getArea()==null? "" : reoDto.getArea().toString())
		        .queryParam("latitude", reoDto.getLatitude()==null? "" : reoDto.getLatitude().toString())
		        .queryParam("longitude", reoDto.getLongitude()==null? "" : reoDto.getLongitude().toString())
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

	@Override
	public boolean addDataToModel(RealEstate realEstate) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("WWW-Authenticate", "Token");
		header.set("Content-Type", "application/json");
		header.set("Authorization", token);
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		RealEstateObjectCrawl reoDto = convertRealEstateToRealEstateObjectCrawl(realEstate);
		HttpEntity param = new HttpEntity<>(reoDto, header);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
		
		try {
			ResponseEntity<String> value = restTemplate.exchange(builder.toUriString(),HttpMethod.POST, param, String.class);
			
//			restTemplate.postForEntity(URL, param);
			System.out.print(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Autowired
	private IHousesFeatureService housesFeatureService;
	
	@Autowired
	private ILandsFeatureService landsFeatureService;
	
	@Autowired
	private LandsDetailRepository landsDetailRepository;
	
	@Autowired
	private HousesDetailRepository housesDetailRepository;
	
	
	private RealEstateObjectCrawl convertRealEstateToRealEstateObjectCrawl(RealEstate realEstate) {
		RealEstateObjectCrawl reoDto = new RealEstateObjectCrawl();
		reoDto.setCodePost(realEstate.getRealEstateId());
		reoDto.setPrice(realEstate.getRealEstatePrice());
		reoDto.setLatitude(realEstate.getRealEstateLat());
		reoDto.setLongitude(realEstate.getRealEstateLng());
		List<HousesFeature> listHousesFeature = housesFeatureService.findAll();
		List<LandsFeature> listLandsFeature = landsFeatureService.findAll();
		Long idHouse = null, idLand = null;
		if (realEstate.getListHouse() != null && realEstate.getListHouse().size() > 1) {
			idHouse = realEstate.getListHouse().get(0).getHouseId();
		}
		if (realEstate.getLand() != null) {
			idLand = realEstate.getLand().getLandId();
		}
		if (idHouse != null) {
			for (HousesFeature element : listHousesFeature) {
				HousesDetail housesDetail = housesDetailRepository.findByIdHouseIdAndIdHousesFeatureId(idHouse, element.getHousesFeatureID());
				if (element.getHousesFeatureName().equals(HousesFeatureNameConstant.NUMBERBEDROOMS)){
					reoDto.setNumberBedrooms(Integer.parseInt(housesDetail.getValue()));
				}
				else if (element.getHousesFeatureName().equals(HousesFeatureNameConstant.NUMBERFLOORS)) {
					reoDto.setNumberFloor(Integer.parseInt(housesDetail.getValue()));
				}
				else if (element.getHousesFeatureName().equals(HousesFeatureNameConstant.NUMBERTOILETS)) {
					reoDto.setNumberToilets(Integer.parseInt(housesDetail.getValue()));
				}
			}
		}
		if (idLand != null) {
			for (LandsFeature element : listLandsFeature) {
				LandsDetail landsDetail = landsDetailRepository.findByIdLandIdAndIdLandsFeatureId(idLand, element.getLandsFeatureID());
				if (element.getLandsFeatureName().equals(LandsFeatureNameConstant.AREA)) {
					reoDto.setArea(new BigDecimal(landsDetail.getValue()));
				}
				else if (element.getLandsFeatureName().equals(LandsFeatureNameConstant.SIZEFRONT)) {
					reoDto.setSizeFront(Double.parseDouble(landsDetail.getValue()));
				}
				else if (element.getLandsFeatureName().equals(LandsFeatureNameConstant.WARDIN)) {
					reoDto.setWardin(Double.parseDouble(landsDetail.getValue()));
				}
			}
		}
		return reoDto;
	}
}
