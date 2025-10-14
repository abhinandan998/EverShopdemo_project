package TestComponent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.extentReports;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener
{
    ExtentTest test;
    ExtentReports extent = extentReports.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();//thread safe

    @Override
    public void onTestStart(ITestResult result)
    {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique thread id -> (error validation test) -> test
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {

        extentTest.get().log(Status.PASS, "Test Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
        String filepath = null;
        try {
            filepath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
