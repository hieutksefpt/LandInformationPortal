package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.Report;
import capstone.lip.landinformationportal.entity.ReportId;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IReportService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author AnhHao
 */
@Named
@ViewScoped
public class ViewRealEstateDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private RealEstate realEstateClicked;
    private String jsonCoordinate;
    private Land currentLand;
    private List<House> currentListHouse;
    private boolean ownRealEstate;
    private String numberOfReport = "0";
    private User currentUser;

    @Autowired
    private IRealEstateService realEstateService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReportService reportService;

    private long tempRealEstateId;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long realEstateId = Long.parseLong(params.get("realEstateId"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (auth != null) {
            username = (String) auth.getPrincipal();
        }

        currentUser = userService.findByUsername(username);

        tempRealEstateId = realEstateId;
        realEstateClicked = realEstateService.findById(realEstateId);

        if (realEstateClicked.getUser().equals(currentUser)) {
            ownRealEstate = true;
        } else {
            ownRealEstate = false;
        }

        numberOfReport = String.valueOf(realEstateClicked.getListReport().size());
        currentLand = realEstateClicked.getLand();
        currentListHouse = realEstateClicked.getListHouse();
        transferCoordinate();
    }

    public Report checkExistInReport() {
        Report r = reportService.findById(currentUser.getUserId(), realEstateClicked.getRealEstateId());
        return r;
    }

    public void reportAndUnreport() {
        Report report = checkExistInReport();
        if (report != null) {
            reportService.delete(report);
        } else if (report == null) {
            reportService.save(new Report().setId(new ReportId(currentUser.getUserId(), realEstateClicked.getRealEstateId())));
        }
        realEstateClicked = realEstateService.findById(realEstateClicked.getRealEstateId());
        numberOfReport = String.valueOf(realEstateClicked.getListReport().size());
    }

    public void goToDetails(long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/user/updatecontributerealestate.xhtml?realEstateId=" + realEstateId);
    }

    public void deleteRealEstate() {
        realEstateService.delete(tempRealEstateId);
        redirectListOwnReo();
    }

    public void transferCoordinate() {
        Coordinate coordinate;
        coordinate = new Coordinate().setLatitude(realEstateClicked.getRealEstateLat()).setLongitude(realEstateClicked.getRealEstateLng());
        Gson gson = new Gson();
        jsonCoordinate = gson.toJson(coordinate);
    }

    public void redirectListOwnReo() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/user/listownrealestate.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<HousesDetail> getListHousesDetail(House house) {
        return house.getListHousesDetail();
    }

    public List<LandsDetail> getListLandsDetail() {
        return this.currentLand.getListLandsDetail();
    }

    public Land getCurrentLand() {
        return currentLand;
    }

    public void setCurrentLand(Land currentLand) {
        this.currentLand = currentLand;
    }

    public List<House> getCurrentListHouse() {
        return currentListHouse;
    }

    public void setCurrentListHouse(List<House> currentListHouse) {
        this.currentListHouse = currentListHouse;
    }

    public String getJsonCoordinate() {
        return jsonCoordinate;
    }

    public void setJsonCoordinate(String jsonCoordinate) {
        this.jsonCoordinate = jsonCoordinate;
    }

    public RealEstate getRealEstateClicked() {
        return realEstateClicked;
    }

    public void setRealEstateClicked(RealEstate realEstateClicked) {
        this.realEstateClicked = realEstateClicked;
    }

    public long getTempRealEstateId() {
        return tempRealEstateId;
    }

    public void setTempRealEstateId(long tempRealEstateId) {
        this.tempRealEstateId = tempRealEstateId;
    }

    public boolean isOwnRealEstate() {
        return ownRealEstate;
    }

    public void setOwnRealEstate(boolean ownRealEstate) {
        this.ownRealEstate = ownRealEstate;
    }

    public String getNumberOfReport() {
        return numberOfReport;
    }

    public void setNumberOfReport(String numberOfReport) {
        this.numberOfReport = numberOfReport;
    }

}
