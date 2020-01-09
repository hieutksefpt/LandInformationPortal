/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.adapter.GeoJSONApdater;
import capstone.rep.realestateportal.dao.LandNearRoadDAO;
import capstone.rep.realestateportal.dao.RoadDAO;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.RealEstateObject;
import capstone.rep.realestateportal.entity.Road;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
public class CommonService {

	public List<Road> getRoadByHint(String hint) {
		// TODO: getroadByHint(String hint)
		RoadDAO roadDAO = new RoadDAO();
		ArrayList<Road> listRoad = null;
		try {
			listRoad = roadDAO.getRoadByName(hint);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Error" + e.getMessage());
			System.out.print("Cause by: " + e.getCause());
		}
		return listRoad;
	}
	
	public List<LandNearRoad> getLandNearByRoadId(String id) {
		// TODO: getLandNearByRoadId
		ArrayList<LandNearRoad> listLand = null;
		LandNearRoadDAO landNearDAO = new LandNearRoadDAO();
		try {
			listLand = landNearDAO.getLandNearByRoadId(id);
		} catch (Exception e) {
			System.out.print("Error" + e.getMessage());
			System.out.print("Cause by: " + e.getCause());
		}
		return listLand;
	}

	public JSONObject createGeoJson(List<LandNearRoad> listLandNearRoad) {
		// TODO: create geo json from list land near road
		JSONObject jsonObject = GeoJSONApdater.createGeoJSON(listLandNearRoad);
		return jsonObject;
	}
	
	//---------------using new db
	
	public List<Road> getRoadByHint_DBNew(String hint) {
		// TODO: getroadByHint(String hint)
		RoadDAO roadDAO = new RoadDAO();
		ArrayList<Road> listRoad = null;
		try {
//			listRoad = roadDAO.getRoadByName(hint);
			listRoad = new ArrayList<>();
			listRoad.add(new Road().setName("Trần Duy Hưng 1").setRoadId(1).setLatitude(21.0107166).setLongitude(105.7983703));
			listRoad.add(new Road().setName("Trần Duy Hưng 2").setRoadId(2).setLatitude(21.0107166).setLongitude(105.7983703));
			listRoad.add(new Road().setName("Trần Duy Hưng 3").setRoadId(3).setLatitude(21.0107166).setLongitude(105.7983703));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Error" + e.getMessage());
			System.out.print("Cause by: " + e.getCause());
		}
		return listRoad;
	}
}
