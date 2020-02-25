/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
@Named
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

    
    private String jsonByRoad;

    public String getJsonByRoad() {
        return jsonByRoad;
    }

    public void setJsonByRoad(String jsonByRoad) {
        this.jsonByRoad = jsonByRoad;
    }

    
    public void calculateButtonClick(){
    	int i = 1;
        i++;
        System.out.print(i);
    }
    
    
    private String clickedLandId;

    public String getClickedLandId() {
        return clickedLandId;
    }

    public void setClickedLandId(String clickedLandId) {
        this.clickedLandId = clickedLandId;
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
