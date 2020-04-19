/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.housesFeature;

import capstone.lip.landinformationportal.common.DataFeatureTest;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.service.HousesFeatureService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractHousesFeatureServiceTest extends DataFeatureTest {
    
    @Autowired
    protected HousesFeatureService instance;
    
    @Autowired
    protected HousesFeatureRepository repository;
    
    protected HousesFeature getSampleHousesFeature() {
        HousesFeature sample = new HousesFeature();
        sample.setHousesFeatureID(DEFAULT_ID);
        sample.setHousesFeatureName("SAMPLE HOUSES FEATURE");
        sample.setHousesFeatureUnit("SAMPLE UNIT");
        sample.setHousesFeatureDataType("INT");
        sample.setHousesFeatureDataRange(getNumericDataRange());
        return sample;
    }
    
    protected void testInsertSuccess(HousesFeature result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<HousesFeature> actual = repository.findById(result.getHousesFeatureID());
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
    
    protected void testUpdateSuccess(HousesFeature result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<HousesFeature> actual = repository.findById(result.getHousesFeatureID());
            if (actual.isPresent()) {
                //Compare id
                //assertEquals(actual.get().getHousesFeatureID(), result.getHousesFeatureID());
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
