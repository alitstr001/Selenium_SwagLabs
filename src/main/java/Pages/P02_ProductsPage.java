package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_ProductsPage {
    static float totalPrice = 0;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
    private final By addToCartButtonForAllProducts = By.xpath("//button[@class]");
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By selectedProductsPrices = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price'])");
    private final WebDriver driver;

    public P02_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getCartIcon() {
        return numberOfProductsOnCartIcon;
    }

    public P02_ProductsPage addAllProductsToCart() {
        allProducts = driver.findElements(addToCartButtonForAllProducts);
        LogsUtils.info("Number of All Products: " + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }

    public String getNumberOfProductsOnCartIcon() {
        try {
            LogsUtils.info("Number Of Selected Products : " + Utility.getTextData(driver, numberOfProductsOnCartIcon));
            return Utility.getTextData(driver, numberOfProductsOnCartIcon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() {
        try {
            selectedProducts = driver.findElements(numberOfSelectedProducts);
            LogsUtils.info("Number Of Selected Products : " + selectedProducts.size());
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public P02_ProductsPage addRandomProducts(int numberOfSelectedProducts, int totalNumberOfProducts) {
        Set<Integer> randomNumbers = Utility.generateUniqueNumber(numberOfSelectedProducts, totalNumberOfProducts);
        for (int random : randomNumbers) {
            LogsUtils.info("Random Number : " + random);
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + random + "]");
            Utility.clickOnElement(driver, addToCartButtonForAllProducts);


        }
        LogsUtils.info("Selected Products are : " + randomNumbers);
        return this;
    }

    public boolean comparingNumberOfSelectedProductsWithCart() {
        LogsUtils.info("Number Of Selected Products Matched The cart Number successfully!!");
        return getNumberOfProductsOnCartIcon().equals(getNumberOfSelectedProducts());

    }

    public P03_CartPage clickingOnCartButton() {
        Utility.clickOnElement(driver, cartIcon);
        return new P03_CartPage(driver);
    }


    public String getTotalPriceForSelectedProducts() {
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
}

