package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.Coordinate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            throw ex;
        }
    }

    public Road getRoadByRoadID(int roadId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        Road road = null;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadID, Name, CityID from Road where RoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadId);
            rs = pre.executeQuery();
            while (rs.next()) {
            	road = new Road();
                road.setRoadId(rs.getInt("RoadID"));
                road.setName(rs.getNString("Name"));
                road.setCity(capstone.rep.realestateportal.dao.CityDAO.cityDAO.getCityByCityID(rs.getInt("CityID")));
            }
        } catch (Exception ex) {
            throw ex;
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
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select r.RoadID, r.Name, c.CityID from Road r inner join City c on r.CityID = c.CityID where r.Name like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + hint + "%");
            rs = pre.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                int roadId = rs.getInt("RoadID");
                String name = rs.getString("Name");
                City city = capstone.rep.realestateportal.dao.CityDAO.cityDAO.getCityByCityID(rs.getInt("CityID"));
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithRoadID(roadId);
                listRoad.add(new Road(roadId, name, city, listCoordinate));
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
//            throw ex;
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listRoad;
    }

}
