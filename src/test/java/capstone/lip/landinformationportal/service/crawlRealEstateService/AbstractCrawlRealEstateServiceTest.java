/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlRealEstateService;

import capstone.lip.landinformationportal.business.repository.CrawledNewsRepository;
import capstone.lip.landinformationportal.business.repository.FeedbackRepository;
import capstone.lip.landinformationportal.business.repository.RealEstateRepository;
import capstone.lip.landinformationportal.business.service.CrawlRealEstateService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
public abstract class AbstractCrawlRealEstateServiceTest extends CRUDTest {

    @Autowired
    protected CrawlRealEstateService instance;

    @Autowired
    protected RealEstateRepository repository;

    protected final String ADDRESS_STRING = "HA NOI, VIET NAM";
    protected final String STATUS_STRING = "CONFUSED";
    protected final String LINK_STRING = "http://batdongsan.com.vn/";
    protected final String SOURCE_STRING = "batdongsan.com/";
    protected final int NEGATIVE_TIME_CRAWL_JOB = -10;
    protected final int ZERO_TIME_CRAWL_JOB = 0;
    protected final int POSITIVE_TIME_CRAWL_JOB = 10;
    protected final String DEFAULT_ID_CRAWJOB = "001";
    protected final long DEFAULT_CODE_POST = 1L;
    protected final String DEFAULT_TYPEPOST = "001";
    protected final String DEFAULT_TITLE = "001";
    protected final String DEFAULT_TYPE = "001";
    protected final String DEFAULT_HOMEDIRECTION = "NAM";
    protected final String DEFAULT_BALCONYDIRECTION = "NAM";
    protected final String DEFAULT_STRING = "DEFAULT";
    protected final Timestamp DEFAULT_TIMESTAMP = new Timestamp(System.currentTimeMillis());
    protected final BigDecimal DEFAULT_BIGDECIMAL = DEFAULT_PRICE;
    protected final Double DEFAULT_DOUBLE = 10.0;
    protected final Double NEGATIVE_LATLONG = -10.0;
    protected final Double ZERO_DOUBLE = 0.0;
    protected final String CONTRIBUTOR_SOURCE = "CONTRIBUTOR";
    protected final String INVALID_STATUS = "ABC";
    
    
    
    protected final long TOTAL_REALESTATE = 5L;

    protected RealEstateObjectCrawl getSampleCrawlRealEstate(){
        RealEstateObjectCrawl sampleRealEstateCrawl = new RealEstateObjectCrawl();
        sampleRealEstateCrawl.setIdCrawlerJob(DEFAULT_ID_CRAWJOB);
        sampleRealEstateCrawl.setCodePost(DEFAULT_CODE_POST);
        sampleRealEstateCrawl.setTypePost(DEFAULT_TYPEPOST);
        sampleRealEstateCrawl.setTitle(DEFAULT_TITLE);
        sampleRealEstateCrawl.setPrice(DEFAULT_PRICE);
        sampleRealEstateCrawl.setArea(DEFAULT_BIGDECIMAL);
        sampleRealEstateCrawl.setType(DEFAULT_TITLE);
        sampleRealEstateCrawl.setDate(DEFAULT_TIMESTAMP);
        sampleRealEstateCrawl.setAddress(ADDRESS_STRING);
        sampleRealEstateCrawl.setNumberBedrooms(POSITIVE_TIME_CRAWL_JOB);
        sampleRealEstateCrawl.setNumberToilets(POSITIVE_TIME_CRAWL_JOB);
        sampleRealEstateCrawl.setSizeFront(DEFAULT_DOUBLE);
        sampleRealEstateCrawl.setNumberFloor(POSITIVE_TIME_CRAWL_JOB);
        sampleRealEstateCrawl.setWardin(DEFAULT_DOUBLE);
        sampleRealEstateCrawl.setHomeDirection(DEFAULT_TITLE);
        sampleRealEstateCrawl.setBalconyDirection(DEFAULT_TITLE);
        sampleRealEstateCrawl.setInterior(DEFAULT_TITLE);
        sampleRealEstateCrawl.setLongitude(DEFAULT_DOUBLE);
        sampleRealEstateCrawl.setLatitude(DEFAULT_DOUBLE);
        sampleRealEstateCrawl.setNameOwner(DEFAULT_TITLE);
        sampleRealEstateCrawl.setMobile(DEFAULT_TITLE);
        sampleRealEstateCrawl.setEmail(DEFAULT_TITLE);
        sampleRealEstateCrawl.setLink(DEFAULT_TITLE);
        sampleRealEstateCrawl.setProjectName(DEFAULT_TITLE);
        sampleRealEstateCrawl.setProjectSize(DEFAULT_TITLE);
        sampleRealEstateCrawl.setProjectOwner(DEFAULT_TITLE);
        sampleRealEstateCrawl.setSource(DEFAULT_TITLE);
        sampleRealEstateCrawl.setDomain(DEFAULT_TITLE);
        sampleRealEstateCrawl.setStartDatePost(DEFAULT_TIMESTAMP);
        sampleRealEstateCrawl.setEndDatePost(DEFAULT_TIMESTAMP);
        
        
        return sampleRealEstateCrawl;
    }
    
    protected void testInsertSuccess(List<RealEstateObjectCrawl> result, boolean testResult) {
        assertEquals(true, testResult);
        //Test number of records is not changed
        assertEquals(TOTAL_REALESTATE + result.size(), repository.count());
        

    }

    protected void testUpdateSuccess(List<RealEstate> result, boolean testResult) {
        assertEquals(true, testResult);
        //Test number of records is not changed
        assertEquals(TOTAL_REALESTATE, repository.count());
    }

    protected void testDeleteSuccess(boolean result, long id) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(false, repository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(TOTAL_REALESTATE - 1, repository.count());
        } else {
            fail();
        }
    }

    protected void testFindSuccess(long id, RealEstate crawlRealEstate) {
        assertEquals(repository.findById(id).get(), crawlRealEstate);
    }

}
