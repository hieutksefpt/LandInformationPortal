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
import java.util.List;

/**
 *
 * @author Anh Hao
 */
public class CoordinateDAO {

    public static CoordinateDAO coordinateDAO = new CoordinateDAO();

    public CoordinateDAO() {
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

    public ArrayList<Coordinate> getListCoordinateWithRoadID(int roadId) throws SQLException {
        ArrayList<Coordinate> listCoordinate = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select c.CoordinateID, c.Longtitude, c.Lattitude from Road r inner join RoadCoordinate rc "
                    + "on r.RoadID = rc.RoadID inner join Coordinate c on rc.RoadID = c.CoordinateID where r.RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int coordinateId = rs.getInt("CoordinateID");
                Float longtitude = rs.getFloat("Longtitude");
                Float lattitude = rs.getFloat("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listCoordinate;
    }

    public ArrayList<Coordinate> getListCoordinateWithLandNearRoadID(int landNearRoadID) throws SQLException {
        ArrayList<Coordinate> listCoordinate = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select c.CoordinateID, c.Longtitude, c.Lattitude from LandNearRoad lnr inner join LandNearRoadCoordinate lnrc on lnr.LandNearRoadID = lnrc.LandNearRoadID "
                    + "inner join Coordinate c on lnrc.CoordinateID = c.CoordinateID where lnr.LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoadID);
            rs = pre.executeQuery();
            while (rs.next()) {
                int coordinateId = rs.getInt("CoordinateID");
                Float longtitude = rs.getFloat("Longtitude");
                Float lattitude = rs.getFloat("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listCoordinate;
    }

    public ArrayList<Coordinate> getListCoordinateWithReoID(int reoId) throws SQLException {
        ArrayList<Coordinate> listCoordinate = new ArrayList<>();
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
                int coordinateId = rs.getInt("CoordinateID");
                Float longtitude = rs.getFloat("Longtitude");
                Float lattitude = rs.getFloat("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listCoordinate;
    }

    public int deleteCoordinateByCoordinateId(ArrayList<Coordinate> listCoordinate) throws SQLException {
        int deleted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            for (int i = 0; i < listCoordinate.size(); i++) {
                String sql = "delete from Coordinate where CoordinateID = ?";
                pre = conn.prepareStatement(sql);
                pre.setInt(1, listCoordinate.get(i).getCoordinateId());
                int rowDeleted = pre.executeUpdate();
                deleted += rowDeleted;
            }

        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return deleted;
    }

    public int insertCoordinate(List<Coordinate> listCoordinate) throws SQLException {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            for (int i = 0; i < listCoordinate.size(); i++) {
                String sql = "insert into Coordinate(CoordinateID, Longtitude, Lattitude, modifiedDate) "
                        + "values(?,?,?,GETDATE());";
                pre = conn.prepareStatement(sql);
                pre.setInt(1, listCoordinate.get(i).getCoordinateId());
                pre.setDouble(2, listCoordinate.get(i).getLongitude());
                pre.setDouble(3, listCoordinate.get(i).getLatitude());
                int rowInserted = pre.executeUpdate();
                inserted += rowInserted;
            }

        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return inserted;
    }
}
