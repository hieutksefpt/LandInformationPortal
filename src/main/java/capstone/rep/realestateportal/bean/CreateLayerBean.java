package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import capstone.rep.realestateportal.service.CommonService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
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

    private Road selectedRoad;
    private List<Road> listRoadByHint;

    public List<Road> listRoadByHint(String hint) {
        if (hint == null) {
            hint = "";
        }
        String hintLowerCase = hint.toLowerCase();
        CommonService commonService = new CommonService();
        listRoadByHint = commonService.getRoadByHint_DBNew(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }

    public ArrayList<RoadSegment> getListRoadSegmentOfRoad() {
        return selectedRoad.getListRoadSegment();
    }

    public void changeRoadViewById(SelectEvent event) {
        String roadId = (String) event.getObject();
        selectedRoad = listRoadByHint.stream()
                .filter(x -> String.valueOf(x.getRoadId())
                        .equals(roadId)).findFirst().orElse(null);
        PrimeFaces.current().executeScript("focusMap(" 
                + selectedRoad.getLatitude() + ", " 
                + selectedRoad.getLongitude() + ");");
        CommonService commonService = new CommonService();
        //Using common service get road segment by road id
        //listRoadSegment = commonService.getRoadByHint_DBNew(roadId);
        //JSONObject jsonObject = commonService.createLineGeoJSON(listRoadSegment);
        //geoJSON = jsonObject.toString();
    }

    public Road getSelectedRoad() {
        return selectedRoad;
    }

    public void setSelectedRoad(Road selectedRoad) {
        this.selectedRoad = selectedRoad;
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
