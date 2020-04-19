/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.house;

import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.compositekey.HousesDetailId;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class HouseServiceTest_1 extends AbstractHouseServiceTest {
    
    /**
     * @Description: Save positive house ID
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_01() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive house ID
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_02() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save negative house ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_03() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save house ID equals zero
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_04() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_05() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(null));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty house name 
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_06() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(EMPTY_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save empty house name 
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_07() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(EMPTY_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic house name
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_08() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic house name
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_09() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(ALPHABETIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric house name
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_10() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric house name
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_11() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese house name
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_12() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese house name
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_13() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese house name
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_14() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese house name
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_15() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save special character house name
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_16() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character house name
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_17() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_18() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHouseName(NULL_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_19() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHouseName(NULL_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive house price
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_20() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHousePrice(POSITIVE_PRICE));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save positive house price
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_21() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHousePrice(POSITIVE_PRICE));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save negative house price
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_22() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHousePrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative house price
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_23() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHousePrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save house price equals zero
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_24() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHousePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save house price equals zero
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_25() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHousePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without house price
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_26() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setHousePrice(null));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without house price
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_27() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setHousePrice(null));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save existed real estate ID
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_28() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(EXISTED_ID)));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save existed real estate ID
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_29() {
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(EXISTED_ID)));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save non-existed real estate ID
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_30() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save non-existed real estate ID
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_31() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(NOT_EXISTED_ID)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate ID
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_32() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(null)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate ID
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_33() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setRealEstate(new RealEstate()
                        .setRealEstateId(null)));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_34() {
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setRealEstate(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without real estate
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_35() {
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setRealEstate(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with existed house feature
     * @Dependency: House ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_HS_1_36() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(EXISTED_ID)));
        
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setListHousesDetail(details));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save with existed house feature
     * @Dependency: House ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_HS_1_37() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(EXISTED_ID)));
        
        long records = repository.count();
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setListHousesDetail(details));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save with non-existed house feature
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_38() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(NOT_EXISTED_ID)));
        
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setListHousesDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with non-existed house feature
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_39() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(NOT_EXISTED_ID)));
        
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setListHousesDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with list existed and non-existed house feature
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_40() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(EXISTED_ID)));
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(NOT_EXISTED_ID)));
        
        House result = instance.save(sampleHouse
                .setHouseId(EXISTED_ID)
                .setListHousesDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with list existed and non-existed house feature
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_41() {
        ArrayList<HousesDetail> details = new ArrayList();
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(EXISTED_ID)));
        details.add(new HousesDetail()
                .setId(new HousesDetailId()
                        .setHouseId(EXISTED_ID)
                        .setHousesFeatureId(NOT_EXISTED_ID)));
        
        House result = instance.save(sampleHouse
                .setHouseId(NOT_EXISTED_ID)
                .setListHousesDetail(details));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: House ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_42() {
        House result = instance.save(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: House ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_1_43() {
        House result = instance.save(null);
        
        testFail(result);
    }
}
