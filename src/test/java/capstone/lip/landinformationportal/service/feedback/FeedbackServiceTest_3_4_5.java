/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.feedback;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.Feedback;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class FeedbackServiceTest_3_4_5 extends AbstractFeedbackServiceTest {
    
    /**
     * @Description: Find positive feedback ID
     * @Dependency: Feedback ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_FS_3_01() {
        Feedback result = instance.findById(EXISTED_ID);
        
        testFindSuccess(EXISTED_ID, result);
    }
    
    /**
     * @Description: Find positive feedback ID
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_3_02() {
        Feedback result = instance.findById(NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find negative feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_3_03() {
        Feedback result = instance.findById(NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find feedback ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_3_04() {
        Feedback result = instance.findById(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find null feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_3_05() {
        Feedback result = instance.findById(NULL_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Invalid status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_4_01() {
        long result = instance.countByFeedbackStatus("INVALID");
        
        //TEST FAIL
        assertEquals(0, result);
    }
    
    /**
     * @Description: Valid status
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_FS_4_02() {
        long result = instance.countByFeedbackStatus(VALID_FEEDBACK_STATUS);
        
        //TEST SUCCESS
        assertEquals(TOTAL_FEEDBACK_OPEN, result);
    }
    
    /**
     * @Description: Empty string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_4_03() {
        long result = instance.countByFeedbackStatus(EMPTY_STRING);
        
        //TEST FAIL
        assertEquals(0, result);
    }
    
    /**
     * @Description: Null status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_4_04() {
        long result = instance.countByFeedbackStatus(NULL_STRING);
        
        //TEST FAIL
        assertEquals(0, result);
    }
    
    /**
     * @Description: Existed page
     * @Dependency: Valid status
     * @Expected Result: Success
     */
    @Test
    public void FT_FS_5_05() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findByFeedbackStatus(VALID_FEEDBACK_STATUS, pageable);
        
        assertEquals(repository.findByFeedbackStatus(VALID_FEEDBACK_STATUS, pageable), result);
    }
    
    /**
     * @Description: Out range page
     * @Dependency: Valid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_5_06() {
        Page result = instance.findByFeedbackStatus(VALID_FEEDBACK_STATUS, 
                PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE));
        
        testFail(result);
    }
}
