/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.dao.ReoDAO;
import capstone.rep.realestateportal.model.Coordinate;
import capstone.rep.realestateportal.model.RealEstateObject;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ViewRoadService {
    public List<RealEstateObject> getListReoByLandNearIdList(String idLandNear, int page){
        //TODO: return list reo inside a land defined by it coordinate
        ReoDAO reoDAO = new ReoDAO();
        List<RealEstateObject> listReo = reoDAO.getListReoByLandNearRoadID(idLandNear,page);
        return listReo;
    }
}
