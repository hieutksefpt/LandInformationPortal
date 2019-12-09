/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.adapter.GeoJSONApdater;
import capstone.rep.realestateportal.dao.LandNearRoadDAO;
import capstone.rep.realestateportal.dao.RoadDAO;
import capstone.rep.realestateportal.model.Coordinate;
import capstone.rep.realestateportal.model.Land;
import capstone.rep.realestateportal.model.RealEstateObject;
import capstone.rep.realestateportal.model.Road;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
public class CommonService {

    public List<Road> getRoadByHint(String hint) {
        //TODO: getroadByHint(String hint)
    	RoadDAO roadDAO = new RoadDAO();
    	ArrayList<Road> listRoad = roadDAO.getRoadByName(hint);
        return listRoad;
    }

    public List<Land> getLandNearByRoadId(String id) {
        //TODO: getLandNearByRoadId
    	ArrayList<Land> listLand = new ArrayList();
    	LandNearRoadDAO landNearDAO = new LandNearRoadDAO();
    	listLand = landNearDAO.getLandNearByRoadId(id);
        return listLand;
    }

    public JSONObject createGeoJson(List<Land> listLandNearRoad) {
        //TODO: create geo json from list land near road
        JSONObject jsonObject = GeoJSONApdater.createGeoJSON(listLandNearRoad);
        return jsonObject;
    }
}
