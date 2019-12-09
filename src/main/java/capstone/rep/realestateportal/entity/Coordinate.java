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
    private float longtitude;
    private float lattitude;

    public Coordinate() {
    }

    public Coordinate(int coordinateId, float longtitude, float lattitude) {
        this.coordinateId = coordinateId;
        this.longtitude = longtitude;
        this.lattitude = lattitude;
    }

    public int getCoordinateId() {
        return coordinateId;
    }

    public Coordinate setCoordinateId(int coordinateId) {
        this.coordinateId = coordinateId;
        return this;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public Coordinate setLongtitude(float longtitude) {
        this.longtitude = longtitude;
        return this;
    }

    public float getLattitude() {
        return lattitude;
    }

    public Coordinate setLattitude(float lattitude) {
        this.lattitude = lattitude;
        return this;
    }
    
}
