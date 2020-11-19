package by.teachmeskills.tests;

import by.teachmeskills.pages.ProductsPage;
import org.testng.annotations.Test;

import java.util.Collections;

import static by.teachmeskills.domain.Constants.*;

public class CartTest extends BaseTest {


    @Test(description = "Products page should be opened after continue shopping button clicking" +
            " if one product is added to cart")
    public void productsPageShouldBeOpenedAfterContinueShoppingButtonClickingIfOneProductAddedToCart() {
        ProductsPage productsPage = loginSafely();
        productsPage
                .addToCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnContinueShoppingButton()
                .isPageOpened();
        String removeButtonName = productsPage
                .isRemoveButtonVisible(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsAssert.buttonNameShouldBeLike(removeButtonName, REMOVE_BUTTON_NAME);
        String actualProductCount = productsPage.getProductsNumberInShoppingCart();
        productsAssert.productNumberShouldBeLike(actualProductCount, "6");
    }

    @Test(description = "Products page should be opened after continue shopping button clicking" +
            " if no product is added to cart")
    public void productsPageShouldBeOpenedAfterContinueShoppingButtonClickingIfNoProductAddedToCart() {
        ProductsPage productsPage = loginSafely();
        productsPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnContinueShoppingButton()
                .isPageOpened();
        String actualProductCount = productsPage.getProductsNumberInShoppingCart();
        productsAssert.productNumberShouldBeLike(actualProductCount, "0");
    }

    @Test(description = "Product number in shopping cart should be equal zero after removing one existed product")
    public void productNumberInShoppingCartShouldBeEqualZeroAfterRemovingOneExistedProduct() {
        ProductsPage productsPage = loginSafely();
        productsPage
                .addToCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        int productsNumber = cartPage
                .openPage()
                .isPageOpened()
                .clickOnRemoveButton(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getProductsNumberInShoppingCart();
        cartAssert.productNumberShouldBeLike(productsNumber, 0);
    }

    @Test(description = "Product number in shopping cart should be equal one after removing one of" +
            " two products in cart")
    public void productNumberInShoppingCartShouldBeEqualOneAfterRemovingOneOfTwoProducts() {
        ProductsPage productsPage = loginSafely();
        productsPage
                .addToCart(SAUCE_LABS_FLEECE_JACKET_NAME)
                .addToCart(SAUCE_LABS_BACKPACK_NAME);
        int productsNumber = cartPage
                .openPage()
                .isPageOpened()
                .clickOnRemoveButton(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getProductsNumberInShoppingCart();
        cartAssert.productNumberShouldBeLike(productsNumber, 1);
        cartAssert.productsNameShouldBeLike(Collections.singletonList(SAUCE_LABS_BACKPACK_NAME),
                Collections.singletonList(SAUCE_LABS_BACKPACK_NAME));
    }
}
