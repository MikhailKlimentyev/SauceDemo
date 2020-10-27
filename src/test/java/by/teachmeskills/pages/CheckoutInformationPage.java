package by.teachmeskills.pages;

import by.teachmeskills.pages.base.BasePage;
import by.teachmeskills.utils.AllureUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static by.teachmeskills.domain.Constants.BASE_URL;

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
        driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_INPUT_LOCATOR).sendKeys(postalCode);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Click on continue button")
    public CheckoutOverviewPage clickOnContinueButton() {
        driver.findElement(CART_BUTTON_LOCATOR).click();
        AllureUtils.takeScreenshot(driver);
        return new CheckoutOverviewPage(driver);
    }

    @Step("Open checkout information page")
    @Override
    public CheckoutInformationPage openPage() {
        driver.get(CHECKOUT_INFORMATION_PAGE_URL);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Override
    public CheckoutInformationPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_YOUR_INFORMATION_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Checkout Your Information page is not opened");
        } finally {
            return this;
        }
    }
}
