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
public class ReportServiceTest_2 extends AbstractReportServiceTest {
    
    /**
     * @Description: Save without user ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_01() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(NULL_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_02() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NULL_ID)
                .setUserId(EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative user ID and negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_03() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NEGATIVE_NOT_EXISTED_ID)
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative user ID and positive real estate ID
     * @Dependency: real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_04() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(POSITIVE_NOT_EXISTED_ID)
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative user ID and positive real estate ID
     * @Dependency: real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_05() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive user ID and negative real estate ID
     * @Dependency: user ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_06() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NEGATIVE_NOT_EXISTED_ID)
                .setUserId(POSITIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive user ID and negative real estate ID
     * @Dependency: user ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_07() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NEGATIVE_NOT_EXISTED_ID)
                .setUserId(EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive user ID and positive real estate ID
     * @Dependency: user ID and real estate ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_RS_2_08() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(EXISTED_ID));
        boolean result = instance.delete(input);
        
        testDeleteSuccess(result, input);
    }
    
    /**
     * @Description: Delete positive user ID and positive real estate ID
     * @Dependency: user ID and real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_09() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NOT_EXISTED_ID)
                .setUserId(NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive user ID and positive real estate ID
     * @Dependency: user ID is existed and real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_10() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NOT_EXISTED_ID)
                .setUserId(EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive user ID and positive real estate ID
     * @Dependency: user ID is not existed and real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_11() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete user ID and real estate ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_12() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(ZERO_NOT_EXISTED_ID)
                .setUserId(ZERO_NOT_EXISTED_ID));
        boolean result = instance.delete(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_2_13() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
}
