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
    private int coornidateId;
    private float longtitude;
    private float lattitude;

    public Coordinate() {
    }

    public Coordinate(int coornidateId, float longtitude, float lattitude) {
        this.coornidateId = coornidateId;
        this.longtitude = longtitude;
        this.lattitude = lattitude;
    }

    public int getCoornidateId() {
        return coornidateId;
    }

    public void setCoornidateId(int coornidateId) {
        this.coornidateId = coornidateId;
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
