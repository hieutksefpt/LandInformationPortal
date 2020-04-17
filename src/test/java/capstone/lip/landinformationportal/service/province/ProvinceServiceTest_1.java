/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import capstone.lip.landinformationportal.entity.Province;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

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
    public void FT_PS_1_02() {
        List<Province> result = instance.findAll();
        
        assertEquals(provinceRepository.count(), result.size());
    }
}
