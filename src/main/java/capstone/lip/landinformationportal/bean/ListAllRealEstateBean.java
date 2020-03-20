package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
        transferListCoordinate();
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
