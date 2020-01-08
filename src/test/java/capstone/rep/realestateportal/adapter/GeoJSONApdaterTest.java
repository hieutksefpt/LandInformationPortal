/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.adapter;

import capstone.rep.realestateportal.entity.LandNearRoad;
import capstone.rep.realestateportal.entity.RoadSegment;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Admin
 */
public class GeoJSONApdaterTest {
    
    public GeoJSONApdaterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createLineGeoJSON method, of class GeoJSONApdater.
     */
    @Test
    public void testCreateLineGeoJSON() {
        System.out.println("createLineGeoJSON");
        List<RoadSegment> listRoadSegment = null;
        JSONObject expResult = null;
        JSONObject result = GeoJSONApdater.createLineGeoJSON(listRoadSegment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
