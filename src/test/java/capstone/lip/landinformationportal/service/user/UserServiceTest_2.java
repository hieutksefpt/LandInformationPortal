/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.user;

import capstone.lip.landinformationportal.common.constant.UserGenderConstant;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.User;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class UserServiceTest_2 extends AbstractUserServiceTest {
    
    private final String EMAIL_MULTI_AT = "mail@mmail@.gmail.com";
    private final String EMAIL_ALPHANUMERIC = "lip123@gmail.com";
    private final String EMAIL_VIETNAMESE = "đồán@gmail.com";
    private final String EMAIL_SPECIAL_CHARACTER = "!#lip%&*(),l;'@gmail.com";
    private final String EMAIL_DOT_FIRST = ".capstone@gmail.com";
    private final String EMAIL_DOT_LAST = "capstone@gmail.com.";
    private final String EMAIL_AT_FIRST = "@capstonegmail.com";
    private final String EMAIL_AT_LAST = "capstonegmail.com@";
    
    private final String MALE = UserGenderConstant.MALE;
    private final String FEMALE = UserGenderConstant.FEMALE;
    
    /**
     * @Description: Save empty email
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_72() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty email
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_73() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with multi @ 
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_74() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_MULTI_AT));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with multi @ 
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_75() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_MULTI_AT));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric email
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_76() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_ALPHANUMERIC));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic numeric email
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_77() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_ALPHANUMERIC));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save Vietnamese email
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_78() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_VIETNAMESE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese email
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_79() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_VIETNAMESE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character email
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_80() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_SPECIAL_CHARACTER));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character email
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_81() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_SPECIAL_CHARACTER));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with dot at first
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_82() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_DOT_FIRST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with dot at first
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_83() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_DOT_FIRST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with dot at last
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_84() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_DOT_LAST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with dot at last
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_85() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_DOT_LAST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with AT at first
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_86() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_AT_FIRST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with AT at first
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_87() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_AT_FIRST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with AT at last
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_88() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(EMAIL_AT_LAST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save email with AT at last
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_89() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(EMAIL_AT_LAST));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without email
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_90() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setEmail(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without email
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_91() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setEmail(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty phone
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_92() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPhone(EMPTY_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save empty phone
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_93() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPhone(EMPTY_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic phone
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_94() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPhone(ALPHABETIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic phone
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_95() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPhone(ALPHABETIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric phone
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_96() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPhone(NUMERIC_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save numeric phone
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_97() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPhone(NUMERIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic numeric phone
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_98() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPhone(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric phone
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_99() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPhone(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without phone
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_100() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setPhone(NULL_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save without phone
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_101() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setPhone(NULL_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save empty user status
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_102() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUserStatus(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty user status
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_103() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUserStatus(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save valid status
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_104() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUserStatus(STATUS_ACTIVE));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save valid status
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_105() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUserStatus(STATUS_BAN));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save invalid status
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_106() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setUserStatus(INVALID_FIELD));
        
        testFail(result);
    }
    
    /**
     * @Description: Save invalid status
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_107() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setUserStatus(INVALID_FIELD));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty gender
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_118() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setGender(EMPTY_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save empty gender
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_119() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setGender(EMPTY_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save male gender
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_120() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setGender(MALE));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save female gender
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_121() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setGender(FEMALE));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save invalid gender
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_122() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setGender(INVALID_FIELD));
        
        testFail(result);
    }
    
    /**
     * @Description: Save invalid gender
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_123() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setGender(INVALID_FIELD));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without gender
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_124() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setGender(NULL_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save without gender
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_125() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setGender(NULL_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save empty address
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_126() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setAddress(EMPTY_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save empty address
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_127() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setAddress(EMPTY_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese address
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_128() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setAddress(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese address
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_129() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setAddress(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save special character address
     * @Dependency: User ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_US_2_130() {
        User result = instance.save(sampleUser
                .setUserId(EXISTED_ID)
                .setAddress(SPECIAL_CHARACTER_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save special character address
     * @Dependency: User ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_US_2_131() {
        User result = instance.save(sampleUser
                .setUserId(NOT_EXISTED_ID)
                .setAddress(SPECIAL_CHARACTER_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_2_132() {
        User result = instance.save(null);
        
        testFail(result);
    }
}
