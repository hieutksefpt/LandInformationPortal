package capstone.lip.landinformationportal.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.entity.CrawledNews;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import org.springframework.data.domain.Sort;

public class LazyCrawledRealEstate extends LazyDataModel<RealEstate> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IRealEstateService realEstateService;

    Page<CrawledNews> listData;

    public LazyCrawledRealEstate(IRealEstateService service) {
        this.realEstateService = service;
        this.setRowCount((int) realEstateService.countByRealEstateStatus(String.valueOf(StatusRealEstateConstant.CONFUSED)));
    }

    @Override
    public List<RealEstate> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        Sort sort = null;
        sort = Sort.by("modifiedDate").descending();
        Page<RealEstate> list
                = realEstateService.findByRealEstateStatus(String.valueOf(StatusRealEstateConstant.CONFUSED),
                        PageRequest.of(first / pageSize, pageSize, sort));
        if (list == null) return null;
        List<RealEstate> list1 = list.stream().map(x -> x).collect(Collectors.toList());
        return list1;
    }
}
