/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.realEstate;

import java.util.List;
import org.junit.Test;
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

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class RealEstateServiceTest_13_14 extends AbstractRealEstateServiceTest {
    
    /**
     * @Description: Empty string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_13_01() {
        List result = instance.listGroupByDateAndValue(EMPTY_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Null string
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_13_02() {
        List result = instance.listGroupByDateAndValue(NULL_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed address
     * @Dependency: N/A
     * @Expected Result: Size = 0
     */
    @Test
    public void FT_RES_13_03() {
        List result = instance.listGroupByDateAndValue("123");
        
        testListEmpty(result);
    }
    
    /**
     * @Description: Existed address
     * @Dependency: N/A
     * @Expected Result: Size != 0
     */
    @Test
    public void FT_RES_13_04() {
        List result = instance.listGroupByDateAndValue("123");
        
        testListNotEmpty(result);
    }
    
    /**
     * @Description: Empty real estate name
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_14_01() {
        List result = instance.listFilterRealEstate(EMPTY_STRING, 
                EXISTED_SOURCE, STATUS_VERIFIED);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty real estate source
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_14_02() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                EMPTY_STRING, STATUS_VERIFIED);
        
        testFail(result);
    }
    
    /**
     * @Description: Empty real estate status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_14_03() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                EXISTED_SOURCE, EMPTY_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate name
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_14_04() {
        List result = instance.listFilterRealEstate(NULL_STRING, 
                EXISTED_SOURCE, STATUS_VERIFIED);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate source
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_14_05() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                NULL_STRING, STATUS_VERIFIED);
        
        testFail(result);
    }
    
    /**
     * @Description: Null real estate status
     * @Dependency: N/A
     * @Expected Result: Fail
     */
    @Test
    public void FT_RES_14_06() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                EXISTED_SOURCE, NULL_STRING);
        
        testFail(result);
    }
    
    /**
     * @Description: Non existed name
     * @Dependency: N/A
     * @Expected Result: Size = 0
     */
    @Test
    public void FT_RES_14_07() {
        List result = instance.listFilterRealEstate("123", 
                EXISTED_SOURCE, STATUS_VERIFIED);
        
        testListEmpty(result);
    }
    
    /**
     * @Description: Non existed real estate source
     * @Dependency: N/A
     * @Expected Result: Size = 0
     */
    @Test
    public void FT_RES_14_08() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                "123", STATUS_VERIFIED);
        
        testListEmpty(result);
    }
    
    /**
     * @Description: Non existed real estate source
     * @Dependency: N/A
     * @Expected Result: Size = 0
     */
    @Test
    public void FT_RES_14_09() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                EXISTED_SOURCE, STATUS_CONFUSED); //db does not have confused yet
        
        testListEmpty(result);
    }
    
    /**
     * @Description: Non existed real estate source
     * @Dependency: N/A
     * @Expected Result: Size != 0
     */
    @Test
    public void FT_RES_14_10() {
        List result = instance.listFilterRealEstate(EXISTED_NAME, 
                EXISTED_SOURCE, STATUS_VERIFIED); 
        
        testListNotEmpty(result);
    }
}
