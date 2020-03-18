/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.admin.mhlf;

import org.junit.Test;
import org.openqa.selenium.By;

/**
 *
 * @author Phong
 * @description: unit testing for test case UI_MHLF_06 ~ UI_MHLF_12
 */
public class ManageHomeLandsFeatures_Test_Data 
        extends ManageHomeLandsFeatures_Test {


    /*
    * UI_MHLF_06: Default state had data
    * UI_MHLF_07: Edit house feature state
    * UI_MHLF_08: Close edit house feature popup
    * UI_MHLF_09: Delete house feature state
    * UI_MHLF_10: Edit land feature state
    * UI_MHLF_11: Close edit land feature popup
    * UI_MHLF_12: Delete land feature state
    */
    @Test
    public void UI_MHLF_06_12() throws Exception {
        //1. Open Manage Home & Land's Feature site
        openSite();
        //ASSERT UI_MHLF_06
            //Default visible item existed
            testDefaultVisibleItem();
            //button edit
            testExisted(BTN_EDIT_FEATURE);
            //button delete
            testExisted(BTN_DELETE_FEATURE);
            
        //2. Click tab house 
        driver.findElement(By.xpath(BTN_HOME_FEATURE_TAB)).click();
        Thread.currentThread().sleep(100);
        //3. Click edit button
        driver.findElement(By.xpath(BTN_EDIT_FEATURE)).click();
        Thread.currentThread().sleep(100);
        //ASSERT UI_MHLF_07
            //popup
            
            //name
            testOnlyOneExisted(TXT_NAME_FEATURE);
            //unit
            testOnlyOneExisted(TXT_UNIT_FEATURE);
            //button save
            testOnlyOneExisted(BTN_SAVE);
            //button close
            testOnlyOneExisted(BTN_CANCEL);
        
        //4. Click close button
        driver.findElement(By.xpath(BTN_CANCEL)).click();
        Thread.currentThread().sleep(100);
        //ASSERT UI_MHLF_08
            //popup is closed
            
            
        //5. Click delete button
        driver.findElement(By.xpath(BTN_DELETE_FEATURE)).click();
        Thread.currentThread().sleep(100);
        //ASSERT UI_MHLF_09
            //confirm dialog
        
        //6. Click no option
        //7. Click tab land
        driver.findElement(By.xpath(BTN_LAND_FEATURE_TAB)).click();
        Thread.currentThread().sleep(100);
        //8. Click edit button
        driver.findElement(By.xpath(BTN_EDIT_FEATURE)).click();
        Thread.currentThread().sleep(100);
        //ASSERT UI_MHLF_10
            //popup
            
            //name
            testOnlyOneExisted(TXT_NAME_FEATURE);
            //unit
            testOnlyOneExisted(TXT_UNIT_FEATURE);
            //button save
            testOnlyOneExisted(BTN_SAVE);
            //button close
            testOnlyOneExisted(BTN_CANCEL);
            
        //9. Click close button
        driver.findElement(By.xpath(BTN_CANCEL)).click();
        Thread.currentThread().sleep(100);
        //ASSERT UI_MHLF_11
            //popup is closed
            
        //10. Click delete button
        driver.findElement(By.xpath(BTN_DELETE_FEATURE)).click();
        Thread.currentThread().sleep(100);
        //ASSERT UI_MHLF_12
            //confirm dialog
    }
    
}