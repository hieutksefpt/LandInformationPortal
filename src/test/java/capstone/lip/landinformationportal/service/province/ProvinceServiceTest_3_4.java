/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import capstone.lip.landinformationportal.common.entity.Province;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class ProvinceServiceTest_3_4 extends AbstractProvinceServiceTest {
    
    /**
     * @Description: Delete positive province ID
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_01() {
        boolean result = instance.delete(sampleProvince
                .setProvinceId(POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive province ID
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_02() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get());
        
        testDeleteSuccess(result, 1L, records);
    }
    
    /**
     * @Description: Delete negative province ID
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_3_03() {
        boolean result = instance.delete(sampleProvince
                .setProvinceId(NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete province ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_3_04() {
        boolean result = instance.delete(sampleProvince
                .setProvinceId(ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_PS_3_05() {
        boolean result = instance.delete(sampleProvince
                .setProvinceId(NULL_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete empty province name 
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_06() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setProvinceName(EMPTY_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete empty province name 
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_07() {
        boolean result = instance.delete(sampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_08() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setProvinceName(NULL_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_09() {
        boolean result = instance.delete(sampleProvince
                .setProvinceId(NOT_EXISTED_ID).setProvinceName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Province ID is existed
     */
    @Test
    public void FT_PS_3_10() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setProvinceLat(1.0).setProvinceLng(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_11() {
        boolean result = instance.delete(sampleProvince
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
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setProvinceLat(null).setProvinceLng(1.0));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_PS_3_13() {
        boolean result = instance.delete(sampleProvince
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
    
    /**
     * @Description: Negative id
     * @Dependency: N/A
     * @Expected: Fail
     */
    @Test
    public void FT_PS_4_01() {
        Province result = instance.findById(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Null id
     * @Dependency: N/A
     * @Expected: Fail
     */
    @Test
    public void FT_PS_4_02() {
        Province result = instance.findById(NULL_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Equals zero id
     * @Dependency: N/A
     * @Expected: Fail
     */
    @Test
    public void FT_PS_4_03() {
        Province result = instance.findById(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Positive id
     * @Dependency: Not existed id
     * @Expected: Fail
     */
    @Test
    public void FT_PS_4_04() {
        Province result = instance.findById(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Positive id
     * @Dependency: Existed id
     * @Expected: Success
     */
    @Test
    public void FT_PS_4_05() {
        Province result = instance.findById(EXISTED_ID);
        
        Assert.assertEquals(repository.findById(EXISTED_ID).get(), result);
    }
}
