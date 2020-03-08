/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen;

import capstone.lip.landinformationportal.common.ChromeDevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageGeoInfoTest extends ChromeDevice {
    
    private void openSite() throws InterruptedException {
        driver.get("http://localhost:8080/managegeoinfo.xhtml");
        Thread.currentThread().sleep(1000);
    }
    
    private void checkCommonItem() {
        assertFalse(driver.findElements(
                By.xpath("//*[contains(@id,'searchbox-Address')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//select[contains(@id,'cbb-Province')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//select[contains(@id,'cbb-Street')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//select[contains(@id,'cbb-District')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//select[contains(@id,'cbb-SegmentOfStreet')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//select[contains(@id,'cbb-IpgType')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//input[contains(@id,'txtInput-Name')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//input[contains(@id,'txtInput-Coordinate')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//button[contains(@id,'btn-AddNew')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//button[contains(@id,'btn-Save')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//button[contains(@id,'btn-Delete')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//input[contains(@id,'txtInput-CoordinateContribute')]"))
                .isEmpty());
        assertFalse(driver.findElements(
                By.xpath("//input[contains(@id,'txtInput-Name')]"))
                .isEmpty());
    }
    
    @Test
    public void testExistedItem_UC001() throws Exception {
        openSite();
        checkCommonItem();
    }
}
