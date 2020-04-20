/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.land;

import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.compositekey.LandsDetailId;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class LandServiceTest_1 extends AbstractLandServiceTest {
    
    /**
     * @Description: Save positive land ID
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_01() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive land ID
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_02() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative land ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_03() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save land ID equals zero
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_04() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_05() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(null));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty land name 
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_06() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(EMPTY_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save empty land name 
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_07() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(EMPTY_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic land name
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_08() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic land name
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_09() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(ALPHABETIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric land name
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_10() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric land name
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_11() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese land name
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_12() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese land name
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_13() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese land name
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_14() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese land name
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_15() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save special character land name
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_16() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character land name
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_17() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_18() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandName(NULL_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_19() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandName(NULL_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive land price
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_20() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandPrice(POSITIVE_PRICE));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save positive land price
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_21() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandPrice(POSITIVE_PRICE));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save negative land price
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_22() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandPrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative land price
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_23() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandPrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save land price equals zero
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_24() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandPrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save land price equals zero
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_25() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandPrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without land price
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_26() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setLandPrice(null));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without land price
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_27() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setLandPrice(null));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save existed real estate ID
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_28() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(EXISTED_ID)));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save existed real estate ID
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_29() {
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(EXISTED_ID)));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save non-existed real estate ID
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_30() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save non-existed real estate ID
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_31() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate ID
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_32() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(null)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate ID
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_33() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(null)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_34() {
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setRealEstate(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_35() {
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setRealEstate(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with existed land feature
     * @Dependency: Land ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_LS_1_36() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(EXISTED_ID)));
        
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setListLandsDetail(details));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save with existed land feature
     * @Dependency: Land ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_LS_1_37() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(EXISTED_ID)));
        
        long records = repository.count();
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setListLandsDetail(details));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save with non-existed land feature
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_38() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(NOT_EXISTED_ID)));
        
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setListLandsDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with non-existed land feature
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_39() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(NOT_EXISTED_ID)));
        
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setListLandsDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with list existed and non-existed land feature
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_40() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(EXISTED_ID)));
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(NOT_EXISTED_ID)));
        
        Land result = instance.save(sampleLand
                .setLandId(EXISTED_ID)
                .setListLandsDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with list existed and non-existed land feature
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_41() {
        ArrayList<LandsDetail> details = new ArrayList();
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(EXISTED_ID)));
        details.add(new LandsDetail()
                .setId(new LandsDetailId()
                        .setLandId(EXISTED_ID)
                        .setLandsFeatureId(NOT_EXISTED_ID)));
        
        Land result = instance.save(sampleLand
                .setLandId(NOT_EXISTED_ID)
                .setListLandsDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: Land ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_42() {
        Land result = instance.save(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: Land ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_1_43() {
        Land result = instance.save(null);
        
        testFail(result);
    }
}
