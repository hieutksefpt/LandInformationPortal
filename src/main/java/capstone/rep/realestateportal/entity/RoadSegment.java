/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.entity;

/**
 *
 * @author Anh Hao
 */
public class RoadSegment {
    private int roadSegmentId;
    private String name;
    private Road road;

    public RoadSegment() {
    }

    public RoadSegment(int roadSegmentId, String name, Road road) {
        this.roadSegmentId = roadSegmentId;
        this.name = name;
        this.road = road;
    }

    public int getRoadSegmentId() {
        return roadSegmentId;
    }

    public RoadSegment setRoadSegmentId(int roadSegmentId) {
        this.roadSegmentId = roadSegmentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoadSegment setName(String name) {
        this.name = name;
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
