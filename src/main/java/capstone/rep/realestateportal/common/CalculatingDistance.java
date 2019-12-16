/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.common;

import capstone.rep.realestateportal.dao.RoadSegmentDAO;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.Point;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
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
        double ms = Math.sqrt(Math.pow(point2.getterY() - point1.getterY(), 2) + Math.pow(point2.getterX(), point1.getterX()));
        return ts/ms;
    }
    
    public RoadSegment checkNearestRoadSegment (LandNearRoad land, Road road) throws Exception{
        List<Coordinate> listCoordinateland = land.getListCoordinate();
        RoadSegmentDAO rsd = new RoadSegmentDAO();
        RoadSegment nearesrRoadSegment = new RoadSegment();
        List<RoadSegment> listRoadSegment = rsd.getRoadSegmentByRoadID(road.getRoadId());
        ArrayList distanceFromReoToRoadSegment = new ArrayList<Double>();
        // đoạn này tính toán xem nó gần phần segment nào nhất
        for (int i = 0; i < listCoordinateland.size(); i++) {
            for (int j = 0; j < listRoadSegment.size(); j++) {
                // get điểm đầu của Road Segment 
                Point roadSegmentA = new Point(listRoadSegment.get(i).getListCoordinate().get(0).getLatitude(), listRoadSegment.get(i).getListCoordinate().get(0).getLongitude());
                // get điểm cuối của Road Segment
                Point roadSegmentB = new Point(listRoadSegment.get(i).getListCoordinate().get(1).getLatitude(), listRoadSegment.get(i).getListCoordinate().get(1).getLongitude());
                // điểm Reo thuộc LandNearRoad cần tính khoảng cách
                Point reoPoint = new Point(listCoordinateland.get(i).getLatitude(), listCoordinateland.get(i).getLongitude());
                distanceFromReoToRoadSegment.add(distanceToPoint(roadSegmentA, roadSegmentB, reoPoint));
                Collections.sort(distanceFromReoToRoadSegment);             // sort để lấy ra thằng min nằm đầu tiên
                if(distanceToPoint(roadSegmentA, roadSegmentB, reoPoint) == (Double)distanceFromReoToRoadSegment.get(0)){
                    nearesrRoadSegment = listRoadSegment.get(i);            // nếu khoảng cách là ngắn nhất thì chọn RoadSegment
                }
            }
        }
        // return Roadsegment nearest LandNearRoad created.
        return nearesrRoadSegment;
    }
}
