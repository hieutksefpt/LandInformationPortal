/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen;

import capstone.lip.landinformationportal.common.ChromeTest;
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
public class ManageGeoInfoTest extends ChromeTest {
    
    @Test
    public void visitIndexPage() throws Exception {
        driver.get("http://localhost:8080/managegeoinfo.xhtml");
        Thread.currentThread().sleep(1000);
        assertTrue(!driver.findElements(
                By.xpath("//select[contains(@id,'cbb-District')]"))
                .isEmpty());
    }
}
