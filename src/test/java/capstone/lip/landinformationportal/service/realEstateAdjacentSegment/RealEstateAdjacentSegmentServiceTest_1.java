/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstateAdjacentSegment;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateAdjacentSegmentServiceTest_1 
        extends AbstractRealEstateAdjacentSegmentServiceTest {
    
    /**
     * @Description: Save negative segment ID and negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_01() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , NEGATIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative segment ID and positive real estate ID
     * @Dependency: real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_02() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , NEGATIVE_NOT_EXISTED_ID, POSITIVE_NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative segment ID and positive real estate ID
     * @Dependency: real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_03() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , NEGATIVE_NOT_EXISTED_ID, EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive segment ID and negative real estate ID
     * @Dependency: segment ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_04() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , POSITIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive segment ID and negative real estate ID
     * @Dependency: segment ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_05() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , EXISTED_ID, NEGATIVE_NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive segment ID and positive real estate ID
     * @Dependency: real estate ID and segment ID is existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_REASS_1_06() {
        long records = repository.count();
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , EXISTED_ID, EXISTED_ID)));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive segment ID and positive real estate ID
     * @Dependency: real estate ID and segment ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_07() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , NOT_EXISTED_ID, NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive segment ID and positive real estate ID
     * @Dependency: real estate ID is existed and segment ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_08() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , NOT_EXISTED_ID, EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive segment ID and positive real estate ID
     * @Dependency: real estate ID is not existed and segment ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_09() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , EXISTED_ID, NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save segment ID and real estate ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_10() {
        RealEstateAdjacentSegment result = instance.save(sampleRealEstateAdjacentSegment
                .setId(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment.getId()
                        , ZERO_NOT_EXISTED_ID, ZERO_NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_1_11() {
        RealEstateAdjacentSegment input = null;
        RealEstateAdjacentSegment result = instance.save(input);
        
        testFail(result);
    }
}
