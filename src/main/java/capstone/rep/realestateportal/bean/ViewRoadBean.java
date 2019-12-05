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
 * @author Phong
 */
@ManagedBean
@RequestScoped
public class ViewRoadBean {

    private String roadId;
    private String geoJSON;

    private String roadSegmentName;
    private String roadSegmentMeanPrice;
    private String roadSegmentPredictPrice;
    private String roadSegmentMinPrice;
    private String roadSegmentMaxPrice;
    
    @PostConstruct
    public void init() {
        //sample data
        roadSegmentName = "Thử nghiệm 1";
        roadSegmentMeanPrice = "150.000.000/m2";
        roadSegmentPredictPrice = "167.000.000/m2";
        roadSegmentMinPrice = "100.000.000/m2";
        roadSegmentMaxPrice = "200.000.000/m2";
    }

    public List<Road> listRoadByHint() {
        String hintLowerCase = (roadId + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }

    public void changeRoadViewById() {
        CommonService commonService = new CommonService();
        List<Land> listLandNearroad = commonService.getLandNearByroadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearroad);
        geoJSON = jsonObject.toString();
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getGeoJSON() {
        return geoJSON;
    }

    public void setGeoJSON(String geoJSON) {
        this.geoJSON = geoJSON;
    }

    public String getRoadSegmentName() {
        return roadSegmentName;
    }

    public void setRoadSegmentName(String roadSegmentName) {
        this.roadSegmentName = roadSegmentName;
    }

    public String getRoadSegmentMeanPrice() {
        return roadSegmentMeanPrice;
    }

    public void setRoadSegmentMeanPrice(String roadSegmentMeanPrice) {
        this.roadSegmentMeanPrice = roadSegmentMeanPrice;
    }

    public String getRoadSegmentPredictPrice() {
        return roadSegmentPredictPrice;
    }

    public void setRoadSegmentPredictPrice(String roadSegmentPredictPrice) {
        this.roadSegmentPredictPrice = roadSegmentPredictPrice;
    }

    public String getRoadSegmentMinPrice() {
        return roadSegmentMinPrice;
    }

    public void setRoadSegmentMinPrice(String roadSegmentMinPrice) {
        this.roadSegmentMinPrice = roadSegmentMinPrice;
    }

    public String getRoadSegmentMaxPrice() {
        return roadSegmentMaxPrice;
    }

    public void setRoadSegmentMaxPrice(String roadSegmentMaxPrice) {
        this.roadSegmentMaxPrice = roadSegmentMaxPrice;
    }
    
}
