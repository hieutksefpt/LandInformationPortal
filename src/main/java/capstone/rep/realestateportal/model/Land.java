/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.model;

import java.util.List;

/**
 *
 * @author tuans
 */

// LAND NEAR road modal
public class Land {
    private int id;
    private String name;
    private double maxPrice;
    private double minPrice;
    private double averagePrice;
    private double predictPrice;
    private int roadSegmentId;
    private List<RealEstateObject> listReo;
    private List<Coordinate> listCoordinate;
    
    public int getId(){
        return id;
    }
    public Land setId(int id){
        this.id = id;
        return this;
    }
    public String getName(){
        return name;
    }
    public Land setName(String name){
        this.name = name;
        return this;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public Land setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public Land setMinPrice(double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public Land setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public double getPredictPrice() {
        return predictPrice;
    }

    public Land setPredictPrice(double predictPrice) {
        this.predictPrice = predictPrice;
        return this;
    }

    public int getRoadSegmentId() {
        return roadSegmentId;
    }

    public Land setRoadSegmentId(int roadSegmentId) {
        this.roadSegmentId = roadSegmentId;
        return this;
    }

    public List<RealEstateObject> getListReo() {
        return listReo;
    }

    public Land setListReo(List<RealEstateObject> listReo) {
        this.listReo = listReo;
        return this;
    }

    public List<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public Land setListCoordinate(List<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }
    
    
    
}
