package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private final WebDriver driver;
    private final By userNameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterUserName(String usrName) {
        Utility.sendData(driver, userNameField, usrName);
        return this;
    }

    public P01_LoginPage enterPassword(String passwd) {
        Utility.sendData(driver, passwordField, passwd);
        return this;
    }

    public P02_LandingPage clickOnLogin() {
        Utility.clickOnElement(driver, loginBtn);
        return new P02_LandingPage(driver);
    }

    public boolean assertLoginTC(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }

}
