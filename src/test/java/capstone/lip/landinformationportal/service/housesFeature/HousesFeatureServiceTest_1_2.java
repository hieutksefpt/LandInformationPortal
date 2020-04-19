/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.housesFeature;

import capstone.lip.landinformationportal.entity.HousesFeature;
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
public class HousesFeatureServiceTest_1_2 extends AbstractHousesFeatureServiceTest {
    
    /**
     * @Description: Normal select all
     * @Dependency: There are 5 record
     */
    @Test
    public void FT_HFS_1_02() {
        List<HousesFeature> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
    
    /**
     * @Description: Save positive province ID
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_HFS_2_01() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(POSITIVE_NOT_EXISTED_ID);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive province ID
     * @Dependency: Province ID is not existed
     */
    @Test
    public void FT_HFS_2_02() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative house's feature ID
     * @Dependency: N/A
     */
    @Test
    public void FT_HFS_2_03() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NEGATIVE_NOT_EXISTED_ID);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save house's feature ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_HFS_2_04() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(ZERO_NOT_EXISTED_ID);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_HFS_2_05() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(null);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty house's feature name 
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_06() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(EMPTY_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty house's feature name 
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_07() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(EMPTY_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic house's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_08() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(ALPHABETIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic house's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_09() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(ALPHABETIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric house's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_10() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(NUMERIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric house's feature name 
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_11() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(NUMERIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese house's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_12() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese house's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_13() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese house's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_14() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(NUMERIC_VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese house's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_15() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(NUMERIC_VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character house's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_16() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(SPECIAL_CHARACTER_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character house's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_17() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(SPECIAL_CHARACTER_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character house's feature name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_18() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(ALL_SPACE_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character house's feature name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_19() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(ALL_SPACE_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_20() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureName(null);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_21() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureName(null);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic house's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_22() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(ALPHABETIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic house's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_23() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(ALPHABETIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric house's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_24() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(NUMERIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric house's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_25() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(NUMERIC_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese house's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_26() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese house's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_27() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese house's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_28() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(NUMERIC_VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric Vietnamese house's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_29() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(NUMERIC_VIETNAMESE_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character house's feature unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_30() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(SPECIAL_CHARACTER_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character house's feature unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_31() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(SPECIAL_CHARACTER_STRING);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without unit
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_32() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(null);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without unit
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_33() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(null);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
}
