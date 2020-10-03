package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    public static final By FIRST_NAME_INPUT_LOCATOR = By.id("first-name");
    public static final By LAST_NAME_INPUT_LOCATOR = By.id("last-name");
    public static final By POSTAL_CODE_INPUT_LOCATOR = By.id("postal-code");
    public static final By CART_BUTTON_LOCATOR = By.className("cart_button");

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
}
