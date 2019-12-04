/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.entity;

import java.util.List;

/**
 *
 * @author tuans
 */
public class RealEstateObject {
    private int id;
    private String name;
    private double price;
    private List<Coordinate> listCoordinate;

    public int getId() {
        return id;
    }

    public RealEstateObject setId(int id) {
        this.id = id;
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

    public List<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public RealEstateObject setListCoordinate(List<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }
    
    
}
