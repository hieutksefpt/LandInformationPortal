/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Land;
import capstone.rep.realestateportal.entity.RealEstateObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuans
 */
public class DrawLandService {
    
    public void deleteLandNear(String idLand){
        //TODO: delete a land near road
        return;
    }
    
    public void submitNewLandNear(Land land){
        //TODO: submit new land to db
        return;
    }

    public List<RealEstateObject> getListReoInside(List<Coordinate> listCoordinates){
        //TODO: return list reo inside a land defined by it coordinate
        
        //hard code:
        List<RealEstateObject> listReo = new ArrayList<>();
        listReo.add(new RealEstateObject().setPrice(15));
        listReo.add(new RealEstateObject().setPrice(16));
        listReo.add(new RealEstateObject().setPrice(17));
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
