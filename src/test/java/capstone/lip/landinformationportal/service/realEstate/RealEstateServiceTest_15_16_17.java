/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateServiceTest_15_16_17 extends AbstractRealEstateServiceTest {
    
    /**
     * @Description: Empty string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_15_01() {
        testFail(instance.listMaxMinAvg(EMPTY_STRING));
    }
    
    /**
     * @Description: Null string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_15_02() {
        testFail(instance.listMaxMinAvg(NULL_STRING));
    }
    
    /**
     * @Description: Non existed address
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_15_03() {
        testFail(instance.listMaxMinAvg(NON_EXISTED_ADDRESS));
    }
    
    /**
     * @Description: Existed address
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_15_04() {
        assertEquals(repository.getMaxMinAvg(EXISTED_ADDRESS), 
                instance.listMaxMinAvg(EXISTED_ADDRESS));
    }
    
    /**
     * @Description: Empty string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_16_01() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddress(EMPTY_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_16_02() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddress(NULL_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed address
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_16_03() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddress(NON_EXISTED_ADDRESS, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Existed address
     * @Dependency: Existed page
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_16_04() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddress(EXISTED_ADDRESS, pageable);
        
        assertEquals(true, result != null);
    }
    
    /**
     * @Description: Existed address
     * @Dependency: Out range page
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_16_05() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddress(EXISTED_ADDRESS, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Normal data
     * @Dependency: Existed page
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_17_02() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findAll(pageable);
        
        assertEquals(repository.findAll(pageable),result);
    }
    
    /**
     * @Description: Normal data
     * @Dependency: Out range page
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_17_03() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAll(pageable);
        
        testFail(result);
    }
}
