package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.dao.RoadDAO;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;

/**
 * @dateCreate 07/01/2020
 * @author tuans
 * @description Service for CreateRoadSegment.java
 */
public class CreateRoadSegmentService {
	public void insertNewRoadSegmentByRoad(RoadSegment roadSegment, Road road) {
		RoadDAO roadDAO = new RoadDAO();
		try {
			roadDAO.insertRoadSegmentByRoad(road, roadSegment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
