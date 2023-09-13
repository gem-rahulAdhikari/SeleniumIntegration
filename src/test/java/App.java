import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.lang.*;
import java.util.*;
import java.awt.*;
import java.math.*;
import java.time.*;

public class App extends driverConfig{
    static String reportName="Report_ea3fb0747dfb0c6920bf615e71f9346dd4ee7569dfb9f840523545158ae9549c_0";

    @Test

public void demo(){
driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
            WebElement iframeElement = driver.findElement(By.xpath("//iframe[@id='iframeResult']")); 
            driver.switchTo().frame(iframeElement);
            WebElement inputElement = driver.findElement(By.xpath("//input[@id='fname']"));
            WebElement inputElement1 = driver.findElement(By.xpath("//input[@type='submit']"));
            inputElement.sendKeys("Selenium");
           


}



























}