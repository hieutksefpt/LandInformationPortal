/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.Land;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.service.CommonService;
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

    private String roadName;
    private String areaNearName;

    @PostConstruct
    public void init() {

    }

    private String roadId;

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public List<Road> listRoadByHint() {
        String hintLowerCase = (roadId + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
//        return listroadByHint.stream().filter(t -> t.getName().toLowerCase().startsWith(roadHint)).collect(Collectors.toList());
    }

    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void changeRoadViewById() {
        
        CommonService commonService = new CommonService();
        List<Land> listLandNearroad = commonService.getLandNearByroadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearroad);
        json = jsonObject.toString();
    }

}
