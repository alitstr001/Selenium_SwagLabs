package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_ProductsPage;
import Utilities.LogsUtils;
import Utilities.Utility;
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

public class TC02_LandingPage {
    private final String USERNAME = getJsonData("validLoginCredentials", "UserName");
    private final String PASSWORD = getJsonData("validLoginCredentials", "Password");
    private final String BASE_URL = getPropertyValue("environment", "Base_URL");
    private final String CURRENT_BROWSER = getPropertyValue("environment", "Browser");
    private final String CART_URL = getPropertyValue("environment", "Cart_URL");


    @BeforeMethod
    public void setup() {
        setupDriver(CURRENT_BROWSER);
        LogsUtils.info("Chrome WebDriver is All Set");
        getDriver().get(BASE_URL);
        LogsUtils.info("Url Redirected Successfully");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkingSelectedProductsTC() {
        new P01_LoginPage(getDriver()).enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLogin().addAllProductsToCart();
        Assert.assertTrue(new P02_ProductsPage(getDriver()).comparingNumberOfSelectedProductsWithCart());

    }

    @Test
    public void selectRandomProductTC() {
        new P01_LoginPage(getDriver()).enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLogin().addRandomProducts(3, 6);
        Assert.assertTrue(new P02_ProductsPage(getDriver()).comparingNumberOfSelectedProductsWithCart());

    }

    @Test
    public void verifyClickingOnCartTC() {
        new P01_LoginPage(getDriver()).enterUserName(USERNAME)
                .enterPassword(PASSWORD).clickOnLogin()
                .clickingOnCartButton();
        Assert.assertTrue(Utility.verifyUrlRedirection(getDriver(), CART_URL));

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
