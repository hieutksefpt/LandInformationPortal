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
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
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
    protected final String LINK_STRING = "https://batdongsan.com.vn/";
    protected final String SOURCE_STRING = "batdongsan.com/";
    protected final int NEGATIVE_TIME_CRAWL_JOB = -10;
    protected final int ZERO_TIME_CRAWL_JOB = 0;
    protected final int POSITIVE_TIME_CRAWL_JOB = 10;
    
    protected final long TOTAL_REALESTATE = 5L;

    protected RealEstate sampleRealEstate = new RealEstate()
            .setRealEstateId(DEFAULT_ID)
            .setRealEstateName("SAMPLE REALESTATE")
            .setRealEstateLat(DEFAULT_LAT)
            .setRealEstateLng(DEFAULT_LNG)
            .setRealEstateAddress(ADDRESS_STRING)
            .setRealEstatePrice(DEFAULT_PRICE)
            .setRealEstateStatus(STATUS_STRING)
            .setRealEstateLink(LINK_STRING)
            .setRealEstateSource(SOURCE_STRING)
            .setUser(new User()
                    .setUserId(EXISTED_ID)
                    .setEmail(DEFAULT_EMAIL));

    protected void testInsertSuccess(List<RealEstate> result) {
        for (RealEstate re : result) {
            if (result != null) {
                //Test exist in DB
                Optional<RealEstate> actual = repository.findById(re.getRealEstateId());
                if (actual.isPresent()) {
                    //Compare others
                    assertEquals(true, actual.get().equals(result));
                    //Test number of records is not changed
                    assertEquals(TOTAL_REALESTATE + result.size(), repository.count());
                } else {
                    fail();
                }
            } else {
                fail();
            }
        }
        //Insert success

    }

    protected void testUpdateSuccess(List<RealEstate> result) {
        for (RealEstate re : result) {
            //Update success
            if (result != null) {
                //Test exist in DB
                Optional<RealEstate> actual = repository.findById(re.getRealEstateId());
                if (actual.isPresent()) {
                    //Compare others
                    assertEquals(true, actual.get().equals(result));
                    //Test number of records is not changed
                    assertEquals(TOTAL_REALESTATE, repository.count());
                } else {
                    fail();
                }
            } else {
                fail();
            }
        }
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
