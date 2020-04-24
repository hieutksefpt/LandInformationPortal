/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.district;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.District;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class DistrictServiceTest_2_3 extends AbstractDistrictServiceTest {
    
    /**
     * @Description: List is null
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_2_01() {
        boolean result = instance.delete(new ArrayList());
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 district
     * @Dependency: Contain a non-existed district
     */
    @Test
    public void FT_DS_2_02() {
        ArrayList<District> districts = new ArrayList();
        districts.add(sampleDistrict.setDistrictId(NOT_EXISTED_ID));
        boolean result = instance.delete(districts);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 district
     * @Dependency: Contain an existed district
     */
    @Test
    public void FT_DS_2_03() {
        ArrayList<District> districts = new ArrayList();
        districts.add(repository.findById(EXISTED_ID).get());
        
        long records = repository.count();
        boolean result = instance.delete(districts);
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: List contain 3 district
     * @Dependency: Contain existed districts
     */
    @Test
    public void FT_DS_2_04() {
        ArrayList<District> districts = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            districts.add(repository.findById(EXISTED_IDs[i]).get());
        }
        
        long records = repository.count();
        boolean result = instance.delete(districts);
        
        testDeleteSuccess(result, EXISTED_IDs, records);
    }
    
    /**
     * @Description: Delete positive district ID
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_3_01() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive district ID
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_3_02() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get());
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete negative district ID
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_3_03() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete district ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_3_04() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_DS_3_05() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(NULL_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete empty district name 
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_3_06() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setDistrictName(EMPTY_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete empty district name 
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_3_07() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_3_08() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setDistrictName(NULL_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_3_09() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_3_10() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setDistrictLng(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_3_11() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: District ID is existed
     */
    @Test
    public void FT_DS_3_12() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setDistrictLat(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_3_13() {
        boolean result = instance.delete(sampleDistrict
                .setDistrictId(NOT_EXISTED_ID)
                .setDistrictLat(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: District ID is not existed
     */
    @Test
    public void FT_DS_3_14() {
        boolean result = instance.delete(new District());
        
        testFail(result);
    }
}
