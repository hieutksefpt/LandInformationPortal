package capstone.rep.realestateportal.dao;

import java.util.ArrayList;
import java.util.List;

import capstone.rep.realestateportal.model.Coordinate;
import capstone.rep.realestateportal.model.Land;
import capstone.rep.realestateportal.model.RealEstateObject;
import capstone.rep.realestateportal.model.Road;

public class RoadDAO {

	public ArrayList<Road> getRoadByName(String hint) {
		// TODO process db get list road by prefix start with hint from db
		ArrayList<Road> listRoad = new ArrayList();
        //hard code
        
        listRoad.add(new Road().setId(1).setName("Đường 1"));
        listRoad.add(new Road().setId(2).setName("Đường 2"));
        listRoad.add(new Road().setId(3).setName("Đường 3"));
        listRoad.add(new Road().setId(4).setName("Đường 4"));
        listRoad.add(new Road().setId(5).setName("Đường 5"));
        listRoad.add(new Road().setId(6).setName("Đường 6"));
        listRoad.add(new Road().setId(7).setName("Đường 7"));
        listRoad.add(new Road().setId(8).setName("Đường 8"));
        listRoad.add(new Road().setId(9).setName("Đường 9"));
		return listRoad;
	}

}
