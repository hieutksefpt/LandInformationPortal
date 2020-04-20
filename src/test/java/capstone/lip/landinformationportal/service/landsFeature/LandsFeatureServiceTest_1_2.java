/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.landsFeature;

import capstone.lip.landinformationportal.entity.LandsFeature;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class LandsFeatureServiceTest_1_2 extends AbstractLandsFeatureServiceTest {
    
    /**
     * @Description: Normal select all
     * @Dependency: There are 5 record
     */
    @Test
    public void FT_LFS_1_02() {
        List<LandsFeature> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
    
    /**
     * @Description: Save positive land's feature ID
     * @Dependency: Land's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_01() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(POSITIVE_NOT_EXISTED_ID);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive land's feature ID
     * @Dependency: Land's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_02() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative land's feature ID
     * @Dependency: N/A
     */
    @Test
    public void FT_LFS_2_03() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NEGATIVE_NOT_EXISTED_ID);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save land's feature ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_LFS_2_04() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(ZERO_NOT_EXISTED_ID);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_LFS_2_05() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(null);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty land's feature name 
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_06() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(EMPTY_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty land's feature name 
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_07() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(EMPTY_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic land's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_08() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(ALPHABETIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic land's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_09() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(ALPHABETIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric land's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_10() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(NUMERIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric land's feature name 
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_11() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(NUMERIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese land's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_12() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese land's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_13() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese land's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_14() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(NUMERIC_VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese land's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_15() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(NUMERIC_VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character land's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_16() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(SPECIAL_CHARACTER_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character land's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_17() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(SPECIAL_CHARACTER_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character land's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_18() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(ALL_SPACE_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character land's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_19() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(ALL_SPACE_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_20() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureName(null);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_21() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureName(null);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic land's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_22() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureUnit(ALPHABETIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic land's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_23() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureUnit(ALPHABETIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric land's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_24() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureUnit(NUMERIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric land's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_25() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureUnit(NUMERIC_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese land's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_26() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureUnit(VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese land's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_27() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureUnit(VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese land's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_28() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureUnit(NUMERIC_VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese land's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_29() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureUnit(NUMERIC_VIETNAMESE_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character land's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_30() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureUnit(SPECIAL_CHARACTER_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character land's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_31() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureUnit(SPECIAL_CHARACTER_STRING);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_32() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureUnit(null);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_33() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureUnit(null);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
}
