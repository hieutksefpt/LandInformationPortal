/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.segment;

import capstone.lip.landinformationportal.business.repository.SegmentOfStreetRepository;
import capstone.lip.landinformationportal.business.service.SegmentOfStreetService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.District;
import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;
import capstone.lip.landinformationportal.common.entity.Street;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractSegmentServiceTest extends CRUDTest {
    
    @Autowired
    protected SegmentOfStreetService instance;
    
    @Autowired
    protected SegmentOfStreetRepository repository;
    
    protected SegmentOfStreet sampleSegment = new SegmentOfStreet()
            .setSegmentId(DEFAULT_ID)
            .setSegmentLat(DEFAULT_LAT).setSegmentLng(DEFAULT_LNG)
            .setSegmentName("SAMPLE SEGMENT")
            .setDistrict(new District()
                    .setDistrictId(EXISTED_ID))
            .setStreet(new Street()
                    .setStreetId(EXISTED_ID));
    
    private boolean isTheSame(SegmentOfStreet actual, SegmentOfStreet result) {
        return actual.getSegmentName().equals(result.getSegmentName())
                && actual.getSegmentLat().equals(result.getSegmentLat())
                && actual.getSegmentLng().equals(result.getSegmentLng())
                && actual.getDistrict().getDistrictId()
                        .equals(result.getDistrict().getDistrictId())
                && actual.getStreet().getStreetId()
                        .equals(result.getStreet().getStreetId());
    }
    
    protected void testInsertSuccess(SegmentOfStreet result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<SegmentOfStreet> actual = repository.findById(result.getSegmentId());
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
    
    protected void testUpdateSuccess(SegmentOfStreet result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<SegmentOfStreet> actual = repository.findById(result.getSegmentId());
            if (actual.isPresent()) {
                //Compare id
                assertEquals(actual.get().getSegmentId(), result.getSegmentId());
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
