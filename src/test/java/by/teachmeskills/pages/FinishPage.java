package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishPage extends BasePage {

    public static final By FINISH_PAGE_LOCATOR = By.id("checkout_complete_container");

    public FinishPage(WebDriver driver) {
        super(driver);
    }

    public String getText() {
        return driver.findElement(FINISH_PAGE_LOCATOR).getText();
    }
}
