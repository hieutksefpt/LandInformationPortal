package capstone.lip.landinformationportal.business.bean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.component.UICommand;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.google.gson.Gson;

import capstone.lip.landinformationportal.business.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.business.service.Interface.IProvinceService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.common.constant.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.dto.Coordinate;
import capstone.lip.landinformationportal.common.dto.GroupByDateMaxMin;
import capstone.lip.landinformationportal.common.dto.MaxMinAvg;
import capstone.lip.landinformationportal.common.dto.Pagination;
import capstone.lip.landinformationportal.common.entity.CrawledNews;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.FormedCoordinate;
import capstone.lip.landinformationportal.common.entity.Province;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;

@Named
@ViewScoped
public class HomepageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ICrawledNewsService crawledNewService;
    @Autowired
    private IRealEstateService realEstateService;
    @Autowired
    private IProvinceService provinceService;

    private Pagination pageNews;
    private Pagination pageReo;

    private List<CrawledNews> listNews;
    private List<Province> listProvince;
    private List<District> listDistrict;
    private List<SegmentOfStreet> listSegmentOfStreet;
    private List<Street> listStreet;
    private String provinceIdSelected;
    private String districtIdSelected;
    private String streetIdSelected;
    private String segmentIdSelected;

    private Province provinceSelected;
    private District districtSelected;
    private SegmentOfStreet segmentSelected;
    private Street streetSelected;

    private List<RealEstate> listRealEstate;
    private String typeReo;
    private boolean isDisplayReoPanel;
    private MaxMinAvg maxMinAvg;
    private LineChartModel lineChartModel;

    @PostConstruct
    public void init() {
        pageNews = new Pagination()
                .setTotalRow((int) crawledNewService.countByStatus(StatusCrawledNewsConstant.DISPLAY))
                .setRowsPerPage(10)
                .setPageRange(3);
        pageNews.setTotalPages(pageNews.getTotalRow() / pageNews.getRowsPerPage()).setCurrentPage(0);

        pageReo = new Pagination().setTotalRow(0).setTotalPages(0);

        openPageNews(0);
        provinceIdSelected = streetIdSelected = segmentIdSelected = districtIdSelected = "";

        isDisplayReoPanel = false;
        districtIdSelected = provinceIdSelected = segmentIdSelected = streetIdSelected = "";
        listProvince = provinceService.findAll();
    }

    private void openPageNews(int page) {
        pageNews.setCurrentPage(page);
        Page<CrawledNews> listNewsPage = crawledNewService.findByCrawledNewsStatus(StatusCrawledNewsConstant.DISPLAY,
                PageRequest.of(pageNews.getCurrentPage(), pageNews.getRowsPerPage(), Sort.by("modifiedDate").descending()));

        listNews = listNewsPage.stream().map(x -> x).collect(Collectors.toList());
    }

    public void openPageReo(int page) {
        isDisplayReoPanel = true;

        if (pageReo == null) {
            return;
        }
        pageReo.setCurrentPage(page);
        Page<RealEstate> listPageReo = null;
        if (districtSelected == null) {
            return;
        }
        String address = districtSelected.getDistrictName();
        if (streetSelected != null) {
            address = streetSelected.getStreetName();
        }
        if (segmentSelected != null) {
            address = segmentSelected.getSegmentName();
        }

        switch (typeReo) {
            case "0":
                listPageReo = realEstateService.listFilterRealEstateByAddress(address,
                        PageRequest.of(pageReo.getCurrentPage(), pageReo.getRowsPerPage()));
                break;
            case "1":
                listPageReo = realEstateService.listFilterRealEstateByAddressAndSource(address,
                        StatusRealEstateConstant.CONTRIBUTOR,
                        PageRequest.of(pageReo.getCurrentPage(), pageReo.getRowsPerPage()));
                break;
            case "2":
                listPageReo = realEstateService.listFilterRealEstateByAddressAndSourceNot(address,
                        StatusRealEstateConstant.CONTRIBUTOR,
                        PageRequest.of(pageReo.getCurrentPage(), pageReo.getRowsPerPage()));
                break;
            default:
                break;
        }
        if (listPageReo != null) {
            listRealEstate = listPageReo.stream().map(x -> x).collect(Collectors.toList());
            PrimeFaces.current().executeScript("displayReoList(true)");

            List<Coordinate> listCoordinate = listRealEstate.stream().map(x -> {
                return new Coordinate().setId(x.getRealEstateId())
                        .setLatitude(x.getRealEstateLat())
                        .setLongitude(x.getRealEstateLng())
                        .setSource(x.getRealEstateSource())
                        .setStatus(x.getRealEstateStatus());
            }).collect(Collectors.toList());
            Gson gson = new Gson();
            PrimeFaces.current().executeScript("drawListMarker(" + gson.toJson(listCoordinate) + ")");

        } else {
            listRealEstate = new ArrayList<>();
//			PrimeFaces.current().executeScript("displayReoList(false)");
        }
    }

	public void provinceChange() {
		setDistrictIdSelected("");
		listSegmentOfStreet= new ArrayList();
		setSegmentIdSelected("");
		listStreet = new ArrayList();
		setStreetIdSelected("");
		if (provinceSelected == null) {
			return;
		}
		listDistrict = provinceSelected.getListDistrict();
		
	}
	public void districtChange() {
		setStreetIdSelected("");
		listSegmentOfStreet = new ArrayList();
		if (districtSelected == null) {
			return;
		}
		List<SegmentOfStreet>listTemp = districtSelected.getListSegmentOfStreet();
		if (listTemp!= null)
			listStreet = listTemp.stream().map(x->x.getStreet()).distinct().collect(Collectors.toList());
		
		
		viewStatistic(districtSelected.getDistrictName());
		setTypeReo("0");
		openPageReo(0);
		
	}
	public void streetChange() {
		setSegmentIdSelected("");
		if (streetSelected == null) {
			return;
		}
		listSegmentOfStreet = streetSelected.getListSegmentOfStreet();
		
		viewStatistic(streetSelected.getStreetName());
		setTypeReo("0");
		openPageReo(0);
		
	}
	public void segmentOfStreetChange() {
		viewStatistic(segmentSelected.getSegmentName());
		setTypeReo("0");
		openPageReo(0);
	}
	public void viewStatistic(String address) {
		MaxMinAvg maxMinAvgTemp = realEstateService.listMaxMinAvg(address);
		maxMinAvg = maxMinAvgTemp;

    }

    public void districtChange() {
        setStreetIdSelected("");
        listSegmentOfStreet = new ArrayList();
        setSegmentIdSelected("");
        if (districtSelected == null) {
            return;
        }
        List<SegmentOfStreet> listTemp = districtSelected.getListSegmentOfStreet();
        if (listTemp != null) {
            listStreet = listTemp.stream().map(x -> x.getStreet()).distinct().collect(Collectors.toList());
        }

        viewStatistic(districtSelected.getDistrictName());
        setTypeReo("0");
        openPageReo(0);

    }

    public void streetChange() {
        setSegmentIdSelected("");
        if (streetSelected == null) {
            return;
        }
        listSegmentOfStreet = streetSelected.getListSegmentOfStreet();

        viewStatistic(streetSelected.getStreetName());
        setTypeReo("0");
        openPageReo(0);

    }

    public void segmentOfStreetChange() {
        viewStatistic(segmentSelected.getSegmentName());
        setTypeReo("0");
        openPageReo(0);
    }

    public void viewStatistic(String address) {
        MaxMinAvg maxMinAvgTemp = realEstateService.listMaxMinAvg(address);
        maxMinAvg = maxMinAvgTemp;

        List<GroupByDateMaxMin> listStat = realEstateService.listGroupByDateAndValue(address);
        lineChartModel = createChart(listStat);
        lineChartModel.setLegendPosition("e");
        lineChartModel.setTitle("Biểu đồ giá");
        lineChartModel.setAnimate(true);
        lineChartModel.getAxes().put(AxisType.X, new CategoryAxis("Ngày"));
    }

    private LineChartModel createChart(List<GroupByDateMaxMin> listStat) {
        LineChartModel model = new LineChartModel();
        ChartSeries max = new ChartSeries();
        max.setLabel("Cao nhất");
        ChartSeries min = new ChartSeries();
        min.setLabel("Thấp nhất");
        ChartSeries avg = new ChartSeries();
        avg.setLabel("Trung bình");
        for (GroupByDateMaxMin element : listStat) {
            Timestamp key = element.getDateCreated();
            MaxMinAvg value = element.getMaxMinAvg();
            Date date = new Date(key.getTime());
            SimpleDateFormat format = new SimpleDateFormat("dd-MM");
            String dateString = format.format(date);
            max.set(dateString, value.getMax().divideToIntegralValue(new BigDecimal(1000000)));
            min.set(dateString, value.getMin().divideToIntegralValue(new BigDecimal(1000000)));
            avg.set(dateString, value.getAvg().divideToIntegralValue(new BigDecimal(1000000)));
        }

        model.addSeries(max);
        model.addSeries(min);
        model.addSeries(avg);

        Axis yaxis = model.getAxis(AxisType.Y);
        yaxis.setMin(0);
        return model;
    }

    public void goToDetails(long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/viewrealestatedetail.xhtml?realEstateId=" + realEstateId);
    }

    public void firstPageNews() {
        openPageNews(0);
    }

    public void lastPageNews() {
        openPageNews(pageNews.getTotalPages() - 1);
    }

    public void pageNewsAt(ActionEvent event) {
        openPageNews(((Integer) ((UICommand) event.getComponent()).getValue() - 1));
    }

    public void pageReoAt(ActionEvent event) {
        openPageReo(((Integer) ((UICommand) event.getComponent()).getValue() - 1));
    }

    public void firstPageReo() {
        openPageReo(0);
    }

    public void lastPageReo() {
        openPageReo(pageReo.getTotalPages() - 1);
    }

    public Pagination getPageNews() {
        return pageNews;
    }

    public void setPageNews(Pagination pageNews) {
        this.pageNews = pageNews;
    }

    public List<CrawledNews> getListNews() {
        return listNews;
    }

    public void setListNews(List<CrawledNews> listNews) {
        this.listNews = listNews;
    }

    public List<Province> getListProvince() {
        return listProvince;
    }

    public void setListProvince(List<Province> listProvince) {
        this.listProvince = listProvince;
    }

    public List<District> getListDistrict() {
        return listDistrict;
    }

    public void setListDistrict(List<District> listDistrict) {
        this.listDistrict = listDistrict;
    }

    public List<SegmentOfStreet> getListSegmentOfStreet() {
        return listSegmentOfStreet;
    }

    public void setListSegmentOfStreet(List<SegmentOfStreet> listSegmentOfStreet) {
        this.listSegmentOfStreet = listSegmentOfStreet;
    }

    public List<Street> getListStreet() {
        return listStreet;
    }

    public void setListStreet(List<Street> listStreet) {
        this.listStreet = listStreet;
    }

    public Province getProvinceSelected() {
        return provinceSelected;
    }

    public void setProvinceSelected(Province provinceSelected) {
        this.provinceSelected = provinceSelected;
    }

    public String getProvinceIdSelected() {
        return provinceIdSelected;
    }

    public void setProvinceIdSelected(String provinceIdSelected) {
        this.provinceIdSelected = provinceIdSelected;
        if (provinceIdSelected != null && !provinceIdSelected.isEmpty()) {
            provinceSelected = listProvince.stream()
                    .filter(x -> x.getProvinceId().equals(Long.parseLong(provinceIdSelected))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + provinceSelected.getProvinceLat() + ", " + provinceSelected.getProvinceLng() + ", 15);");

        } else {
            provinceSelected = null;
        }
    }

    public String getDistrictIdSelected() {
        return districtIdSelected;
    }

    public void setDistrictIdSelected(String districtIdSelected) {
        this.districtIdSelected = districtIdSelected;
        if (districtIdSelected != null && !districtIdSelected.isEmpty()) {
            districtSelected = listDistrict.stream()
                    .filter(x -> x.getDistrictId().equals(Long.parseLong(districtIdSelected))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + districtSelected.getDistrictLat() + ", " + districtSelected.getDistrictLng() + ", 17);");
        } else {
            districtSelected = null;
        }
    }

    public String getStreetIdSelected() {
        return streetIdSelected;
    }

    public void setStreetIdSelected(String streetIdSelected) {
        this.streetIdSelected = streetIdSelected;
        if (streetIdSelected != null && !streetIdSelected.isEmpty()) {
            streetSelected = listStreet.stream()
                    .filter(x -> x.getStreetId().equals(Long.parseLong(streetIdSelected))).collect(Collectors.toList()).get(0);
            PrimeFaces.current().executeScript("focusMap(" + streetSelected.getStreetLat() + ", " + streetSelected.getStreetLng() + ", 19);");
        } else {
            streetSelected = null;
        }
    }

    public String getSegmentIdSelected() {
        return segmentIdSelected;
    }

    public void setSegmentIdSelected(String segmentIdSelected) {
        this.segmentIdSelected = segmentIdSelected;
        if (segmentIdSelected != null && !segmentIdSelected.isEmpty()) {
            segmentSelected = listSegmentOfStreet.stream()
                    .filter(x -> x.getSegmentId().equals(Long.parseLong(segmentIdSelected))).collect(Collectors.toList()).get(0);
            List<FormedCoordinate> listFormedCoordinate = segmentSelected.getListFormedCoordinate();
            List<Coordinate> listCoordinate = listFormedCoordinate.stream().map(x -> {
                return new Coordinate(x.getFormedLng(), x.getFormedLat());
            }).collect(Collectors.toList());
            Gson gson = new Gson();
            PrimeFaces.current().executeScript("clearDataMap()");
            PrimeFaces.current().executeScript("focusMap(" + listCoordinate.get(0).getLatitude() + ", " + listCoordinate.get(0).getLongitude() + ", 19);");
            PrimeFaces.current().executeScript("drawPath(" + gson.toJson(listCoordinate) + ", 19);");

        } else {
            segmentSelected = null;
        }
    }

    public District getDistrictSelected() {
        return districtSelected;
    }

    public void setDistrictSelected(District districtSelected) {
        this.districtSelected = districtSelected;
    }

    public SegmentOfStreet getSegmentSelected() {
        return segmentSelected;
    }

    public void setSegmentSelected(SegmentOfStreet segmentSelected) {
        this.segmentSelected = segmentSelected;
    }

    public Street getStreetSelected() {
        return streetSelected;
    }

    public void setStreetSelected(Street streetSelected) {
        this.streetSelected = streetSelected;
    }

    public List<RealEstate> getListRealEstate() {
        return listRealEstate;
    }

    public void setListRealEstate(List<RealEstate> listRealEstate) {
        this.listRealEstate = listRealEstate;
    }

    public String getTypeReo() {
        return typeReo;
    }

    public void setTypeReo(String typeReo) {
        if (typeReo == null) {
            typeReo = "0";
        }
        this.typeReo = typeReo;
        if (districtSelected == null && streetSelected == null) {
            return;
        }
        String address = districtSelected.getDistrictName();
        if (streetSelected != null) {
            address = streetSelected.getStreetName();
        }
        switch (typeReo) {
            case "0":
                pageReo = new Pagination()
                        .setTotalRow((int) realEstateService.countByRealEstateAddress(address))
                        .setRowsPerPage(100)
                        .setPageRange(3);
                pageReo.setTotalPages(pageReo.getTotalRow() / pageReo.getRowsPerPage())
                        .setCurrentPage(0);
                break;
            case "1":
                pageReo = new Pagination()
                        .setTotalRow((int) realEstateService.countByRealEstateSource(address, StatusRealEstateConstant.CONTRIBUTOR))
                        .setRowsPerPage(100)
                        .setPageRange(3);
                pageReo.setTotalPages(pageReo.getTotalRow() / pageReo.getRowsPerPage()).setCurrentPage(0);
                break;
            case "2":
                pageReo = new Pagination()
                        .setTotalRow((int) realEstateService.countByRealEstateSourceNot(address, StatusRealEstateConstant.CONTRIBUTOR))
                        .setRowsPerPage(100)
                        .setPageRange(3);
                pageReo.setTotalPages(pageReo.getTotalRow() / pageReo.getRowsPerPage()).setCurrentPage(0);
                break;
            default:
                break;
        }
    }

    public Pagination getPageReo() {
        return pageReo;
    }

    public void setPageReo(Pagination pageReo) {
        this.pageReo = pageReo;
    }

    public boolean isDisplayReoPanel() {
        return isDisplayReoPanel;
    }

    public void setDisplayReoPanel(boolean isDisplayReoPanel) {
        this.isDisplayReoPanel = isDisplayReoPanel;
    }

    public MaxMinAvg getMaxMinAvg() {
        return maxMinAvg;
    }

    public void setMaxMinAvg(MaxMinAvg maxMinAvg) {
        this.maxMinAvg = maxMinAvg;
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

}
