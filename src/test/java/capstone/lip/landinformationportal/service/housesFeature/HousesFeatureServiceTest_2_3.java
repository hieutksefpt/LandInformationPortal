/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.housesFeature;

import capstone.lip.landinformationportal.entity.HousesFeature;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class HousesFeatureServiceTest_2_3 extends AbstractHousesFeatureServiceTest {
    
    /**
     * @Description: Save without data type
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_34() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureDataType(null);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save without data type
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_35() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureDataType(null);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save with invalid data type
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_36() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureDataType(INVALID_FEATURE_DATA_TYPE);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save with invalid data type
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_37() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureDataType(INVALID_FEATURE_DATA_TYPE);
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save with valid data type
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_38() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save with valid data type
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_39() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureDataType(NUMBER_VALID_FEATURE_DATA_TYPE);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_40() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureDataRange(null);
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save without data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_41() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureDataRange(null);
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save number data type and numeric data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_42() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getNumericDataRange());
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save number data type and numeric data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_43() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getNumericDataRange());
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save number data type and innumeric data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_44() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getTextDataRange());
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save number data type and innumeric data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_45() {
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(NUMBER_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getTextDataRange());
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Save text data type and character data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_46() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getTextDataRange());
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save text data type and character data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_47() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getTextDataRange());
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save text data type and numeric data range
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_2_48() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(EXISTED_ID);
        expected.setHousesFeatureUnit(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getNumericDataRange());
        HousesFeature result = instance.save(expected);
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save text data type and numeric data range
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_2_49() {
        long records = repository.count();
        HousesFeature expected = getSampleHousesFeature();
        expected.setHousesFeatureID(NOT_EXISTED_ID);
        expected.setHousesFeatureUnit(TEXT_VALID_FEATURE_DATA_TYPE);
        expected.setHousesFeatureDataRange(getNumericDataRange());
        HousesFeature result = instance.save(expected);
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     */
    @Test
    public void FT_HFS_2_50() {
        HousesFeature expected = null;
        HousesFeature result = instance.save(expected);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house's feature ID
     * @Dependency: House's Feature ID is not existed
     */
    @Test
    public void FT_HFS_3_01() {
        boolean result = instance.delete(POSITIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive house's feature ID
     * @Dependency: House's Feature ID is existed
     */
    @Test
    public void FT_HFS_3_02() {
        long records = repository.count();
        boolean result = instance.delete(EXISTED_ID);
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete negative house's feature ID
     * @Dependency: N/A
     */
    @Test
    public void FT_HFS_3_03() {
        boolean result = instance.delete(NEGATIVE_NOT_EXISTED_ID);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete house's feature ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_HFS_3_04() {
        boolean result = instance.delete(ZERO_NOT_EXISTED_ID);
        
        testFail(result);
    }
}
