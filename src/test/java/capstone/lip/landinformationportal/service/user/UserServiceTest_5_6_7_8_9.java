/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.user;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class UserServiceTest_5_6_7_8_9 extends AbstractUserServiceTest {
    
    private final String EXISTED_ALPHANUMERIC_USERNAME = "User1";
    private final String EXISTED_ALPHABETIC_USERNAME = "UserF";
    private final String EXISTED_NUMERIC_USERNAME = "12345";
    
    private final String NOT_EXISTED_ALPHANUMERIC_USERNAME = "User99";
    private final String NOT_EXISTED_ALPHABETIC_USERNAME = "UserP";
    private final String NOT_EXISTED_NUMERIC_USERNAME = "999999";
    
    /**
     * @Description: Find empty username 
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_01() {
        User result = instance.findByUsername(EMPTY_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Find alphabetic username
     * @Dependency: User is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_US_5_02() {
        User result = instance.findByUsername(EXISTED_ALPHABETIC_USERNAME);
        
        testFindSuccess(result);
    }
    
    /**
     * @Description: Find alphabetic username
     * @Dependency: User is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_03() {
        User result = instance.findByUsername(NOT_EXISTED_ALPHABETIC_USERNAME);
        
        testFail(result);
    }
    
    /**
     * @Description: Find numeric username
     * @Dependency: User is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_US_5_04() {
        User result = instance.findByUsername(EXISTED_NUMERIC_USERNAME);
        
        testFindSuccess(result);
    }
    
    /**
     * @Description: Find numeric username
     * @Dependency: User is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_05() {
        User result = instance.findByUsername(NOT_EXISTED_NUMERIC_USERNAME);
        
        testFail(result);
    }
    
    /**
     * @Description: Find Vietnamese username
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_06() {
        User result = instance.findByUsername(VIETNAMESE_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Find alphabetic numeric username
     * @Dependency: User is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_US_5_07() {
        User result = instance.findByUsername(EXISTED_ALPHANUMERIC_USERNAME);
        
        testFindSuccess(result);
    }
    
    /**
     * @Description: Find alphabetic numeric username
     * @Dependency: User is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_08() {
        User result = instance.findByUsername(NOT_EXISTED_ALPHANUMERIC_USERNAME);
        
        testFail(result);
    }
    
    /**
     * @Description: Find numeric Vietnamese username
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_09() {
        User result = instance.findByUsername(NUMERIC_VIETNAMESE_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Find special character username
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_10() {
        User result = instance.findByUsername(SPECIAL_CHARACTER_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Find all space character username
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_11() {
        User result = instance.findByUsername(ALL_SPACE_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Find enter character username
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_12() {
        User result = instance.findByUsername(ENTER_CHARACTER_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Find null username
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_5_13() {
        User result = instance.findByUsername(NULL_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Normal data
     * @Dependency: There are 5 records
     * @Expected Result: Return 5
     */
    @Test
    public void FT_US_6_02() {
        long result = instance.count();
        
        assertEquals(repository.count(), result);
    }
    
    /**
     * @Description: Existed page
     * @Dependency: There are 5 records
     * @Expected Result: Success
     */
    @Test
    public void FT_US_7_02() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page<User> result = instance.findAll(pageable);
        
        assertEquals(repository.findAll(pageable), result);
    }
    
    /**
     * @Description: Out range page
     * @Dependency: There are 5 records
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_7_03() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page<User> result = instance.findAll(pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_8_01() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(null, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty list
     * @Dependency: Out of range
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_8_02() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(EMPTY_LIST_ATTRIBUTE, pageable);
        
        assertEquals(true, result.getTotalPages() < OUT_RANGE_PAGE);
    }
    
    /**
     * @Description: Empty list
     * @Dependency: Valid range
     * @Expected Result: Success
     */
    @Test
    public void FT_US_8_03() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(EMPTY_LIST_ATTRIBUTE, pageable);
        
        assertEquals(true, result != null);
    }
    
    /**
     * @Description: Null page
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_8_04() {
        Page result = instance.findAllByAttribute(EMPTY_LIST_ATTRIBUTE, null);
        
        testFail(result);
    }
    
    /**
     * @Description: Not existed attributes
     * @Dependency: Out of range
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_8_05() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(getNotExistedListAttribute(), pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Not existed attributes
     * @Dependency: Valid range
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_8_06() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(getNotExistedListAttribute(), pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_9_01() {
        long result = instance.countByAttribute(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty list
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_US_9_02() {
        long result = instance.countByAttribute(EMPTY_LIST_ATTRIBUTE);
        
        assertEquals(true, result != -1);
    }
    
    /**
     * @Description: Not existed attributes
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_US_9_03() {
        long result = instance.countByAttribute(getNotExistedListAttribute());
        
        assertEquals(true, result == -1);
    }
}
