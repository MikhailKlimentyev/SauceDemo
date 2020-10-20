package by.teachmeskills.tests;

import by.teachmeskills.pages.ModalMenuPage;
import by.teachmeskills.pages.ProductsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.teachmeskills.domain.Constants.*;
import static by.teachmeskills.pages.LoginPage.LOGIN_BUTTON;
import static by.teachmeskills.pages.LoginPage.LOGIN_PAGE_URL;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidUserNameAndInvalidPasswordDataProvider")
    public static Object[][] invalidUserNameAndInvalidPasswordDataProvider() {
        return new Object[][]{
                {STANDARD_USER_USER_NAME, "Invalid password"},
                {"Invalid user name", PASSWORD},
        };
    }

    @Test
    public void productsPageShouldBeOpenedAfterSuccessfulLogin() {
        ProductsPage productsPage = safelyLogin();
        String productsLabel = productsPage.getProductsLabel();
        productsSteps.productsLabelShouldBeLike(productsLabel, "Products");

        int productItemsNumber = productsPage.getProductItems().size();
        productsSteps.productsNumberShouldBeLike(productItemsNumber, 6);
    }

    @Test
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

    @Test
    public void errorMessageShouldAppearOnAttemptToLoginAsLockedUser() {
        loginPage
                .openPage()
                .isPageOpened()
                .login(LOCKED_OUT_USER_USER_NAME, PASSWORD);

        String errorMessage = loginPage.getLockedUserMessage();
        loginSteps.errorMessageShouldAppearAfterLoginByLockedUser(errorMessage, LOCKED_USER_ERROR_MESSAGE);
    }

    @Test(dataProvider = "invalidUserNameAndInvalidPasswordDataProvider")
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
