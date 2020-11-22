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
public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_URI = "/index.html";
    public static final String LOGIN_PAGE_URL = BASE_URL + LOGIN_PAGE_URI;

    public static final By USERNAME_INPUT = By.id("user-name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login with {username} and {password}")
    public ProductsPage loginSafely(String username, String password) {
        login(username, password);
        return new ProductsPage(driver);
    }

    @Step("Login with {username} and {password}")
    public LoginPage tryLogin(String username, String password) {
        login(username, password);
        return this;
    }

    public String getLockedUserMessage() {
        log.debug(String.format("Getting error message from element with locator %s", ERROR_MESSAGE));
        String message = driver.findElement(ERROR_MESSAGE).getText();
        log.debug(String.format("Error message is %s", message));
        return message;
    }

    @Step("Open login page")
    @Override
    public LoginPage openPage() {
        log.debug(String.format("Opening login page with %s url", LOGIN_PAGE_URL));
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException ex) {
            log.error(String.format("Login page is not opened. " +
                    "Element with locator %s is not visible on the page", LOGIN_BUTTON));
            Assert.fail("Login page is not opened");
        } finally {
            return this;
        }
    }

    private void login(String username, String password) {
        log.info(String.format("Logging in with username %s and password %s", username, password));
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
