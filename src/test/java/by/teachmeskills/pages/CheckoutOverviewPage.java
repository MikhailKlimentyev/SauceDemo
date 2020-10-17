package by.teachmeskills.pages;

import by.teachmeskills.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static by.teachmeskills.domain.Constants.BASE_URL;

public class CheckoutOverviewPage extends BasePage {

    public static final String CHECKOUT_OVERVIEW_PAGE_URI = "/checkout-step-two.html";
    public static final String CHECKOUT_OVERVIEW_PAGE_URL = BASE_URL + CHECKOUT_OVERVIEW_PAGE_URI;

    public static final By FINISH_BUTTON_LOCATOR = By.className("btn_action");
    public static final By CHECKOUT_YOUR_INFORMATION_LOCATOR = By.xpath("//*[text()='Checkout: Overview']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public FinishPage clickOnFinishButton() {
        driver.findElement(FINISH_BUTTON_LOCATOR).click();
        return new FinishPage(driver);
    }

    @Override
    public CheckoutOverviewPage openPage() {
        driver.get(CHECKOUT_OVERVIEW_PAGE_URL);
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_YOUR_INFORMATION_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Checkout Overview page is not opened");
        } finally {
            return this;
        }
    }
}
