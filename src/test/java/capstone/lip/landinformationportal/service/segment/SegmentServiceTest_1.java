/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.segment;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

/**
 *
 * @author Phong
 */
@TestPropertySource(locations = "/application-test-data.properties")
public class SegmentServiceTest_1 extends AbstractSegmentServiceTest {
    
    /**
     * @Description: Save positive street ID
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_01() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(POSITIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save positive street ID
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_02() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save negative street ID
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_1_03() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NEGATIVE_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save street ID equals zero
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_1_04() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(ZERO_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save without ID
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_1_05() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NULL_NOT_EXISTED_ID));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save empty street name 
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_06() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save empty street name 
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_07() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(EMPTY_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save alphabetic street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_08() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(ALPHABETIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_09() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(ALPHABETIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_10() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save numeric street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_11() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(NUMERIC_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save Vietnamese street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_12() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save Vietnamese street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_13() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic numeric street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_14() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(ALPHABETIC_NUMERIC_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save alphabetic numeric street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_15() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(ALPHABETIC_NUMERIC_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_16() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(NUMERIC_VIETNAMESE_STRING));
        
        testUpdateSuccess(result, records);
    }
    
    /**
     * @Description: Save numeric Vietnamese street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_17() {
        long records = repository.count();
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(NUMERIC_VIETNAMESE_STRING));
        
        testInsertSuccess(result, records);
    }
    
    /**
     * @Description: Save special character street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_18() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save special character street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_19() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(SPECIAL_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_20() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save all space character street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_21() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(ALL_SPACE_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character street name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_22() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save enter character street name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_23() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(ENTER_CHARACTER_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_24() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without name
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_25() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentName(NULL_STRING));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_26() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentLat(-1.0).setSegmentLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save negative lat & positive lng
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_27() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLat(-1.0).setSegmentLng(1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_28() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentLat(1.0).setSegmentLng(-1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save positive lat & negative lng
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_29() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLat(1.0).setSegmentLng(-1.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_30() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentLat(0.0).setSegmentLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save lat & lng equal zero
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_31() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLat(0.0).setSegmentLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_32() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentLat(0.0).setSegmentLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lng
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_33() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLat(0.0).setSegmentLng(null));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: SegmentOfStreet ID is existed
     */
    @Test
    public void FT_SgS_1_34() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(EXISTED_ID)
                .setSegmentLat(null).setSegmentLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without lat
     * @Dependency: SegmentOfStreet ID is not existed
     */
    @Test
    public void FT_SgS_1_35() {
        SegmentOfStreet result = instance.save(sampleSegment
                .setSegmentId(NOT_EXISTED_ID)
                .setSegmentLat(null).setSegmentLng(0.0));
        
        testFail(result);
    }
    
    /**
     * @Description: Save without all
     * @Dependency: N/A
     */
    @Test
    public void FT_SgS_1_36() {
        SegmentOfStreet result = instance.save(null);
        
        testFail(result);
    }
}
