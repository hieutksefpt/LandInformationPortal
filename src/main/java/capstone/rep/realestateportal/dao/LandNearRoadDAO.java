package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import java.util.ArrayList;
import java.util.List;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.model.Land;
import capstone.rep.realestateportal.entity.RealEstateObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LandNearRoadDAO {
    public void CloseConnect(Connection conn, PreparedStatement pre, ResultSet rs) throws SQLException {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pre != null && !pre.isClosed()) {
                pre.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
	public ArrayList<LandNearRoad> getLandNearByRoadId(String id) throws SQLException {
            ArrayList<LandNearRoad> listLandNearRoad = new ArrayList<>();
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement pre = null;

            try {
                conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
                String sql = "select LandNearRoadID, Name, MaxPrice, MinPrice, AveragePrice, PredictPrice, RoadSegmentID from LandNearRoad where LandNearRoadID = ?";
                pre = conn.prepareStatement(sql);
                pre.setString(1, id);
                rs = pre.executeQuery();
                while (rs.next()) {
                    int landNearRoadId = rs.getInt("LandNearRoadID");
                    String name = rs.getString("Name");
                    Float maxPrice = rs.getFloat("MaxPrice");
                    Float minPrice = rs.getFloat("MinPrice");
                    Float averagePrice = rs.getFloat("AveragePrice");
                    Float predictPrice = rs.getFloat("PredictPrice");
                    RoadSegment roadSegment = capstone.rep.realestateportal.dao.RoadSegmentDAO.roadSegmentDAO.getRoadSegmentByRoadSegmentID(rs.getInt("RoadSegmentID"));
                    ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoornidateDAO.coornidateDAO.getListCoornidateWithLandNearRoadID(landNearRoadId);
                    ArrayList<RealEstateObject> listReo = capstone.rep.realestateportal.dao.ReoDAO.reoDAO.getListReoByLandNearRoadID(landNearRoadId);
                    listLandNearRoad.add(new LandNearRoad(landNearRoadId, name, maxPrice, minPrice, averagePrice, predictPrice, roadSegment, listCoordinate, listReo));
                }
            } catch (Exception ex) {

            } finally {
                CloseConnect(conn, pre, rs);
            }
            return listLandNearRoad;
	}
	
	public boolean deleteLandNearRoadById(String idLandNearRoad) {
		return true;
	}

	public boolean insertNewLandNearRoad(Land land) {
		return true;
	}
}
