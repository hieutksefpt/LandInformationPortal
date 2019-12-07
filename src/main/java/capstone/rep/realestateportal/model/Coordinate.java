/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.model;

/**
 *
 * @author tuans
 */
public class Coordinate {
    private int id;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public Coordinate setId(int id) {
        this.id = id;
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
