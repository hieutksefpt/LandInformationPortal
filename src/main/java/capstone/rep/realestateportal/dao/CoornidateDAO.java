/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.Coordinate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class CoornidateDAO {

    public static CoornidateDAO coornidateDAO = new CoornidateDAO();

    public CoornidateDAO() {
    }

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

    public ArrayList<Coordinate> getListCoornidateWithRoadID(int roadId) throws SQLException {
        ArrayList<Coordinate> listCoornidate = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select c.CoordinateID, c.Longtitude, c.Lattitude from Road r inner join RoadCoornidate rc "
                    + "on r.RoadID = rc.RoadID inner join Coordinate c on rc.RoadID = c.CoordinateID where r.RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int coornidateId = rs.getInt("CoornidiateID");
                Float longtitude = rs.getFloat("Longtitude");
                Float lattitude = rs.getFloat("Lattitude");
                listCoornidate.add(new Coordinate(coornidateId, longtitude, lattitude));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listCoornidate;
    }

    public ArrayList<Coordinate> getListCoornidateWithLandNearRoadID(int landNearRoadID) throws SQLException {
        ArrayList<Coordinate> listCoornidate = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select c.CoordinateID, c.Longtitude, c.Lattitude from LandNearRoad lnr inner join LandNearRoadCoornidate lnrc on lnr.LandNearRoadID = lnrc.LandNearRoadID "
                    + "inner join Coordinate c on lnrc.CoornidateID = c.CoordinateID where lnr.LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoadID);
            rs = pre.executeQuery();
            while (rs.next()) {
                int coornidateId = rs.getInt("CoornidiateID");
                Float longtitude = rs.getFloat("Longtitude");
                Float lattitude = rs.getFloat("Lattitude");
                listCoornidate.add(new Coordinate(coornidateId, longtitude, lattitude));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listCoornidate;
    }

    public ArrayList<Coordinate> getListCoornidateWithReoID(int reoId) throws SQLException {
        ArrayList<Coordinate> listCoornidate = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select c.CoordinateID, c.Longtitude, c.Lattitude from RealEstateObject reo inner join RealEstateObjectCoordinate reoc on reo.ReoID = reoc.ReoID "
                    + "inner join Coordinate c on reoc.CoordinateID = c.CoordinateID where reo.ReoID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, reoId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int coornidateId = rs.getInt("CoornidiateID");
                Float longtitude = rs.getFloat("Longtitude");
                Float lattitude = rs.getFloat("Lattitude");
                listCoornidate.add(new Coordinate(coornidateId, longtitude, lattitude));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listCoornidate;
    }
}
