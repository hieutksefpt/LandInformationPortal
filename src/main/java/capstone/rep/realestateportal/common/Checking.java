package capstone.rep.realestateportal.common;

import java.util.List;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.RealEstateObject;

public class Checking {
	public boolean isLandNearRoadContainReo(LandNearRoad land, RealEstateObject reo) {
		if (reo.getListCoordinate().size()==0 || land.getListCoordinate().size()==0) {
			return false;
		}
		
		int i;
	    int j;
	    boolean result = false;
		List<Coordinate> listCoordinate = land.getListCoordinate();
//		for (i = 0, j = points.length - 1; i < points.length; j = i++) {
//	        if ((points[i].y > test.y) != (points[j].y > test.y) &&
//	            (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
//	          result = !result;
//	         }
//	      }
		Coordinate coordinateReo = reo.getListCoordinate().get(0);
		
		for (i = 0, j = listCoordinate.size()-1; i < listCoordinate.size(); j = i++) {
			if ((listCoordinate.get(i).getLongitude() > coordinateReo.getLongitude()) != (listCoordinate.get(j).getLongitude() > coordinateReo.getLongitude()) &&
				(coordinateReo.getLatitude() < (listCoordinate.get(j).getLatitude() - listCoordinate.get(i).getLatitude())*(coordinateReo.getLongitude()- listCoordinate.get(i).getLongitude())/(listCoordinate.get(j).getLongitude() - listCoordinate.get(i).getLongitude()) +listCoordinate.get(i).getLatitude() )) {
				result = !result;
			}
		}
		return result;
	}
}
