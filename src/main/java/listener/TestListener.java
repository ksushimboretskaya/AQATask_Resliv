package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Logger;

public class TestListener implements ITestListener {

    private final Logger logger = Logger.getLogger("Test listener logger");

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(String.format("The test {%s} started", result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(String.format("The test {%s} has passed", result.getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info(String.format("The test {%s} has failed", result.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(String.format("The test {%s} has skipped", result.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info(String.format("The test {%s} has failed but within success percentage", result.getName()));
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.info(String.format("The test {%s} failed with timeout", result.getName()));
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}