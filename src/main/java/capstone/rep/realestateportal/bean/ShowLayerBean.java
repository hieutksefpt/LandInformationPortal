package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.dao.RoadDAO;
import capstone.rep.realestateportal.entity.Layer;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import capstone.rep.realestateportal.service.CommonService;
import capstone.rep.realestateportal.service.CreateLayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONObject;

/**
 * @dateCreate 09/01/2020
 * @author Hieu
 * @description Bean model for showlayer.xhtml
 */
@Named
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
    private String jsonLayer;
    
    @PostConstruct
    public void init() {
    	CommonService commonService = new CommonService();
        listRoadByHint = commonService.getRoadByHint_DBNew("");
    }
    public List<Road> listRoadByHint(String hint) {
        if (hint == null) {
            hint = "";
        }
        String hintLowerCase = hint.toLowerCase();
        CommonService commonService = new CommonService();
        listRoadByHint = commonService.getRoadByHint_DBNew(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }

    public void changeRoadViewById() {
    	RoadDAO roadDAO = new RoadDAO();
        try {
        	selectedRoad = roadDAO.getRoadByRoadId_DBNew(Integer.valueOf(roadId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrimeFaces.current().executeScript("focusMap(" + selectedRoad.getLatitude() + ", " + selectedRoad.getLongitude() + ");");
        //Using common service get road segment by road id
        CommonService commonService = new CommonService();
    	JSONObject jsonObject = commonService.createGeoJsonLine(selectedRoad.getListRoadSegment());
        gjsonRoad = jsonObject.toString();
        
        List<Layer> listLayer = new CreateLayerService().getListLayerByRoad(selectedRoad);
        jsonObject = commonService.createGeoJsonLayer(listLayer);
        jsonLayer = jsonObject.toString();
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
    public String getJsonLayer() {
		return jsonLayer;
	}

	public void setJsonLayer(String jsonLayer) {
		this.jsonLayer = jsonLayer;
	}
	public List<Road> getListRoadByHint() {
		return listRoadByHint;
	}
	public void setListRoadByHint(List<Road> listRoadByHint) {
		this.listRoadByHint = listRoadByHint;
	}
	
	
}
