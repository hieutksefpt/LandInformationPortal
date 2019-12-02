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

public class Route {
    private int id;
    private String name;
    public int getId(){
        return id;
    }
    public Route setId(int id){
        this.id = id;
        return this;
    }
    public String getName(){
        return name;
    }
    public Route setName(String name){
        this.name = name;
        return this;
    }
}
