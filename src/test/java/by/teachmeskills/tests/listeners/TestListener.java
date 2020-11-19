package by.teachmeskills.tests.listeners;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener extends TestListenerAdapter {

    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("=========================================== STARTING TEST %s with parameters %s ===========================================",
                iTestResult.getName(), Arrays.toString(iTestResult.getParameters())));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("=========================================== FINISHED TEST %s Duration: %s ===========================================",
                iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.error(String.format("=========================================== FAILED TEST %s Duration: %s ===========================================",
                iTestResult.getName(), getExecutionTime(iTestResult)));
        log.error(ExceptionUtils.getStackTrace(iTestResult.getThrowable()));
        takeScreenshot(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.error(String.format("=========================================== SKIPPING TEST %s ===========================================",
                iTestResult.getName()));
        takeScreenshot(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute("driver");
            if (driver != null) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                return new byte[]{};
            }
        } catch (NoSuchSessionException | IllegalStateException ex) {
            return new byte[]{};
        }
    }
}
