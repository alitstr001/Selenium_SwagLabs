package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckoutPage {
    private final WebDriver driver;
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    public P04_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_CheckoutPage fillingFormData(String fname, String lname, String zipCode) {
        Utility.sendData(driver, firstNameField, fname);
        Utility.sendData(driver, lastNameField, lname);
        Utility.sendData(driver, zipCodeField, zipCode);
        LogsUtils.info("First Name is :" + fname + "\n" + "Last Name is :" + lname + "\n" + "ZipCode is :" + zipCode + "\n");
        return this;

    }

    public P05_OverViewPage clickOnContinue() {
        Utility.clickOnElement(driver, continueBtn);
        return new P05_OverViewPage(driver);
    }
}
