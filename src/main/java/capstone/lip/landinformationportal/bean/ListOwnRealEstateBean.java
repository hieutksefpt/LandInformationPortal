package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author AnhHao
 */
@Named
@ViewScoped
public class ListOwnRealEstateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IUserService userService;

    private List<RealEstate> listUserRealEstate;
    private RealEstate realEstateClicked;
    private String jsonMultipleCoordinate;
    private User currentUser;

    @PostConstruct
    public void init() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (auth != null) {
            username = (String) auth.getPrincipal();
        }
        currentUser = userService.findByUsername(username);
        listUserRealEstate = new ArrayList<>();
        listUserRealEstate = currentUser.getListRealEstate();
        transferListCoordinate();
    }

    public void goToDetails(long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/viewrealestatedetail.xhtml?realEstateId=" + realEstateId);
    }

    public void transferListCoordinate() {
        List<Coordinate> listCoordinate = new ArrayList<>();
        listUserRealEstate.stream().forEach((re) -> {
            listCoordinate.add(new Coordinate().setLatitude(re.getRealEstateLat()).setLongitude(re.getRealEstateLng()));
        });
        Gson gson = new Gson();
        jsonMultipleCoordinate = gson.toJson(listCoordinate);
    }

    public String getJsonMultipleCoordinate() {
        return jsonMultipleCoordinate;
    }

    public void setJsonMultipleCoordinate(String jsonMultipleCoordinate) {
        this.jsonMultipleCoordinate = jsonMultipleCoordinate;
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
