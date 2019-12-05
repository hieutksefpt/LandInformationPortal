/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.entity;

/**
 *
 * @author tuans
 */

public class Road {
    private int id;
    private String name;
    private int cityId;
    
    public int getId(){
        return id;
    }
    public Road setId(int id){
        this.id = id;
        return this;
    }
    public String getName(){
        return name;
    }
    public Road setName(String name){
        this.name = name;
        return this;
    }

    public int getCityId() {
        return cityId;
    }

    public Road setCityId(int cityId) {
        this.cityId = cityId;
        return this;
    }
    
    
}
