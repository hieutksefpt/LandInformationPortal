/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.repository.ProvinceRepository;
import capstone.lip.landinformationportal.service.ProvinceService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractProvinceServiceTest extends CRUDTest {
    
    @Autowired
    protected ProvinceService instance;

    @Autowired 
    protected ProvinceRepository provinceRepository;
    
    protected Province SampleProvince = new Province()
            .setProvinceId(99L)
            .setProvinceLat(1.0).setProvinceLng(-1.0)
            .setProvinceName(ALPHABETIC_STRING);
    
    protected boolean isTheSame(Province actual, Province result) {
        if (!actual.getProvinceName().equals(result.getProvinceName())) return false;
        if (!actual.getProvinceLat().equals(result.getProvinceLat())) return false;
        if (!actual.getProvinceLng().equals(result.getProvinceLng())) return false;
        
        return true;
    }
    
    protected void testInsertSuccess(Province result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<Province> actual = provinceRepository.findById(result.getProvinceId());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records + 1, provinceRepository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }
    
    protected void testUpdateSuccess(Province result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<Province> actual = provinceRepository.findById(result.getProvinceId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getProvinceId(), result.getProvinceId());
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records, provinceRepository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }
    
    protected void testDeleteSuccess(boolean result, long id, long records) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(false, provinceRepository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(records - 1, provinceRepository.count());
        } else {
            fail();
        }
    }
    
    protected void testFail(boolean result) {
        //Delete fail
        assertEquals(false, result);
    }
    
    protected void testFail(Province result) {
        //Save fail
        assertEquals(true, result == null);
    }
}
