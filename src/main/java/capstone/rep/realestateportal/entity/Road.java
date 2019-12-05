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
public class Road {
    private int roadId;
    private String name;
    private City city;
    private ArrayList<Coornidiate> listCoornidate;

    public Road() {
    }

    public Road(int roadId, String name, City city, ArrayList<Coornidiate> listCoornidate) {
        this.roadId = roadId;
        this.name = name;
        this.city = city;
        this.listCoornidate = listCoornidate;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<Coornidiate> getListCoornidate() {
        return listCoornidate;
    }

    public void setListCoornidate(ArrayList<Coornidiate> listCoornidate) {
        this.listCoornidate = listCoornidate;
    }
    
}
