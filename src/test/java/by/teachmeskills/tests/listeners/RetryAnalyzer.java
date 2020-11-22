package by.teachmeskills.tests.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Arrays;

@Log4j2
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRY = 2;
    private int attempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt <= MAX_RETRY) {
                log.debug(String.format("Retrying test %s with parameters %s with an attempt %s", iTestResult.getName(),
                        Arrays.toString(iTestResult.getParameters()), attempt));
                attempt++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
