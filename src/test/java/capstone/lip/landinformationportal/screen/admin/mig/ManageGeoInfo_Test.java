/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.admin.mig;

import capstone.lip.landinformationportal.common.AdminUITest;
import capstone.lip.landinformationportal.common.SitePath;
import capstone.lip.landinformationportal.screen.commonElement.IPGType;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import capstone.lip.landinformationportal.screen.commonElement.ComboboxGeographyCommon;

/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageGeoInfo_Test extends AdminUITest 
        implements ManageGeoInfo_Skeleton, ComboboxGeographyCommon, IPGType {

    @Override
    protected void openSite() throws InterruptedException {
        driver.get(SitePath.MIG);
        Thread.currentThread().sleep(200);
    }

    @Override
    public void testComboboxGeographyCommon() {
        testOnlyOneExisted(SEARCHBOX_ADDRESS);
        testOnlyOneExisted(CBB_PROVINCE);
        testOnlyOneExisted(CBB_DISTRICT);
        testOnlyOneExisted(CBB_STREET);
        testOnlyOneExisted(CBB_SEGMENT);
    }
    
    private void testItemInNormalState() {
        testOnlyOneExisted(TXT_NAME);
        
        testOnlyOneExisted(LBL_COORDINATE);
        testOnlyOneExisted(TXT_LAT);
        testOnlyOneExisted(TXT_LNG);
    }
    
    private void testCommonButton() {
        testOnlyOneExisted(LBL_DO_WITH);
        testOnlyOneExisted(CBB_IPGTYPE);
        
        testOnlyOneExisted(BTN_ADD_NEW);
        testOnlyOneExisted(BTN_SAVE);
        testOnlyOneExisted(BTN_DELETE);
        testOnlyOneExisted(BTN_CLEAR);
    }

    private void testSegmentItem() {
        testOnlyOneExisted(TXT_SEGMENT);
        
        testOnlyOneExisted(LBL_FORMED_COORDINATE);
        testOnlyOneExisted(TXT_FORMED_LAT);
        testOnlyOneExisted(TXT_FORMED_LNG);
        
        testOnlyOneExisted(BTN_SAVE_SEGMENT);
        testOnlyOneExisted(BTN_CLEAR_SEGMENT);
    }

    /*
    * UI_MIG_01: Contain admin navigation bar
     */
    @Test
    public void UI_MIG_01() throws Exception {
        openSite();
        testManageTabCommon();
    }

    /*
    * UI_MIC_02: Normal state
     */
    @Test
    public void UI_MIC_02() throws Exception {
        openSite();
        
        testComboboxGeographyCommon();
        testItemInNormalState();
        testCommonButton();
    }

    /*
    * UI_MIC_03: Verify value of combo box type
     */
    @Test
    public void UI_MIC_03() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        List<WebElement> cbbs = cbbIpgType.getOptions();

        //Check number of items
        assertEquals(4, cbbs.size());

        assertEquals(CBB_IPGTYPE_PROVINCE_TEXT,
                cbbs.get(0).getText());
        assertEquals(CBB_IPGTYPE_DISTRICT_TEXT,
                cbbs.get(1).getText());
        assertEquals(CBB_IPGTYPE_STREET_TEXT,
                cbbs.get(2).getText());
        assertEquals(CBB_IPGTYPE_SEGMENT_TEXT,
                cbbs.get(3).getText());
    }

    /*
    * UI_MIC_04: Normal state when interact with province
     */
    @Test
    public void UI_MIC_04() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_PROVINCE_VALUE);
        
        testComboboxGeographyCommon();
        testItemInNormalState();
        testCommonButton();
    }

    /*
    * UI_MIC_05: Normal state when interact with district
     */
    @Test
    public void UI_MIC_05() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_DISTRICT_VALUE);
        
        testComboboxGeographyCommon();
        testItemInNormalState();
        testCommonButton();
    }

    /*
    * UI_MIC_06: Normal state when interact with street
     */
    @Test
    public void UI_MIC_06() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_STREET_VALUE);
        
        testComboboxGeographyCommon();
        testItemInNormalState();
        testCommonButton();
    }

    /*
    * UI_MIC_07: Segment state when interact with segment
     */
    @Test
    public void UI_MIC_07() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_SEGMENT_VALUE);
        
        testComboboxGeographyCommon();
        testSegmentItem();
        testCommonButton();
    }

    /*
    * UI_MIC_08: Street state when add new street
     */
    @Test
    public void UI_MIC_08() throws Exception {
        //Open MIG site
        openSite();
        
        //Choose type street
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_STREET_VALUE);
        
        //Enter value into inputs
            //name
        driver.findElement(By.xpath(TXT_NAME)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
            //lat
        driver.findElement(By.xpath(TXT_LAT)).sendKeys("1");
        Thread.currentThread().sleep(100);
            //lng
        driver.findElement(By.xpath(TXT_LNG)).sendKeys("1");
        Thread.currentThread().sleep(100);
        
        //Click add new button
        driver.findElement(By.xpath(BTN_ADD_NEW)).click();
        Thread.currentThread().sleep(100);
        
        //ASSERT normal state items are visible
        testItemInNormalState();
        //ASSERT segment items are visible
        testSegmentItem();
    }

    /*
    * UI_MIC_09: Clear button clear text input in normal state
     */
    @Test
    public void UI_MIC_09() throws Exception {
        openSite();

        //Add value
        driver.findElement(By.xpath(TXT_NAME)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
        //lat
        driver.findElement(By.xpath(TXT_LAT)).sendKeys("1");
        Thread.currentThread().sleep(100);
        //lng
        driver.findElement(By.xpath(TXT_LNG)).sendKeys("1");
        Thread.currentThread().sleep(100);

        //Clear
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);

        //Test
        assertTrue(driver.findElement(By.xpath(TXT_NAME)).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath(TXT_LAT)).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath(TXT_LNG)).getText().isEmpty());
    }

    /*
    * UI_MIC_10: Clear button clear text input in street state
     */
    @Test
    public void UI_MIC_10() throws Exception {
        openSite();

        //Select street
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_STREET_VALUE);

        //Add value
        driver.findElement(By.xpath(TXT_NAME)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
        //lat
        driver.findElement(By.xpath(TXT_LAT)).sendKeys("1");
        Thread.currentThread().sleep(100);
        //lng
        driver.findElement(By.xpath(TXT_LNG)).sendKeys("1");
        Thread.currentThread().sleep(100);

        //Clear
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);

        //Test
        assertTrue(driver.findElement(By.xpath(TXT_NAME)).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath(TXT_LAT)).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath(TXT_LNG)).getText().isEmpty());
        
        assertEquals(cbbIpgType.getFirstSelectedOption().getText(),
                CBB_IPGTYPE_STREET_TEXT);
    }

    /*
    * UI_MIC_11: Clear button clear text input in segment state
     */
    @Test
    public void UI_MIC_11() throws Exception {
        openSite();

        //Select street
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_SEGMENT_VALUE);

        //Add value
        driver.findElement(By.xpath(TXT_SEGMENT)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
        //lat
        driver.findElement(By.xpath(TXT_FORMED_LAT)).sendKeys("1");
        Thread.currentThread().sleep(100);
        //lng
        driver.findElement(By.xpath(TXT_FORMED_LNG)).sendKeys("1");
        Thread.currentThread().sleep(100);

        //Clear
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);

        //Test
        assertTrue(driver.findElement(By.xpath(TXT_SEGMENT)).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath(TXT_FORMED_LAT)).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath(TXT_FORMED_LNG)).getText().isEmpty());
        
        assertEquals(cbbIpgType.getFirstSelectedOption().getText(),
                CBB_IPGTYPE_SEGMENT_TEXT);
    }
    
    /*
    * UI_MIC_12: Clear button clear input only
     */
    @Test
    public void UI_MIC_12() throws Exception {
        //Open MIG site
        openSite();
        
        //Select first value
            //province
        Select cbbProvince = new Select(driver.findElement(By.xpath(CBB_PROVINCE)));
        String selectedProvince = cbbProvince.getFirstSelectedOption().getText();
            //district
        Select cbbDistrict = new Select(driver.findElement(By.xpath(CBB_PROVINCE)));
        String selectedDistrict = cbbDistrict.getFirstSelectedOption().getText();
            //street
        Select cbbStreet = new Select(driver.findElement(By.xpath(CBB_PROVINCE)));
        String selectedStreet = cbbStreet.getFirstSelectedOption().getText();
            //segment
        Select cbbSegment = new Select(driver.findElement(By.xpath(CBB_PROVINCE)));
        String selectedSegment = cbbSegment.getFirstSelectedOption().getText();
            
        //Select district type
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_DISTRICT_VALUE);
        
        //Enter input
            //name
        driver.findElement(By.xpath(TXT_SEGMENT)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
            //lat
        driver.findElement(By.xpath(TXT_FORMED_LAT)).sendKeys("1");
        Thread.currentThread().sleep(100);
            //lng
        driver.findElement(By.xpath(TXT_FORMED_LNG)).sendKeys("1");
        Thread.currentThread().sleep(100);
        
        //Click clear button
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);
        
        
        //ASSERT value in text input is cleared
            //name
        assertTrue(driver.findElement(By.xpath(TXT_SEGMENT))
                .getText().isEmpty());
            //lat
        assertTrue(driver.findElement(By.xpath(TXT_FORMED_LAT))
                .getText().isEmpty());
            //lng
        assertTrue(driver.findElement(By.xpath(TXT_FORMED_LNG))
                .getText().isEmpty());
        
        //ASSERT value in combobox is not changed
            //province
        assertEquals(selectedProvince,
                cbbProvince.getFirstSelectedOption().getText());
            //district
        assertEquals(selectedDistrict,
                cbbDistrict.getFirstSelectedOption().getText());
            //street
        assertEquals(selectedStreet,
                cbbStreet.getFirstSelectedOption().getText());
            //segment
        assertEquals(selectedSegment,
                cbbSegment.getFirstSelectedOption().getText());
            //type
        assertEquals(cbbIpgType.getFirstSelectedOption().getText(),
                CBB_IPGTYPE_DISTRICT_TEXT);
    }
}