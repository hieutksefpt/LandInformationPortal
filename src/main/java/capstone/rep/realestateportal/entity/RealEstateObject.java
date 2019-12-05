/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class RealEstateObject {
    private int reoId;
    private String name;
    private float price;
    private ArrayList<Feature> listFeature;
    private ArrayList<Coornidiate> listCoornidate;

    public RealEstateObject() {
    }

    public RealEstateObject(int reoId, String name, float price, ArrayList<Feature> listFeature, ArrayList<Coornidiate> listCoornidate) {
        this.reoId = reoId;
        this.name = name;
        this.price = price;
        this.listFeature = listFeature;
        this.listCoornidate = listCoornidate;
    }

    public int getReoId() {
        return reoId;
    }

    public void setReoId(int reoId) {
        this.reoId = reoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<Feature> getListFeature() {
        return listFeature;
    }

    public void setListFeature(ArrayList<Feature> listFeature) {
        this.listFeature = listFeature;
    }

    public ArrayList<Coornidiate> getListCoornidate() {
        return listCoornidate;
    }

    public void setListCoornidate(ArrayList<Coornidiate> listCoornidate) {
        this.listCoornidate = listCoornidate;
    }
    
    
}
