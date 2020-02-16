/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import capstone.lip.landinformationportal.entity.City;
import capstone.lip.landinformationportal.entity.Coordinate;
import capstone.lip.landinformationportal.entity.Road;
import capstone.lip.landinformationportal.entity.RoadSegment;

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
            ex.printStackTrace();
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
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadSegmentID, Name, RoadID from RoadSegment where RoadSegmentID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadSengmentId);
            rs = pre.executeQuery();
            while (rs.next()) {
                roadSegment = new RoadSegment();
                roadSegment.setRoadSegmentId(rs.getInt("RoadSegmentID"));
                roadSegment.setName(rs.getNString("Name"));
                roadSegment.setRoad(capstone.lip.landinformationportal.dao.RoadDAO.roadDAO.getRoadByRoadID(rs.getInt("RoadID")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select rs.RoadSegmentID, rs.Name, rs.RoadID from RoadSegment rs where rs.RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int roadSegmentId = rs.getInt("RoadSegmentID");
                String name = rs.getNString("Name");
                Road road = capstone.lip.landinformationportal.dao.RoadDAO.roadDAO.getRoadByRoadID(rs.getInt("RoadID"));
                ArrayList<Coordinate> listCoordinate = capstone.lip.landinformationportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithRoadSegmentID(roadSegmentId);
                listRoadSegment.add(new RoadSegment(roadSegmentId, name, road, listCoordinate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listRoadSegment;
    }

    public int insertRoadSegmentCoordinate(int roadSengmentID, double latitude, double longitude) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "INSERT INTO RoadSegmentCoordinate(RoadSegmentID, Latitude, Longitude) "
                    + "VALUES(?,?,?)";
            System.out.println(sql);
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, roadSengmentID);
            pre.setDouble(2, latitude);
            pre.setDouble(3, longitude);
            inserted = pre.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }
    
    public ArrayList<RoadSegment> getRoadSegmentByRoadID_DBNew(int roadId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<RoadSegment> listRoadSegment = new ArrayList<>();
        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadSegmentID, RoadSegmentName from RoadSegment where RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            System.out.println(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int roadSegmentId = rs.getInt("RoadSegmentID");
                String name = rs.getNString("RoadSegmentName");
                ArrayList<Coordinate> listCoordinate = capstone.lip.landinformationportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithRoadSegmentID_DBNew(roadSegmentId);
                listRoadSegment.add(new RoadSegment().setRoadSegmentId(roadSegmentId).setName(name).setListCoordinate(listCoordinate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listRoadSegment;
    }
}


