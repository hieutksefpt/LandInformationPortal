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
public class RoadSegment {
    private int roadSegmentId;
    private String roadSegmentName;
    private Road road;
    private ArrayList<Coordinate> listCoordinate;
    
    public RoadSegment() {
    }

    public RoadSegment(int roadSegmentId, String name, Road road, ArrayList<Coordinate> listCoordinate) {
        this.roadSegmentId = roadSegmentId;
        this.roadSegmentName = name;
        this.road = road;
        this.listCoordinate = listCoordinate;
    }

    public ArrayList<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public RoadSegment setListCoordinate(ArrayList<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }

    public int getRoadSegmentId() {
        return roadSegmentId;
    }

    public RoadSegment setRoadSegmentId(int roadSegmentId) {
        this.roadSegmentId = roadSegmentId;
        return this;
    }

    public String getName() {
        return roadSegmentName;
    }

    public RoadSegment setName(String name) {
        this.roadSegmentName = name;
        return this;
    }

    public Road getRoad() {
        return road;
    }

    public RoadSegment setRoad(Road road) {
        this.road = road;
        return this;
    }
    
}
