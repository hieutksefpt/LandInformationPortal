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
import capstone.rep.realestateportal.service.ViewRoadService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.json.JSONObject;

/**
 *
 * @author Phong
 */
@ManagedBean
@ViewScoped
public class ViewRoadBean {

    private String roadId;
    private String geoJSON;

    private String landNearRoadID;
    private int currentPage;
    private int totalPage;
    
    private List<RealEstateObject> reoInLandNearRoad;
    private List<RealEstateObject> displayedReoInLandNearRoad;
    
    @PostConstruct
    public void init() {
    }

    public List<Road> listRoadByHint(String hint) {
    	if (hint == null) hint = "";
        String hintLowerCase = (hint + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }

    private List<LandNearRoad> listLandNearRoad;
    
    public void changeRoadViewById() {
        CommonService commonService = new CommonService();
        listLandNearRoad = commonService.getLandNearByRoadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearRoad);
        geoJSON = jsonObject.toString();
    }

    public int renderDataLandNearRoad() {
        if (landNearRoadID == null) return 0;
        //call service here
//        reoInLandNearRoad = new ViewRoadService().getListReoByLandNearRoadId(roadId, currentPage);
        LandNearRoad landNearRoadSelected =  listLandNearRoad.stream()
        		.filter(x -> String.valueOf(x.getLandNearRoadId()).compareTo(landNearRoadID) == 0)
        		.findFirst()
        		.get();
        reoInLandNearRoad = landNearRoadSelected.getListRealEstateObject();
        currentPage = 1;
        totalPage = reoInLandNearRoad.size()/5;
        displayedReoInLandNearRoad = new ArrayList();
        for (int i = (currentPage-1)*5; (i < currentPage* 5) && (i < reoInLandNearRoad.size()); i++) {
            displayedReoInLandNearRoad.add(reoInLandNearRoad.get(i));
        }
        //alternative for call service
        //dummy data
//        reoInLandNearRoad = new ArrayList();
//        reoInLandNearRoad.add(new RealEstateObject().setReoId(1).setName("Test 1").setPrice(1000000));
//        reoInLandNearRoad.add(new RealEstateObject().setReoId(2).setName("Test 2").setPrice(5000000));
//        reoInLandNearRoad.add(new RealEstateObject().setReoId(3).setName("Test 3").setPrice(4000000));
//        reoInLandNearRoad.add(new RealEstateObject().setReoId(4).setName("Test 4").setPrice(3000000));
//        reoInLandNearRoad.add(new RealEstateObject().setReoId(5).setName("Test 5").setPrice(2000000));
        
        return 1;
    }
    
    public int previousPage() {
        if (currentPage <= 1) return 0; //check
        currentPage--; //decrease page by 1
        displayedReoInLandNearRoad.clear();
        for (int i = (currentPage-1)*5;  (i < currentPage* 5) && (i < reoInLandNearRoad.size()); i++) {
            displayedReoInLandNearRoad.add(reoInLandNearRoad.get(i));
        }
        return 1;
    }
    
    public int nextPage() {
        currentPage++; //increase page by 1
        if (currentPage > totalPage) return 0;
        displayedReoInLandNearRoad.clear();
        for (int i = (currentPage-1)*5; (i < currentPage* 5) && (i < reoInLandNearRoad.size()); i++) {
            displayedReoInLandNearRoad.add(reoInLandNearRoad.get(i));
        }
        return 1;
    }

    public List<RealEstateObject> getDisplayedReoInLandNearRoad() {
        return displayedReoInLandNearRoad;
    }

    public void setDisplayedReoInLandNearRoad(List<RealEstateObject> displayedReoInLandNearRoad) {
        this.displayedReoInLandNearRoad = displayedReoInLandNearRoad;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<LandNearRoad> getListLandNearRoad() {
        return listLandNearRoad;
    }

    public void setListLandNearRoad(List<LandNearRoad> listLandNearRoad) {
        this.listLandNearRoad = listLandNearRoad;
    }
    
}
