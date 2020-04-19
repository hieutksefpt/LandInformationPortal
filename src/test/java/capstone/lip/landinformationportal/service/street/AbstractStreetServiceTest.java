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
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractStreetServiceTest extends CRUDTest {
    
    @Autowired
    protected StreetService instance;
    
    @Autowired
    protected StreetRepository repository;
    
    protected Street sampleStreet = new Street()
            .setStreetId(DEFAULT_ID)
            .setStreetLat(DEFAULT_LAT).setStreetLng(DEFAULT_LNG)
            .setStreetName("SAMPLE STREET");
    
    private boolean isTheSame(Street actual, Street result) {
        return actual.getStreetName().equals(result.getStreetName())
                && actual.getStreetLat().equals(result.getStreetLat())
                && actual.getStreetLng().equals(result.getStreetLng());
    }
    
    protected void testInsertSuccess(Street result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<Street> actual = repository.findById(result.getStreetId());
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
    
    protected void testUpdateSuccess(Street result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<Street> actual = repository.findById(result.getStreetId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getStreetId(), result.getStreetId());
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
