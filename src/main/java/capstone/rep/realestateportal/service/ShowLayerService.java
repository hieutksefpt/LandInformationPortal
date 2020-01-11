package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.common.Checking;
import capstone.rep.realestateportal.dao.LayerDAO;
import capstone.rep.realestateportal.dao.ReoDAO;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.Layer;
import capstone.rep.realestateportal.entity.RealEstateObject;
import capstone.rep.realestateportal.entity.Road;

import java.util.ArrayList;
import java.util.List;

/**
 * @dateCreate 07/01/2020
 * @author tuans
 * @description Service for ShowLayerBean.java
 * @dateUpdate 10/01/2020
 * @UpdateAuthor Hieu
 */
public class ShowLayerService {

    // function to get List Layer By Road Name on map
    public List<Layer> getListLayerByRoad(Road road) {
        LayerDAO layerDAO = new LayerDAO();
        List<Layer> listLayer = new ArrayList<>();
        try {
            List<Layer> listLayerByRoad = layerDAO.getListLayerByRoad_DBNew(road.getRoadId());
            for (Layer layer : listLayerByRoad) {
                listLayer.add(layer);
            }
        } catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
        return listLayer;
    }
}
