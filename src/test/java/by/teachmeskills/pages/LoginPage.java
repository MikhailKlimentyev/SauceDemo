package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static by.teachmeskills.domain.Constants.BASE_URL;

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

    public void openPage() {
        driver.get(LOGIN_PAGE_URL);
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getLockedUserMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
