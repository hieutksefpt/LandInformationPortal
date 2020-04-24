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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-empty.properties")
public class RealEstateServiceTest_Empty extends AbstractRealEstateServiceTest {
 
    /**
     * @Description: Non existed page
     * @Dependency: Invalid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_6_01() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findByRealEstateStatus("INVALID", pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: Valid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_6_02() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findByRealEstateStatus(STATUS_VERIFIED, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: Null status
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_6_03() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findByRealEstateStatus(NULL_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed page
     * @Dependency: Empty string
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_6_04() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findByRealEstateStatus(EMPTY_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty data
     * @Dependency: There are 0 record
     * @Expected Result: Return 0
     */
    @Test
    public void FT_RES_7_01() {
        assertEquals(0, instance.count());
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Invalid status
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_8_01() {
        assertEquals(-1, instance.countByRealEstateStatus("INVALID"));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Valid status
     * @Expected Result: Return 0
     */
    @Test
    public void FT_RES_8_02() {
        assertEquals(0, instance.countByRealEstateStatus(STATUS_CONFUSED));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Null status
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_8_03() {
        assertEquals(-1, instance.countByRealEstateStatus(NULL_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Empty string
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_8_04() {
        assertEquals(-1, instance.countByRealEstateStatus(EMPTY_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Null address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_9_01() {
        assertEquals(-1, instance.countByRealEstateAddress(NULL_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Empty address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_9_02() {
        assertEquals(-1, instance.countByRealEstateAddress(EMPTY_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Valid address
     * @Expected Result: Return 0
     */
    @Test
    public void FT_RES_9_03() {
        assertEquals(0, instance.countByRealEstateAddress(STATUS_VERIFIED));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Empty address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_01() {
        assertEquals(-1, instance
                .countByRealEstateSource(EMPTY_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Empty source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_02() {
        assertEquals(-1, instance
                .countByRealEstateSource(EXISTED_ADDRESS, EMPTY_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Null address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_03() {
        assertEquals(-1, instance
                .countByRealEstateSource(NULL_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Null source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_04() {
        assertEquals(-1, instance
                .countByRealEstateSource(EXISTED_ADDRESS, NULL_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Valid address and source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_10_05() {
        assertEquals(0, instance
                .countByRealEstateSource(EXISTED_ADDRESS, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Empty address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_01() {
        assertEquals(-1, instance
                .countByRealEstateSourceNot(EMPTY_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Empty source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_02() {
        assertEquals(-1, instance
                .countByRealEstateSourceNot(EXISTED_ADDRESS, EMPTY_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Null address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_03() {
        assertEquals(-1, instance
                .countByRealEstateSourceNot(NULL_STRING, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Null source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_04() {
        assertEquals(-1, instance
                .countByRealEstateSourceNot(EXISTED_ADDRESS, NULL_STRING));
    }
    
    /**
     * @Description: Empty data (0 record)
     * @Dependency: Valid address and source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_11_05() {
        assertEquals(0, instance
                .countByRealEstateSourceNot(EXISTED_ADDRESS, EXISTED_SOURCE));
    }
    
    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     * @Expected Result: Size = 0
     */
    @Test
    public void FT_RES_12_01() {
        assertEquals(0, instance.listRealEstateSource().size());
    }
    
    /**
     * @Description: Empty select all
     * @Dependency: There are 0 record
     * @Expected Result: Size = 0
     */
    @Test
    public void FT_RES_17_01() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        testFail(instance.findAll(pageable));
    }
}
