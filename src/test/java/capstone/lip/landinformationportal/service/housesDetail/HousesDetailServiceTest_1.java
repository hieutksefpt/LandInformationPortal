/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.housesDetail;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.HousesDetail;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class HousesDetailServiceTest_1 extends AbstractHousesDetailServiceTest {
    
    /**
     * @Description: Save null house's detail ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_01() {
        HousesDetail result = instance.save(sampleHousesDetail
                .setId(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative house ID and negative house feature ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_02() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                NEGATIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative house ID and positive house feature ID
     * @Dependency: House feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_03() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                NEGATIVE_NOT_EXISTED_ID, POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative house ID and positive house feature ID
     * @Dependency: House feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_04() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                NEGATIVE_NOT_EXISTED_ID, EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive house ID and negative house feature ID
     * @Dependency: House feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_05() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                POSITIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive house ID and negative house feature ID
     * @Dependency: House feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_06() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive house ID and positive house feature ID
     * @Dependency: house ID and house feature ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HDS_1_07() {
        long records = repository.count();
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                EXISTED_ID, EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save positive house ID and positive house feature ID
     * @Dependency: house ID and house feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_08() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                NOT_EXISTED_ID, NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive house ID and positive house feature ID
     * @Dependency: house ID is existed and house feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_09() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                EXISTED_ID, NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive house ID and positive house feature ID
     * @Dependency: house ID is not existed and house feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_10() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                NOT_EXISTED_ID, EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save house ID and house feature ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_11() {
        HousesDetail result = instance.save(setHousesDetailID(sampleHousesDetail, 
                ZERO_NOT_EXISTED_ID, ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Save null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_1_12() {
        HousesDetail result = instance.save(null);
        
        testFail(result);
    }
}
