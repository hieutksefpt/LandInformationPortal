/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class RoadSegmentDAO {

    public static RoadSegmentDAO roadSegmentDAO = new RoadSegmentDAO();

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
            throw ex;
        }
    }

    public RoadSegmentDAO() {
    }

    public RoadSegment getRoadSegmentByRoadSegmentID(int roadSengmentId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        RoadSegment roadSegment = null;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadSegmentID, Name, RoadID from RoadSegment where RoadSegmentID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadSengmentId);
            rs = pre.executeQuery();
            while (rs.next()) {
            	roadSegment = new RoadSegment();
                roadSegment.setRoadSegmentId(rs.getInt("RoadSegmentID"));
                roadSegment.setName(rs.getNString("Name"));
                roadSegment.setRoad(capstone.rep.realestateportal.dao.RoadDAO.roadDAO.getRoadByRoadID(rs.getInt("RoadID")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(conn, pre, rs);
        }
        return roadSegment;
    }

    public ArrayList<RoadSegment> getRoadSegmentByRoadID(int roadId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<RoadSegment> listRoadSegment = new ArrayList<>();
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select rs.RoadSegmentID, rs.Name, rs.RoadID from RoadSegment rs where rs.RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int roadSegmentId = rs.getInt("RoadSegmentID");
                String name = rs.getNString("Name");
                Road road = capstone.rep.realestateportal.dao.RoadDAO.roadDAO.getRoadByRoadID(rs.getInt("RoadID"));
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithRoadSegmentID(roadSegmentId);
                listRoadSegment.add(new RoadSegment(roadSegmentId, name, road, listCoordinate));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listRoadSegment;
    }
}
