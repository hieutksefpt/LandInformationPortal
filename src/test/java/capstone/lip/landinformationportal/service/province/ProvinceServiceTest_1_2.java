/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import capstone.lip.landinformationportal.entity.Province;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class ProvinceServiceTest_1_2 extends AbstractProvinceServiceTest {
    
    /**
     * @Description: Normal select all
     * @Dependency: There are 5 record
     */
    @Test
    public void FT_PS_1_02() {
        List<Province> result = instance.findAll();
        
        assertEquals(provinceRepository.count(), result.size());
    }
    
    /**
     * @Description: Save positive province ID
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_01() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }

    /**
     * @Description: Save positive province ID
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_02() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative province ID
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_2_03() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save province ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_2_04() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_2_05() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NULL_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty province name 
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_06() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty province name 
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_07() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_08() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_09() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(ALPHABETIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_10() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_11() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_12() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_13() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic numeric province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_14() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic numeric province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_15() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(ALPHABETIC_NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_16() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_17() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(NUMERIC_VIETNAMESE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_18() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(SPECIAL_CHARACTER_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save special character province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_19() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(SPECIAL_CHARACTER_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save all space character province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_20() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_21() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character province name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_22() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character province name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_23() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese province name without trim
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_24() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(WITHOUT_TRIM_VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese province name without trim
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_25() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(WITHOUT_TRIM_VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_26() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID).setProvinceName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_27() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_28() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID)
                .setProvinceLat(-1.0).setProvinceLng(1.0));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_29() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(-1.0).setProvinceLng(1.0));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_30() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID)
                .setProvinceLat(1.0).setProvinceLng(-1.0));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_31() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(1.0).setProvinceLng(-1.0));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_32() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID)
                .setProvinceLat(0.0).setProvinceLng(0.0));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_33() {
        long records = provinceRepository.count();
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(0.0).setProvinceLng(0.0));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_34() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID)
                .setProvinceLat(0.0).setProvinceLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_35() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(0.0).setProvinceLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_2_36() {
        Province result = instance.save(SampleProvince
                .setProvinceId(EXISTED_ID)
                .setProvinceLat(null).setProvinceLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_37() {
        Province result = instance.save(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(null).setProvinceLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_2_38() {
        Province result = instance.save(null);
        
        testFail(result);
    }
}
