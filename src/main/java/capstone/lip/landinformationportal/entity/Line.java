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
public class Line {

    private Point point1;
    private Point point2;

    public Line(Point p1, Point p2) {
        point1 = p1;
        point2 = p2;
    }

    public Point getterPoint1() {
        return this.point1;
    }

    public void setterPoint1(Point p1) {
        this.point1 = p1;
    }

    public Point getterPoint2() {
        return this.point2;
    }

    public void setterPoint2(Point p2) {
        this.point2 = p2;
    }

    
}
