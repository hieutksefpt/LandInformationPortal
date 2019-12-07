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
public class Feature {
    private int featureId;
    private String name;
    private float coefficient;

    public Feature() {
    }

    public Feature(int featureId, String name, float coefficient) {
        this.featureId = featureId;
        this.name = name;
        this.coefficient = coefficient;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }
    
}
