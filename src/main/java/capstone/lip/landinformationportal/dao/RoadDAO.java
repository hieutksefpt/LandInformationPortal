package capstone.lip.landinformationportal.dao;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;

import capstone.lip.landinformationportal.entity.City;
import capstone.lip.landinformationportal.entity.Coordinate;
import capstone.lip.landinformationportal.entity.Road;
import capstone.lip.landinformationportal.entity.RoadSegment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoadDAO {

    public RoadDAO() {
    }

    public static RoadDAO roadDAO = new RoadDAO();

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

    public Road getRoadByRoadID(int roadId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        Road road = null;
        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadID, Name, CityID from Road where RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            rs = pre.executeQuery();
            while (rs.next()) {
                road = new Road();
                road.setRoadId(rs.getInt("RoadID"));
                road.setName(rs.getNString("Name"));
                road.setCity(capstone.lip.landinformationportal.dao.CityDAO.cityDAO.getCityByCityID(rs.getInt("CityID")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return road;
    }

    public ArrayList<Road> getRoadByName(String hint) throws Exception {
        // TODO process db get list road by prefix start with hint from db
        ArrayList<Road> listRoad = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select r.RoadID, r.Name, c.CityID from Road r inner join City c on r.CityID = c.CityID where r.Name like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + hint + "%");
            System.out.println(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int roadId = rs.getInt("RoadID");
                String name = rs.getString("Name");
                City city = capstone.lip.landinformationportal.dao.CityDAO.cityDAO.getCityByCityID(rs.getInt("CityID"));
                ArrayList<Coordinate> listCoordinate = capstone.lip.landinformationportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithRoadID(roadId);
                listRoad.add(new Road(roadId, name, city, listCoordinate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listRoad;
    }

    public int insertRoadSegmentByRoad(Road road, RoadSegment roadSegment) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "INSERT INTO RoadSegment(RoadSegmentName, RoadID) "
                    + "VALUES (?,?)";
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setNString(1, roadSegment.getName());
            pre.setDouble(2, road.getRoadId());
            System.out.println(sql);
            inserted = pre.executeUpdate();

            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    for(Coordinate coordinate : roadSegment.getListCoordinate()){
                        int insertRoadSegmentCoordinate = capstone.lip.landinformationportal.dao.RoadSegmentDAO
                                .roadSegmentDAO.insertRoadSegmentCoordinate(
                                        generatedKeys.getInt(1), 
                                        coordinate.getLatitude(), 
                                        coordinate.getLongitude());
                    }
                } else {
                    System.out.print("Creating roadSegment failed, no ID obtained.");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }
    
    public ArrayList<Road> getRoadByName_DBNew(String hint) throws Exception {
        ArrayList<Road> listRoad = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadID, RoadName, Latitude, Longitude from Road where RoadName like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + hint + "%");
            System.out.println(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int roadId = rs.getInt("RoadID");
                String name = rs.getString("RoadName");
                Double latitude = rs.getDouble("Latitude");
                Double longitude = rs.getDouble("Longitude");
                ArrayList<RoadSegment> listRoadSegment = new ArrayList<>();
                listRoadSegment = capstone.lip.landinformationportal.dao.RoadSegmentDAO.roadSegmentDAO.getRoadSegmentByRoadID_DBNew(roadId);
                listRoad.add(new Road().setRoadId(roadId).setName(name).setListRoadSegment(listRoadSegment).setLatitude(latitude).setLongitude(longitude));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listRoad;
    }
    
    public Road getRoadByRoadId_DBNew(int id) throws Exception {
        // TODO process db get list road by prefix start with hint from db
    	Road road = new Road();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.lip.landinformationportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadID, RoadName, Latitude, Longitude from Road where RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                int roadId = rs.getInt("RoadID");
                String name = rs.getString("RoadName");
                Double latitude = rs.getDouble("Latitude");
                Double longitude = rs.getDouble("Longitude");
                ArrayList<RoadSegment> listRoadSegment = new ArrayList<>();
                listRoadSegment = capstone.lip.landinformationportal.dao.RoadSegmentDAO.roadSegmentDAO.getRoadSegmentByRoadID_DBNew(roadId);
                road.setRoadId(roadId).setName(name).setListRoadSegment(listRoadSegment).setLatitude(latitude).setLongitude(longitude);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return road;
    } 

}
