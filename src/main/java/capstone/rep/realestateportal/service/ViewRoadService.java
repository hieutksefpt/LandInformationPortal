/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import static capstone.rep.realestateportal.dao.ReoDAO.reoDAO;
import capstone.rep.realestateportal.entity.RealEstateObject;
import java.util.ArrayList;

/**
 *
 * @author Phong
 */
public class ViewRoadService {

    public ArrayList<RealEstateObject> getListReoByLandNearRoadId(String landNearRoadId, int desiredPage) {
        try {
            int id = Integer.parseInt(landNearRoadId);
            return reoDAO.getListReoByLandNearRoadID(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error" + e.getMessage());
            System.out.print("Cause by: " + e.getCause());
            return null;
        }
    }
}
