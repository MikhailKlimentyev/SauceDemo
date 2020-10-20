package by.teachmeskills.steps;

import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class LoginSteps {

    public void loginPageShouldBeOpened(boolean actualDisplayingState, String actualURL,
                                        String expectedURL) {
        Assert.assertTrue(actualDisplayingState, "Login button is not displayed");
        assertEquals(actualURL, expectedURL, "URLs do not coincide");
    }

    public void errorMessageShouldAppearAfterLoginByLockedUser(String actualErrorMessage,
                                                               String expectedErrorMessage) {
        assertEquals(actualErrorMessage, actualErrorMessage, expectedErrorMessage);
    }
}
