/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class RealEstateServiceTest_6_7_8_9_10_11_12 extends AbstractRealEstateServiceTest {
    
    /**
     * @Description: Existed page
     * @Dependency: Valid status
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_6_05() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findByRealEstateStatus(STATUS_VERIFIED, pageable);
        
        assertEquals(repository.findByRealEstateStatus(STATUS_VERIFIED, pageable), result);
    }
    
    /**
     * @Description: Out range page
     * @Dependency: Valid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_6_06() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findByRealEstateStatus(STATUS_VERIFIED, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Normal data
     * @Dependency: There are 5 records
     * @Expected Result: Return 5
     */
    @Test
    public void FT_RES_7_02() {
        long result = instance.count();
        
        assertEquals(5, result);
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Valid status
     * @Expected Result: Return 5
     */
    @Test
    public void FT_RES_8_05() {
        long result = instance.countByRealEstateStatus(STATUS_VERIFIED);
        
        assertEquals(5, result);
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Null address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_9_04() {
        long result = instance.countByRealEstateAddress(NULL_STRING);
        
        assertEquals(-1, result);
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Empty address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_9_05() {
        long result = instance.countByRealEstateAddress(EMPTY_STRING);
        
        assertEquals(-1, result);
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Non existed address
     * @Expected Result: Return 0
     */
    @Test
    public void FT_RES_9_06() {
        long result = instance.countByRealEstateAddress(STATUS_CONFUSED);
        
        assertEquals(0, result);
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Existed address
     * @Expected Result: Return 5
     */
    @Test
    public void FT_RES_9_07() {
        long result = instance.countByRealEstateAddress(STATUS_VERIFIED);
        
        assertEquals(5, result);
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Empty address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_06() {
        assertEquals(-1, instance
                .countByRealEstateSource(EMPTY_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Empty source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_07() {
        assertEquals(-1, instance
                .countByRealEstateSource(EXISTED_ADDRESS, EMPTY_STRING));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Null address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_08() {
        assertEquals(-1, instance
                .countByRealEstateSource(NULL_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Null source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_09() {
        assertEquals(-1, instance
                .countByRealEstateSource(EXISTED_ADDRESS, NULL_STRING));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Existed addresses and source
     * @Expected Result: Return 0
     */
    @Test
    public void FT_RES_10_10() {
        assertEquals(0, instance
                .countByRealEstateSource("123", "456"));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Existed addresses and source
     * @Expected Result: Return 5
     */
    @Test
    public void FT_RES_10_11() {
        assertEquals(5, instance
                .countByRealEstateSource(EXISTED_ADDRESS, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Empty address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_06() {
        assertEquals(-1, instance
                .countByRealEstateSource(EMPTY_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Empty source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_07() {
        assertEquals(-1, instance
                .countByRealEstateSource(EXISTED_ADDRESS, EMPTY_STRING));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Null address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_08() {
        assertEquals(-1, instance
                .countByRealEstateSource(NULL_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Null source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_09() {
        assertEquals(-1, instance
                .countByRealEstateSource(EXISTED_ADDRESS, NULL_STRING));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Existed addresses and source
     * @Expected Result: Return 5
     */
    @Test
    public void FT_RES_11_10() {
        assertEquals(5, instance
                .countByRealEstateSource("123", "456"));
    }
    
    /**
     * @Description: Normal data (5 records)
     * @Dependency: Existed addresses and source
     * @Expected Result: Return 0
     */
    @Test
    public void FT_RES_11_11() {
        assertEquals(0, instance
                .countByRealEstateSource(EXISTED_ADDRESS, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Normal select all
     * @Dependency: There are 5 records
     * @Expected Result: Size = 5
     */
    @Test
    public void FT_RES_12_02() {
        assertEquals(TOTAL_RECORDS, instance.listRealEstateSource().size());
    }
}
