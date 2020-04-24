/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlNews;

import capstone.lip.landinformationportal.common.entity.CrawledNews;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class CrawledNewsServiceTest_7_8 extends AbstractCrawledNewsServiceTest {
    
    /**
     * @Description: Delete with negative id
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_7_01() {
        //TEST METHOD
        boolean result = instance.delete(sampleCrawledNews
            .setCrawledNewsID(NEGATIVE_NOT_EXISTED_ID));
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete with positive id
     * @Dependency: ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_7_02() {
        //TEST METHOD
        boolean result = instance.delete(sampleCrawledNews
            .setCrawledNewsID(EXISTED_ID));
        
        //TEST RESULT
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete with positive id
     * @Dependency: ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_7_03() {
        //TEST METHOD
        boolean result = instance.delete(sampleCrawledNews
            .setCrawledNewsID(NOT_EXISTED_ID));
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete with ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_7_04() {
        //TEST METHOD
        boolean result = instance.delete(sampleCrawledNews
            .setCrawledNewsID(ZERO_NOT_EXISTED_ID));
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete with null ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_7_05() {
        //TEST METHOD
        boolean result = instance.delete(sampleCrawledNews
            .setCrawledNewsID(NULL_NOT_EXISTED_ID));
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_7_06() {
        //TEST DATA
        CrawledNews input = null;
        
        //TEST METHOD
        boolean result = instance.delete(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_8_01() {
        //TEST DATA
        List<CrawledNews> input = null;
        
        //TEST METHOD
        boolean result = instance.delete(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete empty list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_8_02() {
        //TEST METHOD
        boolean result = instance.delete(EMPTY_LIST);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete list had 1 news
     * @Dependency: ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_8_03() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID));
        
        //TEST METHOD
        boolean result = instance.delete(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete list had 1 news
     * @Dependency: ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_8_04() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(EXISTED_ID));
        
        //TEST METHOD
        boolean result = instance.delete(input);
        
        //TEST RESULT
        testDeleteSuccess(result, input);
    }
    
        /**
     * @Description: Delete list had 3 news
     * @Dependency: All IDs are not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_8_05() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID));
        }
        
        //TEST METHOD
        boolean result = instance.delete(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Delete list had 3 news
     * @Dependency: All IDs are existed
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_8_06() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            input.add(repository.findById(EXISTED_IDs[i]).get());
        }
        
        //TEST METHOD
        boolean result = instance.delete(input);
        
        //TEST RESULT
        testDeleteSuccess(result, input);
    }
}
