/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

/**
 *
 * @author Admin
 */
public class Point {

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getterX() {
        return this.x;
    }

    public void setterX(double x) {
        this.x = x;
    }

    public double getterY() {
        return this.y;
    }

    public void setter(double y) {
        this.y = y;
    }
    private double x;
    private double y;
}
