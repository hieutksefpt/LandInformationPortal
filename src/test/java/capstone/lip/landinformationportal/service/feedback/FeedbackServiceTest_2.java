/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.feedback;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class FeedbackServiceTest_2 extends AbstractFeedbackServiceTest {
    
    /**
     * @Description: Delete without feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_01() {

        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(NULL_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete with negative feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_02() {
        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete with positive feedback ID
     * @Dependency: Feedback ID is existed
     * @Expected Result: Delete success
     */
    @Test
    public void FT_FS_2_03() {
        boolean result = instance.delete(repository
                .findById(EXISTED_ID).get());
        
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete with positive feedback ID
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_04() {
        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete with feedback ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_05() {
        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Delete success
     */
    @Test
    public void FT_FS_2_06() {
        boolean result = instance.delete(repository
                .findById(EXISTED_ID).get()
                    .setFeedbackTitle(NULL_STRING));
        
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete without feedback title
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_07() {
        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                    .setFeedbackTitle(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Delete success
     */
    @Test
    public void FT_FS_2_08() {
        boolean result = instance.delete(repository
                .findById(EXISTED_ID).get()
                    .setFeedbackContent(NULL_STRING));
        
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete without feedback content
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_09() {
        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                    .setFeedbackContent(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Delete success
     */
    @Test
    public void FT_FS_2_10() {
        boolean result = instance.delete(repository
                .findById(EXISTED_ID).get()
                    .setFeedbackAdminReply(NULL_STRING));
        
        
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete without feedback reply
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_11() {
        boolean result = instance.delete(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                    .setFeedbackAdminReply(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without user
     * @Dependency: N/A
     * @Expected Result: Delete success
     */
    @Test
    public void FT_FS_2_12() {
        boolean result = instance.delete(repository
                .findById(EXISTED_ID).get()
                    .setUser(null));
        
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_2_13() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
}
