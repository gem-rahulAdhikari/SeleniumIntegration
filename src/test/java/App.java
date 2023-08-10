import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class App extends driverConfig{

    @Test
    public void run(){
        try {
            driver.get("https://www.google.com");

            WebElement inputElement = driver.findElement(By.xpath("//textarea"));
            inputElement.sendKeys("Selenium" + Keys.ENTER);
            String x = driver.findElement(By.xpath("(//span[text()='Selenium'])[3]")).getText();
            System.out.println("Output: " + x);
            extentTest.log(Status.INFO,"Log1");
            WebElement linkElement = driver.findElement(By.cssSelector("h3 a"));
            linkElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
