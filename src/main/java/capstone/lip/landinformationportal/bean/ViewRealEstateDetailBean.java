package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class ViewRealEstateDetailBean implements Serializable {

    private RealEstate realEstateClicked;
    private User currentUser;
    private String jsonCoordinate;
    private List<House> listCurrentHouse;
    private Land currentLand;

    @Autowired
    private IRealEstateService realEstateService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IHouseService houseService;
    
    @Autowired
    private ILandService landService;
    
    @Autowired
    private ILandsDetailService landDetailService;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long userId = Long.parseLong(params.get("userId"));
        long realEstateId = Long.parseLong(params.get("realEstateId"));
        currentUser = userService.findById(userId);
        realEstateClicked = realEstateService.findById(realEstateId);
        listCurrentHouse = houseService.findByRealEstateId(realEstateId);
        currentLand = landService.findByRealEstateId(realEstateId);
        transferCoordinate();
    }
    
    public void transferCoordinate(){
        Coordinate coordinate;
        coordinate = new Coordinate().setLatitude(realEstateClicked.getRealEstateLat()).setLongitude(realEstateClicked.getRealEstateLng());
        Gson gson = new Gson();
        jsonCoordinate = gson.toJson(coordinate);
    }

    public List<House> getListCurrentHouse() {
        return listCurrentHouse;
    }

    public void setListCurrentHouse(List<House> listCurrentHouse) {
        this.listCurrentHouse = listCurrentHouse;
    }

    public Land getCurrentLand() {
        return currentLand;
    }

    public void setCurrentLand(Land currentLand) {
        this.currentLand = currentLand;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
