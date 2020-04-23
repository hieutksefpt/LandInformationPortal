/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateServiceTest_4_5 extends AbstractRealEstateServiceTest {
    
    /**
     * @Description: Get by negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_4_01() {
        Land result = instance.getLand(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Get by real estate ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_4_02() {
        Land result = instance.getLand(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Get by positive real estate ID
     * @Dependency: Real estate ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_4_03() {
        Land result = instance.getLand(EXISTED_ID);
        
        testGetLandSuccess(EXISTED_ID, result);
    }
    
    /**
     * @Description: Get by positive real estate ID
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_4_04() {
        Land result = instance.getLand(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_5_01() {
        List<House> result = instance.getListHouse(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find real estate ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_5_02() {
        List<House> result = instance.getListHouse(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive real estate ID
     * @Dependency: Real estate ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_5_03() {
        List<House> result = instance.getListHouse(EXISTED_ID);
        
        testGetListHouseSuccess(EXISTED_ID, result);
    }
    
    /**
     * @Description: Find positive real estate ID
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_5_04() {
        List<House> result = instance.getListHouse(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
}
