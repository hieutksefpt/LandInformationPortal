/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.dao;

import capstone.rep.realestateportal.entity.Coordinate;
import capstone.rep.realestateportal.entity.Layer;
import capstone.rep.realestateportal.entity.Road;
import capstone.rep.realestateportal.entity.RoadSegment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class LayerDAO {

    public static LayerDAO layerDAO = new LayerDAO();

    public LayerDAO() {
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

    public ArrayList<Layer> getListLayerByRoad_DBNew(String roadName) throws Exception {
        // TODO process db get list road by prefix start with hint from db
        ArrayList<Layer> listLayer = new ArrayList<>();
        // get Road here from DB
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select l.LayerID, l.LayerName, lt.LayerTypeName from Road r inner join RoadSegment rs on r.RoadID = rs.RoadID "
                    + "inner join RoadSegmentLayer rsl on rs.RoadSegmentID = rsl.RoadSegmentID "
                    + "inner join Layer l on rsl.LayerID = l.LayerID "
                    + "inner join LayerType lt on l.LayerTypeID = lt.LayerTypeID "
                    + "where r.RoadName like ? ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + roadName + "%");
            System.out.println(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int layerId = rs.getInt("LayerID");
                String name = rs.getString("LayerName");
                String layerType = rs.getString("LayerTypeName");
                ArrayList<Coordinate> listCoordinate = new ArrayList<>();
                listCoordinate = capstone.rep.realestateportal.dao.CoordinateDAO.coordinateDAO.getListCoordinateWithLayerId_DBNew(layerId);
                listLayer.add(new Layer().setLayerId(layerId).setLayerName(name).setLayerType(layerType).setListCoordinate(listCoordinate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return listLayer;
    }

    public int insertLayerByRoadSegment_DBNew(Layer layer, RoadSegment roadSegment) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            int layerId = getTypeIdWithTypeName_DBNew(layer.getLayerName());

            String sql = "insert into Layer (LayerName, LayerTypeID) "
                    + "values (?,?)";
            System.out.println(sql);
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setNString(1, layer.getLayerName());
            pre.setInt(2, layerId);
            inserted = pre.executeUpdate();

            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    layer.setLayerId(generatedKeys.getInt(1));
                    int insertRoadSegmentLayer = insertRoadSegmentLayer_DBNew(roadSegment.getRoadSegmentId(), layer.getLayerId());
                    int insertLayerCoordinate = insertLayerCoordinate_DBNew(layer);
                } else {
                    System.out.print("Creating layer failed, no ID obtained.");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }

    private int insertLayerCoordinate_DBNew(Layer layer) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "insert LayerCoordinate(LayerID, Latitude, Latitude) "
                    + "values ";
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (Coordinate coordinate : layer.getListCoordinate()) {
                sql = sql + ",(" + layer.getLayerId() + "," + coordinate.getLatitude() + "," + coordinate.getLongitude() + ")";
            }
            System.out.println(sql);
            inserted = pre.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }

    private int insertRoadSegmentLayer_DBNew(int roadSegmentId, int layerId) throws Exception {
        int inserted = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "insert RoadSegmentLayer(RoadSegmentID, LayerID) "
                    + "values(?,?)";
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, roadSegmentId);
            pre.setInt(2, layerId);
            System.out.println(sql);
            inserted = pre.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return inserted;
    }

    private int getTypeIdWithTypeName_DBNew(String typeName) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        int layerId = 0;
        try {
            conn = capstone.rep.realestateportal.connection.Connection.dBContext.getConnection();
            String sql = "select LayerTypeID from LayerType where LayerTypeName like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "'" + typeName + "'");
            rs = pre.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                layerId = rs.getInt("LayerTypeID");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pre, rs);
        }
        return layerId;
    }
}
