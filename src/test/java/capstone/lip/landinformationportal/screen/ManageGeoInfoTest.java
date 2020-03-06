/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen;

import capstone.lip.landinformationportal.common.WebDriverUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ManageGeoInfoTest extends WebDriverUtil {
    
    @Test
    public void visitIndexPage() throws Exception {
        driver.get("http://localhost:8080/managegeoinfo.xhtml");
        Assert.assertNotNull(driver.findElements(By.id("123456")));
    }
}
