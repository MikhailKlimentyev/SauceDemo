package by.teachmeskills.pages.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage extends AbstractPage {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public abstract BasePage openPage();
}
