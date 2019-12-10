/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.dao.LandNearRoadDAO;
import capstone.rep.realestateportal.dao.ReoDAO;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.RealEstateObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuans
 */
public class DrawLandService {
    
    public int deleteLandNearRoadById(String idLand){
        //TODO: delete a land near road
    	LandNearRoadDAO landNearRoadDAO = new LandNearRoadDAO();
    	int result = 0;
    	try {
    		result = landNearRoadDAO.deleteLandNearRoadById(idLand);
    	}catch(Exception e) {
    		System.out.println("Error: "+e.getMessage());
    		System.out.print("Cause by: "+e.getCause());
    	}
    	return result;
    }
    
    public int submitNewLandNear(LandNearRoad land){
        //TODO: submit new land to db
    	LandNearRoadDAO landNearRoadDAO = new LandNearRoadDAO();
    	int result = 0;
    	try {
    		result = landNearRoadDAO.insertNewLandNearRoad(land);
    	}catch(Exception e) {
    		System.out.println("Error: "+e.getMessage());
    		System.out.print("Cause by: "+e.getCause());
    	}
    	return result;
    }

    public List<RealEstateObject> getListReoInside(List<Coordinate> listCoordinates){
        //TODO: return list reo inside a land defined by it coordinate
        ReoDAO reoDAO = new ReoDAO();
        List<RealEstateObject> listReo = reoDAO.getListReoInside(listCoordinates);
        return listReo;
    }
    
    public LandNearRoad createNewLandByCoordinate(List<Coordinate> listCoordinateSubmit) {
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
        
        LandNearRoad land = new LandNearRoad().setMaxPrice(maxPrice).setMinPrice(minPrice).setAveragePrice(averagePrice).setListRealEstateObject(listReo);
        return land;
    }
    
}
