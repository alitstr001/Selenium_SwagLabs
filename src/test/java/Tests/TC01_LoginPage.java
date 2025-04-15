package Tests;

import Pages.P01_LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static DriverFactory.DriverFactoryClass.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

public class TC01_LoginPage {

    private final String USERNAME = getJsonData("validLoginCredentials", "UserName");
    private final String PASSWORD = getJsonData("validLoginCredentials", "Password");
    private final String BASE_URL = getPropertyValue("environment", "Base_URL");
    private final String HOME_URL = getPropertyValue("environment", "Home_URL");
    private final String CURRENT_BROWSER = getPropertyValue("environment", "Browser");


    @BeforeMethod
    public void setup() {
        setupDriver(CURRENT_BROWSER);
        getDriver().get(BASE_URL);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validLoginTC() {
        new P01_LoginPage(getDriver()).enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLogin();
        Assert.assertTrue(new P01_LoginPage(getDriver()).assertLoginTC(HOME_URL));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
