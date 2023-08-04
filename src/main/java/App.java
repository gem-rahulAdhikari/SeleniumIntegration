import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class App {
    static WebDriver driver;

    @BeforeMethod
    public void configureDriver(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void quitDriver(){
        driver.quit();
    }
    @Test
    public void run() {
        try {
            driver.get("https://www.google.com");
            WebElement inputElement = driver.findElement(By.xpath("//textarea"));
            inputElement.sendKeys("Selenium" + Keys.ENTER);
            String x = driver.findElement(By.xpath("(//span[text()='Selenium'])[3]")).getText();
            System.out.println("Output: " + x);
            WebElement linkElement = driver.findElement(By.cssSelector("h3 a"));
            linkElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
  