/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author Phong
 */
public abstract class GUITest {

    protected WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    protected abstract void openSite() throws InterruptedException;
    
    protected void testOnlyOneExisted(String xpath) {
        assertFalse("MISSING " + xpath, driver.findElements(
                By.xpath(xpath))
                .size() != 1);
    }

    protected void testExisted(String xpath) {
        assertFalse("MISSING " + xpath, driver.findElements(
                By.xpath(xpath))
                .size() < 1);
    }
}
