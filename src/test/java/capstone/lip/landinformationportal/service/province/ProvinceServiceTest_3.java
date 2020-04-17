/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class ProvinceServiceTest_3 extends AbstractProvinceServiceTest {
    
    /**
     * @Description: Delete positive province ID
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_01() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive province ID
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_02() {
        long records = provinceRepository.count();
        boolean result = instance.delete(SampleProvince.setProvinceId(1L));
        
        testDeleteSuccess(result, 1L, records);
    }
    
    /**
     * @Description: Delete negative province ID
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_3_03() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete province ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_3_04() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_3_05() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(NULL_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete empty province name 
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_06() {
        long records = provinceRepository.count();
        boolean result = instance.delete(SampleProvince
                .setProvinceId(2L).setProvinceName(EMPTY_STRING));
        
        testDeleteSuccess(result, 2L, records);
    }
    
    /**
     * @Description: Delete empty province name 
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_07() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_08() {
        long records = provinceRepository.count();
        boolean result = instance.delete(SampleProvince
                .setProvinceId(3L).setProvinceName(NULL_STRING));
        
        testDeleteSuccess(result, 3L, records);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_09() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_10() {
        long records = provinceRepository.count();
        boolean result = instance.delete(SampleProvince
                .setProvinceId(4L)
                .setProvinceLat(1.0).setProvinceLng(null));
        
        testDeleteSuccess(result, 4L, records);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_11() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(1.0).setProvinceLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_12() {
        long records = provinceRepository.count();
        boolean result = instance.delete(SampleProvince
                .setProvinceId(5L)
                .setProvinceLat(null).setProvinceLng(1.0));
        
        testDeleteSuccess(result, 5L, records);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_13() {
        boolean result = instance.delete(SampleProvince
                .setProvinceId(NOT_EXISTED_ID)
                .setProvinceLat(null).setProvinceLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_14() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
}
