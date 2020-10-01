package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    public static final By FINISH_BUTTON_LOCATOR = By.className("btn_action");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnFinishButton() {
        driver.findElement(FINISH_BUTTON_LOCATOR).click();
    }
}
