package capstone.lip.landinformationportal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import capstone.lip.landinformationportal.common.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;

//@Component
public class LazyCrawledNew extends LazyDataModel<CrawledNews> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired 
	private ICrawledNewsService crawledNewService;
	
	Page<CrawledNews> listData;
	
	public LazyCrawledNew(ICrawledNewsService service) {
		this.crawledNewService = service;
		this.setRowCount((int)crawledNewService.countByStatus(StatusCrawledNewsConstant.NON_DISPLAY));
	}
	@Override
	public List<CrawledNews> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		Page<CrawledNews> list = 
				crawledNewService.findByCrawledNewsStatus(StatusCrawledNewsConstant.NON_DISPLAY, PageRequest.of(first/pageSize, pageSize));
		List<CrawledNews> list1 = list.stream().map(x->x).collect(Collectors.toList());
		return list1;
	}
	@Override
	public List<CrawledNews> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		return super.load(first, pageSize, multiSortMeta, filters);
	}
	@Override
    public CrawledNews getRowData(String rowKey) {
        for (CrawledNews news : listData) {
            if (news.getCrawledNewsID().equals(rowKey)) {
                return news;
            }
        }
 
        return null;
    }
	@Override
	public int getRowCount() {
		return super.getRowCount();
	}
    @Override
    public Object getRowKey(CrawledNews news) {
        return news.getCrawledNewsID();
    }
}
