/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.house;

import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.service.HouseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractHouseServiceTest extends CRUDTest {
    
    @Autowired
    protected HouseService instance;
    
    @Autowired
    protected HouseRepository repository;
    
    protected House sampleHouse = new House()
            .setHouseId(DEFAULT_ID)
            .setHouseName("SAMPLE HOUSE")
            .setHousePrice(DEFAULT_PRICE)
            .setRealEstate(new RealEstate()
                    .setRealEstateId(EXISTED_ID));
    
    protected void testInsertSuccess(House result, long records) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<House> actual = repository.findById(result.getHouseId());
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
    
    protected void testUpdateSuccess(House result, long records) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<House> actual = repository.findById(result.getHouseId());
            if (actual.isPresent()) {
                //Compare id
                //assertEquals(actual.get().getHouseId(), result.getHouseId());
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
