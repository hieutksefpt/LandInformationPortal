/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.adapter.GeoJSONApdater;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Land;
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
    public List<Road> getRoadByHint(String hint){
        //TODO: getroadByHint(String hint)
        
        //hard code
        ArrayList<Road> listroad = new ArrayList();
        listroad.add(new Road().setId(1).setName("a"));
        listroad.add(new Road().setId(2).setName("b"));
        listroad.add(new Road().setId(3).setName("c"));
        listroad.add(new Road().setId(4).setName("d"));
        listroad.add(new Road().setId(5).setName("e"));
        listroad.add(new Road().setId(6).setName("f"));
        listroad.add(new Road().setId(7).setName("g"));
        listroad.add(new Road().setId(8).setName("h"));
        listroad.add(new Road().setId(9).setName("i"));
        return listroad;
    }
    
    public List<Land> getLandNearByroadId(String id){
        //TODO: getLandNearByroadId
        
        // hard code
        ArrayList<Land> listLand = new ArrayList();
        
        Land land1 = new Land();
        land1.setId(1).setMaxPrice(1000).setMinPrice(10).setAveragePrice(50).setName("Land 1").setRoadSegmentId(0);
        RealEstateObject reo1 = new RealEstateObject();
        List<Coordinate> listCoordinates = new ArrayList<>();
        
        Coordinate coo1 = new Coordinate().setId(0).setLongitude(105.5241537094116).setLatitude(21.011269908487428);
        Coordinate coo2 = new Coordinate().setId(0).setLongitude(105.52434146404266).setLatitude(21.010869288954442);
        Coordinate coo3 = new Coordinate().setId(0).setLongitude(105.52473843097685).setLatitude(21.0106389322358);
        Coordinate coo4 = new Coordinate().setId(0).setLongitude(105.52521049976349).setLatitude(21.011004498167104);
        Coordinate coo5 = new Coordinate().setId(0).setLongitude(105.5249959230423).setLatitude(21.011385086404026);
        Coordinate coo6 = new Coordinate().setId(0).setLongitude(105.5244916677475).setLatitude(21.011535318335525);
        listCoordinates.add(coo1);listCoordinates.add(coo2);listCoordinates.add(coo3);listCoordinates.add(coo4);
        listCoordinates.add(coo5);listCoordinates.add(coo6);

        land1.setListCoordinate(listCoordinates);
        //------------------
        Land land2 = new Land();
        land2.setId(2).setMaxPrice(2000).setMinPrice(20).setAveragePrice(100).setName("Land 2").setRoadSegmentId(0);
        listCoordinates = new ArrayList<>();
        
        coo1 = new Coordinate().setId(0).setLongitude(105.52500128746031).setLatitude(21.011745642785428);
        coo2 = new Coordinate().setId(0).setLongitude(105.52550554275513).setLatitude(21.011224838843674);
        coo3 = new Coordinate().setId(0).setLongitude(105.52606344223022).setLatitude(21.011655503771777);
        coo4 = new Coordinate().setId(0).setLongitude(105.52561819553375).setLatitude(21.012066136615044);
        listCoordinates.add(coo1);listCoordinates.add(coo2);listCoordinates.add(coo3);listCoordinates.add(coo4);
        

        land2.setListCoordinate(listCoordinates);
        //------------------
        Land land3 = new Land();
        land3.setId(3).setMaxPrice(3000).setMinPrice(30).setAveragePrice(300).setName("Land 3").setRoadSegmentId(0);
        listCoordinates = new ArrayList<>();
        
        coo1 = new Coordinate().setId(0).setLongitude(105.52478671073914).setLatitude(21.010563815837596);
        coo2 = new Coordinate().setId(0).setLongitude(105.52543044090271).setLatitude(21.010032992212228);
        coo3 = new Coordinate().setId(0).setLongitude(105.52532851696014).setLatitude(21.01095942844316);
        listCoordinates.add(coo1);listCoordinates.add(coo2);listCoordinates.add(coo3);

        land3.setListCoordinate(listCoordinates);
        //------------------
        Land land4 = new Land();
        land4.setId(4).setMaxPrice(4000).setMinPrice(40).setAveragePrice(400).setName("Land 4").setRoadSegmentId(0);
        listCoordinates = new ArrayList<>();
        
        coo1 = new Coordinate().setId(0).setLongitude(105.52552163600922).setLatitude(21.01049871492854);
        coo2 = new Coordinate().setId(0).setLongitude(105.52670180797577).setLatitude(21.01049871492854);
        coo3 = new Coordinate().setId(0).setLongitude(105.52670180797577).setLatitude(21.011069598855414);
        coo4 = new Coordinate().setId(0).setLongitude(105.52552163600922).setLatitude(21.011069598855414);
        listCoordinates.add(coo1);listCoordinates.add(coo2);listCoordinates.add(coo3);listCoordinates.add(coo4);
        

        land4.setListCoordinate(listCoordinates);
        //-----------
        listLand.add(land1);
        listLand.add(land2);
        listLand.add(land3);
        listLand.add(land4);
        return listLand;
    }

    public JSONObject createGeoJson(List<Land> listLandNearroad) {
        //TODO: create geo json from list land near road
        JSONObject jsonObject = GeoJSONApdater.createGeoJSON(listLandNearroad);
        return jsonObject;
    }
}
