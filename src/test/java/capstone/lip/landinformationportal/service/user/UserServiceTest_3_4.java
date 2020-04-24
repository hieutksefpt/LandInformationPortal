/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.user;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.User;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class UserServiceTest_3_4 extends AbstractUserServiceTest {
    
    private final int DEFAULT_PASSWORD_LENGTH = 8;
    
    /**
     * @Description: Find positive user ID
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_3_01() {
        User result = instance.findById(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive user ID
     * @Dependency: User ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_US_3_02() {
        User result = instance.findById(EXISTED_ID);
        
        testFindSuccess(result);
    }
    
    /**
     * @Description: Find negative user ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_3_03() {
        User result = instance.findById(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find user ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_3_04() {
        User result = instance.findById(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find null ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_3_05() {
        User result = instance.findById(NULL_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset negative user ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_01() {
        String result = instance
                .resetPassword(NEGATIVE_NOT_EXISTED_ID, DEFAULT_PASSWORD_LENGTH);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset user ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_02() {
        String result = instance
                .resetPassword(ZERO_NOT_EXISTED_ID, DEFAULT_PASSWORD_LENGTH);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset null ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_03() {
        String result = instance
                .resetPassword(NULL_ID, DEFAULT_PASSWORD_LENGTH);
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length is less than zero
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_04() {
        String result = instance
                .resetPassword(NOT_EXISTED_ID, -1);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length is less than zero
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_05() {
        String result = instance
                .resetPassword(EXISTED_ID, -1);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length is more than zero less than 8
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_06() {
        String result = instance
                .resetPassword(NOT_EXISTED_ID, 1);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length is more than zero less than 8
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_07() {
        String result = instance
                .resetPassword(EXISTED_ID, 1);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length is more than 8
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_08() {
        String result = instance
                .resetPassword(NOT_EXISTED_ID, 9);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length is more than 8
     * @Dependency: User ID is not existed
     * @Expected Result: Success
     */
    @Test
    public void FT_US_4_09() {
        String oldPassword = repository.findById(EXISTED_ID).get().getPassword();
        String result = instance
                .resetPassword(EXISTED_ID, 9);
        
        assertEquals(false, oldPassword.equals(result));
    }
    
    /**
     * @Description: Reset with password length equals zero
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_10() {
        String result = instance
                .resetPassword(NOT_EXISTED_ID, 0);
        
        testFail(result);
    }
    
    /**
     * @Description: Reset with password length equals zero
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_4_11() {
        String result = instance
                .resetPassword(EXISTED_ID, 0);
        
        testFail(result);
    }
}
