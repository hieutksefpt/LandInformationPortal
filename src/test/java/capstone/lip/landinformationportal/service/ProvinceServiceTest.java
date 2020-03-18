/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import org.junit.Test;
import org.testng.AssertJUnit;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public class ProvinceServiceTest extends CRUDTest {

    @Autowired
    private ProvinceService instance;
    
    /**
     * Test of findAll method, of class ProvinceService.
     */
    @Test
    public void testFindAll() {
        //System.out.println("findAll");
        //List<Province> expResult = null;
        //List<Province> result = instance.findAll();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class ProvinceService.
     */
    @Test
    public void testSave() {
        //System.out.println("save");
        //Province province = null;
        //Province expResult = null;
        //Province result = instance.save(province);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deleteById method, of class ProvinceService.
     */
    @Test
    public void testDeleteById() {
        //System.out.println("deleteById");
        ///Long id = null;
        //ProvinceService instance = new ProvinceService();
        //instance.deleteById(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getListDistrictByProvinceId method, of class ProvinceService.
     */
    @Test
    @DatabaseSetup(value = "/expectedData.xml")
    public void testGetListDistrictByProvinceId() {
        System.out.println("getListDistrictByProvinceId");
        
        //EXPECTED dataset
        List<District> expResult = new ArrayList();
        expResult.add(new District()
                .setDistrictId(1L)
                .setDistrictName("A")
                .setDistrictLat(1.0)
                .setDistrictLng(2.0)
                .setProvince(new Province()
                        .setProvinceId(1L)));
        expResult.add(new District()
                .setDistrictId(2L)
                .setDistrictName("B")
                .setDistrictLat(1.0)
                .setDistrictLng(2.0)
                .setProvince(new Province()
                        .setProvinceId(1L)));
        
        //ACTUAL
        List<District> result = instance.getListDistrictByProvinceId(1L);
        
        //ASSERT
        for (int i = 0; i < result.size(); i++) {
            //Id
            AssertJUnit.assertEquals(expResult.get(i).getDistrictId(),
                    result.get(i).getDistrictId());
            //Name
            AssertJUnit.assertEquals(expResult.get(i).getDistrictName(),
                    result.get(i).getDistrictName());
            //Lat
            AssertJUnit.assertEquals(expResult.get(i).getDistrictLat(),
                    result.get(i).getDistrictLat());
            //Lng
            AssertJUnit.assertEquals(expResult.get(i).getDistrictLng(),
                    result.get(i).getDistrictLng());
            //Province 
            AssertJUnit.assertEquals(expResult.get(i).getProvince().getProvinceId(),
                    result.get(i).getProvince().getProvinceId());
        }
    }

    /**
     * Test of delete method, of class ProvinceService.
     */
    @Test
    public void testDelete() {
        //System.out.println("delete");
        //Province province = null;
        //instance.delete(province);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
