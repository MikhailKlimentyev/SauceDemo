package by.teachmeskills.tests;

import by.teachmeskills.pages.MenuPage;
import org.testng.annotations.Test;

import static by.teachmeskills.domain.Constants.*;
import static by.teachmeskills.pages.LoginPage.*;

public class LoginTest extends BaseTest {

    @Test
    public void productsPageShouldBeOpenedAfterSuccessfulLogin() {
        loginPage.openPage();
        loginPage.isPageOpened();

        loginPage.login(STANDARD_USER_USER_NAME, PASSWORD);
        productsPage.isPageOpened();
        String productsLabel = productsPage.getProductsLabel();
        productsSteps.productsLabelShouldBeLike(productsLabel, "Products");

        int productItemsNumber = productsPage.getProductItems().size();
        productsSteps.productsNumberShouldBeLike(productItemsNumber, 6);
    }

    @Test
    public void logoutShouldLeadToLoginPage() {
        loginPage.openPage();
        loginPage.isPageOpened();

        loginPage.login(STANDARD_USER_USER_NAME, PASSWORD);
        MenuPage menuPage = productsPage.openMenu();
        menuPage.isPageOpened();

        menuPage.logOut();
        loginPage.isPageOpened();
        boolean isLoginDisplayed = loginPage.isElementDisplayed(LOGIN_BUTTON);
        String pageUrl = loginPage.getPageUrl();
        loginSteps.loginPageShouldBeOpened(isLoginDisplayed, pageUrl, LOGIN_PAGE_URL);
    }

    @Test
    public void errorMessageShouldAppearOnAttemptToLoginAsLockedUser() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.login(LOCKED_OUT_USER_USER_NAME, PASSWORD);
        String errorMessage = loginPage.getLockedUserMessage();
        loginSteps.errorMessageShouldAppearAfterLoginByLockedUser(errorMessage, LOCKED_USER_ERROR_MESSAGE);
    }
}
