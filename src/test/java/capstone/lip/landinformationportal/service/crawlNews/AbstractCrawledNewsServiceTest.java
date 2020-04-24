/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlNews;

import capstone.lip.landinformationportal.business.repository.CrawledNewsRepository;
import capstone.lip.landinformationportal.business.service.CrawledNewsService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.TimePack;
import capstone.lip.landinformationportal.common.constant.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.entity.CrawledNews;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractCrawledNewsServiceTest extends CRUDTest
        implements TimePack {

    protected final String EXISTED_LINK = "https://dantri.com.vn/kinh-doanh/can-ho-ha-noi-dua-giam-gia-ca-tram-trieu-dong-2017120620103522.htm";

    protected final String STATUS_VALID_EXISTED = StatusCrawledNewsConstant.DISPLAY;
    protected final String STATUS_VALID_NOT_EXISTED = StatusCrawledNewsConstant.NON_DISPLAY;
    protected final String STATUS_INVALID = "INVALID";

    @Autowired
    protected CrawledNewsService instance;

    @Autowired
    protected CrawledNewsRepository repository;

    protected CrawledNews sampleCrawledNews = new CrawledNews()
            .setCrawledNewsID(DEFAULT_ID)
            .setCrawledNewsTitle("SAMPLE NEWS")
            .setCrawledNewsLink(DEFAULT_LINK)
            .setCrawledNewsWebsite("BATDONGSAN.COM.VN")
            .setCrawledNewsTime(TIME_CURRENT)
            .setCrawledNewsStatus(STATUS_VALID_EXISTED);

    protected final int TOTAL_RECORDS = 5;

    protected void testInsertSuccess(List<CrawledNews> results) {
        //Insert success
        if (results != null) {
            for (CrawledNews result : results) {
                //Test exist in DB
                Optional<CrawledNews> actual
                        = repository.findById(result.getCrawledNewsID());
                if (actual.isPresent()) {
                    //Compare others
                    assertEquals(true, actual.get().equals(result));
                } else {
                    fail();
                }
            }
            //Test number of records is not changed
            assertEquals(TOTAL_RECORDS + results.size(), repository.count());
        } else {
            fail();
        }
    }

    protected void testUpdateSuccess(List<CrawledNews> results) {
        //Update success
        if (results != null) {
            for (CrawledNews result : results) {
                //Test exist in DB
                Optional<CrawledNews> actual
                        = repository.findById(result.getCrawledNewsID());
                if (actual.isPresent()) {
                    //Compare others
                    assertEquals(true, actual.get().equals(result));
                } else {
                    fail();
                }
            }
            //Test number of records is not changed
            assertEquals(TOTAL_RECORDS, repository.count());
        } else {
            fail();
        }
    }

    protected void testDeleteSuccess(boolean result, List<CrawledNews> inputs) {
        //Delete success
        if (result) {
            for (CrawledNews input : inputs) {
                //Test exist in DB
                assertEquals(false, repository.existsById(input.getCrawledNewsID()));
            }

            //Test number of records is decreased
            assertEquals(TOTAL_RECORDS - inputs.size(), repository.count());
        } else {
            fail();
        }
    }

    protected void testDeleteSuccess(boolean result, long id) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(false, repository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(TOTAL_RECORDS - 1, repository.count());
        } else {
            fail();
        }
    }
}
