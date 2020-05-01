package capstone.lip.landinformationportal.business.service.Interface;

import capstone.lip.landinformationportal.common.entity.RealEstate;

public interface IPredictPriceService {
	
//	String getPredictPrice(String numberToilets, String numberBedrooms, String area, String latitude, String longitude);
	String getPredictPrice(RealEstate realEstate);
	
	boolean addDataToModel(RealEstate realEstate);
}
