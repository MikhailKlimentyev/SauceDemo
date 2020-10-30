package by.teachmeskills.tests;

import by.teachmeskills.pages.CheckoutInformationPage;
import by.teachmeskills.pages.CheckoutOverviewPage;
import by.teachmeskills.pages.FinishPage;
import by.teachmeskills.pages.ProductsPage;
import org.testng.annotations.Test;

import static by.teachmeskills.domain.Constants.*;

public class FinishTest extends BaseTest {

    @Test(description = "Thanks for order message should appear after successful order")
    public void thanksForOrderMessageShouldAppearAfterSuccessfulOrder() {
        ProductsPage productsPage = loginSafely();
        productsPage
                .addToCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        CheckoutInformationPage checkoutInformationPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnCheckOutButton();
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage
                .isPageOpened()
                .fillInformation(FIRST_NAME, LAST_MANE, POSTAL_CODE)
                .clickOnContinueButton();
        FinishPage finishPage = checkoutOverviewPage
                .isPageOpened()
                .clickOnFinishButton();
        finishPage
                .isPageOpened();

        String thanksForOrderMessage = finishPage.getText();
        finishAssert.messageShouldBeLike(thanksForOrderMessage, THANK_YOU_FOR_YOUR_ORDER_MESSAGE);
    }
}
