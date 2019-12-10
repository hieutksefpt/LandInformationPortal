package capstone.rep.realestateportal.dao;

import java.util.ArrayList;
import java.util.List;

import capstone.rep.realestateportal.model.Coordinate;
import capstone.rep.realestateportal.model.RealEstateObject;

public class ReoDAO {

    public List<RealEstateObject> getListReoInside(List<Coordinate> listCoordinates) {
        List<RealEstateObject> listReo = new ArrayList<>();
        listReo.add(new RealEstateObject().setPrice(15));
        listReo.add(new RealEstateObject().setPrice(16));
        listReo.add(new RealEstateObject().setPrice(17));
        return listReo;
    }

    public List<RealEstateObject> getListReoByLandNearRoadID(String landNearRoadId, int page) {
        List<RealEstateObject> listReo = new ArrayList<>();
        // get from DB 
        return listReo;
    }
}
