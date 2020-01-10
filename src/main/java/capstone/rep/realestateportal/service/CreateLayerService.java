package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.dao.LayerDAO;
import capstone.rep.realestateportal.entity.Layer;
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
}
