package capstone.rep.realestateportal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONObject;

import capstone.rep.realestateportal.dao.LayerDAO;
import capstone.rep.realestateportal.dao.RoadDAO;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import capstone.rep.realestateportal.service.CommonService;
import capstone.rep.realestateportal.service.CreateRoadSegmentService;
import capstone.rep.realestateportal.service.DrawLandService;

/**
 * @dateCreate 07/01/2020
 * @author tuans
 * @description bean model for createroadsegment.xhtml
 */
@ManagedBean
@ViewScoped
public class CreateRoadSegmentBean implements Serializable{
	private String jsonByRoad;
    private String roadId;
    private String firstLat;
    private String firstLng;
    private String secondLat;
    private String secondLng;
    private String roadSegmentName;
    private Road selectedRoad;
    private List<Road> listRoadByHint;
    
    public List<Road> listRoadByHint(String hint) {
    	if (hint == null) hint = "";
        String hintLowerCase = hint.toLowerCase();
        CommonService commonService = new CommonService();
        listRoadByHint = commonService.getRoadByHint_DBNew(hintLowerCase);
        return listRoadByHint.stream().collect(Collectors.toList());
    }
    
    public void changeRoadViewById() { 
    	String idRoadSelected = roadId;
    	RoadDAO roadDAO = new RoadDAO();
    	try {
			selectedRoad = roadDAO.getRoadByRoadId_DBNew(Integer.valueOf(idRoadSelected));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	selectedRoad = listRoadByHint.stream().filter(x -> String.valueOf(x.getRoadId()).equals(idRoadSelected)).findFirst().orElse(null);
    	PrimeFaces.current().executeScript("focusMap("+selectedRoad.getLatitude()+", "+selectedRoad.getLongitude()+");");
    	CommonService commonService = new CommonService();
    	JSONObject jsonObject = commonService.createGeoJsonLine(selectedRoad.getListRoadSegment());
        jsonByRoad = jsonObject.toString();
    }

    public void saveButtonClick(){
    	ArrayList<Coordinate> listCoordinate = new ArrayList();

    	listCoordinate.add(new Coordinate().setLatitude(Double.parseDouble(firstLat)).setLongitude(Double.parseDouble(firstLng)));
    	listCoordinate.add(new Coordinate().setLatitude(Double.parseDouble(secondLat)).setLongitude(Double.parseDouble(secondLng)));
    	RoadSegment roadSegment = new RoadSegment();
    	roadSegment.setName(roadSegmentName).setRoad(selectedRoad).setListCoordinate(listCoordinate);
    	CreateRoadSegmentService createRoadSegmentService = new CreateRoadSegmentService();
    	createRoadSegmentService.insertNewRoadSegmentByRoad(roadSegment, selectedRoad);
        changeRoadViewById();
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

	public String getFirstLat() {
		return firstLat;
	}

	public void setFirstLat(String firstLat) {
		this.firstLat = firstLat;
	}

	public String getFirstLng() {
		return firstLng;
	}

	public void setFirstLng(String firstLng) {
		this.firstLng = firstLng;
	}

	public String getSecondLat() {
		return secondLat;
	}

	public void setSecondLat(String secondLat) {
		this.secondLat = secondLat;
	}

	public String getSecondLng() {
		return secondLng;
	}

	public void setSecondLng(String secondLng) {
		this.secondLng = secondLng;
	}

	public String getRoadSegmentName() {
		return roadSegmentName;
	}

	public void setRoadSegmentName(String roadSegmentName) {
		this.roadSegmentName = roadSegmentName;
	}
    
    
}
