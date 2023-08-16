import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Base64;

public abstract class driverConfig extends WebdriverEventListener{
    static ThreadLocal<WebDriver> wDriver = new ThreadLocal<WebDriver>();
    public static WebDriver driver;

    @BeforeSuite
    public void reporter() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/"+App.reportName+".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentTest = extentReports.createTest(getClass().getSimpleName());
    }

    @BeforeMethod
    public void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        WebDriverListener listener = new WebdriverEventListener();
        wDriver.set(new ChromeDriver(options));
        WebDriver decorated = new EventFiringDecorator(listener).decorate(wDriver.get());
        wDriver.set(decorated);
        driver = wDriver.get();


//        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/extent-report.html");
//        extentReports = new ExtentReports();
//        extentReports.attachReporter(htmlReporter);
//        extentTest = extentReports.createTest(getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        extentReports.flush();
    }

    @AfterSuite
    public void uploadReport() throws IOException, InterruptedException {
        String scriptPath = "./upload.sh";
        ProcessBuilder processBuilder = new ProcessBuilder(scriptPath);
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        System.out.println("https://storage.googleapis.com/selenium-output/"+App.reportName+".html");
    }
}
