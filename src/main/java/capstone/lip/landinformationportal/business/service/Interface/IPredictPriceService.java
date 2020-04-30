package capstone.lip.landinformationportal.business.service.Interface;

public interface IPredictPriceService {
	String getPredictPrice(String numberToilets, String numberBedrooms, String area, String latitude, String longitude);
}
