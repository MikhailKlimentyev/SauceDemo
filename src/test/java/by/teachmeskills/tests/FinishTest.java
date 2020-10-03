package by.teachmeskills.tests;

import org.testng.annotations.Test;

import static by.teachmeskills.domain.Constants.*;
import static by.teachmeskills.domain.Constants.POSTAL_CODE;

public class FinishTest extends BaseTest {

    @Test
    public void thanksForOrderMessageShouldAppearAfterSuccessfulOrder() {
        loginPage.openPage();
        loginPage.isPageOpened();

        loginPage.login(STANDARD_USER_USER_NAME, PASSWORD);
        productsPage.isPageOpened();

        productsPage.addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        cartPage.openPage();
        cartPage.isPageOpened();

        cartPage.clickOnCheckOutButton();
        checkoutInformationPage.isPageOpened();
        checkoutInformationPage.fillInformation(FIRST_NAME, LAST_MANE, POSTAL_CODE);
        checkoutInformationPage.clickOnContinueButton();

        checkoutOverviewPage.isPageOpened();
        checkoutOverviewPage.clickOnFinishButton();

        finishPage.isPageOpened();
        String thanksForOrderMessage = finishPage.getText();
        finishSteps.messageShouldBeLike(thanksForOrderMessage, THANK_YOU_FOR_YOUR_ORDER_MESSAGE);
    }
}
