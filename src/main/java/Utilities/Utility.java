package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public class Utility {
    private static final String SCREENSHOTS_PATH = "TestOutputs/ScreenShots/";

    public static void clickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(35)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(35)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getTextData(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(35)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait general(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebElement toWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void handlingAlerts(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", toWebElement(driver, locator));
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(toWebElement(driver, locator)).selectByVisibleText(option);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void takeScreenshot(WebDriver driver, String screenShotName) {
        try {
            File screenShotSRC = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenShotFile = new File(SCREENSHOTS_PATH + screenShotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenShotSRC, screenShotFile);
            Allure.addAttachment(screenShotName, Files.newInputStream(screenShotFile.toPath()));
        } catch (IOException e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public static void takeNewScreenshot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL).highlight(toWebElement(driver, locator)).save(SCREENSHOTS_PATH);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> generateUniqueNumber(int numberOfSelectedProducts, int totalNumberOfProducts) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberOfSelectedProducts) {
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static boolean verifyUrlRedirection(WebDriver driver, String expectedURL) {
        try {
            Utility.general(driver).until(ExpectedConditions.urlToBe(expectedURL));

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
        LogsUtils.info("The URL Matches Successfully!");
        return true;
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0) {
            return null;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }


}
