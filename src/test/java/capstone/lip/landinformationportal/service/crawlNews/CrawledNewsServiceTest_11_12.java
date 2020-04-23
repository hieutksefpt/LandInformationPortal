/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlNews;

import capstone.lip.landinformationportal.common.entity.CrawledNews;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class CrawledNewsServiceTest_11_12 extends AbstractCrawledNewsServiceTest {
 
    /**
     * @Description: Empty status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_11_01() {
        //TEST DATA
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        
        //TEST METHOD
        Page<CrawledNews> result = instance
                .findByCrawledNewsStatus(EMPTY_STRING, pageable);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Null status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_11_02() {
        //TEST DATA
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        
        //TEST METHOD
        Page<CrawledNews> result = instance
                .findByCrawledNewsStatus(NULL_STRING, pageable);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Invalid status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_11_03() {
        //TEST DATA
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        
        //TEST METHOD
        Page<CrawledNews> result = instance
                .findByCrawledNewsStatus(STATUS_INVALID, pageable);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Valid status
     * @Dependency: Existed page
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_11_04() {
        //TEST DATA
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        
        //TEST METHOD
        Page<CrawledNews> result = instance
                .findByCrawledNewsStatus(STATUS_VALID_EXISTED, pageable);
        
        //TEST RESULT
        assertEquals(repository.findByCrawledNewsStatus(STATUS_VALID_EXISTED, pageable), result);
    }
    
    /**
     * @Description: Valid status
     * @Dependency: Out range page
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_11_05() {
        //TEST DATA
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        
        //TEST METHOD
        Page<CrawledNews> result = instance
                .findByCrawledNewsStatus(STATUS_INVALID, pageable);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Empty link
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_12_01() {
        //TEST METHOD
        CrawledNews result = instance.findByCrawledNewsLink(EMPTY_STRING);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Null link
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_12_02() {
        //TEST METHOD
        CrawledNews result = instance.findByCrawledNewsLink(NULL_STRING);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Valid status
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_12_03() {
        //TEST METHOD
        CrawledNews result = instance.findByCrawledNewsLink(EXISTED_LINK);
        
        //TEST RESULT
        assertEquals(repository.findByCrawledNewsLink(EXISTED_LINK), result);
    }
    
    /**
     * @Description: Not existed link
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_12_04() {
        //TEST METHOD
        CrawledNews result = instance.findByCrawledNewsLink(DEFAULT_LINK);
        
        //TEST RESULT
        testFail(result);
    }
}