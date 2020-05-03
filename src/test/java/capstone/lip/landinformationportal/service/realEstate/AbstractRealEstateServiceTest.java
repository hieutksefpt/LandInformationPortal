/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import capstone.lip.landinformationportal.business.repository.RealEstateRepository;
import capstone.lip.landinformationportal.business.service.RealEstateService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractRealEstateServiceTest extends CRUDTest {

    protected final String STATUS_CONFUSED = "CONFUSED";
    protected final String STATUS_NOT_VERIFIED = "NOT_VERIFIED";
    protected final String STATUS_VERIFIED = "VERIFIED";

    protected final String EXISTED_SOURCE = "CONTRIBUTOR";
    protected final String NON_EXISTED_SOURCE = "ADMIN";
    protected final String EXISTED_ADDRESS = "Hola";
    protected final String NON_EXISTED_ADDRESS = "123";

    protected final String EXISTED_NAME = "Real Estate 1";
    
    protected final int TOTAL_RECORDS = 5;

    @Autowired
    protected RealEstateService instance;

    @Autowired
    protected RealEstateRepository repository;

    public RealEstate sampleRealEstate = new RealEstate()
            .setRealEstateId(DEFAULT_ID)
            .setRealEstateName("SAMPLE REAL ESTATE")
            .setRealEstatePrice(DEFAULT_PRICE)
            .setRealEstateAddress(EXISTED_ADDRESS)
            .setRealEstateSource(EXISTED_SOURCE)
            .setRealEstateLat(DEFAULT_LAT).setRealEstateLng(DEFAULT_LNG)
            .setRealEstateStatus(STATUS_VERIFIED)
            .setUser(new User()
                    .setUserId(EXISTED_ID));

    protected void testInsertSuccess(RealEstate result) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<RealEstate> actual = repository.findById(result.getRealEstateId());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, actual.get().equals(result));
                //Test number of records is not changed
                assertEquals(TOTAL_RECORDS + 1, repository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }

    protected void testUpdateSuccess(RealEstate result) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<RealEstate> actual = repository.findById(result.getRealEstateId());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, actual.get().equals(result));
                //Test number of records is not changed
                assertEquals(TOTAL_RECORDS, repository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }

    protected void testDeleteSuccess(boolean result, long id) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(false, repository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(TOTAL_RECORDS - 1, repository.count());
        } else {
            fail();
        }
    }

    protected void testFindSuccess(long id, RealEstate realEstate) {
        assertEquals(repository.findById(id).get(), realEstate);
    }

    protected void testGetLandSuccess(long id, Land result) {
        Land expected = repository.findById(id).get().getLand();
        assertEquals(expected, result);
    }

    protected void testGetListHouseSuccess(long id, List<House> result) {
        List<House> expected = repository.findById(id).get().getListHouse();
        assertEquals(expected, result);
    }
    
    protected void testListNotEmpty(List result) {
        assertEquals(false, result.isEmpty());
    }
    
    protected void testListEmpty(List result) {
        assertEquals(true, result.isEmpty());
    }
    
}
