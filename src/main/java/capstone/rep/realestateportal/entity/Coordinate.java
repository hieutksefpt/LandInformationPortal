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
public class Coordinate {
    private int coordinateId;
    private double longitude;
    private double latitude;

    public Coordinate() {
    }

    public Coordinate(int coordinateId, double longitude, double latitude) {
        this.coordinateId = coordinateId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getCoordinateId() {
        return coordinateId;
    }

    public Coordinate setCoordinateId(int coordinateId) {
        this.coordinateId = coordinateId;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Coordinate setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Coordinate setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }
    
}
