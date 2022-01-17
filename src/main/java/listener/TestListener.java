package listener;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("The test {} started", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The test {} has passed", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("The test {} has failed", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("The test {} has skipped", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("The test {} has failed but within success percentage", result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.info("The test {} failed with timeout", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}