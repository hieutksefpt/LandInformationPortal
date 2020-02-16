/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import capstone.lip.landinformationportal.dao.RoadSegmentDAO;
import capstone.lip.landinformationportal.entity.Coordinate;
import capstone.lip.landinformationportal.entity.LandNearRoad;
import capstone.lip.landinformationportal.entity.Point;
import capstone.lip.landinformationportal.entity.Road;
import capstone.lip.landinformationportal.entity.RoadSegment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CalculatingDistance {
    public double distanceOfTwoPoints(Point pp1, Point pp2) {
        double result;
        result = Math.sqrt(Math.pow((pp2.getterX() - pp1.getterX()), 2) + Math.pow((pp2.getterY() - pp1.getterY()), 2));
        return result;
    }

    public double distanceToPoint(Point point1, Point point2, Point point3) {
        double ts = ((point2.getterY() - point1.getterY())* point3.getterX()) 
                - (point2.getterX() - point1.getterX()) * point3.getterY()
                + point2.getterX() * point1.getterY() 
                - point2.getterY() * point1.getterX();
        if(ts < 0) ts = ts * (-1);
        double ms = Math.sqrt(Math.pow(point2.getterY() - point1.getterY(), 2) + Math.pow(point2.getterX()-  point1.getterX(), 2));
        return ts/ms;
    }
    
    public RoadSegment checkNearestRoadSegment (LandNearRoad land, Road road) throws Exception{
        List<Coordinate> listCoordinateland = land.getListCoordinate();
        RoadSegment nearesrRoadSegment = new RoadSegment();
        RoadSegmentDAO rsd = new RoadSegmentDAO();
        List<RoadSegment> listRoadSegment = rsd.getRoadSegmentByRoadID(road.getRoadId());
        ArrayList distanceFromReoToRoadSegment = new ArrayList<Double>();
        // đoạn này tính toán xem nó gần phần segment nào nhất
        for (int i = 0; i < listCoordinateland.size(); i++) {
            for (int j = 0; j < listRoadSegment.size(); j++) {
                // get điểm đầu của Road Segment - cái này tùy vào cách cài đặt của mình trong DB
                Point roadSegmentA = new Point(listRoadSegment.get(j).getListCoordinate().get(0).getLatitude(), listRoadSegment.get(j).getListCoordinate().get(0).getLongitude());
                // get điểm cuối của Road Segment - cái này tùy vào cách cài đặt của mình trong DB
                Point roadSegmentB = new Point(listRoadSegment.get(j).getListCoordinate().get(1).getLatitude(), listRoadSegment.get(j).getListCoordinate().get(1).getLongitude());
                // điểm Reo thuộc LandNearRoad cần tính khoảng cách
                Point reoPoint = new Point(listCoordinateland.get(i).getLatitude(), listCoordinateland.get(i).getLongitude());
                distanceFromReoToRoadSegment.add(distanceToPoint(roadSegmentA, roadSegmentB, reoPoint));
                Collections.sort(distanceFromReoToRoadSegment);             // sort để lấy ra thằng min nằm đầu tiên
                if(distanceToPoint(roadSegmentA, roadSegmentB, reoPoint) == (Double)distanceFromReoToRoadSegment.get(0)){
                    nearesrRoadSegment = listRoadSegment.get(j);            // nếu khoảng cách là ngắn nhất thì chọn RoadSegment
                }
            }
        }
        // return Roadsegment nearest LandNearRoad created.
        return nearesrRoadSegment;
    }
}
