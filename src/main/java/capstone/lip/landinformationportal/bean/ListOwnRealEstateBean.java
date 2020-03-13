package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
public class ListOwnRealEstateBean implements Serializable {

    @Autowired
    private IUserService userService;

    private List<RealEstate> listUserRealEstate;
    private RealEstate realEstateClicked;

    @PostConstruct
    public void init() {
        //Hàm init này phải truyền vào biến userId để list ra những thằng RealEstate thuộc user đó
        listUserRealEstate = new ArrayList<>();
        //truyền userId vào hàm dưới, đang hard code
        listUserRealEstate = userService.getListRealEstate(1L);
    }

    public void goToDetails(long userId, long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/common/viewrealestatedetail.xhtml?userId=" + userId + "&realEstateId=" + realEstateId);
    }

    public List<RealEstate> getListUserRealEstate() {
        return listUserRealEstate;
    }

    public void setListUserRealEstate(List<RealEstate> listUserRealEstate) {
        this.listUserRealEstate = listUserRealEstate;
    }

    public RealEstate getRealEstateClicked() {
        return realEstateClicked;
    }

    public void setRealEstateClicked(RealEstate realEstateClicked) {
        this.realEstateClicked = realEstateClicked;
    }
}
