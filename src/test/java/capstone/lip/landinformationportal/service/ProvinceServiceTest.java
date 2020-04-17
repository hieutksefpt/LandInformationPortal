/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.service.Interface.IProvinceService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public class ProvinceServiceTest extends CRUDTest {
    
    @Autowired
    private IProvinceService instance;

    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     */
    @Test
    @DatabaseSetup(value = SAMPLE + EMPTY_DB)
    public void FT_PS_1_01() {
        List<Province> result = instance.findAll();
        Assert.assertNull(result);
    }
    
    /**
     * @Description: Save positive province ID
     * @Dependency: Province ID is not existed
     */
    @Test
    @DatabaseSetup(value = SAMPLE + EMPTY_DB)
    @ExpectedDatabase(value = EXPECTED + EMPTY_DB, 
            assertionMode=DatabaseAssertionMode.NON_STRICT)
    public void FT_PS_2_01() {
        
    }
}
