/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.dao.LandNearRoadDAO;
import capstone.rep.realestateportal.dao.ReoDAO;
import capstone.rep.realestateportal.model.Coordinate;
import capstone.rep.realestateportal.model.Land;
import capstone.rep.realestateportal.model.RealEstateObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuans
 */
public class DrawLandService {
    
    public boolean deleteLandNearRoadById(String idLand){
        //TODO: delete a land near road
    	LandNearRoadDAO landNearRoadDAO = new LandNearRoadDAO();
    	return landNearRoadDAO.deleteLandNearRoadById(idLand);
    }
    
    public boolean submitNewLandNear(Land land){
        //TODO: submit new land to db
    	LandNearRoadDAO landNearRoadDAO = new LandNearRoadDAO();
    	return landNearRoadDAO.insertNewLandNearRoad(land);
    }

    public List<RealEstateObject> getListReoInside(List<Coordinate> listCoordinates){
        //TODO: return list reo inside a land defined by it coordinate
        ReoDAO reoDAO = new ReoDAO();
        List<RealEstateObject> listReo = reoDAO.getListReoInside(listCoordinates);
        return listReo;
    }
    
    public Land createNewLandByCoordinate(List<Coordinate> listCoordinateSubmit) {
        List<RealEstateObject> listReo = getListReoInside(listCoordinateSubmit);
        double maxPrice = Double.MIN_VALUE;
        double minPrice = Double.MAX_VALUE;
        double averagePrice = 0;
        
        double sum = 0;
        for (RealEstateObject element: listReo){
            if (element.getPrice() > maxPrice){
                maxPrice = element.getPrice();
            }
            if (element.getPrice() < minPrice){
                minPrice = element.getPrice();
            }
            sum+=element.getPrice();
        }
        averagePrice = sum/listReo.size();
        
        Land land = new Land().setMaxPrice(maxPrice).setMinPrice(minPrice).setAveragePrice(averagePrice).setListReo(listReo);
        return land;
    }
    
}
