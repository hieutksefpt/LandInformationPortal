/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import java.util.List;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.Province;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class ProvinceServiceTest_1 extends AbstractProvinceServiceTest {
    
    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     */
    @Test
    public void FT_PS_1_01() {
        List<Province> result = instance.findAll();
        
        assertEquals(repository.count(), result.size());
    }
}
