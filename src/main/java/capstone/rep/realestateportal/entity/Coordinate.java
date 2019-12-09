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

    public void setCoordinateId(int coordinateId) {
        this.coordinateId = coordinateId;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }
    
}
