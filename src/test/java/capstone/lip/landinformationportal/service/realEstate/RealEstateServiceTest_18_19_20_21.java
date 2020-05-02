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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateServiceTest_18_19_20_21 extends AbstractRealEstateServiceTest {
    
    /**
     * @Description: Empty real estate address
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_01() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(EMPTY_STRING, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty real estate source
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_02() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(EXISTED_ADDRESS, 
                EMPTY_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate address
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_03() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(NULL_STRING, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate source
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_04() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(EXISTED_ADDRESS, 
                NULL_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed real estate address
     * @Dependency: Existed real estate source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_05() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(NON_EXISTED_ADDRESS, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed real estate source
     * @Dependency: Existed real estate address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_06() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(EXISTED_ADDRESS, 
                NON_EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Existed real estate address, source
     * @Dependency: Existed page
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_18_07() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(EXISTED_ADDRESS, 
                EXISTED_SOURCE, pageable);
        
        assertEquals(true, result != null);
    }
    
    /**
     * @Description: Existed real estate address, source
     * @Dependency: Out range page
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_18_08() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSource(EXISTED_ADDRESS, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty real estate address
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_01() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(EMPTY_STRING, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty real estate source
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_02() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(EXISTED_ADDRESS, 
                EMPTY_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate address
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_03() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(NULL_STRING, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate source
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_04() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(EXISTED_ADDRESS, 
                NULL_STRING, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Existed real estate address
     * @Dependency: Non existed real estate source
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_05() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(EXISTED_ADDRESS, 
                NON_EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Existed real estate source
     * @Dependency: Non existed real estatet address
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_06() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(NON_EXISTED_ADDRESS, 
                EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed real estate address, source
     * @Dependency: Existed page
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_19_07() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(NON_EXISTED_ADDRESS, 
                NON_EXISTED_SOURCE, pageable);
        
        assertEquals(true, result != null);
    }
    
    /**
     * @Description: Non existed real estate address, source
     * @Dependency: Out range page
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_19_08() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.listFilterRealEstateByAddressAndSourceNot(NON_EXISTED_ADDRESS, 
                NON_EXISTED_SOURCE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_20_01() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(null, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty list
     * @Dependency: Out of range
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_20_02() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(EMPTY_LIST_ATTRIBUTE, pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty list
     * @Dependency: Valid range
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_20_03() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(EMPTY_LIST_ATTRIBUTE, pageable);
        
        assertEquals(true, result != null);
    }
    
    /**
     * @Description: Null page
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_20_04() {
        Page result = instance.findAllByAttribute(EMPTY_LIST_ATTRIBUTE, null);
        
        testFail(result);
    }
    
    /**
     * @Description: Not existed attributes
     * @Dependency: Out of range
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_20_05() {
        Pageable pageable = PageRequest.of(OUT_RANGE_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(getNotExistedListAttribute(), pageable);
        
        testFail(result);
    }
    
    /**
     * @Description: Not existed attributes
     * @Dependency: Valid range
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_20_06() {
        Pageable pageable = PageRequest.of(EXISTED_PAGE, PAGE_SIZE);
        Page result = instance.findAllByAttribute(getNotExistedListAttribute(), pageable);
        
        assertEquals(true, result != null);
    }
    
    /**
     * @Description: Null list
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_21_01() {
        long result = instance.countByAttribute(null);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty list
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_21_02() {
        long result = instance.countByAttribute(EMPTY_LIST_ATTRIBUTE);
        
        assertEquals(true, result != -1);
    }
    
    /**
     * @Description: Not existed attributes
     * @Dependency: N/A
     * @Expected Result: Success
     */
    @Test
    public void FT_RES_21_03() {
        long result = instance.countByAttribute(getNotExistedListAttribute());
        
        assertEquals(true, result != -1);
    }
}
