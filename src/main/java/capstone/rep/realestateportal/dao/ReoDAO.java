package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Feature;
import java.util.ArrayList;
import java.util.List;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.RealEstateObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReoDAO {

    public ReoDAO() {
    }

    public static ReoDAO reoDAO = new ReoDAO();

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

    public List<RealEstateObject> getListReoInside(List<Coordinate> listCoordinates) {
        List<RealEstateObject> listReo = new ArrayList<>();
        return listReo;
    }

    public ArrayList<RealEstateObject> getListReoByLandNearRoadID(int landNearRoadId) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<RealEstateObject> listReo = null;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select reo.ReoID, reo.Name, reo.Price from RealEstateObject reo inner join LandNearRoadDetail lnrd on reo.ReoID = lnrd.ReoID "
                    + "where lnrd.LandNearRoadID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, landNearRoadId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int reoId = rs.getInt("ReoID");
                String name = rs.getString("Name");
                Float price = rs.getFloat("Price");
                ArrayList<Feature> listFeature = capstone.rep.realestateportal.dao.FeatureDAO.featureDAO.getListFeatureByReoID(reoId);
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithReoID(reoId);
                listReo.add(new RealEstateObject(reoId, name, price, listFeature, listCoordinate));
            }
        } catch (Exception ex) {
        	System.out.print(ex.getMessage());
        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listReo;
    }
    
    public ArrayList<RealEstateObject> getAllReoInDb() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<RealEstateObject> listReo = new ArrayList<>();
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select * from RealEstateObject";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int reoId = rs.getInt("ReoID");
                String name = rs.getString("Name");
                Float price = rs.getFloat("Price");
                ArrayList<Feature> listFeature = capstone.rep.realestateportal.dao.FeatureDAO.featureDAO.getListFeatureByReoID(reoId);
                ArrayList<Coordinate> listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithReoID(reoId);
                listReo.add(new RealEstateObject(reoId, name, price, listFeature, listCoordinate));
            }
        } catch (Exception ex) {
        	System.out.print(ex.getMessage());
        } finally {
            CloseConnect(conn, pre, rs);
        }
        return listReo;
    }
}
