/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.user;

import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.User;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class UserServiceTest_1_2 extends AbstractUserServiceTest {
    
    /**
     * @Description: Normal select all
     * @Dependency: There are 5 records
     * @Expected Result: List size = 5
     */
    @Test
    public void FT_US_1_02() {
        List<User> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
    
    /**
     * @Description: Save positive user ID
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_01() {
        User result = instance.save(sampleUser
                .setUserId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save positive user ID
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_02() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save negative user ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_03() {
        User result = instance.save(sampleUser
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save user ID equals zero
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_04() {
        User result = instance.save(sampleUser
                .setUserId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_05() {
        User result = instance.save(sampleUser
                .setUserId(NULL_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save empty username 
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_06() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty username 
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_07() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_08() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(NO_SPACE_ALPHABETIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic username
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_09() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(NO_SPACE_ALPHABETIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_10() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric username
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_11() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(NUMERIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save Vietnamese username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_12() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese username
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_13() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_14() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric username
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_15() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(ALPHABETIC_NUMERIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_16() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese username
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_17() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_18() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character username
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_19() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_20() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character username
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_21() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_22() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character username
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_23() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without username
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_24() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUsername(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without username
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_25() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUsername(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty password
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_26() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty password
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_27() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic password
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_28() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(NO_SPACE_ALPHABETIC_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic password
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_29() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(NO_SPACE_ALPHABETIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric password
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_30() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(NUMERIC_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save numeric password
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_31() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(NUMERIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save Vietnamese password
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_32() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese password
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_33() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric password
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_34() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(ALPHABETIC_NUMERIC_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic numeric password
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_35() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(ALPHABETIC_NUMERIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese password
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_36() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese password
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_37() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character password
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_38() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(SPECIAL_CHARACTER_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save special character password
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_39() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(SPECIAL_CHARACTER_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save all space character password
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_40() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character password
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_41() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character password
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_42() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character password
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_43() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without password
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_44() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPassword(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without password
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_45() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPassword(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty user fullname
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_46() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty user fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_47() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic fullname
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_48() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_49() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(ALPHABETIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric fullname
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_50() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_51() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese fullname
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_52() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save Vietnamese fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_53() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese fullname
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_54() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_55() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character fullname
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_56() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_57() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character fullname
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_58() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_59() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without fullname
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_60() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setFullName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without fullname
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_61() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setFullName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty role
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_62() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setRole(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty role
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_63() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setRole(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save contributor role
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_64() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setRole(ROLE_ADMIN));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save admin role
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_65() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setRole(ROLE_CONTRIBUTOR));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric role
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_66() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setRole(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric role
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_67() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setRole(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric role
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_68() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setRole(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric role
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_69() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setRole(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without role
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_70() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setRole(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without role
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_71() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setRole(NULL_STRING));
        
        testFail(result);
    }
}
