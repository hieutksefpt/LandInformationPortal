/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.report;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.Report;
import capstone.lip.landinformationportal.common.entity.compositekey.ReportId;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class ReportServiceTest_3 extends AbstractReportServiceTest {
 
    /**
     * @Description: Find positive user ID and real estate ID
     * @Dependency: User ID and Real Estate ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_RS_3_01() {
        long userId = EXISTED_ID;
        long realEstateId = EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFindSuccess(result, userId, realEstateId);
    }
    
    /**
     * @Description: Find positive user ID and real estate ID
     * @Dependency: User ID and Real Estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_02() {
        long userId = POSITIVE_NOT_EXISTED_ID;
        long realEstateId = POSITIVE_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive user ID and real estate ID
     * @Dependency: User ID is existed and Real Estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_03() {
        long userId = EXISTED_ID;
        long realEstateId = POSITIVE_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive user ID and real estate ID
     * @Dependency: User ID is not existed and Real Estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_04() {
        long userId = POSITIVE_NOT_EXISTED_ID;
        long realEstateId = EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find negative user ID and positive real estate ID
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_05() {
        long userId = NEGATIVE_NOT_EXISTED_ID;
        long realEstateId = POSITIVE_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find negative user ID and positive real estate ID
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_06() {
        long userId = NEGATIVE_NOT_EXISTED_ID;
        long realEstateId = EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive user ID and negative real estate ID
     * @Dependency: User ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_07() {
        long userId = POSITIVE_NOT_EXISTED_ID;
        long realEstateId = NEGATIVE_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find positive user ID and negative real estate ID
     * @Dependency: User ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_08() {
        long userId = EXISTED_ID;
        long realEstateId = NEGATIVE_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find negative user ID and negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_09() {
        long userId = NEGATIVE_NOT_EXISTED_ID;
        long realEstateId = NEGATIVE_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
    
    /**
     * @Description: Find user ID and negative ID equals zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_3_10() {
        long userId = ZERO_NOT_EXISTED_ID;
        long realEstateId = ZERO_NOT_EXISTED_ID;
        Report result = instance.findById(userId, realEstateId);
        
        testFail(result);
    }
}
