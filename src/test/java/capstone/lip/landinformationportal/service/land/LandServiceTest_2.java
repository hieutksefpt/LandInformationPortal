/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.land;

import capstone.lip.landinformationportal.entity.Land;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class LandServiceTest_2 extends AbstractLandServiceTest {
    
    /**
     * @Description: Delete a non-existed land
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_LS_2_01() {
        boolean result = instance.delete(sampleLand.setLandId(NOT_EXISTED_ID));
        
        testFail(result);
    }
    
    /**
     * @Description: Delete a null land
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_02() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete an existed land
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_HS_2_03() {
        long records = repository.count();
        boolean result = instance.delete(repository.findById(EXISTED_ID).get());
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
}
