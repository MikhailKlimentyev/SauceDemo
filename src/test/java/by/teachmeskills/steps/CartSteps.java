package by.teachmeskills.steps;

import static org.testng.Assert.*;

public class CartSteps {

    public void productDetailsShouldBeLike(String actualProductPrice, String actualProductQuantity,
                                           String expectedProductPrice, String expectedProductQuantity) {
        assertEquals(actualProductPrice, expectedProductPrice, "Price is not correct");
        assertEquals(actualProductQuantity, expectedProductQuantity, "Quantity is not correct");
    }
}
