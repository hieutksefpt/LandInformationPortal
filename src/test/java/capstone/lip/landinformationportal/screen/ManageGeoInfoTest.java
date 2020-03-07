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
    
    @Test
    public void checkExist() throws Exception {
        driver.get("http://localhost:8080/managegeoinfo.xhtml");
        Thread.currentThread().sleep(1000);
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'searchbox-Address')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'cbb-Province')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'cbb-Street')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'cbb-District')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'cbb-SegmentOfStreet')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'cbb-IpgType')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'txtInput-Name')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'txtInput-Coordinate')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'btn-AddNew')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'btn-Save')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'btn-Delete')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'txtInput-CoordinateContribute')]"))
                .isEmpty());
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'txtInput-Name')]"))
                .isEmpty());
    }
}
