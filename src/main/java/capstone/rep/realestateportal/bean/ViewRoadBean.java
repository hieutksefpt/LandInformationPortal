/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RealEstateObject;
import capstone.rep.realestateportal.service.CommonService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Phong
 */
@ManagedBean
@RequestScoped
public class ViewRoadBean {

    private String roadId;
    private String geoJSON;

    private String landNearRoadID;
    private int currentPage;
    
    private List<RealEstateObject> reoInLandNearRoad;
    
    @PostConstruct
    public void init() {
        currentPage = 1;
    }

    public List<Road> listRoadByHint() {
        String hintLowerCase = (roadId + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }

    public void changeRoadViewById() {
        CommonService commonService = new CommonService();
        List<LandNearRoad> listLandNearroad = commonService.getLandNearByRoadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearroad);
        geoJSON = jsonObject.toString();
    }

    public int renderDataLandNearRoad() {
        if (landNearRoadID == null) return 0;
        //call service here
        
        //alternative for call service
        //dummy data
        reoInLandNearRoad = new ArrayList();
        reoInLandNearRoad.add(new RealEstateObject().setReoId(1).setName("Test 1").setPrice(1000000));
        reoInLandNearRoad.add(new RealEstateObject().setReoId(2).setName("Test 2").setPrice(5000000));
        reoInLandNearRoad.add(new RealEstateObject().setReoId(3).setName("Test 3").setPrice(4000000));
        reoInLandNearRoad.add(new RealEstateObject().setReoId(4).setName("Test 4").setPrice(3000000));
        reoInLandNearRoad.add(new RealEstateObject().setReoId(5).setName("Test 5").setPrice(2000000));
        
        return 1;
    }
    
    public int previousPage() {
        if (currentPage < 1) return 0; //check
        currentPage--; //decrease page by 1
        if (renderDataLandNearRoad() == 0) { //if get false
            currentPage++; //roll back
            return 0;
        }
        return 1;
    }
    
    public int nextPage() {
        currentPage++; //increase page by 1
        if (renderDataLandNearRoad() == 0) { //if get false
            currentPage--; //roll back
            return 0;
        }
        return 1;
    }
    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getGeoJSON() {
        return geoJSON;
    }

    public void setGeoJSON(String geoJSON) {
        this.geoJSON = geoJSON;
    }

    public String getLandNearRoadID() {
        return landNearRoadID;
    }

    public void setLandNearRoadID(String landNearRoadID) {
        this.landNearRoadID = landNearRoadID;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<RealEstateObject> getReoInLandNearRoad() {
        return reoInLandNearRoad;
    }

    public void setReoInLandNearRoad(List<RealEstateObject> reoInLandNearRoad) {
        this.reoInLandNearRoad = reoInLandNearRoad;
    }

    
    
}
