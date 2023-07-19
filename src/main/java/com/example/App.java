

/**
 * Hello world!
 *
 */
/**public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}*/
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Keys;

public class App {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options=new ChromeOptions();
options.addArguments("--no-sandbox");
options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage"); //!!!should be enabled for Jenkins
options.addArguments("--window-size=1920x1080"); //!!!should be enabled for Jenkins

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver(options);
System.out.println("Test1");
        // Navigate to a webpage
        driver.get("https://www.google.com");
System.out.println("Test2");

        // Find an input element and enter text
        WebElement inputElement = driver.findElement(By.xpath("//textarea"));
        inputElement.sendKeys("Selenium"+ Keys.ENTER);
String x=driver.findElement(By.xpath("(//span[text()='Selenium'])[3]")).getText();
System.out.println("Output: "+x);

        // Wait for the search results page to load
        // You might need to use explicit or implicit waits here

        // Find a link on the search results page and click it
        WebElement linkElement = driver.findElement(By.cssSelector("h3 a"));
        linkElement.click();

        // Perform other actions or assertions as needed
System.out.println("Test3");
        // Close the browser
        driver.quit();
    }
}

