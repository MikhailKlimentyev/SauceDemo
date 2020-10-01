package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {

    public static final By LOGOUT_LOCATOR = By.id("logout_sidebar_link");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void logOut() {
        driver.findElement(LOGOUT_LOCATOR).click();
    }
}
