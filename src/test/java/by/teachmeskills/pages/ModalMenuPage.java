package by.teachmeskills.pages;

import by.teachmeskills.pages.base.ModalBasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ModalMenuPage extends ModalBasePage {

    public static final By LOGOUT_LOCATOR = By.id("logout_sidebar_link");
    public static final By ALL_ITEMS_LOCATOR = By.id("inventory_sidebar_link");

    public ModalMenuPage(WebDriver driver) {
        super(driver);
    }

    @Step("Log out")
    public LoginPage logOut() {
        log.info(String.format("Logging out by click on button with %s locator", LOGOUT_LOCATOR));
        driver.findElement(LOGOUT_LOCATOR).click();
        return new LoginPage(driver);
    }

    @Override
    public ModalMenuPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_ITEMS_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Modal menu page is not opened. " +
                    "Element with locator %s is not visible on the page", ALL_ITEMS_LOCATOR));
            Assert.fail("Menu Page is not opened");
        } finally {
            return this;
        }
    }
}
