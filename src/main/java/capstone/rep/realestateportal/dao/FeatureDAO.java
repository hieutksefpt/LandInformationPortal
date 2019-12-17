/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Feature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class FeatureDAO {

    public FeatureDAO() {
    }

    public static FeatureDAO featureDAO = new FeatureDAO();

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

    public ArrayList<Feature> getListFeatureByReoID(int reoId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<Feature> listFeature = new ArrayList<>();
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select f.FeatureID, f.Name, f.Coefficient from RealEstateObject reo inner join RealEstateObjectFeature reof on reo.ReoID = reof.ReoID "
                    + "inner join Feature f on reof.FeatureID = f.FeatureID where reo.ReoID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, reoId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int featureId = rs.getInt("FeatureID");
                String name = rs.getString("Name");
                Float coefficient = rs.getFloat("Coefficient");
                listFeature.add(new Feature(featureId, name, coefficient));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listFeature;
    }
}
