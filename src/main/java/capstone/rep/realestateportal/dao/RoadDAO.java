package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import java.util.ArrayList;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.Coordinate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoadDAO {

    public RoadDAO() {
    }

    public static RoadDAO roadDAO = new RoadDAO();
    
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
    
    public Road getRoadByRoadID(int roadId) throws SQLException{
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
                road.setRoadId(rs.getInt("RoadID"));
                road.setName(rs.getNString("RoadName"));
                road.setCity(capstone.rep.realestateportal.dao.CityDAO.cityDAO.getCityByCityID(rs.getInt("CityID")));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return road;
    }

    public ArrayList<Road> getRoadByName(String hint) throws SQLException {
        // TODO process db get list road by prefix start with hint from db
        ArrayList<Road> listRoad = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select r.RoadID, r.Name, c.CityID from Road r " +
            "inner join City c on r.CityID = c.CityID where r.Name like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "'%" + hint + "%'");
            rs = pre.executeQuery();
            while (rs.next()) {
                int roadId = rs.getInt("RoadID");
                String name = rs.getString("Name");
                City city = capstone.rep.realestateportal.dao.CityDAO.cityDAO.getCityByCityID(rs.getInt("CityID"));
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithRoadID(roadId);
                listRoad.add(new Road(roadId, name, city, listCoordinate));
            }
        } catch (Exception ex) {

        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listRoad;
    }

}
