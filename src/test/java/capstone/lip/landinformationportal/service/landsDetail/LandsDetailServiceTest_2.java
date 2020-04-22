/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.landsDetail;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.LandsDetail;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class LandsDetailServiceTest_2 extends AbstractLandsDetailServiceTest {
    
    private ArrayList<LandsDetail> getListLandsDetails() {
        ArrayList<LandsDetail> details = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            details.add(repository.findByIdLandIdAndIdLandsFeatureId(EXISTED_ID, EXISTED_ID));
        }
        return details;
    }
    
    /**
     * @Description: Delete null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_01() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 land's detail
     * @Dependency: contain a non-existed land's detail
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_02() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 land's detail
     * @Dependency: contain an existed land's detail
     * @Expected Result: Success
     */
    @Test
    public void FT_LDS_2_03() {
        long records = repository.count();
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(repository.findByIdLandIdAndIdLandsFeatureId(EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: List contain 3 land's detail
     * @Dependency: contain a non-existed land's detail
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_04() {
        ArrayList<LandsDetail> details = getListLandsDetails();
        details.set(details.size() - 1, 
                setLandsDetailID(sampleLandsDetail, EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 3 land's detail
     * @Dependency: contain existed land's details
     * @Expected Result: Success
     */
    @Test
    public void FT_LDS_2_05() {
        long records = repository.count();
        ArrayList<LandsDetail> details = getListLandsDetails();
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: Delete null land's detail ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_06() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(sampleLandsDetail.setId(null));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative land ID and negative land feature ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_07() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    NEGATIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative land ID and positive land feature ID
     * @Dependency: land feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_08() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    NEGATIVE_NOT_EXISTED_ID, POSITIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative land ID and positive land feature ID
     * @Dependency: land feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_09() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    NEGATIVE_NOT_EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land ID and negative land feature ID
     * @Dependency: land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_10() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    POSITIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land ID and negative land feature ID
     * @Dependency: land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_11() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land ID and positive land feature ID
     * @Dependency: land ID and land feature ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_LDS_2_12() {
        long records = repository.count();
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(repository.findByIdLandIdAndIdLandsFeatureId(EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: Delete positive land ID and positive land feature ID
     * @Dependency: land ID and land feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_13() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land ID and positive land feature ID
     * @Dependency: land ID is existed and land feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_14() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land ID and positive land feature ID
     * @Dependency: land ID is not existed and land feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_15() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    NOT_EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete land ID and land feature ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_16() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(setLandsDetailID(sampleLandsDetail, 
                    ZERO_NOT_EXISTED_ID, ZERO_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete null object
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LDS_2_17() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(null);
        boolean result = instance.delete(details);
        
        testFail(result);
    }
}
