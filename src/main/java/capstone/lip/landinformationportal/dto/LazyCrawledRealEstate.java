package capstone.lip.landinformationportal.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;

public class LazyCrawledRealEstate extends LazyDataModel<RealEstate> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired 
	private IRealEstateService realEstateService;
	
	Page<CrawledNews> listData;
	
	public LazyCrawledRealEstate(IRealEstateService service) {
		this.realEstateService = service;
		this.setRowCount((int)realEstateService.count());
	}
	@Override
	public List<RealEstate> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		Page<RealEstate> list = 
				realEstateService.findByRealEstateStatus(String.valueOf(StatusRealEstateConstant.NOT_VERIFIED),
						PageRequest.of(first/pageSize, pageSize));
		List<RealEstate> list1 = list.stream().map(x->x).collect(Collectors.toList());
		return list1;
	}
}
