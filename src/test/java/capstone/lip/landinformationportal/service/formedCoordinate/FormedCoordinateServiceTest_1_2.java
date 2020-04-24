/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.formedCoordinate;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.FormedCoordinate;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class FormedCoordinateServiceTest_1_2
        extends AbstractFormedCoordinateServiceTest {

    /**
     * @Description: Save positive formed coordinate ID
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_01() {
        long records = repository.count();
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(POSITIVE_NOT_EXISTED_ID));

        testInsertSuccess(result, records);
    }

    /**
     * @Description: Save positive formed coordinate ID
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_02() {
        long records = repository.count();
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID));

        testUpdateSuccess(result, records);
    }

    /**
     * @Description: Save negative formed coordinate ID
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_1_03() {
        long records = repository.count();
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NEGATIVE_NOT_EXISTED_ID));

        testInsertSuccess(result, records);
    }

    /**
     * @Description: Save formed coordinate ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_1_04() {
        long records = repository.count();
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(ZERO_NOT_EXISTED_ID));

        testInsertSuccess(result, records);
    }

    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_1_05() {
        long records = repository.count();
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NULL_ID));

        testInsertSuccess(result, records);
    }

    /**
     * @Description: Save empty formed coordinate segment
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_06() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setSegmentOfStreet(null));

        testFail(result);
    }

    /**
     * @Description: Save empty formed coordinate segment
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_07() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(null));

        testFail(result);
    }

    /**
     * @Description: Save negative formed coordinate segment id
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_08() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setSegmentOfStreet(new SegmentOfStreet()
                        .setSegmentId(NEGATIVE_NOT_EXISTED_ID)));

        testFail(result);
    }

    /**
     * @Description: Save negative formed coordinate segment id
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_09() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(new SegmentOfStreet()
                        .setSegmentId(NEGATIVE_NOT_EXISTED_ID)));

        testFail(result);
    }

    /**
     * @Description: Save zero formed coordinate segment id
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_10() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setSegmentOfStreet(new SegmentOfStreet()
                        .setSegmentId(ZERO_NOT_EXISTED_ID)));

        testFail(result);
    }

    /**
     * @Description: Save zero formed coordinate segment id
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_11() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(new SegmentOfStreet()
                        .setSegmentId(ZERO_NOT_EXISTED_ID)));

        testFail(result);
    }

    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_12() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLat(-99.0).setFormedLng(99.0));

        testFail(result);
    }

    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_13() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setFormedLat(-99.0).setFormedLng(99.0));

        testFail(result);
    }

    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_14() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLat(99.0).setFormedLng(-99.0));

        testFail(result);
    }

    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_15() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setFormedLat(99.0).setFormedLng(-99.0));

        testFail(result);
    }

    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_16() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLat(0.0).setFormedLng(0.0));

        testFail(result);
    }

    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_17() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setFormedLat(0.0).setFormedLng(0.0));

        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_18() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLng(null));

        testFail(result);
    }

    /**
     * @Description: Save without lng
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_19() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setFormedLng(null));

        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Formed coordinate ID is existed
     */
    @Test
    public void FT_FCS_1_20() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(EXISTED_ID)
                .setFormedLat(null));

        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Formed coordinate ID is not existed
     */
    @Test
    public void FT_FCS_1_21() {
        FormedCoordinate result = instance.save(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setFormedLat(null));

        testFail(result);
    }

    /**
     * @Description: Save without all
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_1_22() {
        FormedCoordinate result = instance.save(null);

        testFail(result);
    }
    
    /**
     * @Description: List is null
     * @Dependency: N/A
     */
    @Test
    public void FT_FCS_2_01() {
        List<FormedCoordinate> results = instance.saveAll(null);

        testFail(results);
    }
    
    /**
     * @Description: List contain 1 non-existed formed coordinate
     * @Dependency: Contain a non-existed segment
     */
    @Test
    public void FT_FCS_2_02() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(new SegmentOfStreet()
                        .setSegmentId(NOT_EXISTED_ID)));
        List<FormedCoordinate> results = instance.saveAll(formedCoordinates);

        testFail(results);
    }
    
    /**
     * @Description: List contain 1 non-existed formed coordinate
     * @Dependency: Contain a null segment
     */
    @Test
    public void FT_FCS_2_03() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(null));
        
        List<FormedCoordinate> results = instance.saveAll(formedCoordinates);

        testFail(results);
    }
    
    /**
     * @Description: List contain 1 non-existed formed coordinate
     * @Dependency: Contain an existed segment
     */
    @Test
    public void FT_FCS_2_04() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        formedCoordinates.add(sampleFormedCoordinate
                .setFormedCoordinateId(NOT_EXISTED_ID)
                .setSegmentOfStreet(new SegmentOfStreet()
                        .setSegmentId(EXISTED_ID)));
        List<FormedCoordinate> results = instance.saveAll(formedCoordinates);

        testInsertSuccess(results, records);
    }
    
    /**
     * @Description: List contain 3 existed formed coordinate
     * @Dependency: Contain a non-existed segment
     */
    @Test
    public void FT_FCS_2_05() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            formedCoordinates.add(sampleFormedCoordinate
                    .setFormedCoordinateId(EXISTED_IDs[i])
                    .setSegmentOfStreet(new SegmentOfStreet()
                            .setSegmentId(EXISTED_ID)));
        }
        formedCoordinates.set(0, formedCoordinates.get(0)
                        .setSegmentOfStreet(new SegmentOfStreet()
                            .setSegmentId(NOT_EXISTED_ID)));
        List<FormedCoordinate> results = instance.saveAll(formedCoordinates);

        testFail(results);
    }
    
    /**
     * @Description: List contain 3 existed formed coordinate
     * @Dependency: Contain a null segment
     */
    @Test
    public void FT_FCS_2_06() {
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            formedCoordinates.add(sampleFormedCoordinate
                    .setFormedCoordinateId(EXISTED_IDs[i])
                    .setSegmentOfStreet(new SegmentOfStreet()
                            .setSegmentId(EXISTED_ID)));
        }
        formedCoordinates.set(0, formedCoordinates.get(0)
                        .setSegmentOfStreet(null));
        List<FormedCoordinate> results = instance.saveAll(formedCoordinates);

        testFail(results);
    }
    
    /**
     * @Description: List contain 3 existed formed coordinate
     * @Dependency: Contain a null segment
     */
    @Test
    public void FT_FCS_2_07() {
        long records = repository.count();
        ArrayList<FormedCoordinate> formedCoordinates = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            formedCoordinates.add(sampleFormedCoordinate
                    .setFormedCoordinateId(EXISTED_IDs[i])
                    .setSegmentOfStreet(new SegmentOfStreet()
                            .setSegmentId(EXISTED_ID)));
        }
        List<FormedCoordinate> results = instance.saveAll(formedCoordinates);

        testUpdateSuccess(results, records);
    }
}
