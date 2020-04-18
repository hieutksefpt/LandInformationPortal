/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.street;

import capstone.lip.landinformationportal.entity.Street;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class StreetServiceTest_1 extends AbstractStreetServiceTest {
    
    /**
     * @Description: Save positive street ID
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_01() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive street ID
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_02() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative street ID
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_1_03() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save street ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_1_04() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_1_05() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NULL_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty street name 
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_06() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty street name 
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_07() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_08() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_09() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(ALPHABETIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_10() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_11() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_12() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_13() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic numeric street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_14() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(ALPHABETIC_NUMERIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic numeric street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_15() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(ALPHABETIC_NUMERIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_16() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_17() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save special character street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_18() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_19() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_20() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_21() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character street name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_22() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character street name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_23() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_24() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_25() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_26() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetLat(-1.0).setStreetLng(1.0));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_27() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLat(-1.0).setStreetLng(1.0));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_28() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetLat(1.0).setStreetLng(-1.0));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_29() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLat(1.0).setStreetLng(-1.0));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_30() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetLat(0.0).setStreetLng(0.0));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_31() {
        long records = streetRepository.count();
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLat(0.0).setStreetLng(0.0));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_32() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetLat(0.0).setStreetLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_33() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLat(0.0).setStreetLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_1_34() {
        Street result = instance.save(SampleStreet
                .setStreetId(EXISTED_ID)
                .setStreetLat(null).setStreetLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_1_35() {
        Street result = instance.save(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLat(null).setStreetLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_1_36() {
        Street result = instance.save(null);
        
        testFail(result);
    }
}
