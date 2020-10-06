package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MenuPage extends BasePage {

    public static final By LOGOUT_LOCATOR = By.id("logout_sidebar_link");
    public static final By ALL_ITEMS_LOCATOR = By.id("inventory_sidebar_link");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void logOut() {
        driver.findElement(LOGOUT_LOCATOR).click();
    }

    public void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_ITEMS_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Menu Page is not opened");
        }
    }
}
