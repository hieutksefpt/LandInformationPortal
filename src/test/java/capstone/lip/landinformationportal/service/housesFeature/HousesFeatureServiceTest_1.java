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

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class HousesFeatureServiceTest_1 extends AbstractHousesFeatureServiceTest {
    
    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     */
    @Test
    public void FT_HFS_1_01() {
        List<HousesFeature> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
}
