import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class App extends driverConfig {
    //Report name=Report_UserId_ExecutionCount
    static String reportName = "Report_a33c942d7004831f4ae560c72abcff94234d2bcae705eb2d0ab9fe1fb3686923_1";


    @Test
    public void demo() {
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
        WebElement iframeElement = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
        driver.switchTo().frame(iframeElement);
        WebElement inputElement = driver.findElement(By.xpath("//input[@id='fname']"));
        WebElement inputElement1 = driver.findElement(By.xpath("//input[@type='submit']"));
        inputElement.sendKeys("Selenium");
        Actions action = new Actions(driver);
        action.click(inputElement1).build().perform();
    }
}