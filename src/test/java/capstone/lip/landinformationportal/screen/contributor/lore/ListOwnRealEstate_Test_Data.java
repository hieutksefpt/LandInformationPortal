/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.contributor.lore;

import org.junit.Test;

/**
 *
 * @author Phong
 */
public class ListOwnRealEstate_Test_Data extends ListOwnRealEstate_Test {
 
    /*
    * UI_LORE_04: Has data state
     */
    @Test
    public void UI_LORE_04() throws Exception {
        //1. Open List Own Real Estate site
        openSite();
        
        //ASSERT
            //items when data is existed
            testDefaultExistedItem();
            testFormItem();
    }
}
