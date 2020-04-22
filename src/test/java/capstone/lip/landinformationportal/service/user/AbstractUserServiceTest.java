/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.user;

import capstone.lip.landinformationportal.business.repository.UserRepository;
import capstone.lip.landinformationportal.business.service.UserService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractUserServiceTest extends CRUDTest {
    
    protected final String ROLE_ADMIN = "ADMIN";
    protected final String ROLE_CONTRIBUTOR = "USER";
    
    protected final String INVALID_FIELD = "INVALID";
    protected final String STATUS_ACTIVE = "ACTIVE";
    protected final String STATUS_BAN = "BAN";
    
    protected final long TOTAL_RECORD = 5L;
    
    @Autowired
    protected UserService instance;
    
    @Autowired
    protected UserRepository repository;
    
    protected User sampleUser = new User()
            .setUserId(DEFAULT_ID)
            .setUsername("SAMPLEUSER").setPassword("PASSWORD")
            .setFullName("SAMPLE USER").setEmail(DEFAULT_EMAIL)
            .setRole(ROLE_ADMIN).setUserStatus(STATUS_ACTIVE);
    
    
    protected void testInsertSuccess(User result) {
        //Update success
        if (result != null) {
            User actual = repository.findById(result.getUserId()).get();
            //Compare others
            assertEquals(true, actual.equals(result));
            //Test number of records is not changed
            assertEquals(TOTAL_RECORD + 1, repository.count());
        } else {
            fail();
        }
    }
    
    protected void testUpdateSuccess(User result) {
        //Update success
        if (result != null) {
            User actual = repository.findById(result.getUserId()).get();
            //Compare others
            assertEquals(true, actual.equals(result));
            //Test number of records is not changed
            assertEquals(TOTAL_RECORD, repository.count());
        } else {
            fail();
        }
    }

    protected void testDeleteSuccess(boolean result, User detail) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(false, repository.findById(detail.getUserId()).isPresent());
            //Test number of records is decreased by 1
            assertEquals(TOTAL_RECORD - 1, repository.count());
        } else {
            fail();
        }
    }
    
    protected void testFindSuccess(User result) {
        assertEquals(repository.findById(result.getUserId()), result);
    }
}
