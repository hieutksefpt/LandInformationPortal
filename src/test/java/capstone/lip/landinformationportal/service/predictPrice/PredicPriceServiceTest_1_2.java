/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.predictPrice;

import capstone.lip.landinformationportal.common.constant.StatusRealEstateConstant;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class PredicPriceServiceTest_1_2 extends AbstractPredictPriceServiceTest {
    
    /**
     * @Description: Predict null real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_1_01() {
        String result = instance.getPredictPrice(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Predict empty real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_1_02() {
        String result = instance.getPredictPrice(new RealEstate());
        
        testFail(result);
    }
    
    /**
     * @Description: Predict normal real estate
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_PPS_1_03() {
        String result = instance.getPredictPrice(sampleRealEstate);
        
        Assert.assertEquals(true, result != null);
    }
    
    /**
     * @Description: Add null real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_2_01() {
        boolean result = instance.addDataToModel(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Add empty real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_2_02() {
        boolean result = instance.addDataToModel(new RealEstate());
        
        testFail(result);
    }
    
    /**
     * @Description: Add empty price real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_2_03() {
        boolean result = instance.addDataToModel(sampleRealEstate
                .setRealEstatePrice(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Add negative price real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_2_04() {
        boolean result = instance.addDataToModel(sampleRealEstate
                .setRealEstatePrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Add equal zero price real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_2_05() {
        boolean result = instance.addDataToModel(sampleRealEstate
                .setRealEstatePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Add not verified real estate
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_PPS_2_06() {
        boolean result = instance.addDataToModel(sampleRealEstate
                .setRealEstateStatus(StatusRealEstateConstant.NOT_VERIFIED));
        
        testFail(result);
    }
    
    /**
     * @Description: Add positive price and verify real estate
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_PPS_2_07() {
        boolean result = instance.addDataToModel(sampleRealEstate
                .setRealEstatePrice(POSITIVE_PRICE));
        
        Assert.assertEquals(true, result);
    }
}
