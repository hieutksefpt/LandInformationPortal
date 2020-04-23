/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlNews;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class CrawledNewsServiceTest_2_3_4_5_6 extends AbstractCrawledNewsServiceTest {
    
    /**
     * @Description: Init job
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_2_01() {
        assertEquals(true,instance.initCrawlJob() != null); 
    }
    
    /**
     * @Description: Negative value
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_3_01() {
        assertEquals(false,instance.setTimeCrawlJob(-1)); 
    }
    
    /**
     * @Description: Zero value
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CNS_3_02() {
        assertEquals(false,instance.setTimeCrawlJob(0)); 
    }
    
    /**
     * @Description: Positive value
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_3_03() {
        assertEquals(true,instance.setTimeCrawlJob(1)); 
    }
    
    /**
     * @Description: Turn on
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_4_01() {
        assertEquals(true,instance.turnOnCrawler()); 
    }
    
    /**
     * @Description: Turn off
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_5_01() {
        assertEquals(true,instance.turnOffCrawler()); 
    }
    
    /**
     * @Description: Start crawl
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_CNS_6_01() {
        assertEquals(true,instance.crawlNow()); 
    }
}
