import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class driverConfig extends WebdriverEventListener {
    public static WebDriver driver;
    static ThreadLocal<WebDriver> wDriver = new ThreadLocal<WebDriver>();

    @BeforeSuite
    public void reporter() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/" + App.reportName + ".html");
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
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        extentReports.flush();
    }

    @AfterSuite
    public void uploadReport() throws IOException, InterruptedException {
        System.out.println("in upload function");
        String scriptPath = "./upload.sh";
        ProcessBuilder processBuilder = new ProcessBuilder(scriptPath);
        System.out.println("upload started");
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        String reportName="https://storage.googleapis.com/selenium-output/" + App.reportName + ".html";
        System.out.println("Report name: https://storage.googleapis.com/selenium-output/" + App.reportName + ".html");
        mongoTransfer(reportName);
    }

    public void mongoTransfer(String reportName) throws IOException {
        String userId=App.reportName.split("_")[1];
        String url="http://g-codeeditor.el.r.appspot.com/editor?name="+userId;
        String filePath = "src/test/java/App.java";
        String classContent = readClassFileAsString(filePath);

    }
    public static String readClassFileAsString(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }
        }

        return content.toString();
    }
}
