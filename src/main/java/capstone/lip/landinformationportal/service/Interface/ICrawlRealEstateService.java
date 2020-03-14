package capstone.lip.landinformationportal.service.Interface;

import java.util.List;

import capstone.lip.landinformationportal.dto.RealEstateObjectCrawl;

public interface ICrawlRealEstateService {
	void saveRealEstateCrawl(List<RealEstateObjectCrawl> listReoCrawl);
}
