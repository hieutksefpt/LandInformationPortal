/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.province;

import capstone.lip.landinformationportal.business.repository.ProvinceRepository;
import capstone.lip.landinformationportal.business.service.ProvinceService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.Province;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
    protected ProvinceRepository repository;
    
    protected Province sampleProvince = new Province()
            .setProvinceId(DEFAULT_ID)
            .setProvinceLat(DEFAULT_LAT).setProvinceLng(DEFAULT_LNG)
            .setProvinceName("SAMPLE PROVINCE");
    
    private boolean isTheSame(Province actual, Province result) {
        return actual.getProvinceName().equals(result.getProvinceName())
                && actual.getProvinceLat().equals(result.getProvinceLat())
                && actual.getProvinceLng().equals(result.getProvinceLng());
    }
    
    protected void testInsertSuccess(Province result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<Province> actual = repository.findById(result.getProvinceId());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records + 1, repository.count());
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
            Optional<Province> actual = repository.findById(result.getProvinceId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getProvinceId(), result.getProvinceId());
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records, repository.count());
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
            assertEquals(false, repository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(records - 1, repository.count());
        } else {
            fail();
        }
    }
    
}
