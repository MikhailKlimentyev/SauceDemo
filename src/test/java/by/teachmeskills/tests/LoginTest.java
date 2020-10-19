package by.teachmeskills.tests;

import by.teachmeskills.pages.ModalMenuPage;
import by.teachmeskills.pages.ProductsPage;
import by.teachmeskills.tests.listeners.RetryAnalyzer;
import by.teachmeskills.tests.listeners.TestListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static by.teachmeskills.domain.Constants.*;
import static by.teachmeskills.pages.LoginPage.LOGIN_BUTTON;
import static by.teachmeskills.pages.LoginPage.LOGIN_PAGE_URL;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidUserNameAndInvalidPasswordDataProvider")
    public static Object[][] invalidUserNameAndInvalidPasswordDataProvider() {
        return new Object[][]{
                {STANDARD_USER_USER_NAME, "Invalid password"},
                {"Invalid user name", PASSWORD},
        };
    }

    @Test(description = "Products page should be opened after successful login")
    public void productsPageShouldBeOpenedAfterSuccessfulLogin() {
        ProductsPage productsPage = safelyLogin();
        String productsLabel = productsPage.getProductsLabel();
        productsSteps.productsLabelShouldBeLike(productsLabel, "Products");

        int productItemsNumber = productsPage.getProductItems().size();
        productsSteps.productsNumberShouldBeLike(productItemsNumber, 6);
    }

    @Test(description = "Logout should lead to login page",
            retryAnalyzer = RetryAnalyzer.class)
    public void logoutShouldLeadToLoginPage() {
        ProductsPage productsPage = safelyLogin();
        ModalMenuPage modalMenuPage = productsPage
                .openMenu()
                .isPageOpened();
        modalMenuPage
                .logOut();
        loginPage
                .isPageOpened();

        boolean isLoginDisplayed = loginPage.isElementDisplayed(LOGIN_BUTTON);
        String pageUrl = loginPage.getPageUrl();
        loginSteps.loginPageShouldBeOpened(isLoginDisplayed, pageUrl, LOGIN_PAGE_URL);
    }

    @Test(description = "Error message should appear on attempt to login as locked user")
    public void errorMessageShouldAppearOnAttemptToLoginAsLockedUser() {
        loginPage
                .openPage()
                .isPageOpened()
                .login(LOCKED_OUT_USER_USER_NAME, PASSWORD);

        String errorMessage = loginPage.getLockedUserMessage();
        loginSteps.errorMessageShouldAppearAfterLoginByLockedUser(errorMessage, LOCKED_USER_ERROR_MESSAGE);
    }

    @Test(description = "Error message should appear on attempt to login with invalid username and password",
            dataProvider = "invalidUserNameAndInvalidPasswordDataProvider")
    public void errorMessageShouldAppearOnAttemptToLoginWithInvalidUsernameAndPassword(String userName,
                                                                                       String password) {
        loginPage
                .openPage()
                .isPageOpened()
                .login(userName, password);

        String errorMessage = loginPage.getLockedUserMessage();
        loginSteps.errorMessageShouldAppearAfterLoginByLockedUser(errorMessage,
                INVALID_USER_NAME_PASSWORD_ERROR_MESSAGE);
    }
}
