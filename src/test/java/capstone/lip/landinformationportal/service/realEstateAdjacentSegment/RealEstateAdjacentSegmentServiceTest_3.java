/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstateAdjacentSegment;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateAdjacentSegmentServiceTest_3 extends AbstractRealEstateAdjacentSegmentServiceTest {
    
    /**
     * @Description: List is null
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_3_01() {
        List<RealEstateAdjacentSegment> details = null;
        List<RealEstateAdjacentSegment> result = instance.save(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 real estate adjacent segment
     * @Dependency: contain a non-existed real estate adjacent segment
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_3_02() {
        List<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        List<RealEstateAdjacentSegment> result = instance.save(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 real estate adjacent segment
     * @Dependency: contain an existed real estate adjacent segment
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_3_03() {
        long records = repository.count();
        List<RealEstateAdjacentSegment> details = new ArrayList();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    EXISTED_ID, EXISTED_ID));
        List<RealEstateAdjacentSegment> result = instance.save(details);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: List contain many real estate adjacent segment
     * @Dependency: contain a non-existed real estate adjacent segment
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_3_04() {
        List<RealEstateAdjacentSegment> details = getListRealEstateAdjacentSegments();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        List<RealEstateAdjacentSegment> result = instance.save(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain many real estate adjacent segment
     * @Dependency: contain existed real estate adjacent segments
     * @Expected Result: Fail
     */
    @Test
    public void FT_REASS_3_05() {
        long records = repository.count();
        List<RealEstateAdjacentSegment> details = getListRealEstateAdjacentSegments();
        details.add(setRealEstateAdjacentSegmentId(sampleRealEstateAdjacentSegment, 
                    EXISTED_ID, EXISTED_ID));
        List<RealEstateAdjacentSegment> result = instance.save(details);
        
        testUpdateSuccess(result, records);
    }
}
