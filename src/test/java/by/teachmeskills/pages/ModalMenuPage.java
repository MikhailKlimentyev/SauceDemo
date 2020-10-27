package by.teachmeskills.pages;

import by.teachmeskills.pages.base.ModalBasePage;
import by.teachmeskills.utils.AllureUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ModalMenuPage extends ModalBasePage {

    public static final By LOGOUT_LOCATOR = By.id("logout_sidebar_link");
    public static final By ALL_ITEMS_LOCATOR = By.id("inventory_sidebar_link");

    public ModalMenuPage(WebDriver driver) {
        super(driver);
    }

    @Step("Log out")
    public LoginPage logOut() {
        driver.findElement(LOGOUT_LOCATOR).click();
        AllureUtils.takeScreenshot(driver);
        return new LoginPage(driver);
    }

    @Override
    public ModalMenuPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_ITEMS_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Menu Page is not opened");
        } finally {
            return this;
        }
    }
}
