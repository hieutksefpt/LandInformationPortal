/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.district;

import capstone.lip.landinformationportal.business.repository.DistrictRepository;
import capstone.lip.landinformationportal.business.service.DistrictService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.Province;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
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
public abstract class AbstractDistrictServiceTest extends CRUDTest {
    
    @Autowired
    protected DistrictService instance;
    
    @Autowired
    protected DistrictRepository repository;
    
    protected District sampleDistrict = new District()
            .setDistrictId(DEFAULT_ID)
            .setDistrictLat(DEFAULT_LAT).setDistrictLng(DEFAULT_LNG)
            .setDistrictName("SAMPLE DISTRICT")
            .setProvince(new Province()
                    .setProvinceId(EXISTED_ID));
    
    private boolean isTheSame(District actual, District result) {
        return actual.getDistrictName().equals(result.getDistrictName())
                && actual.getDistrictLat().equals(result.getDistrictLat())
                && actual.getDistrictLng().equals(result.getDistrictLng());
    }
    
    protected void testInsertSuccess(District result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<District> actual = repository.findById(result.getDistrictId());
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
    
    protected void testUpdateSuccess(District result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<District> actual = repository.findById(result.getDistrictId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getDistrictId(), result.getDistrictId());
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
    
    protected void testDeleteSuccess(boolean result, long[] id, long records) {
        //Delete success
        if (result) {
            //Test exist in DB
            for (int i = 0; i < id.length; i++) {
                assertEquals(false, repository.existsById(id[i]));
            }
            //Test number of records is decreased by 1
            assertEquals(records - id.length, repository.count());
        } else {
            fail();
        }
    }
}
