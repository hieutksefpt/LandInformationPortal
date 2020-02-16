/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class Road implements Serializable{
    private int roadId;
    private String name;
    private City city;
    private ArrayList<Coordinate> listCoordinate;
    
    private ArrayList<RoadSegment> listRoadSegment;
    private double latitude;
    private double longitude;
    
    public ArrayList<RoadSegment> getListRoadSegment() {
        return listRoadSegment;
    }

    public Road setListRoadSegment(ArrayList<RoadSegment> listRoadSegment) {
        this.listRoadSegment = listRoadSegment;
        return this;
    }
    
    public Road() {
    }

    public Road(int roadId, String name, City city, ArrayList<Coordinate> listCoordinate) {
        this.roadId = roadId;
        this.name = name;
        this.city = city;
        this.listCoordinate = listCoordinate;
    }

    public int getRoadId() {
        return roadId;
    }

    public Road setRoadId(int roadId) {
        this.roadId = roadId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Road setName(String name) {
        this.name = name;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Road setCity(City city) {
        this.city = city;
        return this;
    }

    public ArrayList<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public Road setListCoordinate(ArrayList<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }

	public double getLatitude() {
		return latitude;
	}

	public Road setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public double getLongitude() {
		return longitude;
	}

	public Road setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
    
    
}
