/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common.dto;

import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.common.entity.RealEstate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author AnhHao
 */
public class LazyListAllRealEstate extends LazyDataModel<RealEstate> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
    private IRealEstateService realEstateService;

    public LazyListAllRealEstate(IRealEstateService service) {
        this.realEstateService = service;
        this.setRowCount((int) realEstateService.count());
    }

    @Override
    public List<RealEstate> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        Page<RealEstate> list
                = realEstateService.findAll(PageRequest.of(first / pageSize, pageSize));
        List<RealEstate> list1 = list.stream().map(x -> x).collect(Collectors.toList());
        return list1;
    }

    @Override
    public RealEstate getRowData(String rowKey) {
        return realEstateService.findById(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(RealEstate realEstate) {
        return realEstate.getRealEstateId();
    }

}
