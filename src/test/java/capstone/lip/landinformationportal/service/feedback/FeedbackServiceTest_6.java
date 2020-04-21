/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.feedback;

import capstone.lip.landinformationportal.entity.Feedback;
import capstone.lip.landinformationportal.entity.User;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class FeedbackServiceTest_6 extends AbstractFeedbackServiceTest {
    
    /**
     * @Description: Reply without feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_01() {
        Feedback input = sampleFeedback
                .setFeedBackID(NULL_NOT_EXISTED_ID);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with negative feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_02() {
        Feedback input = sampleFeedback
                .setFeedBackID(NEGATIVE_NOT_EXISTED_ID);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with non existed positive feedback ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_03() {
        Feedback input = sampleFeedback
                .setFeedBackID(POSITIVE_NOT_EXISTED_ID);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with feedback ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_04() {
        Feedback input = sampleFeedback
                .setFeedBackID(ZERO_NOT_EXISTED_ID);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply without feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_05() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(NULL_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply without feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Reply success
     */
    @Test
    public void FT_FS_6_06() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(NUMERIC_VIETNAMESE_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testReplySuccess(result,input);
    }
    
    /**
     * @Description: Reply with special character feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_07() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(SPECIAL_CHARACTER_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with empty feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_08() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(EMPTY_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply without feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_09() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(NULL_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with numeric Vietnamese feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Reply success
     */
    @Test
    public void FT_FS_6_10() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(NUMERIC_VIETNAMESE_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testReplySuccess(result, input);
    }
    
    /**
     * @Description: Reply with special character feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Reply success
     */
    @Test
    public void FT_FS_6_11() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(SPECIAL_CHARACTER_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testReplySuccess(result, input);
    }
    
    /**
     * @Description: Reply with empty feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_12() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(EMPTY_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply without feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_13() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(NULL_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with numeric Vietnamese feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Reply success
     */
    @Test
    public void FT_FS_6_14() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(NUMERIC_VIETNAMESE_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testReplySuccess(result, input);
    }
    
    /**
     * @Description: Reply with special character feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Reply success
     */
    @Test
    public void FT_FS_6_15() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(SPECIAL_CHARACTER_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testReplySuccess(result, input);
    }
    
    /**
     * @Description: Reply with empty feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_16() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(EMPTY_STRING);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with invalid value feedback status
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_17() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setFeedbackStatus("INVALID");
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply with non-existed user
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_18() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setUser(new User().setUserId(NOT_EXISTED_ID));
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply without user
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_19() {
        Feedback input = sampleFeedback
                .setFeedBackID(EXISTED_ID)
                .setUser(null);
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Reply without all
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_6_20() {
        Feedback input = null;
        boolean result = instance.sendFeedbackReply(input);
        
        testFail(result);
    }
}