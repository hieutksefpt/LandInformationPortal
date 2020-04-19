/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.district;

import capstone.lip.landinformationportal.entity.District;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class DistrictServiceTest_1 extends AbstractDistrictServiceTest {
    
    /**
     * @Description: Save positive district ID
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_01() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive district ID
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_02() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative district ID
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_1_03() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save district ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_1_04() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_1_05() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(NULL_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty district name 
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_06() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty district name 
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_07() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_08() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_09() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(ALPHABETIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_10() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_11() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_12() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_13() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic numeric district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_14() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_15() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_16() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_17() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_18() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_19() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_20() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_21() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character district name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_22() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character district name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_23() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese district name without trim
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_24() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(WITHOUT_TRIM_VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese district name without trim
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_25() {
        long records = repository.count();
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(WITHOUT_TRIM_VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_26() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_27() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_28() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictLat(-1.0).setDistrictLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_29() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLat(-1.0).setDistrictLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_30() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictLat(1.0).setDistrictLng(-1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_31() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLat(1.0).setDistrictLng(-1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_32() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictLat(0.0).setDistrictLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_33() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLat(0.0).setDistrictLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_34() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictLat(0.0).setDistrictLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_35() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLat(0.0).setDistrictLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_1_36() {
        District result = instance.save(sampleDistrict
                .setDistrictId(EXISTED_ID)
                .setDistrictLat(null).setDistrictLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_1_37() {
        District result = instance.save(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLat(null).setDistrictLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_1_38() {
        District result = instance.save(null);
        
        testFail(result);
    }
}
