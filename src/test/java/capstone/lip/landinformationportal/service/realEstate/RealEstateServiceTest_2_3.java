/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import capstone.lip.landinformationportal.common.entity.RealEstate;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateServiceTest_2_3 extends AbstractRealEstateServiceTest {
    
    /**
     * @Description: Delete negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_2_01() {
        boolean result = instance.delete(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete real estate ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_2_02() {
        boolean result = instance.delete(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive real estate ID
     * @Dependency: Real estate ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_2_03() {
        boolean result = instance.delete(EXISTED_ID);
        
        testDeleteSuccess(result, EXISTED_ID);
    }
    
    /**
     * @Description: Delete positive real estate ID
     * @Dependency: Real estate ID not is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_2_04() {
        boolean result = instance.delete(NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_3_01() {
        RealEstate result = instance.findById(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find real estate ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_3_02() {
        RealEstate result = instance.findById(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive real estate ID
     * @Dependency: Real estate ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_3_03() {
        RealEstate result = instance.findById(EXISTED_ID);
        
        testFindSuccess(EXISTED_ID, result);
    }
    
    /**
     * @Description: Find positive real estate ID
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_3_04() {
        RealEstate result = instance.findById(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
}
