/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.common.Checking;
import capstone.lip.landinformationportal.entity.Coordinate;
import capstone.lip.landinformationportal.entity.LandNearRoad;
import capstone.lip.landinformationportal.entity.Road;
import capstone.lip.landinformationportal.service.CommonService;
import capstone.lip.landinformationportal.service.DrawLandService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
@ManagedBean
@ViewScoped
public class DrawLandBean implements Serializable{

    private String roadName;
    private String areaNearName;

    @PostConstruct
    public void init() {
        int i = 1;
        i++;
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

    public List<Road> listRoadByHint(String hint) {
    	if (hint == null) hint = "";
        String hintLowerCase = hint.toLowerCase();
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

    
    private LandNearRoad landCalculated;

    public LandNearRoad getLandCalculated() {
        return landCalculated;
    }

    public void setLandCalculated(LandNearRoad landCalculated) {
        this.landCalculated = landCalculated;
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
        LandNearRoad land = new LandNearRoad().setListCoordinate(listCoordinateSubmit);
        
        landCalculated = drawLandService.createNewLandByCoordinate(land, roadId);
        landCalculated.setName(name);
        numberReo = String.valueOf(landCalculated.getListRealEstateObject().size());
        minPrice = String.format("%.2f",landCalculated.getMinPrice());
        maxPrice = String.format("%.2f",landCalculated.getMaxPrice());
        predictPrice = String.format("%.2f",landCalculated.getPredictPrice());
        averagePrice = String.format("%.2f",landCalculated.getAveragePrice());
    }
    
    public void saveButtonClick(){
        DrawLandService drawLandService = new DrawLandService();
        drawLandService.submitNewLandNear(landCalculated);
        
        changeRoadViewById();
    }
    
    private String clickedLandId;

    public String getClickedLandId() {
        return clickedLandId;
    }

    public void setClickedLandId(String clickedLandId) {
        this.clickedLandId = clickedLandId;
    }
    
    public void deleteButtonClick(){
        DrawLandService drawLandService = new DrawLandService();
        drawLandService.deleteLandNearRoadById(clickedLandId);
        
        changeRoadViewById();
    }
    
    public void changeRoadViewById() { 
        CommonService commonService = new CommonService();
        List<LandNearRoad> listLandNearRoad = commonService.getLandNearByRoadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearRoad);
        jsonByRoad = jsonObject.toString();
    }

    private String name;
    private String averagePrice;
    private String predictPrice;
    private String minPrice;
    private String maxPrice;
    private String numberReo;
    
    public void setNumberReo(String numberReo) {
    	this.numberReo = numberReo;
    }
    
    public String getNumberReo(){
    	return numberReo;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getPredictPrice() {
        return predictPrice;
    }

    public void setPredictPrice(String predictPrice) {
        this.predictPrice = predictPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }
    
}
