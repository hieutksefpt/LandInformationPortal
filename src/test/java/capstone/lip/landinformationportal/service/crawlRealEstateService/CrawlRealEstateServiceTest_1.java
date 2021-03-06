/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlRealEstateService;

import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Admin
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class CrawlRealEstateServiceTest_1 extends AbstractCrawlRealEstateServiceTest {

    /**
     * @Description: Save null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_01() {
        boolean result = instance.saveRealEstateCrawl(null);
        testFail(result);
    }

    /**
     * @Description: Save empty title
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_02() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(EMPTY_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save empty title
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_03() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(EMPTY_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save null title
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_04() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NULL_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save null title
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_05() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NULL_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save alphanumeric title
     * @Dependency: List had 1 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_06() {
 
        System.out.println(repository.count());
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(ALPHABETIC_NUMERIC_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setLatitude(DEFAULT_LAT);
        reoCrawl.setLongitude(DEFAULT_LNG);
        reoCrawl.setPrice(DEFAULT_PRICE);
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save alphanumeric title
     * @Dependency: List had 3 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_07() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(ALPHABETIC_NUMERIC_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setLatitude(DEFAULT_LAT);
            reoCrawl.setLongitude(DEFAULT_LNG);
            reoCrawl.setPrice(DEFAULT_PRICE);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save numeric Vietnamese title
     * @Dependency: List had 1 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_08() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setLatitude(DEFAULT_LAT);
        reoCrawl.setLongitude(DEFAULT_LNG);
        reoCrawl.setPrice(DEFAULT_PRICE);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        reoCrawl.setSource(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save numeric Vietnamese title
     * @Dependency: List had 3 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_09() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setLatitude(DEFAULT_LAT);
            reoCrawl.setLongitude(DEFAULT_LNG);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setPrice(DEFAULT_PRICE);
            reoCrawl.setLink(LINK_STRING + i);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save all space title
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_10() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(ALL_SPACE_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save all space title
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_11() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(ALL_SPACE_STRING);
            reoCrawl.setLatitude(DEFAULT_LAT);
            reoCrawl.setLongitude(DEFAULT_LNG);
            reoCrawl.setPrice(DEFAULT_PRICE);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save negative lat & lng
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_12() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setLatitude(NEGATIVE_LATLONG);
        reoCrawl.setLongitude(NEGATIVE_LATLONG);
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save negative lat & lng
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_13() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setLatitude(NEGATIVE_LATLONG);
            reoCrawl.setLongitude(NEGATIVE_LATLONG);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save lat & lng equals zero
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_14() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setLatitude(ZERO_DOUBLE);
        reoCrawl.setLongitude(ZERO_DOUBLE);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        reoCrawl.setSource(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save lat & lng equals zero
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_15() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setLatitude(ZERO_DOUBLE);
            reoCrawl.setLongitude(ZERO_DOUBLE);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save positive lat & lng
     * @Dependency: List had 1 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_16() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setLatitude(DEFAULT_DOUBLE);
        reoCrawl.setLongitude(DEFAULT_DOUBLE);
        reoCrawl.setPrice(DEFAULT_PRICE);
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save positive lat & lng
     * @Dependency: List had 3 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_17() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setLatitude(DEFAULT_DOUBLE);
            reoCrawl.setLongitude(DEFAULT_DOUBLE);
            reoCrawl.setPrice(DEFAULT_PRICE);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save negative price
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_18() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setPrice(NEGATIVE_PRICE);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save negative price
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_19() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setPrice(NEGATIVE_PRICE);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setSource(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save price equals zero
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_20() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setPrice(ZERO_PRICE);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save price equals zero
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_21() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setPrice(ZERO_PRICE);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setSource(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save positive price
     * @Dependency: List had 1 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_22() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setPrice(DEFAULT_BIGDECIMAL);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setLatitude(DEFAULT_LAT);
        reoCrawl.setLongitude(DEFAULT_LNG);
        reoCrawl.setSource(ADDRESS_STRING);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save positive price
     * @Dependency: List had 3 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_23() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setPrice(DEFAULT_BIGDECIMAL);
            reoCrawl.setLatitude(DEFAULT_LAT);
            reoCrawl.setLongitude(DEFAULT_LNG);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save CONTRIBUTOR source
     * @Dependency: List had 1 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_24() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setSource(CONTRIBUTOR_SOURCE);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save CONTRIBUTOR source
     * @Dependency: List had 3 real estate
     * @Expected Result: Fail
     */
    @Test
    public void FT_CRES_1_25() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setSource(CONTRIBUTOR_SOURCE);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testFail(result);
    }

    /**
     * @Description: Save special character link
     * @Dependency: List had 1 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_26() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setLink(LINK_STRING + "1");
        reoCrawl.setLatitude(DEFAULT_LAT);
        reoCrawl.setLongitude(DEFAULT_LNG);
        reoCrawl.setPrice(DEFAULT_PRICE);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        reoCrawl.setSource(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save special character link
     * @Dependency: List had 3 real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_27() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setLink(LINK_STRING + i);
            reoCrawl.setLatitude(DEFAULT_LAT);
            reoCrawl.setLongitude(DEFAULT_LNG);
            reoCrawl.setPrice(DEFAULT_PRICE);
            reoCrawl.setSource(ADDRESS_STRING);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save Vietnamese link
     * @Dependency: List had 1 real estate
     * @Expected Result: Insert Success
     */
    @Test
    public void FT_CRES_1_28() {
        RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
        reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
        reoCrawl.setLink(VIETNAMESE_STRING);
        reoCrawl.setLatitude(DEFAULT_LAT);
        reoCrawl.setLongitude(DEFAULT_LNG);
        reoCrawl.setPrice(DEFAULT_PRICE);
        reoCrawl.setDomain(ADDRESS_STRING);
        reoCrawl.setAddress(ADDRESS_STRING);
        reoCrawl.setSource(ADDRESS_STRING);
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        testReoCrawl.add(reoCrawl);
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

    /**
     * @Description: Save Vietnamese link
     * @Dependency: List had 3 real estate
     * @Expected Result: Insert Success
     */
    @Test
    public void FT_CRES_1_29() {
        List<RealEstateObjectCrawl> testReoCrawl = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstateObjectCrawl reoCrawl = new RealEstateObjectCrawl();
            reoCrawl.setTitle(NUMERIC_VIETNAMESE_STRING);
            reoCrawl.setLink(VIETNAMESE_STRING + i);
            reoCrawl.setLatitude(DEFAULT_LAT);
            reoCrawl.setLongitude(DEFAULT_LNG);
            reoCrawl.setPrice(DEFAULT_PRICE);
            reoCrawl.setDomain(ADDRESS_STRING);
            reoCrawl.setAddress(ADDRESS_STRING);
            reoCrawl.setSource(ADDRESS_STRING);
            testReoCrawl.add(reoCrawl);
        }
        boolean result = instance.saveRealEstateCrawl(testReoCrawl);
        testInsertSuccess(testReoCrawl, result);
    }

}
