/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.adapter;

import capstone.rep.realestateportal.common.ColorDrawer;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import java.util.List;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
/**
 *
 * @author Phong
 */
public class GeoJSONApdater {
    
    public static JSONObject parseStringToJSON(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject createGeoJSON(List<LandNearRoad> listLand) {
        JSONObject json = new JSONObject();
        json.put("type", "FeatureCollection");
        JSONArray jsonArray = new JSONArray();

        for (LandNearRoad land : listLand) {
            JSONObject elementInArray = new JSONObject();
            List listCoordinate = land.getListCoordinate();
            
            String color = ColorDrawer.IdentifyColor(land.getAveragePrice());
            
            elementInArray = createJSONObjectArea(land, color);
            jsonArray.put(elementInArray);
        }

        json.put("features", (Object) jsonArray);
        return json;
    }

    private static JSONObject createJSONGeometry(LandNearRoad land) {
        List<Coordinate> listCoordinate = land.getListCoordinate();
        
        JSONObject json = new JSONObject();
        json.put("type", "Polygon");
        JSONArray jsonAxis = new JSONArray();
        JSONArray jsonArrayFirst = null;
        for (Coordinate coordinate: listCoordinate){
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(coordinate.getLongitude());
            jsonArray.put(coordinate.getLatitude());
            jsonAxis.put(jsonArray);
            
            if (jsonArrayFirst == null){
                jsonArrayFirst = new JSONArray();
                jsonArrayFirst.put(coordinate.getLongitude());
                jsonArrayFirst.put(coordinate.getLatitude());
            }
        }
        jsonAxis.put(jsonArrayFirst);

        //----------
        JSONArray jsonTemp = new JSONArray();
        jsonTemp.put((Object) jsonAxis);

        json.put("coordinates", jsonTemp);
        return json;
    }

    private static JSONObject createJSONProperty(LandNearRoad land, String color){
        JSONObject json = new JSONObject();
        json.put("color", color);
        json.put("name", land.getName());
        json.put("id", land.getLandNearRoadId());
        json.put("averagePrice", land.getAveragePrice());
        json.put("maxPrice", land.getMaxPrice());
        json.put("minPrice", land.getMinPrice());
        json.put("predictPrice", land.getPredictPrice());
        return json;
    }
    
    private static JSONObject createJSONObjectArea(LandNearRoad land, String areaColor) {
        JSONObject json = new JSONObject();
        json.put("type", "Feature");
        
        JSONObject property = new JSONObject();
        property = createJSONProperty(land, areaColor);
        json.put("properties", (Object) property);
        
        JSONObject geometry = new JSONObject();
        geometry = createJSONGeometry(land);
        json.put("geometry", (Object) geometry);

        return json;
    }
}
