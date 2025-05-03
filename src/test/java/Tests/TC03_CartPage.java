package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_ProductsPage;
import Pages.P03_CartPage;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import static DriverFactory.DriverFactoryClass.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultsListenerClass.class})

public class TC03_CartPage {
    private final String USERNAME = getJsonData("validLoginCredentials", "UserName");
    private final String PASSWORD = getJsonData("validLoginCredentials", "Password");
    private final String BASE_URL = getPropertyValue("environment", "Base_URL");
    private final String CURRENT_BROWSER = getPropertyValue("environment", "Browser");


    @BeforeMethod
    public void setup() {
        setupDriver(CURRENT_BROWSER);
        LogsUtils.info("Chrome WebDriver is All Set");
        getDriver().get(BASE_URL);
        LogsUtils.info("Url Redirected Successfully");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void VerifyingCartTotalPriceTC() {
        new P01_LoginPage(getDriver()).enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLogin().addRandomProducts(3, 6).clickingOnCartButton();
        Assert.assertTrue(new P03_CartPage(getDriver()).comparingBothPrices(new P02_ProductsPage(getDriver()).getTotalPriceForSelectedProducts()));

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
