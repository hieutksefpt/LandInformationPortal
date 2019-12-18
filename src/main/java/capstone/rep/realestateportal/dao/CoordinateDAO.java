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
import java.sql.Statement;
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

    public ArrayList<Coordinate> getListCoordinateWithRoadID(int roadId) throws Exception {
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
                double longtitude = rs.getDouble("Longtitude");
                double lattitude = rs.getDouble("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listCoordinate;
    }

    public ArrayList<Coordinate> getListCoordinateWithRoadSegmentID(int roadSegmentId) throws Exception {
        ArrayList<Coordinate> listCoordinate = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select c.CoordinateID, c.Longtitude, c.Lattitude from RoadSegmentCoordinate rsc"
                    + " inner join Coordinate c on rsc.CoordinateID = c.CoordinateID where rsc.RoadSegmentID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadSegmentId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int coordinateId = rs.getInt("CoordinateID");
                double longtitude = rs.getDouble("Longtitude");
                double lattitude = rs.getDouble("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listCoordinate;
    }

    public ArrayList<Coordinate> getListCoordinateWithLandNearRoadID(int landNearRoadID) throws Exception {
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
                double longtitude = rs.getDouble("Longtitude");
                double lattitude = rs.getDouble("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listCoordinate;
    }

    public ArrayList<Coordinate> getListCoordinateWithReoID(int reoId) throws Exception {
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
                double longtitude = rs.getDouble("Longtitude");
                double lattitude = rs.getDouble("Lattitude");
                listCoordinate.add(new Coordinate(coordinateId, longtitude, lattitude));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listCoordinate;
    }

    public int deleteCoordinateByCoordinateId(ArrayList<Coordinate> listCoordinate) throws Exception {
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
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return deleted;
    }

    public int insertCoordinate(List<Coordinate> listCoordinate) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            for (int i = 0; i < listCoordinate.size(); i++) {
                String sql = "insert into Coordinate(Longtitude, Lattitude, modifiedDate) "
                        + "values(?,?,GETDATE());";
                pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pre.setDouble(1, listCoordinate.get(i).getLongitude());
                pre.setDouble(2, listCoordinate.get(i).getLatitude());
                int rowInserted = pre.executeUpdate();
                try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                    	listCoordinate.get(i).setCoordinateId(generatedKeys.getInt(1));
                    }
                    else {
                        System.out.print("Creating land failed, no ID obtained.");
                    }
                }
                inserted += rowInserted;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }
}
