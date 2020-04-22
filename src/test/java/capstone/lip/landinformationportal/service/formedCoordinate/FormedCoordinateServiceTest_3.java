/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.formedCoordinate;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.FormedCoordinate;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class FormedCoordinateServiceTest_3 extends AbstractFormedCoordinateServiceTest {
    
    /**
     * @Description: List is null
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_3_01() {
        boolean result = instance.delete(null);

        testFail(result);
    }
    
    /**
     * @Description: List contain 1 formed coordinate
     * @Dependency: Contain a non-existed Formed coordinate
     */
    @Test
    public void FT_FCS_3_02() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID));
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
    
    /**
     * @Description: List contain 1 formed coordinate
     * @Dependency: Contain an existed Formed coordinate
     */
    @Test
    public void FT_FCS_3_03() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(repository.findById(EXISTED_ID).get());
        boolean result = instance.delete(formedCoordinates);

        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: List contain 3 formed coordinates
     * @Dependency: Contain a non-existed Formed coordinate
     */
    @Test
    public void FT_FCS_3_04() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            formedCoordinates.add(repository.findById(EXISTED_IDs[i]).get());
        }
        formedCoordinates.set(0, formedCoordinates.get(0)
                        .setFormedCoordinateId(NOT_EXISTED_ID));
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
    
    /**
     * @Description: List contain 3 formed coordinates
     * @Dependency: Contain an existed Formed coordinate
     */
    @Test
    public void FT_FCS_3_05() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            formedCoordinates.add(repository.findById(EXISTED_IDs[i]).get());
        }
        boolean result = instance.delete(formedCoordinates);

        testDeleteSuccess(result, EXISTED_IDs, records);
    }
    
    /**
     * @Description: Delete without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_3_06() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(null));
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
    
    /**
     * @Description: Delete empty formed coordinate segment
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_3_07() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(repository.findById(EXISTED_ID).get()
                .setSegmentOfStreet(null));
        boolean result = instance.delete(formedCoordinates);

        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete empty formed coordinate segment
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_3_08() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(null));
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_3_09() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(repository.findById(EXISTED_ID).get()
                .setFormedLng(null));
        boolean result = instance.delete(formedCoordinates);

        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_3_10() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLng(null));
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_3_11() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(repository.findById(EXISTED_ID).get()
                .setFormedLat(null));
        boolean result = instance.delete(formedCoordinates);

        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_3_12() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLat(null));
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_3_13() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(new FormedCoordinate());
        boolean result = instance.delete(formedCoordinates);

        testFail(result);
    }
}
