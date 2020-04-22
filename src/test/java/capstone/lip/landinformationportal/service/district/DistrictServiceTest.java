/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.district;

import capstone.lip.landinformationportal.business.service.DistrictService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.District;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public class DistrictServiceTest extends CRUDTest{
    
    @Autowired
    private DistrictService instance;

    /**
     * Test of save method, of class DistrictService.
     */
    @Test
    @DatabaseSetup(value = "/sampleData.xml")
    @ExpectedDatabase( value = "/expectedData.xml")
    public void testSave() {
        System.out.println("save");
        District district = new District()
                .setDistrictId(2L)
                .setDistrictName("B")
                .setDistrictLat(1.0)
                .setDistrictLng(2.0);
        instance.save(district);
    }

}