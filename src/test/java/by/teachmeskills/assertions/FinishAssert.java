package by.teachmeskills.assertions;

import static org.testng.Assert.assertEquals;

public class FinishAssert {

    public void messageShouldBeLike(String actualMessage, String expectedMessage) {
        assertEquals(actualMessage, expectedMessage, "Message is not correct");
    }
}
