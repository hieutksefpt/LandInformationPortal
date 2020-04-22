/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlRealEstateService;

import capstone.lip.landinformationportal.common.entity.RealEstate;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Admin
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class CrawlRealEstateServiceTest_2 extends AbstractCrawlRealEstateServiceTest {
    
    /**
     * @Description: Init job
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CRES_2_01() {
        assertEquals(true,instance.initCrawlJob() != null); 
    }
    
    /**
     * @Description: Negative value
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_3_01() {
        assertEquals(false,instance.setTimeCrawlJob(NEGATIVE_TIME_CRAWL_JOB)); 
    }
    
    /**
     * @Description: Zero value
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_3_02() {
        assertEquals(false,instance.setTimeCrawlJob(ZERO_TIME_CRAWL_JOB)); 
    }
    
    /**
     * @Description: Positive value
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CRES_3_03() {
        assertEquals(true,instance.setTimeCrawlJob(POSITIVE_TIME_CRAWL_JOB)); 
    }
    
    
    /**
     * @Description: Turn on
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CRES_4_01() {
        assertEquals(true,instance.turnOnCrawler()); 
    }
    
    /**
     * @Description: Turn off
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CRES_5_01() {
        assertEquals(true,instance.turnOffCrawler()); 
    }
    
    /**
     * @Description: Start crawl
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CRES_6_01() {
        assertEquals(true,instance.crawlNow()); 
    }
}
