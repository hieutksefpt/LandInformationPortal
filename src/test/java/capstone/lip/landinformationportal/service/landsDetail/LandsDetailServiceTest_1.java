/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.landsDetail;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.LandsDetail;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class LandsDetailServiceTest_1 extends AbstractLandsDetailServiceTest {
    
    /**
     * @Description: Save null land's detail ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_01() {
        LandsDetail result = instance.save(sampleLandsDetail
                .setId(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative land ID and negative land feature ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_02() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                NEGATIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative land ID and positive land feature ID
     * @Dependency: Land feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_03() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                NEGATIVE_NOT_EXISTED_ID, POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative land ID and positive land feature ID
     * @Dependency: Land feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_04() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                NEGATIVE_NOT_EXISTED_ID, EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive land ID and negative land feature ID
     * @Dependency: Land feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_05() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                POSITIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive land ID and negative land feature ID
     * @Dependency: Land feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_06() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive land ID and positive land feature ID
     * @Dependency: land ID and land feature ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LDS_1_07() {
        long records = repository.count();
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                EXISTED_ID, EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save positive land ID and positive land feature ID
     * @Dependency: land ID and land feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_08() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                NOT_EXISTED_ID, NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive land ID and positive land feature ID
     * @Dependency: land ID is existed and land feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_09() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                EXISTED_ID, NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive land ID and positive land feature ID
     * @Dependency: land ID is not existed and land feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_10() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                NOT_EXISTED_ID, EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save land ID and land feature ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_11() {
        LandsDetail result = instance.save(setLandsDetailID(sampleLandsDetail, 
                ZERO_NOT_EXISTED_ID, ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_1_12() {
        LandsDetail result = instance.save(null);
        
        testFail(result);
    }
}
