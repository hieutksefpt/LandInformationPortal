package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AnhHao
 */
@Named
@ViewScoped
public class ListAllRealEstateBean implements Serializable {

    @Autowired
    private IRealEstateService realEstateService;

    @Autowired
    private ILandService landService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ILandsDetailService landsDetailService;

    @Autowired
    private IHousesDetailService housesDetailService;

    private List<RealEstate> listAllRealEstate;
    private RealEstate realEstateClicked;
    private String jsonMultipleCoordinate;
    private String txtSearchBox;
    private String txtComboBoxSource;
    private String txtComboBoxStatus;
    private List<String> listRealEstateSource;

    @PostConstruct
    public void init() {
        this.listAllRealEstate = realEstateService.findAll();
        this.listRealEstateSource = realEstateService.listRealEstateSource();
        drawMarkers();
    }

    public void clickButtonDeleteOnRow(long realEstateId) {
        this.realEstateClicked = realEstateService.findById(realEstateId);
    }

    public void drawMarkers() {
        transferListCoordinate();
        PrimeFaces.current().executeScript("drawMarkers(" + this.jsonMultipleCoordinate + ")");
    }

    public void deleteRealEstate() {
        List<LandsDetail> listLandDetail = realEstateClicked.getLand().getListLandsDetail();
        landsDetailService.delete(listLandDetail);
        Land land = realEstateClicked.getLand();
        landService.delete(land);

        List<House> listHouse = realEstateClicked.getListHouse();
        List<HousesDetail> listHouseDetail = listHouse.stream()
                .map(x -> x.getListHousesDetail()).flatMap(List::stream).collect(Collectors.toList());

        housesDetailService.delete(listHouseDetail);
        houseService.delete(listHouse);

        realEstateService.delete(realEstateClicked);
        System.out.println(listAllRealEstate.remove(findRealEstateIndexInList(realEstateClicked.getRealEstateId())).getRealEstateName());
        drawMarkers();
    }

    public int findRealEstateIndexInList(long realEstateId) {
        for (int i = 0; i < listAllRealEstate.size(); i++) {
            if (listAllRealEstate.get(i).getRealEstateId() == realEstateId) {
                return i;
            }
        }
        return -1;
    }

    public void transferListCoordinate() {
        List<Coordinate> listCoordinate = new ArrayList<>();
        listAllRealEstate.stream().forEach((re) -> {
            listCoordinate.add(new Coordinate().setLatitude(re.getRealEstateLat()).setLongitude(re.getRealEstateLng()));
        });
        Gson gson = new Gson();
        jsonMultipleCoordinate = gson.toJson(listCoordinate);
    }

    public void listFilterRealEstate() {
        String tempStatus = txtComboBoxStatus;
        if (txtComboBoxStatus.equalsIgnoreCase("2")) {
            tempStatus = null;
        }
        if (txtComboBoxSource.equalsIgnoreCase("Nguồn BĐS")) {
            this.txtComboBoxSource = null;
        }
        if (txtComboBoxStatus.equalsIgnoreCase("Trạng thái BĐS")) {
            this.txtComboBoxStatus = null;
        }
        listAllRealEstate = realEstateService.listFilterRealEstate(txtSearchBox, txtComboBoxSource, tempStatus);
        drawMarkers();
    }

    public void goToDetails(long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/user/viewrealestatedetail.xhtml?realEstateId=" + realEstateId);
    }

    public String getTxtSearchBox() {
        return txtSearchBox;
    }

    public void setTxtSearchBox(String txtSearchBox) {
        this.txtSearchBox = txtSearchBox;
    }

    public List<RealEstate> getListAllRealEstate() {
        return listAllRealEstate;
    }

    public void setListAllRealEstate(List<RealEstate> listAllRealEstate) {
        this.listAllRealEstate = listAllRealEstate;
    }

    public RealEstate getRealEstateClicked() {
        return realEstateClicked;
    }

    public void setRealEstateClicked(RealEstate realEstateClicked) {
        this.realEstateClicked = realEstateClicked;
    }

    public String getJsonMultipleCoordinate() {
        return jsonMultipleCoordinate;
    }

    public void setJsonMultipleCoordinate(String jsonMultipleCoordinate) {
        this.jsonMultipleCoordinate = jsonMultipleCoordinate;
    }

    public String getTxtComboBoxSource() {
        return txtComboBoxSource;
    }

    public void setTxtComboBoxSource(String txtComboBoxSource) {
        this.txtComboBoxSource = txtComboBoxSource;
    }

    public String getTxtComboBoxStatus() {
        return txtComboBoxStatus;
    }

    public void setTxtComboBoxStatus(String txtComboBoxStatus) {
        this.txtComboBoxStatus = txtComboBoxStatus;
    }

    public List<String> getListRealEstateSource() {
        return listRealEstateSource;
    }

    public void setListRealEstateSource(List<String> listRealEstateSource) {
        this.listRealEstateSource = listRealEstateSource;
    }
}
