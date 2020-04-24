/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.formedCoordinate;

import capstone.lip.landinformationportal.business.repository.FormedCoordinateRepository;
import capstone.lip.landinformationportal.business.service.FormedCoordinateService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.FormedCoordinate;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

import java.util.List;
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
public abstract class AbstractFormedCoordinateServiceTest extends CRUDTest {

    @Autowired
    protected FormedCoordinateService instance;

    @Autowired
    protected FormedCoordinateRepository repository;

    protected FormedCoordinate sampleFormedCoordinate = new FormedCoordinate()
            .setFormedCoordinateId(DEFAULT_ID)
            .setFormedLat(DEFAULT_LAT).setFormedLng(DEFAULT_LNG)
            .setSegmentOfStreet(new SegmentOfStreet()
                    .setSegmentId(EXISTED_ID));

    private boolean isTheSame(FormedCoordinate actual, FormedCoordinate result) {
        return actual.getFormedLat().equals(result.getFormedLng())
                && actual.getFormedLng().equals(result.getFormedLng())
                && actual.getSegmentOfStreet().getSegmentId()
                        .equals(result.getSegmentOfStreet().getSegmentId());
    }

    protected void testInsertSuccess(FormedCoordinate result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<FormedCoordinate> actual = repository.findById(result.getFormedCoordinateId());
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

    protected void testInsertSuccess(List<FormedCoordinate> results, long records) {
        //Insert success
        if (results != null) {
            for (FormedCoordinate result : results) {
                //Test exist in DB
                Optional<FormedCoordinate> actual = repository.findById(result.getFormedCoordinateId());
                if (actual.isPresent()) {
                    //Compare others
                    assertEquals(true, isTheSame(actual.get(), result));
                } else {
                    fail();
                }
            }
            //Test number of records is not changed
            assertEquals(records + results.size(), repository.count());
        } else {
            fail();
        }
    }

    protected void testUpdateSuccess(FormedCoordinate result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<FormedCoordinate> actual = repository.findById(result.getFormedCoordinateId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getFormedCoordinateId(),
                        result.getFormedCoordinateId());
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

    protected void testUpdateSuccess(List<FormedCoordinate> results, long records) {
        //Insert success
        if (results != null) {
            for (FormedCoordinate result : results) {
                //Test exist in DB
                Optional<FormedCoordinate> actual = repository.findById(result.getFormedCoordinateId());
                if (actual.isPresent()) {
                    //Compare id
                    assertEquals(actual.get().getFormedCoordinateId(),
                            result.getFormedCoordinateId());
                    //Compare others
                    assertEquals(true, isTheSame(actual.get(), result));
                } else {
                    fail();
                }
            }
            //Test number of records is not changed
            assertEquals(records, repository.count());
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
