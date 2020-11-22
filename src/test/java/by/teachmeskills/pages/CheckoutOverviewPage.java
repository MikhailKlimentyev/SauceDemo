package by.teachmeskills.pages;

import by.teachmeskills.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static by.teachmeskills.domain.Constants.BASE_URL;

@Log4j2
public class CheckoutOverviewPage extends BasePage {

    public static final String CHECKOUT_OVERVIEW_PAGE_URI = "/checkout-step-two.html";
    public static final String CHECKOUT_OVERVIEW_PAGE_URL = BASE_URL + CHECKOUT_OVERVIEW_PAGE_URI;

    public static final By FINISH_BUTTON_LOCATOR = By.className("btn_action");
    public static final By CHECKOUT_YOUR_INFORMATION_LOCATOR = By.xpath("//*[text()='Checkout: Overview']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on finish button")
    public FinishPage clickOnFinishButton() {
        log.info(String.format("Clicking on finish button with locator %s", FINISH_BUTTON_LOCATOR));
        driver.findElement(FINISH_BUTTON_LOCATOR).click();
        return new FinishPage(driver);
    }

    @Step("Open checkout overview page")
    @Override
    public CheckoutOverviewPage openPage() {
        log.debug(String.format("Opening checkout overview page with %s url", CHECKOUT_OVERVIEW_PAGE_URL));
        driver.get(CHECKOUT_OVERVIEW_PAGE_URL);
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_YOUR_INFORMATION_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Checkout overview page is not opened. " +
                    "Element with locator %s is not visible on the page", CHECKOUT_YOUR_INFORMATION_LOCATOR));
            Assert.fail("Checkout Overview page is not opened");
        } finally {
            return this;
        }
    }
}
