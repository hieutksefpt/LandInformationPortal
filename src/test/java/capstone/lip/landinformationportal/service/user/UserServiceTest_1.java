/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.user;

import capstone.lip.landinformationportal.entity.User;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class UserServiceTest_1 extends AbstractUserServiceTest {
    
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
}
