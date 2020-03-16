package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
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
public class ListOwnRealEstateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
    private IUserService userService;

    private List<RealEstate> listUserRealEstate;
    private RealEstate realEstateClicked;
    private String jsonMultipleCoordinate;

    @PostConstruct
    public void init() {
        //Ham init nay phai truyen vao userId
        listUserRealEstate = new ArrayList<>();
        //truyen userId vao ham ben duoi, �ang hard code
        listUserRealEstate = userService.getListRealEstate(1L);
        transferListCoordinate();
    }

    public void goToDetails(long userId, long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/user/viewrealestatedetail.xhtml?userId=" + userId + "&realEstateId=" + realEstateId);
    }

    public void transferListCoordinate(){
        List<Coordinate> listCoordinate = new ArrayList<>();
        for(RealEstate re : listUserRealEstate){
            listCoordinate.add(new Coordinate().setLatitude(re.getRealEstateLat()).setLongitude(re.getRealEstateLng()));
        }
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
