/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.service;

import capstone.rep.realestateportal.entity.Land;
import capstone.rep.realestateportal.entity.Route;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuans
 */
public class CommonService {
    public List<Route> getRouteByHint(String hint){
        //TODO: getRouteByHint(String hint)
        
        //hard code
        ArrayList<Route> listRoute = new ArrayList();
        listRoute.add(new Route().setId(1).setName("a"));
        listRoute.add(new Route().setId(2).setName("b"));
        listRoute.add(new Route().setId(3).setName("c"));
        listRoute.add(new Route().setId(4).setName("d"));
        listRoute.add(new Route().setId(5).setName("e"));
        listRoute.add(new Route().setId(6).setName("f"));
        listRoute.add(new Route().setId(7).setName("g"));
        listRoute.add(new Route().setId(8).setName("h"));
        listRoute.add(new Route().setId(9).setName("i"));
        return listRoute;
    }
    
    public List<Land> getLandNearByRouteId(String id){
        //TODO: getLandNearByRouteId
        
        // hard code
        ArrayList<Land> listLand = new ArrayList();
        
        return listLand;
    }
}
