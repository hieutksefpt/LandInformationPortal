/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.feedback;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class FeedbackServiceTest_5 extends AbstractFeedbackServiceTest {
    
    /**
     * @Description: Non existed page
     * @Dependency: Invalid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_5_01() {
        Page result = instance.findByFeedbackStatus("INVALID", 
                PageRequest.of(EXISTED_PAGE, PAGE_SIZE));
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: Valid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_5_02() {
        Page result = instance.findByFeedbackStatus(VALID_FEEDBACK_STATUS, 
                PageRequest.of(EXISTED_PAGE, PAGE_SIZE));
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: Null status
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_5_03() {
        Page result = instance.findByFeedbackStatus(NULL_STRING, 
                PageRequest.of(EXISTED_PAGE, PAGE_SIZE));
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: Empty string
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_5_04() {
        Page result = instance.findByFeedbackStatus(EMPTY_STRING, 
                PageRequest.of(EXISTED_PAGE, PAGE_SIZE));
        
        testFail(result);
    }
}
