package capstone.lip.landinformationportal.common.dto;

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

import capstone.lip.landinformationportal.business.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.common.constant.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.entity.CrawledNews;
import org.springframework.data.domain.Sort;

//@Component
public class LazyCrawledNew extends LazyDataModel<CrawledNews> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ICrawledNewsService crawledNewService;

    public LazyCrawledNew(ICrawledNewsService service) {
        this.crawledNewService = service;
        this.setRowCount((int) crawledNewService.countByStatus(StatusCrawledNewsConstant.NON_DISPLAY));
    }

    @Override
    public List<CrawledNews> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        Sort sort = null;
        sort = Sort.by("modifiedDate").descending();
        Page<CrawledNews> list
                = crawledNewService.findByCrawledNewsStatus(StatusCrawledNewsConstant.NON_DISPLAY, PageRequest.of(first / pageSize, pageSize, sort));
        List<CrawledNews> list1 = list.stream().map(x -> x).collect(Collectors.toList());
        return list1;
    }

    @Override
    public List<CrawledNews> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        return super.load(first, pageSize, multiSortMeta, filters);
    }

    @Override
    public CrawledNews getRowData(String rowKey) {
        return crawledNewService.findById(Long.parseLong(rowKey));
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
