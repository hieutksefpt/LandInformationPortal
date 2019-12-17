package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import java.util.ArrayList;
import java.util.List;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.RealEstateObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LandNearRoadDAO {

    public void closeConnection(Connection conn, PreparedStatement pre, ResultSet rs) throws Exception {
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<LandNearRoad> getLandNearByRoadId(String id) throws Exception {
        ArrayList<LandNearRoad> listLandNearRoad = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select * from LandNearRoad " + 
	            		"join RoadSegment on LandNearRoad.RoadSegmentID = RoadSegment.RoadSegmentID " + 
	            		"join Road on RoadSegment.RoadID = Road.RoadID and Road.RoadID = ?";
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
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithLandNearRoadID(landNearRoadId);
                ArrayList<RealEstateObject> listReo = capstone.rep.realestateportal.dao.ReoDAO.reoDAO.getListReoByLandNearRoadID(landNearRoadId);
                listLandNearRoad.add(new LandNearRoad(landNearRoadId, name, maxPrice, minPrice, averagePrice, predictPrice, roadSegment, listCoordinate, listReo));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listLandNearRoad;
    }

    public int deleteLandNearRoadById(String idLandNearRoad) throws Exception {
        int id = 0;
        try {
            id = Integer.parseInt(idLandNearRoad);
        } catch (NumberFormatException e) {

        }
        int deleteLNRCoordinate = deleteLandNearRoadCoordinateByLandNearRoadId(id);
        int deleteLNRDetail = deleteLandNearRoadDetailByLandNearRoadId(id);
        ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithLandNearRoadID(id);
        int deleteCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.deleteCoordinateByCoordinateId(listCoordinate);

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        int deleted = 0;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "delete from LandNearRoad where LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            deleted = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return deleted;
    }

    public int insertNewLandNearRoad(LandNearRoad landNearRoad) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "insert into LandNearRoad(Name, MaxPrice, MinPrice, AveragePrice, PredictPrice, RoadSegmentID, modifiedDate) "
                    + "values(?,?,?,?,?,?,GETDATE());";
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setNString(1, landNearRoad.getName());
            pre.setDouble(2, landNearRoad.getMaxPrice());
            pre.setDouble(3, landNearRoad.getMinPrice());
            pre.setDouble(4, landNearRoad.getAveragePrice());
            pre.setDouble(5, landNearRoad.getPredictPrice());
            pre.setInt(6, landNearRoad.getRoadSegment().getRoadSegmentId());
            inserted = pre.executeUpdate();
            int insertCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.insertCoordinate(landNearRoad.getListCoordinate());
            
            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    landNearRoad.setLandNearRoadId((generatedKeys.getInt(1)));
                }
                else {
                    System.out.print("Creating land failed, no ID obtained.");
                }
            }
            
            int insertedLNRDetail = insertLandNearRoadDetail(landNearRoad, landNearRoad.getListRealEstateObject());
            int insertLNRCoordinate = insertLandNearRoadCoordinate(landNearRoad, landNearRoad.getListCoordinate());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }

    public int insertLandNearRoadDetail(LandNearRoad landNearRoad, List<RealEstateObject> listReo) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            for (int i = 0; i < listReo.size(); i++) {
                String sql = "insert into LandNearRoadDetail(LandNearRoadID, ReoID, modifiedDate) "
                        + "values(?,?,GETDATE());";
                pre = conn.prepareStatement(sql);
                pre.setInt(1, landNearRoad.getLandNearRoadId());
                pre.setInt(2, listReo.get(i).getReoId());
                int rowInserted = pre.executeUpdate();
                inserted += rowInserted;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }

    public int insertLandNearRoadCoordinate(LandNearRoad landNearRoad, List<Coordinate> listCoordinate) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            for (int i = 0; i < listCoordinate.size(); i++) {
                String sql = "insert into LandNearRoadCoordinate(LandNearRoadID, CoordinateID, modifiedDate) "
                        + "values(?,?,GETDATE());";
                pre = conn.prepareStatement(sql);
                pre.setInt(1, landNearRoad.getLandNearRoadId());
                pre.setInt(2, listCoordinate.get(i).getCoordinateId());
                int rowInserted = pre.executeUpdate();
                inserted += rowInserted;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }

    public int deleteLandNearRoadCoordinateByLandNearRoadId(int landNearRoadId) throws Exception {
        int deleted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "delete from LandNearRoadCoordinate where LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoadId);
            deleted = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return deleted;
    }

    public int deleteLandNearRoadDetailByLandNearRoadId(int landNearRoadId) throws Exception {
        int deleted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "delete from LandNearRoadDetail where LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoadId);
            deleted = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return deleted;
    }

}
