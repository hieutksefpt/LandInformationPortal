/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.landsFeature;

import capstone.lip.landinformationportal.common.DataFeatureTest;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.service.LandsFeatureService;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public abstract class AbstractLandsFeatureServiceTest extends DataFeatureTest {
    
    @Autowired
    protected LandsFeatureService instance;
    
    @Autowired
    protected LandsFeatureRepository repository;
    
    protected LandsFeature getSampleLandsFeature() {
        LandsFeature sample = new LandsFeature();
        sample.setLandsFeatureID(DEFAULT_ID);
        sample.setLandsFeatureName("SAMPLE LANDS FEATURE");
        sample.setLandsFeatureUnit("SAMPLE UNIT");
        sample.setLandsFeatureDataType("INT");
        sample.setLandsFeatureDataRange(getNumericDataRange());
        return sample;
    }
    
    protected void testInsertSuccess(LandsFeature result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<LandsFeature> actual = repository.findById(result.getLandsFeatureID());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, actual.get().equals(result));
                //Test number of records is not changed
                assertEquals(records + 1, repository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }
    
    protected void testUpdateSuccess(LandsFeature result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<LandsFeature> actual = repository.findById(result.getLandsFeatureID());
            if (actual.isPresent()) {
                //Compare id
                //assertEquals(actual.get().getLandsFeatureID(), result.getLandsFeatureID());
                //Compare others
                assertEquals(true, actual.get().equals(result));
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
