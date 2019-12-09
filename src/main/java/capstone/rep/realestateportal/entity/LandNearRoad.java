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
    private ArrayList<Coordinate> listCoordinate;
    private ArrayList<RealEstateObject> listRealEstateObject;

    public LandNearRoad() {
    }

    public LandNearRoad(int landNearRoadId, String name, float maxPrice, float minPrice, float averagePrice, float predictPrice, RoadSegment roadSegment, ArrayList<Coordinate> listCoordinate, ArrayList<RealEstateObject> listRealEstateObject) {
        this.landNearRoadId = landNearRoadId;
        this.name = name;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.averagePrice = averagePrice;
        this.predictPrice = predictPrice;
        this.roadSegment = roadSegment;
        this.listCoordinate = listCoordinate;
        this.listRealEstateObject = listRealEstateObject;
    }

    public int getLandNearRoadId() {
        return landNearRoadId;
    }

    public LandNearRoad setLandNearRoadId(int landNearRoadId) {
        this.landNearRoadId = landNearRoadId;
        return this;
    }

    public String getName() {
        return name;
    }

    public LandNearRoad setName(String name) {
        this.name = name;
        return this;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public LandNearRoad setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public LandNearRoad setMinPrice(float minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public LandNearRoad setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public float getPredictPrice() {
        return predictPrice;
    }

    public LandNearRoad setPredictPrice(float predictPrice) {
        this.predictPrice = predictPrice;
        return this;
    }

    public RoadSegment getRoadSegment() {
        return roadSegment;
    }

    public LandNearRoad setRoadSegment(RoadSegment roadSegment) {
        this.roadSegment = roadSegment;
        return this;
    }

    public ArrayList<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public LandNearRoad setListCoordinate(ArrayList<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }

    public ArrayList<RealEstateObject> getListRealEstateObject() {
        return listRealEstateObject;
    }

    public LandNearRoad setListRealEstateObject(ArrayList<RealEstateObject> listRealEstateObject) {
        this.listRealEstateObject = listRealEstateObject;
        return this;
    }
    
}
