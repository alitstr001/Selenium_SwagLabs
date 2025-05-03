package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverViewPage {
    private final WebDriver driver;
    private final By finishBTN = By.id("finish");
    private final By priceBeforeTax = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By totalPriceAfterTax = By.className("summary_total_label");

    public P05_OverViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public Float getSubTotal() {
        LogsUtils.info("The Order Total Before Adding Taxes : " + Utility.getTextData(driver, priceBeforeTax).replace("Item total: $", ""));
        return Float.parseFloat(Utility.getTextData(driver, priceBeforeTax).replace("Item total: $", ""));

    }

    public Float getTax() {
        LogsUtils.info("The Taxes : " + Utility.getTextData(driver, tax).replace("Tax: $", ""));
        return Float.parseFloat(Utility.getTextData(driver, tax).replace("Tax: $", ""));
    }

    public Float getTotal() {
        LogsUtils.info("The Actual Order Total After Adding Taxes : " + Utility.getTextData(driver, totalPriceAfterTax).replace("Total: $", ""));
        return Float.parseFloat(Utility.getTextData(driver, totalPriceAfterTax).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        LogsUtils.info("The Calculated Order Total After Adding Taxes : " + (getSubTotal() + getTax()));
        return String.valueOf(getSubTotal() + getTax());
    }

    public boolean verifyTotalPrice() {
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public P06_OrderConfirmationPage clickOnFinish() {
        Utility.clickOnElement(driver, finishBTN);
        return new P06_OrderConfirmationPage(driver);
    }
}
