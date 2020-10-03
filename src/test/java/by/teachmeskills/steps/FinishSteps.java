package by.teachmeskills.steps;

import static org.testng.Assert.assertEquals;

public class FinishSteps {

    public void messageShouldBeLike(String actualMessage, String expectedMessage) {
        assertEquals(actualMessage, expectedMessage, "Message is not correct");
    }
}
