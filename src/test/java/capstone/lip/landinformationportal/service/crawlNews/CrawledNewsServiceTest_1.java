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
public class CrawledNewsServiceTest_1 extends AbstractCrawledNewsServiceTest {
    
    
    /**
     * @Description: Save null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_01() {
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(null);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_CNS_1_02() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(EXISTED_ID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News ID is positive but not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_03() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(POSITIVE_NOT_EXISTED_ID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News ID is negative
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_04() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NEGATIVE_NOT_EXISTED_ID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News ID equals zero
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_05() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(ZERO_NOT_EXISTED_ID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News ID is null
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_06() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NULL_ID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains numeric Vietnamese title
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_07() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTitle(NUMERIC_VIETNAMESE_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains special character title
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_08() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTitle(SPECIAL_CHARACTER_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains no title
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_09() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTitle(NULL_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contain current time
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_10() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTime(TIME_CURRENT));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contain future time
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_11() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTime(TIME_FUTURE));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains past time
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_12() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTime(TIME_PAST));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains no time
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_13() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsTime(null));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contain link
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_14() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsLink(DEFAULT_LINK));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contain no link
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_15() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsLink(DEFAULT_LINK));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains alphabetic website source
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_16() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsWebsite(ALPHABETIC_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains special character website source
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_17() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsWebsite(ALL_SPACE_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains no website
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_18() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsWebsite(NULL_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains image link
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_19() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsImageUrl(DEFAULT_LINK));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains no image link
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_20() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsImageUrl(NULL_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains valid status
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_21() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsStatus(STATUS_INVALID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains invalid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_22() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsStatus(STATUS_INVALID));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains no status
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_23() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsStatus(NULL_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains numeric Vietnamese description
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_24() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsShortDescription(NUMERIC_VIETNAMESE_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains special character description
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_25() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsShortDescription(SPECIAL_CHARACTER_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 1 record
     * @Dependency: News contains no description
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_26() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID)
                .setCrawledNewsShortDescription(NULL_STRING));
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 3 records
     * @Dependency: All news is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_CNS_1_27() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            input.add(sampleCrawledNews
                .setCrawledNewsID(EXISTED_IDs[i]));
        }
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save list contain 3 records
     * @Dependency: All news is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CNS_1_28() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID));
        }
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 3 records
     * @Dependency: There is a null news
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_1_29() {
        //TEST DATA
        List<CrawledNews> input = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            input.add(sampleCrawledNews
                .setCrawledNewsID(NOT_EXISTED_ID));
        }
        input.set(input.size()-1, null);
        
        //TEST METHOD
        List<CrawledNews> result = instance.saveAll(input);
        
        //TEST RESULT
        testFail(result);
    }
}