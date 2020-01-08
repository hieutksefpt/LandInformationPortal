package capstone.rep.realestateportal.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.json.JSONObject;

import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.service.CommonService;

/**
 * @dateCreate 07/01/2020
 * @author tuans
 * @description bean model for createroadsegment.xhtml
 */
@ManagedBean
@ViewScoped
public class CreateRoadSegmentBean {
	private String jsonByRoad;
    private String roadId;
    
    public List<Road> listRoadByHint(String hint) {
    	if (hint == null) hint = "";
        String hintLowerCase = hint.toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }
    
    public void changeRoadViewById() { 
        CommonService commonService = new CommonService();
        List<LandNearRoad> listLandNearRoad = commonService.getLandNearByRoadId(roadId);
        JSONObject jsonObject = commonService.createGeoJson(listLandNearRoad);
        jsonByRoad = jsonObject.toString();
    }

	
    public String getJsonByRoad() {
        return jsonByRoad;
    }

    public void setJsonByRoad(String jsonByRoad) {
        this.jsonByRoad = jsonByRoad;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }
}
