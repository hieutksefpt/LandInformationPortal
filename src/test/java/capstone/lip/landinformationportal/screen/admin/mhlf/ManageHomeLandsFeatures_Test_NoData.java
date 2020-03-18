/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.admin.mhlf;

import static capstone.lip.landinformationportal.screen.admin.mhlf.ManageHomeLandsFeatures_Skeleton.BTN_HOME_FEATURE_TAB;
import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.*;

/**
 *
 * @author Phong
 * @description: unit testing for test case UI_MHLF_01 ~ UI_MHLF_05
 */
public class ManageHomeLandsFeatures_Test_NoData 
        extends ManageHomeLandsFeatures_Test {
    
    /*
    * UI_MHLF_01: Contain admin navigation bar
    * UI_MHLF_02: Default state no data
    * UI_MHLF_03: Tab land is default selected
    * UI_MHLF_04: Add new feature state
    * UI_MHLF_05: Close add new popup
     */
    @Test
    public void UI_MHLF_01_05() throws Exception {
        //1. Open Manage Home & Land's Feature site
        openSite();
        //ASSERT
            //Contain admin navigation bar
            testManageTabCommon();
            //Default visible item existed
            testDefaultVisibleItem();
            //Tab land is default selected
            assertTrue(driver.findElement(By.xpath(BTN_HOME_FEATURE_TAB))
                    .getAttribute("tabindex").equals("-1"));
            
        //2. Click add new button
        driver.findElement(By.xpath(BTN_ADD_NEW_FEATURE)).click();
        Thread.currentThread().sleep(100);
        //ASSERT
            //popup
            
            //name
            testOnlyOneExisted(TXT_NAME_FEATURE);
            //unit
            testOnlyOneExisted(TXT_UNIT_FEATURE);
            //button save
            testOnlyOneExisted(BTN_ADD_FEATURE);
            //button close
            testOnlyOneExisted(BTN_CANCEL);
            
        //3. Click close button
        driver.findElement(By.xpath(BTN_CANCEL)).click();
        Thread.currentThread().sleep(100);
        //ASSERT
            //popup is not displayed
    }
}
