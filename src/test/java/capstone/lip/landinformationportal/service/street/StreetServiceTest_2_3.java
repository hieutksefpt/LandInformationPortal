/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.street;

import capstone.lip.landinformationportal.entity.Street;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class StreetServiceTest_2_3 extends AbstractStreetServiceTest {
    
    /**
     * @Description: List is null
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_2_01() {
        boolean result = instance.delete(new ArrayList());
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 street
     * @Dependency: Contain a non-existed street
     */
    @Test
    public void FT_StS_2_02() {
        ArrayList<Street> streets = new ArrayList();
        streets.add(SampleStreet.setStreetId(NOT_EXISTED_ID));
        boolean result = instance.delete(streets);
        
        testFail(result);
    }
    
    /**
     * @Description: List contain 1 street
     * @Dependency: Contain an existed street
     */
    @Test
    public void FT_StS_2_03() {
        ArrayList<Street> streets = new ArrayList();
        streets.add(repository.findById(EXISTED_ID).get());
        
        long records = repository.count();
        boolean result = instance.delete(streets);
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: List contain 3 street
     * @Dependency: Contain existed streets
     */
    @Test
    public void FT_StS_2_04() {
        ArrayList<Street> streets = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            streets.add(repository.findById(EXISTED_IDs[i]).get());
        }
        
        long records = repository.count();
        boolean result = instance.delete(streets);
        
        testDeleteSuccess(result, EXISTED_IDs, records);
    }
    
    /**
     * @Description: Delete positive street ID
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_3_01() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(POSITIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete positive street ID
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_3_02() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get());
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete negative street ID
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_3_03() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(NEGATIVE_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete street ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_3_04() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(ZERO_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_StS_3_05() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(NULL_NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete empty street name 
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_3_06() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setStreetName(EMPTY_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete empty street name 
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_3_07() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_3_08() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setStreetName(NULL_STRING));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without name
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_3_09() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_3_10() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setStreetLng(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lng
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_3_11() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Street ID is existed
     */
    @Test
    public void FT_StS_3_12() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get()
                .setStreetLat(null));
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete without lat
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_3_13() {
        boolean result = instance.delete(SampleStreet
                .setStreetId(NOT_EXISTED_ID)
                .setStreetLat(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete without all
     * @Dependency: Street ID is not existed
     */
    @Test
    public void FT_StS_3_14() {
        boolean result = instance.delete(new Street());
        
        testFail(result);
    }
}
