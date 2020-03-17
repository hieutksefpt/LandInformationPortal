package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
import java.io.Serializable;
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
    private Land currentLand;
    private List<House> currentListHouse;

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
    
    @Autowired
    private IUserService userService;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long userId = Long.parseLong(params.get("userId"));
        long realEstateId = Long.parseLong(params.get("realEstateId"));
        currentUser = userService.findById(userId);
        realEstateClicked = realEstateService.findById(realEstateId);
        currentLand = realEstateService.getLand(realEstateId);
        currentListHouse = realEstateService.getListHouse(realEstateId);
        transferCoordinate();
    }
    
    public void deleteRealEstate(){
        List<LandsDetail> listLandsDetail = getListLandsDetail();
        for(LandsDetail ld : listLandsDetail){
            
        }
    }
    
    public void transferCoordinate(){
        Coordinate coordinate;
        coordinate = new Coordinate().setLatitude(realEstateClicked.getRealEstateLat()).setLongitude(realEstateClicked.getRealEstateLng());
        Gson gson = new Gson();
        jsonCoordinate = gson.toJson(coordinate);
    }
    
    public List<HousesDetail> getListHousesDetail(House house){
        return house.getListHousesDetail();
    }
    
    public List<LandsDetail> getListLandsDetail(){
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
