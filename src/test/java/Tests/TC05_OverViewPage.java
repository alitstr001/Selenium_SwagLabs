package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P01_LoginPage;
import Pages.P05_OverViewPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
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
public class TC05_OverViewPage {
    private final String USERNAME = getJsonData("validLoginCredentials", "UserName");
    private final String PASSWORD = getJsonData("validLoginCredentials", "Password");
    private final String BASE_URL = getPropertyValue("environment", "Base_URL");
    private final String CURRENT_BROWSER = getPropertyValue("environment", "Browser");
    private final String FIRST_NAME = getJsonData("validCheckOutData", "FirstName") + "-" + Utility.getTimeStamp();
    private final String LAST_NAME = getJsonData("validCheckOutData", "LastName") + "-" + Utility.getTimeStamp();
    private final String ZIP_CODE = new Faker().number().digits(5);
    private final String CURRENT_URL = getPropertyValue("environment", "OrderConfirmation_URL");


    @BeforeMethod
    public void setup() {
        setupDriver(CURRENT_BROWSER);
        LogsUtils.info("Chrome WebDriver is All Set");
        getDriver().get(BASE_URL);
        LogsUtils.info("Url Redirected Successfully");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validateTotalPriceTC() {
        new P01_LoginPage(getDriver()).enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLogin().addAllProductsToCart().clickingOnCartButton().clickOnCheckoutBTN().fillingFormData(FIRST_NAME, LAST_NAME, ZIP_CODE).clickOnContinue();
        Assert.assertTrue(new P05_OverViewPage(getDriver()).verifyTotalPrice());
        new P05_OverViewPage(getDriver()).clickOnFinish();
        Assert.assertTrue(Utility.verifyUrlRedirection(getDriver(), CURRENT_URL));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
