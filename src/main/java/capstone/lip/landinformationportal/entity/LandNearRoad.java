/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Hao
 */
public class LandNearRoad {
    private int landNearRoadId;
    private String name;
    private double maxPrice;
    private double minPrice;
    private double averagePrice;
    private double predictPrice;
    private RoadSegment roadSegment;
    private List<Coordinate> listCoordinate;
    private List<RealEstateObject> listRealEstateObject;

    public LandNearRoad() {
    }

    public LandNearRoad(int landNearRoadId, String name, double maxPrice, double minPrice, double averagePrice, double predictPrice, RoadSegment roadSegment, ArrayList<Coordinate> listCoordinate, ArrayList<RealEstateObject> listRealEstateObject) {
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

    public double getMaxPrice() {
        return maxPrice;
    }

    public LandNearRoad setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public LandNearRoad setMinPrice(double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public LandNearRoad setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public double getPredictPrice() {
        return predictPrice;
    }

    public LandNearRoad setPredictPrice(double predictPrice) {
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

    public List<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public LandNearRoad setListCoordinate(List<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }

    public List<RealEstateObject> getListRealEstateObject() {
        return listRealEstateObject;
    }

    public LandNearRoad setListRealEstateObject(List<RealEstateObject> listRealEstateObject) {
        this.listRealEstateObject = listRealEstateObject;
        return this;
    }
    
}
