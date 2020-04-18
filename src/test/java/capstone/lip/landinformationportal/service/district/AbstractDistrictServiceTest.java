/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.district;

import capstone.lip.landinformationportal.common.CRUDTest;
import org.springframework.beans.factory.annotation.Autowired;
import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.Province;
import capstone.lip.landinformationportal.repository.DistrictRepository;
import capstone.lip.landinformationportal.service.DistrictService;
import java.util.Optional;
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
    protected DistrictRepository districtRepository;
    
    protected District SampleDistrict = new District()
            .setDistrictId(99L)
            .setDistrictLat(1.0).setDistrictLng(1.0)
            .setDistrictName(ALPHABETIC_STRING)
            .setProvince(new Province()
                    .setProvinceId(EXISTED_ID));
    
    private boolean isTheSame(District actual, District result) {
        if (!actual.getDistrictName().equals(result.getDistrictName())) return false;
        if (!actual.getDistrictLat().equals(result.getDistrictLat())) return false;
        if (!actual.getDistrictLng().equals(result.getDistrictLng())) return false;
        
        return true;
    }
    
    protected void testInsertSuccess(District result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<District> actual = districtRepository.findById(result.getDistrictId());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records + 1, districtRepository.count());
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
            Optional<District> actual = districtRepository.findById(result.getDistrictId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getDistrictId(), result.getDistrictId());
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records, districtRepository.count());
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
            assertEquals(false, districtRepository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(records - 1, districtRepository.count());
        } else {
            fail();
        }
    }
    
    protected void testDeleteSuccess(boolean result, long[] id, long records) {
        //Delete success
        if (result) {
            //Test exist in DB
            for (int i = 0; i < id.length; i++) {
                assertEquals(false, districtRepository.existsById(id[i]));
            }
            //Test number of records is decreased by 1
            assertEquals(records - id.length, districtRepository.count());
        } else {
            fail();
        }
    }
}
