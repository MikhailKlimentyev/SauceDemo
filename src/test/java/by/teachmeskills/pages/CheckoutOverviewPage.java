package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CheckoutOverviewPage extends BasePage {

    public static final By FINISH_BUTTON_LOCATOR = By.className("btn_action");
    public static final By CHECKOUT_YOUR_INFORMATION_LOCATOR = By.xpath("//*[text()='Checkout: Overview']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnFinishButton() {
        driver.findElement(FINISH_BUTTON_LOCATOR).click();
    }

    public void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_YOUR_INFORMATION_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Checkout Overview page is not opened");
        }
    }
}
