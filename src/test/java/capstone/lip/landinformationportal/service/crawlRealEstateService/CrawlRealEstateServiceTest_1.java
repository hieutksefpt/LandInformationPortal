/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.crawlRealEstateService;

import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.RealEstate;
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
        List<RealEstate> result = null;
        testFail(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_CRES_1_02() {
        RealEstate test = new RealEstate();
        test.setRealEstateId(EXISTED_ID);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testUpdateSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate ID is positive but not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_03() {
        RealEstate test = new RealEstate();
        test.setRealEstateId(POSITIVE_NOT_EXISTED_ID);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate ID is negative
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_04() {
        RealEstate test = new RealEstate();
        test.setRealEstateId(NEGATIVE_NOT_EXISTED_ID);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate ID equals zero
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_05() {
        RealEstate test = new RealEstate();
        test.setRealEstateId(ZERO_NOT_EXISTED_ID);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate ID is null
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_06() {
        RealEstate test = new RealEstate();
        test.setRealEstateId(NULL_NOT_EXISTED_ID);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains a house which its ID is existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_07() {
        RealEstate test = new RealEstate();
        List<House> listHouse = new ArrayList<>();
        House house = new House();
        house.setHouseId(EXISTED_ID);
        listHouse.add(house);
        test.setListHouse(listHouse);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testFail(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains a house which its ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_08() {
        RealEstate test = new RealEstate();
        List<House> listHouse = new ArrayList<>();
        House house = new House();
        house.setHouseId(NOT_EXISTED_ID);
        listHouse.add(house);
        test.setListHouse(listHouse);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testFail(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains a land which its ID is existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_09() {
        RealEstate test = new RealEstate();
        Land land = new Land();
        land.setLandId(EXISTED_ID);
        test.setLand(land);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testFail(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains a land which its ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_10() {
        RealEstate test = new RealEstate();
        Land land = new Land();
        land.setLandId(NOT_EXISTED_ID);
        test.setLand(land);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains no house
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_11() {
        RealEstate test = new RealEstate();
        List<House> listHouse = new ArrayList<>();
        test.setListHouse(listHouse);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains no land
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_12() {
        RealEstate test = new RealEstate();
        Land land = new Land();
        test.setLand(land);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate contains no house and land
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_13() {
        RealEstate test = new RealEstate();
        List<House> listHouse = new ArrayList<>();
        test.setListHouse(listHouse);
        Land land = new Land();
        test.setLand(land);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate has negative price
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_14() {
        RealEstate test = new RealEstate();
        test.setRealEstatePrice(NEGATIVE_PRICE);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testFail(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate has price which equals zero
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_15() {
        RealEstate test = new RealEstate();
        test.setRealEstatePrice(ZERO_PRICE);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testInsertSuccess(result);
    }

    /**
     * @Description: Save list contain 1 record
     * @Dependency: Real estate has null price
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_16() {
        RealEstate test = new RealEstate();
        test.setRealEstatePrice(null);
        List<RealEstate> result = new ArrayList<>();
        result.add(test);
        testFail(result);
    }

    /**
     * @Description: Save list contain 3 record
     * @Dependency: All real estate is existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_17() {
        List<RealEstate> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstate test = new RealEstate();
            test.setRealEstateId(EXISTED_IDs[i]);
            result.add(test);
        }
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save list contain 3 record
     * @Dependency: All real estate is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_18() {
        List<RealEstate> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RealEstate test = new RealEstate();
            test.setRealEstateId(NOT_EXISTED_ID);
            result.add(test);
        }
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save list contain 3 record
     * @Dependency: There is a null real estate
     * @Expected Result: Insert success
     */
    @Test
    public void FT_CRES_1_19() {
        List<RealEstate> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            RealEstate test = new RealEstate();
            test.setRealEstateId(NOT_EXISTED_ID);
            result.add(test);
        }
        RealEstate test = null;
        result.add(test);
        testFail(result);
    }
}
