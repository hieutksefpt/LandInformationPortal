/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstateAdjacentSegment;

import capstone.lip.landinformationportal.business.repository.RealEstateAdjacentSegmentRepository;
import capstone.lip.landinformationportal.business.service.RealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.common.entity.compositekey.RealEstateAdjacentSegmentId;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractRealEstateAdjacentSegmentServiceTest extends CRUDTest {
    
    @Autowired 
    protected RealEstateAdjacentSegmentService instance;
    
    @Autowired
    protected RealEstateAdjacentSegmentRepository repository;
    
    protected RealEstateAdjacentSegment sampleRealEstateAdjacentSegment = 
            new RealEstateAdjacentSegment()
                    .setId(setRealEstateAdjacentSegmentId(new RealEstateAdjacentSegmentId(),
                            EXISTED_ID, EXISTED_ID));
    
    protected RealEstateAdjacentSegment setRealEstateAdjacentSegmentId(
            RealEstateAdjacentSegment realEstateAdjacentSegment, 
            Long segmentId, Long realEstateId) {
        realEstateAdjacentSegment.setId(setRealEstateAdjacentSegmentId(
                realEstateAdjacentSegment.getId(), segmentId, realEstateId));
        
        return realEstateAdjacentSegment;
    }
    
    protected RealEstateAdjacentSegmentId setRealEstateAdjacentSegmentId(
            RealEstateAdjacentSegmentId realEstateAdjacentSegmentId, 
            Long segmentId, Long realEstateId) {
        realEstateAdjacentSegmentId.setRealEstateId(realEstateId);
        realEstateAdjacentSegmentId.setSegmentOfStreetId(segmentId);
        
        return realEstateAdjacentSegmentId;
    }
    
    protected ArrayList<RealEstateAdjacentSegment> getListRealEstateAdjacentSegments() {
        ArrayList<RealEstateAdjacentSegment> details = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            details.add(repository
                    .findByIdSegmentOfStreetIdAndIdRealEstateId(EXISTED_IDs[i], EXISTED_IDs[i]));
        }
        return details;
    }
    
    protected void testInsertSuccess(RealEstateAdjacentSegment result, long records) {
        //Update success
        if (result != null) {
            RealEstateAdjacentSegment actual = repository
                    .findByIdSegmentOfStreetIdAndIdRealEstateId(
                            result.getId().getSegmentOfStreetId(),
                            result.getId().getRealEstateId());
            //Compare others
            assertEquals(true, actual.equals(result));
            //Test number of records is not changed
            assertEquals(records + 1, repository.count());
        } else {
            fail();
        }
    }
    
    protected void testUpdateSuccess(RealEstateAdjacentSegment result, long records) {
        //Update success
        if (result != null) {
            RealEstateAdjacentSegment actual = repository
                    .findByIdSegmentOfStreetIdAndIdRealEstateId(
                            result.getId().getSegmentOfStreetId(),
                            result.getId().getRealEstateId());
            //Compare others
            assertEquals(true, actual.equals(result));
            //Test number of records is not changed
            assertEquals(records, repository.count());
        } else {
            fail();
        }
    }

    protected void testUpdateSuccess(List<RealEstateAdjacentSegment> results, long records) {
        //Update success
        if (results != null) {
            for (RealEstateAdjacentSegment result : results) {
                RealEstateAdjacentSegment actual = repository
                    .findByIdSegmentOfStreetIdAndIdRealEstateId(
                            result.getId().getSegmentOfStreetId(),
                            result.getId().getRealEstateId());
            //Compare others
            assertEquals(true, actual.equals(result));
            }
            //Test number of records is not changed
            assertEquals(records, repository.count());
        } else {
            fail();
        }
    }
    
    protected void testDeleteSuccess(boolean result, 
            List<RealEstateAdjacentSegment> details, long records) {
        //Delete success
        if (result) {
            //Test exist in DB
            for (RealEstateAdjacentSegment detail : details) {
                assertEquals(true, repository
                        .findByIdSegmentOfStreetIdAndIdRealEstateId(
                                detail.getId().getSegmentOfStreetId(), 
                                detail.getId().getRealEstateId()) == null);
            }
            //Test number of records is decreased
            assertEquals(records - details.size(), repository.count());
        } else {
            fail();
        }
    }
}
