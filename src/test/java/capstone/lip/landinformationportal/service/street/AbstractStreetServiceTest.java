/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.street;

import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.entity.Street;
import capstone.lip.landinformationportal.repository.StreetRepository;
import capstone.lip.landinformationportal.service.StreetService;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public abstract class AbstractStreetServiceTest extends CRUDTest {
    
    @Autowired
    protected StreetService instance;
    
    @Autowired
    protected StreetRepository streetRepository;
    
    protected Street SampleStreet = new Street()
            .setStreetId(99L)
            .setStreetLat(99.0).setStreetLng(99.0)
            .setStreetName("SAMPLE STREET");
    
    private boolean isTheSame(Street actual, Street result) {
        if (!actual.getStreetName().equals(result.getStreetName())) return false;
        if (!actual.getStreetLat().equals(result.getStreetLat())) return false;
        if (!actual.getStreetLng().equals(result.getStreetLng())) return false;
        
        return true;
    }
    
    protected void testInsertSuccess(Street result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<Street> actual = streetRepository.findById(result.getStreetId());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records + 1, streetRepository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }
    
    protected void testUpdateSuccess(Street result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<Street> actual = streetRepository.findById(result.getStreetId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getStreetId(), result.getStreetId());
                //Compare others
                assertEquals(true, isTheSame(actual.get(), result));
                //Test number of records is not changed
                assertEquals(records, streetRepository.count());
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
            assertEquals(false, streetRepository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(records - 1, streetRepository.count());
        } else {
            fail();
        }
    }
    
    protected void testDeleteSuccess(boolean result, long[] id, long records) {
        //Delete success
        if (result) {
            //Test exist in DB
            for (int i = 0; i < id.length; i++) {
                assertEquals(false, streetRepository.existsById(id[i]));
            }
            //Test number of records is decreased by 1
            assertEquals(records - id.length, streetRepository.count());
        } else {
            fail();
        }
    }
}
