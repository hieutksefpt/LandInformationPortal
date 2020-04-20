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
@TestPropertySource(locations = "/application-test-empty.properties")
public class LandsFeatureServiceTest_1 extends AbstractLandsFeatureServiceTest {
    
    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     */
    @Test
    public void FT_LFS_1_01() {
        List<LandsFeature> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
}
