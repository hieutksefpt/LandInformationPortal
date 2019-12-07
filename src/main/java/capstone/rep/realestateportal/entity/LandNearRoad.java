/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.entity;

import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class LandNearRoad {
    private int landNearRoadId;
    private String name;
    private float maxPrice;
    private float minPrice;
    private float averagePrice;
    private float predictPrice;
    private RoadSegment roadSegment;
    private ArrayList<Coornidiate> listCoornidate;
    private ArrayList<RealEstateObject> listRealEstateObject;

    public LandNearRoad() {
    }

    public LandNearRoad(int landNearRoadId, String name, float maxPrice, float minPrice, float averagePrice, float predictPrice, RoadSegment roadSegment, ArrayList<Coornidiate> listCoornidate, ArrayList<RealEstateObject> listRealEstateObject) {
        this.landNearRoadId = landNearRoadId;
        this.name = name;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.averagePrice = averagePrice;
        this.predictPrice = predictPrice;
        this.roadSegment = roadSegment;
        this.listCoornidate = listCoornidate;
        this.listRealEstateObject = listRealEstateObject;
    }

    public int getLandNearRoadId() {
        return landNearRoadId;
    }

    public void setLandNearRoadId(int landNearRoadId) {
        this.landNearRoadId = landNearRoadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public float getPredictPrice() {
        return predictPrice;
    }

    public void setPredictPrice(float predictPrice) {
        this.predictPrice = predictPrice;
    }

    public RoadSegment getRoadSegment() {
        return roadSegment;
    }

    public void setRoadSegment(RoadSegment roadSegment) {
        this.roadSegment = roadSegment;
    }

    public ArrayList<Coornidiate> getListCoornidate() {
        return listCoornidate;
    }

    public void setListCoornidate(ArrayList<Coornidiate> listCoornidate) {
        this.listCoornidate = listCoornidate;
    }

    public ArrayList<RealEstateObject> getListRealEstateObject() {
        return listRealEstateObject;
    }

    public void setListRealEstateObject(ArrayList<RealEstateObject> listRealEstateObject) {
        this.listRealEstateObject = listRealEstateObject;
    }
    
}
