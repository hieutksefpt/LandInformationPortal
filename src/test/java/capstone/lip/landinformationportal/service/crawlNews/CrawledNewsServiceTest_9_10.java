/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlNews;

import capstone.lip.landinformationportal.common.entity.CrawledNews;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class CrawledNewsServiceTest_9_10 extends AbstractCrawledNewsServiceTest {
    
    /**
     * @Description: Empty status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_9_01() {
        //TEST METHOD
        long result = instance.countByStatus(EMPTY_STRING);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Null status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_9_02() {
        //TEST METHOD
        long result = instance.countByStatus(NULL_STRING);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Invalid status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_9_03() {
        //TEST METHOD
        long result = instance.countByStatus(STATUS_INVALID);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Valid status
     * @Dependency: Existed status
     * @Expected Result: Return 5
     */
    @Test
    public void FT_CNS_9_04() {
        //TEST METHOD
        long result = instance.countByStatus(STATUS_VALID_EXISTED);
        
        //TEST RESULT
        assertEquals(5, result);
    }
    
    /**
     * @Description: Valid status
     * @Dependency: Not existed status
     * @Expected Result: Return 0
     */
    @Test
    public void FT_CNS_9_05() {
        //TEST METHOD
        long result = instance.countByStatus(STATUS_VALID_NOT_EXISTED);
        
        //TEST RESULT
        assertEquals(0, result);
    }
    
    /**
     * @Description: Null id
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_10_01() {
        //TEST METHOD
        CrawledNews result = instance.findById(NULL_NOT_EXISTED_ID);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Negative id
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_10_02() {
        //TEST METHOD
        CrawledNews result = instance.findById(NEGATIVE_NOT_EXISTED_ID);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_10_03() {
        //TEST METHOD
        CrawledNews result = instance.findById(ZERO_NOT_EXISTED_ID);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Positive id
     * @Dependency: Existed id
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_10_04() {
        //TEST METHOD
        CrawledNews result = instance.findById(EXISTED_ID);
        
        //TEST RESULT
        assertEquals(repository.findById(EXISTED_ID).get(), result);
    }
    
    /**
     * @Description: Positive id
     * @Dependency: Not existed id
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_10_05() {
        //TEST METHOD
        CrawledNews result = instance.findById(NOT_EXISTED_ID);
        
        //TEST RESULT
        testFail(result);
    }
}
