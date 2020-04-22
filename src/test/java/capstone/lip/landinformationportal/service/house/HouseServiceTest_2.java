/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.house;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.House;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class HouseServiceTest_2 extends AbstractHouseServiceTest {
    
    private ArrayList<House> getListHouses() {
        ArrayList<House> houses = new ArrayList();
        for (int i = 0; i < EXISTED_IDs.length; i++) {
            houses.add(repository.findById(EXISTED_IDs[i]).get());
        }
        return houses;
    }
    
    /**
     * @Description: Delete null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_01() {
        boolean result = instance.delete(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete empty list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_02() {
        boolean result = instance.delete(EMPTY_LIST);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete a non-existed house
     * @Dependency: List contain 1 house
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_03() {
        ArrayList<House> houses = new ArrayList();
        houses.add(sampleHouse.setHouseId(NOT_EXISTED_ID));
        boolean result = instance.delete(houses);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete a non-existed house
     * @Dependency: List contain 3 houses
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_04() {
        ArrayList<House> houses = getListHouses();
        houses.set(houses.size()-1, 
                sampleHouse.setHouseId(NOT_EXISTED_ID));
        boolean result = instance.delete(houses);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete a null house
     * @Dependency: List contain 1 house
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_05() {
        ArrayList<House> houses = new ArrayList();
        houses.add(null);
        boolean result = instance.delete(houses);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete a null house
     * @Dependency: List contain 3 houses
     * @Expected Result: Fail
     */
    @Test
    public void FT_HS_2_06() {
        ArrayList<House> houses = getListHouses();
        houses.set(houses.size()-1, null);
        boolean result = instance.delete(houses);
        
        testFail(result);
    }
    
    /**
     * @Description: Delete an existed house
     * @Dependency: List contain 1 house
     * @Expected Result: Success
     */
    @Test
    public void FT_HS_2_07() {
        long records = repository.count();
        ArrayList<House> houses = new ArrayList();
        houses.add(repository.findById(EXISTED_ID).get());
        boolean result = instance.delete(houses);
        
        testDeleteSuccess(result, EXISTED_ID, records);
    }
    
    /**
     * @Description: Delete an existed house
     * @Dependency: List contain 3 houses
     * @Expected Result: Success
     */
    @Test
    public void FT_HS_2_08() {
        long records = repository.count();
        ArrayList<House> houses = getListHouses();
        boolean result = instance.delete(houses);
        
        testDeleteSuccess(result, EXISTED_IDs, records);
    }
}
