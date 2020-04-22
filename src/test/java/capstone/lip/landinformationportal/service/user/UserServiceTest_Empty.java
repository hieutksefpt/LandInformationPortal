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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class UserServiceTest_Empty extends AbstractUserServiceTest {
    
    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     * @Expected Result: List size = 0
     */
    @Test
    public void FT_US_1_01() {
        List<User> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
    
    /**
     * @Description: Empty data
     * @Dependency: There are 0 record
     * @Expected Result: Return 0
     */
    @Test
    public void FT_US_6_01() {
        long result = instance.count();
        
        assertEquals(repository.count(), result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: There are 0 record
     * @Expected Result: Fail
     */
    @Test
    public void FT_US_7_01() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page<User> result = instance.findAll(pageable);
        
        testFail(result);
    }
}
