/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.City;
import capstone.rep.realestateportal.entity.RoadSegment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anh Hao
 */
public class RoadSegmentDAO {

    public static RoadSegmentDAO roadSegmentDAO = new RoadSegmentDAO();
    
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
    
    public RoadSegmentDAO() {
    }
    
    public RoadSegment getRoadSegmentByRoadSegmentID(int roadSengmentId) throws SQLException{
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        RoadSegment roadSegment = null;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select RoadSegmentID, Name, RoadID from RoadSegment where RoadSegmentID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, roadSengmentId);
            rs = pre.executeQuery();
            while (rs.next()) {
                roadSegment.setRoadSegmentId(rs.getInt("RoadSegmentID"));
                roadSegment.setName(rs.getNString("Name"));
                roadSegment.setRoad(capstone.rep.realestateportal.dao.RoadDAO.roadDAO.getRoadByRoadID(rs.getInt("RoadID")));
            }
        } catch (Exception ex) {
        	
        } finally {
            CloseConnect(conn, pre, rs);
        }
        return roadSegment;
    }
}
