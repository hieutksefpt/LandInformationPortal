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
import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.common.entity.HousesDetail;
import capstone.lip.landinformationportal.common.entity.HousesFeature;
import capstone.lip.landinformationportal.common.entity.LandsDetail;
import capstone.lip.landinformationportal.common.entity.LandsFeature;
import capstone.lip.landinformationportal.common.entity.RealEstate;

@Service
public class PredictPriceService implements IPredictPriceService {

	@Value("${service.predictor.url}")
	private String URL;

	@Value("${service.token}")
	private String token;

	@Override
	public String getPredictPrice(RealEstate realEstate) {
		try {
			if (realEstate == null) {
				throw new Exception();
			}
			if (realEstate.getRealEstateId() == null) {
				throw new Exception();
			}
//			ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.set("WWW-Authenticate", "Token");
			header.set("Content-Type", "application/json");
			header.set("Authorization", token);
			header.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			
			RealEstateObjectCrawl reoDto = convertRealEstateToRealEstateObjectCrawl(realEstate);

			Map<String, String> map = new HashMap<>();
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
					.queryParam("numberToilets",
							reoDto.getNumberToilets() == null ? "" : reoDto.getNumberToilets().toString())
					.queryParam("numberBedrooms",
							reoDto.getNumberBedrooms() == null ? "" : reoDto.getNumberBedrooms().toString())
					.queryParam("area", reoDto.getArea() == null ? "" : reoDto.getArea().toString())
					.queryParam("latitude", reoDto.getLatitude() == null ? "" : reoDto.getLatitude().toString())
					.queryParam("longitude", reoDto.getLongitude() == null ? "" : reoDto.getLongitude().toString());
			HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, header);
			ResponseEntity<String> value = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					String.class);
			return value.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addDataToModel(RealEstate realEstate) {
		try {
			if(realEstate == null) {
				throw new Exception();
			}
			if(realEstate.getRealEstateId() == null || realEstate.getRealEstatePrice() == null || realEstate.getRealEstatePrice().compareTo(BigDecimal.ZERO) <= 0 || realEstate.getRealEstateStatus() != StatusRealEstateConstant.VERIFIED) {
				throw new Exception();
			}
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.set("WWW-Authenticate", "Token");
			header.set("Content-Type", "application/json");
			header.set("Authorization", token);
			header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			RealEstateObjectCrawl reoDto = convertRealEstateToRealEstateObjectCrawl(realEstate);
			HttpEntity param = new HttpEntity<>(reoDto, header);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);

			ResponseEntity<String> value = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, param,
					String.class);

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
				HousesDetail housesDetail = housesDetailRepository.findByIdHouseIdAndIdHousesFeatureId(idHouse,
						element.getHousesFeatureID());
				Integer value = 0;
				try {
					value = Integer.parseInt(housesDetail.getValue());
				} catch (Exception e) {
					value = 0;
				}

				if (element.getHousesFeatureName().equals(HousesFeatureNameConstant.NUMBERBEDROOMS)) {
					reoDto.setNumberBedrooms(value);
				} else if (element.getHousesFeatureName().equals(HousesFeatureNameConstant.NUMBERFLOORS)) {
					reoDto.setNumberFloor(value);
				} else if (element.getHousesFeatureName().equals(HousesFeatureNameConstant.NUMBERTOILETS)) {
					reoDto.setNumberToilets(value);
				}
			}
		}
		if (idLand != null) {
			for (LandsFeature element : listLandsFeature) {
				LandsDetail landsDetail = landsDetailRepository.findByIdLandIdAndIdLandsFeatureId(idLand,
						element.getLandsFeatureID());
				Double value = 0.0;
				try {
					value = Double.parseDouble(landsDetail.getValue());
				} catch (Exception e) {
					value = 0.0;
				}
				if (element.getLandsFeatureName().equals(LandsFeatureNameConstant.AREA)) {
					BigDecimal bigNum = new BigDecimal(value.intValue());
					if (bigNum.compareTo(BigDecimal.ZERO) != 1) {
						reoDto.setArea(BigDecimal.ONE);
					} else {
						reoDto.setArea(bigNum);
					}
				} else if (element.getLandsFeatureName().equals(LandsFeatureNameConstant.SIZEFRONT)) {
					reoDto.setSizeFront(value);
				} else if (element.getLandsFeatureName().equals(LandsFeatureNameConstant.WARDIN)) {
					reoDto.setWardin(value);
				}
			}
		}
		return reoDto;
	}
}
