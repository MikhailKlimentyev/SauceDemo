package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CheckoutInformationPage extends BasePage {

    public static final By FIRST_NAME_INPUT_LOCATOR = By.id("first-name");
    public static final By LAST_NAME_INPUT_LOCATOR = By.id("last-name");
    public static final By POSTAL_CODE_INPUT_LOCATOR = By.id("postal-code");
    public static final By CART_BUTTON_LOCATOR = By.className("cart_button");
    public static final By CHECKOUT_YOUR_INFORMATION_LOCATOR = By.xpath("//*[text()='Checkout: Your Information']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_INPUT_LOCATOR).sendKeys(postalCode);
    }

    public void clickOnContinueButton() {
        driver.findElement(CART_BUTTON_LOCATOR).click();
    }

    public void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_YOUR_INFORMATION_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Checkout Your Information is not opened");
        }
    }
}
