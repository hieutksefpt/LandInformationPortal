/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.housesDetail;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.HousesDetail;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class HousesDetailServiceTest_2 extends AbstractHousesDetailServiceTest {
    
    private ArrayList<HousesDetail> getListHousesDetails() {
        ArrayList<HousesDetail> details = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            details.add(repository
                    .findByIdHouseIdAndIdHousesFeatureId(EXISTED_IDs[i], EXISTED_IDs[i]));
        }
        return details;
    }
    
    /**
     * @Description: Delete null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_01() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 house's detail
     * @Dependency: contain a non-existed house's detail
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_02() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 house's detail
     * @Dependency: contain an existed house's detail
     * @Expected Result: Success
     */
    @Test
    public void FT_HDS_2_03() {
        long records = repository.count();
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(repository.findByIdHouseIdAndIdHousesFeatureId(EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: List contain 3 house's detail
     * @Dependency: contain a non-existed house's detail
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_04() {
        ArrayList<HousesDetail> details = getListHousesDetails();
        details.set(details.size() - 1, 
                setHousesDetailID(sampleHousesDetail, NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 3 house's detail
     * @Dependency: contain existed house's details
     * @Expected Result: Success
     */
    @Test
    public void FT_HDS_2_05() {
        long records = repository.count();
        ArrayList<HousesDetail> details = getListHousesDetails();
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: Delete null house's detail ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_06() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(sampleHousesDetail.setId(null));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative house ID and negative house feature ID
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_07() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    NEGATIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative house ID and positive house feature ID
     * @Dependency: house feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_08() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    NEGATIVE_NOT_EXISTED_ID, POSITIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete negative house ID and positive house feature ID
     * @Dependency: house feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_09() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    NEGATIVE_NOT_EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house ID and negative house feature ID
     * @Dependency: house ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_10() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    POSITIVE_NOT_EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house ID and negative house feature ID
     * @Dependency: house ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_11() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    EXISTED_ID, NEGATIVE_NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house ID and positive house feature ID
     * @Dependency: house ID and house feature ID is existed
     * @Expected Result: Success
     */
    @Test
    public void FT_HDS_2_12() {
        long records = repository.count();
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(repository.findByIdHouseIdAndIdHousesFeatureId(EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testDeleteSuccess(result, details, records);
    }
    
    /**
     * @Description: Delete positive house ID and positive house feature ID
     * @Dependency: house ID and house feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_13() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    NOT_EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house ID and positive house feature ID
     * @Dependency: house ID is existed and house feature ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_14() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    EXISTED_ID, NOT_EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house ID and positive house feature ID
     * @Dependency: house ID is not existed and house feature ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_15() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
                    NOT_EXISTED_ID, EXISTED_ID));
        boolean result = instance.delete(details);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete house ID and house feature ID equal zero
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HDS_2_16() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(setHousesDetailID(sampleHousesDetail, 
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
    public void FT_HDS_2_17() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(null);
        boolean result = instance.delete(details);
        
        testFail(result);
    }
}
