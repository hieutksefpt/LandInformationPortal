/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.feedback;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.Feedback;
import capstone.lip.landinformationportal.common.entity.User;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class FeedbackServiceTest_1 extends AbstractFeedbackServiceTest {
    
    /**
     * @Description: Save without feedback ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_01() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NULL_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with negative feedback ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_02() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with positive feedback ID
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_03() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with positive feedback ID
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_04() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with feedback ID equals zero
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_05() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save without feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_06() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without feedback title
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_07() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackTitle(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with numeric Vietnamese feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_08() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with numeric Vietnamese feedback title
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_09() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackTitle(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with special character feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_10() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with special character feedback title
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_11() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackTitle(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with empty feedback title
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_12() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackTitle(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with empty feedback title
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_13() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackTitle(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_14() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without feedback content
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_15() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackContent(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with numeric Vietnamese feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_16() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with numeric Vietnamese feedback content
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_17() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackContent(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with special character feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_18() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(SPECIAL_CHARACTER_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with special character feedback content
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_19() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackContent(SPECIAL_CHARACTER_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with empty feedback content
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_20() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackContent(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with empty feedback content
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_21() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackContent(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_22() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(NULL_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save without feedback reply
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_23() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackAdminReply(NULL_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with numeric Vietnamese feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_24() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with numeric Vietnamese feedback reply
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_25() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackAdminReply(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with special character feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_26() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(SPECIAL_CHARACTER_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with special character feedback reply
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_27() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackAdminReply(SPECIAL_CHARACTER_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with empty feedback reply
     * @Dependency: Feedback ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_FS_1_28() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackAdminReply(EMPTY_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with empty feedback reply
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_FS_1_29() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackAdminReply(EMPTY_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with invalid value feedback status
     * @Dependency: Feedback ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_30() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setFeedbackStatus("INVALID"));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with invalid value feedback status
     * @Dependency: Feedback ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_31() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(NOT_EXISTED_ID)
                .setFeedbackStatus("INVALID"));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with non-existed user
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_32() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setUser(new User()
                        .setUserId(NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without user
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_33() {
        Feedback result = instance.save(getSampleFeedback()
                .setFeedBackID(EXISTED_ID)
                .setUser(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_FS_1_34() {
        Feedback result = instance.save(null);
        
        testFail(result);
    }
}
