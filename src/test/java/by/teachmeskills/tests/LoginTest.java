package by.teachmeskills.tests;

import by.teachmeskills.pages.ModalMenuPage;
import by.teachmeskills.pages.ProductsPage;
import by.teachmeskills.tests.listeners.RetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.teachmeskills.domain.Constants.*;
import static by.teachmeskills.pages.LoginPage.LOGIN_BUTTON;
import static by.teachmeskills.pages.LoginPage.LOGIN_PAGE_URL;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidUserNameAndInvalidPasswordDataProvider")
    public static Object[][] invalidUserNameAndInvalidPasswordDataProvider() {
        return new Object[][]{
                {STANDARD_USER_USER_NAME, "Invalid password", INVALID_USER_NAME_PASSWORD_ERROR_MESSAGE},
                {"Invalid user name", PASSWORD, INVALID_USER_NAME_PASSWORD_ERROR_MESSAGE},
                {LOCKED_OUT_USER_USER_NAME, PASSWORD, LOCKED_USER_ERROR_MESSAGE},
        };
    }

    @Test(description = "Products page should be opened after successful login")
    public void productsPageShouldBeOpenedAfterSuccessfulLogin() {
        ProductsPage productsPage = loginSafely();
        String productsLabel = productsPage.getProductsLabel();
        productsAssert.productsLabelShouldBeLike(productsLabel, "Products");
        int productItemsNumber = productsPage.getProductItems().size();
        productsAssert.productsNumberShouldBeLike(productItemsNumber, 6);
    }

    @Test(description = "Logout should lead to login page",
            retryAnalyzer = RetryAnalyzer.class)
    public void logoutShouldLeadToLoginPage() {
        ProductsPage productsPage = loginSafely();
        ModalMenuPage modalMenuPage = productsPage
                .openMenu()
                .isPageOpened();
        modalMenuPage
                .logOut();
        loginPage
                .isPageOpened();

        boolean isLoginDisplayed = loginPage.isElementDisplayed(LOGIN_BUTTON);
        String pageUrl = loginPage.getPageUrl();
        loginAssert.loginPageShouldBeOpened(isLoginDisplayed, pageUrl, LOGIN_PAGE_URL);
    }

    @Test(description = "Error message should appear on attempt to login with invalid username and password" +
            " or with locked user",
            dataProvider = "invalidUserNameAndInvalidPasswordDataProvider")
    public void errorMessageShouldAppearOnAttemptToLoginWithInvalidUsernameAndPassword(String userName,
                                                                                       String password,
                                                                                       String expectedErrorMessage) {
        loginPage
                .openPage()
                .isPageOpened()
                .tryLogin(userName, password);
        String errorMessage = loginPage.getLockedUserMessage();
        loginAssert.errorMessageShouldAppearAfterLoginByLockedUser(errorMessage, expectedErrorMessage);
    }
}
