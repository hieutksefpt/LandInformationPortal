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

/**
 * @dateCreate 09/01/2020
 * @author Hieu
 * @description Bean model for showlayer.xhtml
 */
@ManagedBean
@ViewScoped
public class ShowLayerBean {

    private String roadId;
    private String gjsonRoad;
    private String jsonCoordinateSubmit;
    private String jsonByRoad;
    private String firstLat;
    private String firstLng;
    private String secondLat;
    private String secondLng;
    private String roadSegmentName;
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

    public void changeRoadViewById(SelectEvent event) {
        int i = 1;
        i++;
        i--;
        String idRoadSelected = (String) event.getObject();
        Road roadSelected = listRoadByHint.stream().filter(x -> String.valueOf(x.getRoadId()).equals(idRoadSelected)).findFirst().orElse(null);
        PrimeFaces.current().executeScript("focusMap(" + roadSelected.getLatitude() + ", " + roadSelected.getLongitude() + ");");
        i--;
//        CommonService commonService = new CommonService();
//        List<LandNearRoad> listLandNearRoad = commonService.getLandNearByRoadId(roadId);
//        JSONObject jsonObject = commonService.createGeoJson(listLandNearRoad);
//        jsonByRoad = jsonObject.toString();
    }

    public ArrayList<RoadSegment> getListRoadSegmentOfRoad() {
        ArrayList sample = new ArrayList();
        sample.add(new RoadSegment(0, "Test 1", null, null));
        return sample;
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
