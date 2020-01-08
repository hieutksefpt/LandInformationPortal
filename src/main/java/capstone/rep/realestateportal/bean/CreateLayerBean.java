package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import capstone.rep.realestateportal.service.CommonService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.json.JSONObject;

/**
 * @dateCreate 07/01/2020
 * @author tuans
 * @description bean model for createlayer.xhtml
 */
@ManagedBean
@ViewScoped
public class CreateLayerBean {

    private String roadId;
    private String gjsonRoad;
    private String jsonCoordinateSubmit;
    
    //private List<RoadSegment> listRoadSegment;
    
    public List<Road> listRoadByHint(String hint) {
        if (hint == null) {
            hint = "";
        }
        String hintLowerCase = (hint + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Road> listRoadByHint = commonService.getRoadByHint(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }
    
    public ArrayList<RoadSegment> getListRoadSegmentOfRoad() {
        ArrayList sample = new ArrayList();
        sample.add(new RoadSegment(0, "Test 1", null, null));
        return sample;
    }
    
    public void changeRoadViewById() {
        CommonService commonService = new CommonService();
        //Using common service get road segment by road id
        //listRoadSegment = commonService.getListRoadSegmentByRoadId(roadId);
        //JSONObject jsonObject = commonService.createLineGeoJSON(listRoadSegment);
        //geoJSON = jsonObject.toString();
    }

    public String getJsonCoordinateSubmit() {
        return jsonCoordinateSubmit;
    }

    public void setJsonCoordinateSubmit(String jsonCoordinateSubmit) {
        this.jsonCoordinateSubmit = jsonCoordinateSubmit;
    }

    public String getGjsonRoad() {
        return gjsonRoad;
    }

    public void setGjsonRoad(String gjsonRoad) {
        this.gjsonRoad = gjsonRoad;
    }


    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

}
