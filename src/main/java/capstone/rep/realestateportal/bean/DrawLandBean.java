/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Land;
import capstone.rep.realestateportal.entity.RealEstateObject;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.service.CommonService;
import capstone.rep.realestateportal.service.DrawLandService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
@ManagedBean
@RequestScoped
public class DrawLandBean {

    private String roadName;
    private String areaNearName;

    @PostConstruct
    public void init() {

    }

    private String roadId;

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    private String jsonCoordinateSubmit;

    public String getJsonCoordinateSubmit() {
        return jsonCoordinateSubmit;
    }

    public void setJsonCoordinateSubmit(String jsonCoordinateSubmit) {
        this.jsonCoordinateSubmit = jsonCoordinateSubmit;
    }
    
    
    
    public List<Road> listRoadByHint() {
        String hintLowerCase = (roadId + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
//        return listroadByHint.stream().filter(t -> t.getName().toLowerCase().startsWith(roadHint)).collect(Collectors.toList());
    }

    private String jsonByRoad;

    public String getJsonByRoad() {
        return jsonByRoad;
    }

    public void setJsonByRoad(String jsonByRoad) {
        this.jsonByRoad = jsonByRoad;
    }

    public void calculateButtonClick(){
        JSONArray jsonArray = new JSONArray(jsonCoordinateSubmit);
        List<Coordinate> listCoordinateSubmit = new ArrayList<>();
        for (Object element: jsonArray){
            double longitude = (double)((JSONObject)element).get("lng");
            double latitude = (double)((JSONObject)element).get("lat");
            listCoordinateSubmit.add(new Coordinate().setLatitude(latitude).setLongitude(longitude));
        }
        DrawLandService drawLandService = new DrawLandService();
        Land land = drawLandService.createNewLandByCoordinate(listCoordinateSubmit);
        
    }
    
    public void saveButtonClick(){
        int i =1 ;
        i++;
        i--;
//        JSONObject jsonObject = new JSONObject(jsonCoordinateSubmit);
        JSONArray jsonArray = new JSONArray(jsonCoordinateSubmit);
        List<Coordinate> listCoordinateSubmit = new ArrayList<>();
        for (Object element: jsonArray){
            double longitude = (double)((JSONObject)element).get("lng");
            double latitude = (double)((JSONObject)element).get("lat");
            listCoordinateSubmit.add(new Coordinate().setLatitude(latitude).setLongitude(longitude));
        }
        DrawLandService drawLandService = new DrawLandService();
        Land land = drawLandService.createNewLandByCoordinate(listCoordinateSubmit);
        
        i++;
    }
    
    private String clickedLandId;

    public String getClickedLandId() {
        return clickedLandId;
    }

    public void setClickedLandId(String clickedLandId) {
        this.clickedLandId = clickedLandId;
    }
    
    public void deleteButtonClick(){
        int i = 1;
        i++;
        i--;
        DrawLandService drawLandService = new DrawLandService();
        drawLandService.deleteLandNear(clickedLandId);
    }
    
    
    public void changeRoadViewById() {
        
        CommonService commonService = new CommonService();
        List<Land> listLandNearRoad = commonService.getLandNearByroadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearRoad);
        jsonByRoad = jsonObject.toString();
    }

}
