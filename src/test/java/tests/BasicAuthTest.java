package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AuthSuccessPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class BasicAuthTest {

    private WebDriver driver;
    private static final String DOMAIN = "auth-demo.aerobatic.io";
    private static final String PROTOCOL = "https://";
    private static final String CATALOG = "/protected-standard/";
    private String login = "aerobatic";
    private String password = "aerobatic";

    @BeforeTest
    public void beforeTest() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            throw new Error("Malformed URL Exception expected");
        }
    }

    @Test
    public void basicAuthorizationTest() {
        driver.get(PROTOCOL + login + ':' + password + '@' + DOMAIN + CATALOG);
        AuthSuccessPage authSuccessPage = new AuthSuccessPage(driver);
        assertTrue(authSuccessPage.titleIsAuthSuccess());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
