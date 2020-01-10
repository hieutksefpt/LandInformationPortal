package capstone.rep.realestateportal.service;

import java.util.List;

import capstone.rep.realestateportal.dao.LayerDAO;
import capstone.rep.realestateportal.entity.Layer;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;

/**
 * @dateCreate 07/01/2020
 * @author tuans
 * @description Service for CreateLayerBean.java
 */
public class CreateLayerService {
    public int insertLayerByRoadSegment(Layer layer, RoadSegment roadSegment) {
        try {
            return new LayerDAO().insertLayerByRoadSegment_DBNew(layer, roadSegment);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public List<Layer> getListLayerByRoad(Road road){
		try {
			return new LayerDAO().getListLayerByRoad_DBNew(road.getRoadId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
}
