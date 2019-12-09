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
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoornidateWithLandNearRoadID(landNearRoadId);
                ArrayList<RealEstateObject> listReo = capstone.rep.realestateportal.dao.ReoDAO.reoDAO.getListReoByLandNearRoadID(landNearRoadId);
                listLandNearRoad.add(new LandNearRoad(landNearRoadId, name, maxPrice, minPrice, averagePrice, predictPrice, roadSegment, listCoordinate, listReo));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listLandNearRoad;
    }

    public int deleteLandNearRoadById(String idLandNearRoad) throws SQLException {
        int id = 0;
        try {
            id = Integer.parseInt(idLandNearRoad);
        } catch (NumberFormatException e) {

        }
        int deleteLNRCoordinate = deleteLandNearRoadCoordinateByLandNearRoadId(id);
        int deleteLNRDetail = deleteLandNearRoadDetailByLandNearRoadId(id);
        ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoornidateWithLandNearRoadID(id);
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

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return deleted;
    }

    public int insertNewLandNearRoad(LandNearRoad landNearRoad) throws SQLException {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "insert into LandNearRoad(LandNearRoadID, Name, MaxPrice, MinPrice, AveragePrice, PredictPrice, RoadSegmentID, modifiedDate) "
                    + "values(?,?,?,?,?,?,?,GETDATE());";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoad.getLandNearRoadId());
            pre.setNString(2, landNearRoad.getName());
            pre.setFloat(3, landNearRoad.getMaxPrice());
            pre.setFloat(4, landNearRoad.getMinPrice());
            pre.setFloat(5, landNearRoad.getAveragePrice());
            pre.setFloat(6, landNearRoad.getPredictPrice());
            pre.setInt(7, landNearRoad.getRoadSegment().getRoadSegmentId());
            inserted = pre.executeUpdate();
            int insertCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.insertCoordinate(landNearRoad.getListCoornidate());
            int insertedLNRDetail = insertLandNearRoadDetail(landNearRoad, landNearRoad.getListRealEstateObject());
            int insertLNRCoordinate = insertLandNearRoadCoordinate(landNearRoad, landNearRoad.getListCoornidate());
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return inserted;
    }

    public int insertLandNearRoadDetail(LandNearRoad landNearRoad, ArrayList<RealEstateObject> listReo) throws SQLException {
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

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return inserted;
    }

    public int insertLandNearRoadCoordinate(LandNearRoad landNearRoad, ArrayList<Coordinate> listCoordinate) throws SQLException {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            for (int i = 0; i < listCoordinate.size(); i++) {
                String sql = "insert into LandNearRoadCoornidate(LandNearRoadID, CoornidateID, modifiedDate) "
                        + "values(?,?,GETDATE());";
                pre = conn.prepareStatement(sql);
                pre.setInt(1, landNearRoad.getLandNearRoadId());
                pre.setInt(2, listCoordinate.get(i).getCoornidateId());
                int rowInserted = pre.executeUpdate();
                inserted += rowInserted;
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return inserted;
    }

    public int deleteLandNearRoadCoordinateByLandNearRoadId(int landNearRoadId) throws SQLException {
        int deleted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "delete from LandNearRoadCoornidate where LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoadId);
            deleted = pre.executeUpdate();
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return deleted;
    }

    public int deleteLandNearRoadDetailByLandNearRoadId(int landNearRoadId) throws SQLException {
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

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return deleted;
    }

}
