/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phong
 */
public abstract class DataFeatureTest extends CRUDTest {
    
    protected final String NUMBER_VALID_FEATURE_DATA_TYPE = "INT";
    protected final String TEXT_VALID_FEATURE_DATA_TYPE = "STR";
    protected final String INVALID_FEATURE_DATA_TYPE = "ABC";
    
    protected List<String> getNumericDataRange() {
        List<String> dataRange = new ArrayList();
        dataRange.add("1");
        dataRange.add("2");
        dataRange.add("3");
        dataRange.add("4");
        dataRange.add("5");
        
        return dataRange;
    }
    
    protected List<String> getTextDataRange() {
        List<String> dataRange = new ArrayList();
        dataRange.add("Đông");
        dataRange.add("Tây");
        dataRange.add("Nam");
        dataRange.add("Bắc");
        
        return dataRange;
    }
}
