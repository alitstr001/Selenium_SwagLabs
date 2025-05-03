package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    static float totalPrice = 0;
    private final WebDriver driver;
    private final By selectedProductsPrices = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price'])");
    private final By checkOutBTN = By.id("checkout");
    private final By continueShoppingBTN = By.id("continue-shopping");

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPrices() {
        try {
            List<WebElement> productsPrices = driver.findElements(selectedProductsPrices);
            for (int i = 1; i <= productsPrices.size(); i++) {
                By elements = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String price = Utility.getTextData(driver, elements);
                totalPrice += Float.parseFloat(price.replace("$", ""));
            }
            LogsUtils.info("Total Price is : " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            LogsUtils.info("No Products Selected to the cart, The Result is = 0");
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public boolean comparingBothPrices(String price) {
        return getTotalPrices().equals(price);
    }

    public P04_CheckoutPage clickOnCheckoutBTN() {
        Utility.clickOnElement(driver, checkOutBTN);
        return new P04_CheckoutPage(driver);

    }
}

