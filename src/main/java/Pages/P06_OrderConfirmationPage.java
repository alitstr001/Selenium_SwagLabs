package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.toWebElement;

public class P06_OrderConfirmationPage {
    private final WebDriver driver;
    private final By backToHomeButton = By.id("back-to-products");
    private final By thankYouMessage = By.tagName("h2");

    public P06_OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkMessageVisibility() {
        LogsUtils.info("Order Completed Successfully!!");
        return toWebElement(driver, thankYouMessage).isDisplayed();
    }

    public P06_OrderConfirmationPage getBackToHome() {
        Utility.clickOnElement(driver, backToHomeButton);
        return this;
    }
}
