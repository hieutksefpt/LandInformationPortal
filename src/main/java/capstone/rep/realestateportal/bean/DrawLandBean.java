/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.Land;
import capstone.rep.realestateportal.entity.Route;
import capstone.rep.realestateportal.service.CommonService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
@ManagedBean
@RequestScoped
public class DrawLandBean {

    private String routeName;
    private String areaNearName;

    @PostConstruct
    public void init() {

    }

    private String routeId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public List<Route> listRouteByHint() {
        String hintLowerCase = (routeId + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Route> listRouteByHint = commonService.getRouteByHint(hintLowerCase);
        return listRouteByHint.stream().collect(Collectors.toList());
//        return listRouteByHint.stream().filter(t -> t.getName().toLowerCase().startsWith(routeHint)).collect(Collectors.toList());
    }

    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void changeRouteViewById() {
        
        CommonService commonService = new CommonService();
        List<Land> listLandNearRoute = commonService.getLandNearByRouteId(routeId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearRoute);
        json = jsonObject.toString();
    }

}
