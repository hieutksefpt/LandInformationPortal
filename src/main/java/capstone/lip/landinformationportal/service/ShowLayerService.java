package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.common.Checking;
import capstone.lip.landinformationportal.dao.LayerDAO;
import capstone.lip.landinformationportal.dao.ReoDAO;
import capstone.lip.landinformationportal.entity.LandNearRoad;
import capstone.lip.landinformationportal.entity.Layer;
import capstone.lip.landinformationportal.entity.RealEstateObject;
import capstone.lip.landinformationportal.entity.Road;

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
