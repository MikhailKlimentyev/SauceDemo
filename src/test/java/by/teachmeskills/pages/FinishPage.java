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
public class FinishPage extends BasePage {

    public static final String FINISH_PAGE_URI = "/checkout-complete.html";
    public static final String FINISH_PAGE_URL = BASE_URL + FINISH_PAGE_URI;

    public static final By FINISH_PAGE_LOCATOR = By.id("checkout_complete_container");
    public static final By FINISH_LOCATOR = By.xpath("//*[text()='Finish']");

    public FinishPage(WebDriver driver) {
        super(driver);
    }

    public String getText() {
        log.debug(String.format("Getting text from element with locator %s", FINISH_PAGE_LOCATOR));
        String text = driver.findElement(FINISH_PAGE_LOCATOR).getText();
        log.debug(String.format("Text is %s", text));
        return text;
    }

    @Step("Open finish page")
    @Override
    public FinishPage openPage() {
        log.debug(String.format("Opening finish page with %s url", FINISH_PAGE_URL));
        driver.get(FINISH_PAGE_URL);
        return this;
    }

    @Override
    public FinishPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Finish page is not opened. " +
                    "Element with locator %s is not visible on the page", FINISH_LOCATOR));
            Assert.fail("Finish page is not opened");
        } finally {
            return this;
        }
    }
}
