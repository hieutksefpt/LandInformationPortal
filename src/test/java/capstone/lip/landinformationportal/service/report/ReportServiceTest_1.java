/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.report;

import capstone.lip.landinformationportal.entity.Report;
import capstone.lip.landinformationportal.entity.compositekey.ReportId;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class ReportServiceTest_1 extends AbstractReportServiceTest {
    
    /**
     * @Description: Save without user ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_01() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(NULL_NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_02() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NULL_NOT_EXISTED_ID)
                .setUserId(EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative user ID and negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_03() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NEGATIVE_NOT_EXISTED_ID)
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative user ID and positive real estate ID
     * @Dependency: real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_04() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(POSITIVE_NOT_EXISTED_ID)
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative user ID and positive real estate ID
     * @Dependency: real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_05() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(NEGATIVE_NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive user ID and negative real estate ID
     * @Dependency: user ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_06() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NEGATIVE_NOT_EXISTED_ID)
                .setUserId(POSITIVE_NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive user ID and negative real estate ID
     * @Dependency: user ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_07() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NEGATIVE_NOT_EXISTED_ID)
                .setUserId(EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive user ID and positive real estate ID
     * @Dependency: user ID and real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_08() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(EXISTED_ID));
        Report result = instance.save(input);
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save positive user ID and positive real estate ID
     * @Dependency: user ID and real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_09() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NOT_EXISTED_ID)
                .setUserId(NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive user ID and positive real estate ID
     * @Dependency: user ID is existed and real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_10() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(NOT_EXISTED_ID)
                .setUserId(EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive user ID and positive real estate ID
     * @Dependency: user ID is not existed and real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_11() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(EXISTED_ID)
                .setUserId(NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save user ID and real estate ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_12() {
        Report input = sampleReport.setId(new ReportId()
                .setRealestateId(ZERO_NOT_EXISTED_ID)
                .setUserId(ZERO_NOT_EXISTED_ID));
        Report result = instance.save(input);
        
        testFail(result);
    }
    
    /**
     * @Description: Save null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RS_1_13() {
        Report result = instance.save(null);
        
        testFail(result);
    }
}
