package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.dao.RoadDAO;
import capstone.lip.landinformationportal.entity.Road;
import capstone.lip.landinformationportal.entity.RoadSegment;

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
