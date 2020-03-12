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
    private IRealEstateService realEstateService;

    @Autowired
    private IUserService userService;

    private List<RealEstate> listUserRealEstate;
    private RealEstate realEstateClicked;

    @PostConstruct
    public void init() {
        listUserRealEstate = new ArrayList<>();
        listUserRealEstate = userService.getListRealEstate(1L);
    }

//    public void goToDetails(long userId) throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(ec.getRequestContextPath() + "/common/viewrealestatedetail?userId=" + userId);
//    }
//
//    public String clickOnButtonRowRealEstate(String realEstateId) {
//        realEstateClicked = listUserRealEstate.stream().filter(x -> x.getRealEstateId().equals(Long.parseLong(realEstateId))).collect(Collectors.toList()).get(0);
//        return "common/viewrealestatedetail.xhtml?faces-redirect=true";
//    }

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
