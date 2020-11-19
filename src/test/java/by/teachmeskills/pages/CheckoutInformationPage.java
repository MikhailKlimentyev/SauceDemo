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
public class CheckoutInformationPage extends BasePage {

    public static final String CHECKOUT_INFORMATION_PAGE_URI = "/checkout-step-one.html";
    public static final String CHECKOUT_INFORMATION_PAGE_URL = BASE_URL + CHECKOUT_INFORMATION_PAGE_URI;

    public static final By FIRST_NAME_INPUT_LOCATOR = By.id("first-name");
    public static final By LAST_NAME_INPUT_LOCATOR = By.id("last-name");
    public static final By POSTAL_CODE_INPUT_LOCATOR = By.id("postal-code");
    public static final By CART_BUTTON_LOCATOR = By.className("cart_button");
    public static final By CHECKOUT_YOUR_INFORMATION_LOCATOR = By.xpath("//*[text()='Checkout: Your Information']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill in information with {firstName} firstName, {lastName} lastName, {postalCode} postalCode")
    public CheckoutInformationPage fillInformation(String firstName, String lastName, String postalCode) {
        log.info(String.format("Entering first name %s in input with locator %s", firstName, FIRST_NAME_INPUT_LOCATOR));
        driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(firstName);
        log.info(String.format("Entering last name %s in input with locator %s", lastName, LAST_NAME_INPUT_LOCATOR));
        driver.findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(lastName);
        log.info(String.format("Entering postal code %s in input with locator %s", lastName, POSTAL_CODE_INPUT_LOCATOR));
        driver.findElement(POSTAL_CODE_INPUT_LOCATOR).sendKeys(postalCode);
        return this;
    }

    @Step("Click on continue button")
    public CheckoutOverviewPage clickOnContinueButton() {
        log.info(String.format("Clicking on continue button with locator %s", CART_BUTTON_LOCATOR));
        driver.findElement(CART_BUTTON_LOCATOR).click();
        return new CheckoutOverviewPage(driver);
    }

    @Step("Open checkout information page")
    @Override
    public CheckoutInformationPage openPage() {
        log.debug(String.format("Opening checkout information page with %s url", CHECKOUT_INFORMATION_PAGE_URL));
        driver.get(CHECKOUT_INFORMATION_PAGE_URL);
        return this;
    }

    @Override
    public CheckoutInformationPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_YOUR_INFORMATION_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Checkout information page is not opened. " +
                    "Element with locator %s is not visible on the page", CHECKOUT_YOUR_INFORMATION_LOCATOR));
            Assert.fail("Checkout Your Information page is not opened");
        } finally {
            return this;
        }
    }
}
