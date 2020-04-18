/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.segment;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class SegmentServiceTest_2_3 extends AbstractSegmentServiceTest {
    
    /**
     * @Description: List is null
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_2_01() {
        boolean result = instance.delete(new ArrayList());
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 segment
     * @Dependency: Contain a non-existed segment
     */
    @Test
    public void FT_SgS_2_02() {
        ArrayList<SegmentOfStreet> segments = new ArrayList();
        segments.add(SampleSegment.setSegmentId(NOT_EXISTED_ID));
        boolean result = instance.delete(segments);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 segment
     * @Dependency: Contain an existed segment
     */
    @Test
    public void FT_SgS_2_03() {
        ArrayList<SegmentOfStreet> segments = new ArrayList();
        segments.add(repository.findById(EXISTED_ID).get());
        
        long records = repository.count();
        boolean result = instance.delete(segments);
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: List contain 3 segment
     * @Dependency: Contain existed segments
     */
    @Test
    public void FT_SgS_2_04() {
        ArrayList<SegmentOfStreet> segments = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            segments.add(repository.findById(EXISTED_IDs[i]).get());
        }
        
        long records = repository.count();
        boolean result = instance.delete(segments);
        
        testDeleteSuccess(result, EXISTED_IDs, records);
    }
    
    /**
     * @Description: Delete positive segment ID
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_3_01() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive segment ID
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_3_02() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get());
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete negative segment ID
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_3_03() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete segment ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_3_04() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_3_05() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(NULL_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete empty segment name 
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_3_06() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setSegmentName(EMPTY_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete empty segment name 
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_3_07() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_3_08() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setSegmentName(NULL_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_3_09() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_3_10() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setSegmentLng(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_3_11() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_3_12() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setSegmentLat(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_3_13() {
        boolean result = instance.delete(SampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLat(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_3_14() {
        boolean result = instance.delete(new SegmentOfStreet());
        
        testFail(result);
    }
}
