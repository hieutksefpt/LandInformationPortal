/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import capstone.lip.landinformationportal.common.CoordinatePack;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateServiceTest_1 extends AbstractRealEstateServiceTest 
    implements CoordinatePack {
    
    /**
     * @Description: Save positive real estate ID
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_01() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save positive real estate ID
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_02() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save negative real estate ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_03() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save real estate ID equals zero
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_04() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_05() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NULL_ID));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save empty real estate name 
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_06() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty real estate name 
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_07() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_08() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(EMPTY_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_09() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(EMPTY_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_10() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_11() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_12() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save Vietnamese real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_13() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic numeric real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_14() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(ALPHABETIC_NUMERIC_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save alphabetic numeric real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_15() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(ALPHABETIC_NUMERIC_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_16() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_17() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save special character real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_18() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_19() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_20() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_21() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character real estate name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_22() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character real estate name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_23() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_24() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_25() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & lng
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_26() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateLat(POSITIVE_COORDINATE)
                .setRealEstateLng(POSITIVE_COORDINATE));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save positive lat & lng
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_27() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateLat(POSITIVE_COORDINATE)
                .setRealEstateLng(POSITIVE_COORDINATE));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_28() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateLat(NEGATIVE_COORDINATE)
                .setRealEstateLng(POSITIVE_COORDINATE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_29() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateLat(NEGATIVE_COORDINATE)
                .setRealEstateLng(POSITIVE_COORDINATE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_30() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateLat(POSITIVE_COORDINATE)
                .setRealEstateLng(NEGATIVE_COORDINATE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_31() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateLat(POSITIVE_COORDINATE)
                .setRealEstateLng(NEGATIVE_COORDINATE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_32() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateLat(ZERO_COORDINATE)
                .setRealEstateLng(ZERO_COORDINATE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_33() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateLat(ZERO_COORDINATE)
                .setRealEstateLng(ZERO_COORDINATE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_34() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_35() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_36() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstateLat(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_37() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstateLat(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with negative price
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_38() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstatePrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with negative price
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_39() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstatePrice(NEGATIVE_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with positive price
     * @Dependency: Real estate ID is existed
     * @Expected Result: Update success
     */
    @Test
    public void FT_RES_1_40() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstatePrice(POSITIVE_PRICE));
        
        testUpdateSuccess(result);
    }
    
    /**
     * @Description: Save with positive price
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Insert success
     */
    @Test
    public void FT_RES_1_41() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstatePrice(POSITIVE_PRICE));
        
        testInsertSuccess(result);
    }
    
    /**
     * @Description: Save with price equals zero
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_42() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstatePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save with price equals zero
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_43() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstatePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without price
     * @Dependency: Real estate ID is existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_44() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(EXISTED_ID)
                .setRealEstatePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without price
     * @Dependency: Real estate ID is not existed
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_45() {
        RealEstate result = instance.save(sampleRealEstate
                .setRealEstateId(NOT_EXISTED_ID)
                .setRealEstatePrice(ZERO_PRICE));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_1_46() {
        RealEstate result = instance.save(null);
        
        testFail(result);
    }
}
