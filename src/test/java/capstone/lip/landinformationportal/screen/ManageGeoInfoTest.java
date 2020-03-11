/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen;

import capstone.lip.landinformationportal.common.AdminUITest;
import capstone.lip.landinformationportal.screen.element.IPGType;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import capstone.lip.landinformationportal.screen.element.GroupLocation;

/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageGeoInfoTest extends AdminUITest 
        implements GroupLocation, IPGType {

    private final String TXT_NAME = "//input[contains(@id,'txtInput-Name')]";
    private final String LBL_COORDINATE = "//input[contains(@id,'lbl-Coordinate')]";
    private final String TXT_LAT = "//input[contains(@id,'txtInput-Latitude')]";
    private final String TXT_LNG = "//input[contains(@id,'txtInput-Longitude')]";

    private final String BTN_ADD_NEW = "//button[contains(@id,'btn-AddNew')]";
    private final String BTN_SAVE = "//button[contains(@id,'btn-Save')]";
    private final String BTN_DELETE = "//button[contains(@id,'btn-Delete')]";
    private final String BTN_CLEAR = "//button[contains(@id,'btn-Clear')]";

    private final String TXT_SEGMENT = "//button[contains(@id,'txtInput-SegmentName')]";
    private final String LBL_FORMED_COORDINATE = "//input[contains(@id,'lbl-FormedCoordinate')]";
    private final String TXT_FORMED_LAT = "//button[contains(@id,'txtInput-FormedLatitude')]";
    private final String TXT_FORMED_LNG = "//button[contains(@id,'txtInput-FormedLongitude')]";
    private final String BTN_SAVE_SEGMENT = "//button[contains(@id,'btn-SaveSegmentStreet')]";
    private final String BTN_CLEAR_SEGMENT = "//button[contains(@id,'btn-ClearSegment')]";

    private void openSite() throws InterruptedException {
        driver.get("http://" + HOST + "/admin/managegeoinfo.xhtml");
        Thread.currentThread().sleep(200);
    }

    @Override
    public void checkGroupLocation() {
        checkExistItem(SEARCHBOX_ADDRESS);
        checkExistItem(CBB_PROVINCE);
        checkExistItem(CBB_DISTRICT);
        checkExistItem(CBB_STREET);
        checkExistItem(CBB_SEGMENT);
    }
    
    private void checkItemNormalState() {
        checkExistItem(TXT_NAME);
        
        checkExistItem(LBL_COORDINATE);
        checkExistItem(TXT_LAT);
        checkExistItem(TXT_LNG);
    }
    
    private void checkCommonButton() {
        checkExistItem(LBL_DO_WITH);
        checkExistItem(CBB_IPGTYPE);
        
        checkExistItem(BTN_ADD_NEW);
        checkExistItem(BTN_SAVE);
        checkExistItem(BTN_DELETE);
        checkExistItem(BTN_CLEAR);
    }

    private void checkSegmentItem() {
        checkExistItem(TXT_SEGMENT);
        
        checkExistItem(LBL_FORMED_COORDINATE);
        checkExistItem(TXT_FORMED_LAT);
        checkExistItem(TXT_FORMED_LNG);
        
        checkExistItem(BTN_SAVE_SEGMENT);
        checkExistItem(BTN_CLEAR_SEGMENT);
    }

    /*
    * GUI_MIG_01: Contain admin navigation bar
     */
    @Test
    public void GUI_MIG_01() throws Exception {
        openSite();
        checkAdminLeftBarItem();
    }

    /*
    * GUI_MIC_02: Normal state
     */
    @Test
    public void GUI_MIC_02() throws Exception {
        openSite();
        
        checkGroupLocation();
        checkItemNormalState();
        checkCommonButton();
    }

    /*
    * GUI_MIC_03: Verify value of combo box type
     */
    @Test
    public void GUI_MIC_03() throws Exception {
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
    * GUI_MIC_04: Normal state when interact with province
     */
    @Test
    public void GUI_MIC_04() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_PROVINCE_VALUE);
        
        checkGroupLocation();
        checkItemNormalState();
        checkCommonButton();
    }

    /*
    * GUI_MIC_05: Normal state when interact with district
     */
    @Test
    public void GUI_MIC_05() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_DISTRICT_VALUE);
        
        checkGroupLocation();
        checkItemNormalState();
        checkCommonButton();
    }

    /*
    * GUI_MIC_06: Normal state when interact with street
     */
    @Test
    public void GUI_MIC_06() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_STREET_VALUE);
        
        checkGroupLocation();
        checkItemNormalState();
        checkCommonButton();
    }

    /*
    * GUI_MIC_07: Segment state when interact with segment
     */
    @Test
    public void GUI_MIC_07() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(CBB_IPGTYPE_SEGMENT_VALUE);
        
        checkGroupLocation();
        checkSegmentItem();
        checkCommonButton();
    }

    /*
    * GUI_MIC_08: Street state when add new street
     */
    @Test
    public void GUI_MIC_08() throws Exception {
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
        checkItemNormalState();
        //ASSERT segment items are visible
        checkSegmentItem();
    }

    /*
    * GUI_MIC_09: Clear button clear text input in normal state
     */
    @Test
    public void GUI_MIC_09() throws Exception {
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
    * GUI_MIC_10: Clear button clear text input in street state
     */
    @Test
    public void GUI_MIC_10() throws Exception {
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
    * GUI_MIC_11: Clear button clear text input in segment state
     */
    @Test
    public void GUI_MIC_11() throws Exception {
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
    * GUI_MIC_12: Clear button clear input only
     */
    @Test
    public void GUI_MIC_12() throws Exception {
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