/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstateAdjacentSegment;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateAdjacentSegmentServiceTest_2 
        extends AbstractRealEstateAdjacentSegmentServiceTest {
    
    /**
     * @Description: Delete null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_01() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 real estate adjacent segment
     * @Dependency: contain a non-existed real estate adjacent segment
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_02() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 real estate adjacent segment
     * @Dependency: contain an existed real estate adjacent segment
     * @Expected Result: Success
     */
    @Test
    public void FT_REASS_2_03() {
        long records = repository.count();
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: List contain many real estate adjacent segments
     * @Dependency: contain a non-existed real estate adjacent segment
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_04() {
        ArrayList<RealEstateAdjacentSegment> details = getListRealEstateAdjacentSegments();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain many real estate adjacent segment
     * @Dependency: contain existed real estate adjacent segments
     * @Expected Result: Success
     */
    @Test
    public void FT_REASS_2_05() {
        long records = repository.count();
        ArrayList<RealEstateAdjacentSegment> details = getListRealEstateAdjacentSegments();
//        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
//                    EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: Delete negative real estate ID and negative segment ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_06() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NEGATIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative real estate ID and positive segment ID
     * @Dependency: segment ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_07() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    POSITIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative real estate ID and positive segment ID
     * @Dependency: segment ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_08() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive real estate ID and negative segment ID
     * @Dependency: real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_09() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NEGATIVE_NOT_EXISTED_ID, POSITIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive real estate ID and negative segment ID
     * @Dependency: real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_10() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NEGATIVE_NOT_EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive real estate ID and positive segment ID
     * @Dependency: real estate ID and segment ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_REASS_2_11() {
        long records = repository.count();
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: Delete positive real estate ID and positive segment ID
     * @Dependency: real estate ID and segment ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_12() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive real estate ID and positive segment ID
     * @Dependency: real estate ID is existed and segment ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_13() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NOT_EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive real estate ID and positive segment ID
     * @Dependency: real estate ID is not existed and segment ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_14() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete real estate ID and segment ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_15() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    ZERO_NOT_EXISTED_ID, ZERO_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_2_16() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(null);
        boolean result = instance.delete(details);
        
        testFail(result);
    }
}
