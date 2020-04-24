
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class
})
public abstract class CRUDTest {

    protected final Long DEFAULT_ID = 99L;
    protected final Double DEFAULT_LAT = 99.0;
    protected final Double DEFAULT_LNG = 99.0;
    protected final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(999999);
    
    protected final String EXISTED_LINK = "https://dantri.com.vn/kinh-doanh/can-ho-ha-noi-dua-giam-gia-ca-tram-trieu-dong-2017120620103522.htm";
    protected final String DEFAULT_LINK = "https://batdongsan.com.vn/";
    protected final String DEFAULT_EMAIL = "lipsystem.capstone@gmail.com";
    
    protected final String EMPTY_STRING = "";
    protected final String NO_SPACE_ALPHABETIC_STRING = "LandInformationPortal";
    protected final String ALPHABETIC_STRING = "Land Information Portal";
    protected final String NUMERIC_STRING = "123456789";
    protected final String NO_SPACE_NUMERIC_VIETNAMESE_STRING = "Cổngthôngtinbấtđộngsản123";
    protected final String NUMERIC_VIETNAMESE_STRING = "Cổng thông tin bất động sản 123";
    protected final String VIETNAMESE_STRING = "Cổng thông tin bất động sản";
    protected final String ALPHABETIC_NUMERIC_STRING = "123a123b";
    protected final String SPECIAL_CHARACTER_STRING = "--@#$%^&*!";
    protected final String ALL_SPACE_STRING = "             ";
    protected final String ENTER_CHARACTER_STRING = "\n\n\n\n\n";
    protected final String WITHOUT_TRIM_VIETNAMESE_STRING = " Cổng thông tin bất động sản        ";
    protected final String NULL_STRING = null;

    protected final ArrayList EMPTY_LIST = new ArrayList();

    protected final Long POSITIVE_NOT_EXISTED_ID = 99L;
    protected final Long EXISTED_ID = 1L;
    protected final Long NOT_EXISTED_ID = -1L;
    protected final Long NEGATIVE_NOT_EXISTED_ID = -99L;
    protected final Long ZERO_NOT_EXISTED_ID = 0L;
    protected final Long NULL_ID = null;
    
    protected final long[] EXISTED_IDs = {1L, 2L, 3L};

    protected final BigDecimal POSITIVE_PRICE = BigDecimal.valueOf(999999);
    protected final BigDecimal NEGATIVE_PRICE = BigDecimal.valueOf(-999999);
    protected final BigDecimal ZERO_PRICE = BigDecimal.ZERO;
    
    protected final int PAGE_SIZE = 2;
    protected final int EXISTED_PAGE = 1;
    protected final int OUT_RANGE_PAGE = 99999;
    
    protected void testFail(boolean result) {
        //Delete fail
        assertEquals(false, result);
    }

    protected void testFail(Object result) {
        //Save fail
        assertEquals(true, result == null);
    }
    
    protected void testFail(ArrayList result) {
        //Save fail
        assertEquals(true, result == null);
    }
    
    protected void testFail(long result) {
        //Count fail
        assertEquals(-1, result);
    }
}
