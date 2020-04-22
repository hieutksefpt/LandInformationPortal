/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.landsFeature;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.LandsFeature;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class LandsFeatureServiceTest_2_3 extends AbstractLandsFeatureServiceTest {
    
    /**
     * @Description: Save without data type
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_34() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(null);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without data type
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_35() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(null);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save with invalid data type
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_36() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(INVALID_FEATURE_DATA_TYPE);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save with invalid data type
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_37() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(INVALID_FEATURE_DATA_TYPE);
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save with valid data type
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_38() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save with valid data type
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_39() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_40() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataRange(null);
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_41() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataRange(null);
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save number data type and numeric data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_42() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getNumericDataRange());
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save number data type and numeric data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_43() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getNumericDataRange());
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save number data type and innumeric data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_44() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getTextDataRange());
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save number data type and innumeric data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_45() {
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getTextDataRange());
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save text data type and character data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_46() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getTextDataRange());
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save text data type and character data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_47() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getTextDataRange());
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save text data type and numeric data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_2_48() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(EXISTED_ID);
        expected.setLandsFeatureDataType(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getNumericDataRange());
        LandsFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save text data type and numeric data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_2_49() {
        long records = repository.count();
        LandsFeature expected = getSampleLandsFeature();
        expected.setLandsFeatureID(NOT_EXISTED_ID);
        expected.setLandsFeatureDataType(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setLandsFeatureDataRange(getNumericDataRange());
        LandsFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     */
    @Test
    public void FT_LFS_2_50() {
        LandsFeature expected = null;
        LandsFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land's feature ID
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_LFS_3_01() {
        boolean result = instance.delete(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive land's feature ID
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_LFS_3_02() {
        long records = repository.count();
        boolean result = instance.delete(EXISTED_ID);
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete negative land's feature ID
     * @Dependency: N/A
     */
    @Test
    public void FT_LFS_3_03() {
        boolean result = instance.delete(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete land's feature ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_LFS_3_04() {
        boolean result = instance.delete(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
}
