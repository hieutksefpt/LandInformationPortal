package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dao.LayerDAO;
import capstone.lip.landinformationportal.dao.RoadDAO;
import capstone.lip.landinformationportal.entity.Coordinate;
import capstone.lip.landinformationportal.entity.Layer;
import capstone.lip.landinformationportal.entity.Road;
import capstone.lip.landinformationportal.entity.RoadSegment;
import capstone.lip.landinformationportal.service.CommonService;
import capstone.lip.landinformationportal.service.CreateLayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
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
    private String layerType;
    private String selectedRoadSegmentID;
    
    private Road selectedRoad;
    private List<Road> listRoadByHint;
    private List<Layer> listLayerType;
    
    private String layerTypeSelected;
    private String jsonLayer;
    @PostConstruct
    public void init() {
    	LayerDAO layerDAO = new LayerDAO();
    	listLayerType = layerDAO.getAllLayerType();
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

    public ArrayList<RoadSegment> getListRoadSegmentOfRoad() {
        return selectedRoad.getListRoadSegment();
    }

    public void changeRoadViewById() {
        RoadDAO roadDAO = new RoadDAO();
        try {
			selectedRoad = roadDAO.getRoadByRoadId_DBNew(Integer.valueOf(roadId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrimeFaces.current().executeScript("focusMap(" 
                + selectedRoad.getLatitude() + ", " 
                + selectedRoad.getLongitude() + ");");
        //Using common service get road segment by road id
        CommonService commonService = new CommonService();
    	JSONObject jsonObject = commonService.createGeoJsonLine(selectedRoad.getListRoadSegment());
        gjsonRoad = jsonObject.toString();
        
        List<Layer> listLayer = new CreateLayerService().getListLayerByRoad(selectedRoad);
        jsonObject = commonService.createGeoJsonLayer(listLayer);
        jsonLayer = jsonObject.toString();
    }

    /*
    * Insert layer when click button Save
    */
    public void saveLayer() {
        //get marker coordinate
        JSONArray jsonArray = new JSONArray(jsonCoordinateSubmit);
        
        //parse marker coordinate to list
        ArrayList<Coordinate> listCoordinateSubmit = new ArrayList<>();
        for (Object element: jsonArray){
            double longitude = (double)((JSONObject)element).get("lng");
            double latitude = (double)((JSONObject)element).get("lat");
            listCoordinateSubmit.add(new Coordinate()
                    .setLatitude(latitude).setLongitude(longitude));
        }
        
        //init layer
        Layer layer = new Layer();
        layer.setLayerName("Test") //default
                .setLayerType(layerTypeSelected).setListCoordinate(listCoordinateSubmit);
        
        //insert layer by ID of road segment
        new CreateLayerService().insertLayerByRoadSegment(layer,
                new RoadSegment().setRoadSegmentId(Integer.parseInt(selectedRoadSegmentID))); //give ID road segment only
        
        changeRoadViewById();
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

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String layerType) {
        this.layerType = layerType;
    }

    public String getSelectedRoadSegmentID() {
        return selectedRoadSegmentID;
    }

    public void setSelectedRoadSegmentID(String selectedRoadSegmentID) {
        this.selectedRoadSegmentID = selectedRoadSegmentID;
    }

	public List<Layer> getListLayerType() {
		return listLayerType;
	}

	public void setListLayerType(List<Layer> listLayerType) {
		this.listLayerType = listLayerType;
	}

	public String getLayerTypeSelected() {
		return layerTypeSelected;
	}

	public void setLayerTypeSelected(String layerTypeSelected) {
		this.layerTypeSelected = layerTypeSelected;
	}

	public String getJsonLayer() {
		return jsonLayer;
	}

	public void setJsonLayer(String jsonLayer) {
		this.jsonLayer = jsonLayer;
	}
	
    
}
