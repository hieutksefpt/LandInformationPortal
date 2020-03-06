/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import java.io.File;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
/**
 *
 * @author Phong
 */
public abstract class WebDriverUtil {

    private static final String CHROMEDRIVER_EXE = "chromedriver.exe";
    protected WebDriver driver;

    @Before
    public void setUp() {
        String driverFile = findFile();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverFile))
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.addArguments("--headless");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.merge(capabilities);
        this.driver = new ChromeDriver(service, options);
    }

    private String findFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(CHROMEDRIVER_EXE);
        return url.getFile();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
