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
public class RealEstateObject {
    private int reoId;
    private String name;
    private double price;
    private ArrayList<Feature> listFeature;
    private ArrayList<Coordinate> listCoordinate;

    public RealEstateObject() {
    }

    public RealEstateObject(int reoId, String name, double price, ArrayList<Feature> listFeature, ArrayList<Coordinate> listCoordinate) {
        this.reoId = reoId;
        this.name = name;
        this.price = price;
        this.listFeature = listFeature;
        this.listCoordinate = listCoordinate;
    }

    public int getReoId() {
        return reoId;
    }

    public RealEstateObject setReoId(int reoId) {
        this.reoId = reoId;
        return this;
    }

    public String getName() {
        return name;
    }

    public RealEstateObject setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public RealEstateObject setPrice(double price) {
        this.price = price;
        return this;
    }

    public ArrayList<Feature> getListFeature() {
        return listFeature;
    }

    public RealEstateObject setListFeature(ArrayList<Feature> listFeature) {
        this.listFeature = listFeature;
        return this;
    }

    public ArrayList<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public RealEstateObject setListCoordinate(ArrayList<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }
    
    
}
