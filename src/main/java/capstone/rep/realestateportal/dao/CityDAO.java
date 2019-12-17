/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Anh Hao
 */
public class CityDAO {

    public static CityDAO cityDAO = new CityDAO();
    
    public CityDAO() {
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
            throw ex;
        }
    }
    
    public City getCityByCityID(int cityId) throws Exception{
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        City city = null;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select Name from City where CityID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, cityId);
            rs = pre.executeQuery();
            while (rs.next()) {
            	city = new City();
                city.setCityId(cityId);
                city.setName(rs.getNString("Name"));
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
//            throw ex;
        } finally {
            closeConnection(conn, pre, rs);
        }
        return city;
    }
}
