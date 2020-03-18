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
public class ListOwnRealEstate_Test_NoData extends ListOwnRealEstate_Test {
    
    /*
    * UI_LORE_01: Contain HCC
    * UI_LORE_02: Default visible items
    * UI_LORE_03: Has no data state
     */
    @Test
    public void UI_LORE_01_03() throws Exception {
        //1. Open List Own Real Estate site
        openSite();
        
        //ASSERT
            //HCC
            testExistedHCC();
            //default visible items
            testDefaultExistedItem();
            //items when no data
            testDefaultExistedItem();
    }
}
