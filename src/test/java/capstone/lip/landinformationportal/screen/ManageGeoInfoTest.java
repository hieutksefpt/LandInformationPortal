/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen;

import capstone.lip.landinformationportal.common.GUITest;
import capstone.lip.landinformationportal.common.TestCommonKeyword;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageGeoInfoTest extends GUITest {

    private final String SEARCHBOX_ADDRESS = "//input[contains(@id,'searchbox-Address')]";
    private final String CBB_PROVINCE = "//select[contains(@id,'cbb-Province')]";
    private final String CBB_DISTRICT = "//select[contains(@id,'cbb-District')]";
    private final String CBB_STREET = "//select[contains(@id,'cbb-Street')]";
    private final String CBB_SEGMENT = "//select[contains(@id,'cbb-SegmentOfStreet')]";

    private final String CBB_IPGTYPE = "//select[contains(@id,'cbb-IpgType')]";
    private final String TXT_INPUT_NAME = "//input[contains(@id,'txtInput-Name')]";

    private final String BTN_ADD_NEW = "//button[contains(@id,'btn-AddNew')]";
    private final String BTN_SAVE = "//button[contains(@id,'btn-Save')]";
    private final String BTN_DELETE = "//button[contains(@id,'btn-Delete')]";
    private final String BTN_CLEAR = "//button[contains(@id,'btn-Clear')]";

    private final String BTN_SAVE_SEGMENT = "//button[contains(@id,'btn-SaveSegmentStreet')]";
    private final String BTN_CLEAR_SEGMENT = "//button[contains(@id,'btn-ClearSegment')]";

    private void openSite() throws InterruptedException {
        driver.get("http://" + TestCommonKeyword.HOST + "/admin/managegeoinfo.xhtml");
        Thread.currentThread().sleep(200);
    }

    private void checkDefaultVisibleItem() {

        checkExistItem(SEARCHBOX_ADDRESS);
        checkExistItem(CBB_PROVINCE);
        checkExistItem(CBB_DISTRICT);
        checkExistItem(CBB_STREET);
        checkExistItem(CBB_SEGMENT);

        checkExistItem(CBB_IPGTYPE);
        checkExistItem(TXT_INPUT_NAME);
        //pending - wait document
        //checkExistItem("//input[contains(@id,'txtInput-Coordinate')]");

        checkExistItem(BTN_ADD_NEW);
        checkExistItem(BTN_SAVE);
        checkExistItem(BTN_DELETE);
        checkExistItem(BTN_CLEAR);
    }

    private void checkSegmentItem() {
        //pending - wait document
        //checkExistItem("//input[contains(@id,'txtInput-CoordinateContribute')]");
        checkExistItem(BTN_SAVE_SEGMENT);
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
        checkDefaultVisibleItem();
    }

    /*
    * GUI_MIC_03: Verify value of combo box type
     */
    @Test
    public void GUI_MIC_03() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        List<WebElement> cbbs = cbbIpgType.getOptions();

        assertEquals(4, cbbs.size());

        assertEquals(TestCommonKeyword.CBB_IPGTYPE_PROVINCE_TEXT,
                cbbs.get(0).getText());
        assertEquals(TestCommonKeyword.CBB_IPGTYPE_DISTRICT_TEXT,
                cbbs.get(1).getText());
        assertEquals(TestCommonKeyword.CBB_IPGTYPE_STREET_TEXT,
                cbbs.get(2).getText());
        assertEquals(TestCommonKeyword.CBB_IPGTYPE_SEGMENT_TEXT,
                cbbs.get(3).getText());
    }

    /*
    * GUI_MIC_04: Normal state when interact with province
     */
    @Test
    public void GUI_MIC_04() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_PROVINCE_VALUE);
        checkDefaultVisibleItem();
    }

    /*
    * GUI_MIC_05: Normal state when interact with district
     */
    @Test
    public void GUI_MIC_05() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_DISTRICT_VALUE);
        checkDefaultVisibleItem();
    }

    /*
    * GUI_MIC_06: Normal state when interact with street
     */
    @Test
    public void GUI_MIC_06() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_STREET_VALUE);
        checkDefaultVisibleItem();
    }

    /*
    * GUI_MIC_07: Segment state when interact with segment
     */
    @Test
    public void GUI_MIC_07() throws Exception {
        openSite();
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_SEGMENT_VALUE);
        //checkDefaultVisibleItem();
    }

    /*
    * GUI_MIC_08: Street state when add new street
     */
    @Test
    public void GUI_MIC_08() throws Exception {
        //
    }

    /*
    * GUI_MIC_09: Clear button clear text input in normal state
     */
    @Test
    public void GUI_MIC_09() throws Exception {
        openSite();

        //Select street
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_STREET_VALUE);

        //Add value
        driver.findElement(By.xpath(TXT_INPUT_NAME)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
        //thiếu coordinate
        //lat
        Thread.currentThread().sleep(100);
        //lng
        Thread.currentThread().sleep(100);

        //Clear
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);

        //Test
        assertTrue(driver.findElement(By.xpath(TXT_INPUT_NAME)).getText().isEmpty());
    }

    /*
    * GUI_MIC_10: Clear button clear text input in street state
     */
    @Test
    public void GUI_MIC_10() throws Exception {
        openSite();

        //Select street
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_STREET_VALUE);

        //Add value
        driver.findElement(By.xpath(TXT_INPUT_NAME)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
        //thiếu coordinate
        //lat
        Thread.currentThread().sleep(100);
        //lng
        Thread.currentThread().sleep(100);

        //Clear
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);

        //Test
        assertTrue(driver.findElement(By.xpath(TXT_INPUT_NAME)).getText().isEmpty());
        assertEquals(cbbIpgType.getFirstSelectedOption().getText(),
                TestCommonKeyword.CBB_IPGTYPE_STREET_TEXT);
    }

    /*
    * GUI_MIC_11: Clear button clear text input in segment state
     */
    @Test
    public void GUI_MIC_11() throws Exception {
        openSite();

        //Select street
        Select cbbIpgType = new Select(driver.findElement(By.xpath(CBB_IPGTYPE)));
        cbbIpgType.selectByValue(TestCommonKeyword.CBB_IPGTYPE_SEGMENT_VALUE);

        //Add value
        driver.findElement(By.xpath(TXT_INPUT_NAME)).sendKeys("Some thing");
        Thread.currentThread().sleep(100);
        //thiếu coordinate
        //lat
        Thread.currentThread().sleep(100);
        //lng
        Thread.currentThread().sleep(100);

        //Clear
        driver.findElement(By.xpath(BTN_CLEAR)).click();
        Thread.currentThread().sleep(100);

        //Test
        assertTrue(driver.findElement(By.xpath(TXT_INPUT_NAME)).getText().isEmpty());
        assertEquals(cbbIpgType.getFirstSelectedOption().getText(),
                TestCommonKeyword.CBB_IPGTYPE_SEGMENT_TEXT);
    }
    
    /*
    * GUI_MIC_12: Clear button clear input only
     */
    @Test
    public void GUI_MIC_12() throws Exception {
        //
    }
}
