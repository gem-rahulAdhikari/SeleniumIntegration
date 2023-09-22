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
    static String reportName="Report_4df3223f370c407f2cc1b0a58d85511d7bfe2855b9da22526639692e40368076_0";

    @Test
public void demo(){
driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
            WebElement iframeElement = driver.findElement(By.xpath("//iframe[@id='iframeResult']")); 
            driver.switchTo().frame(iframeElement);
            WebElement inputElement = driver.findElement(By.xpath("//input[@id='fname']"));
            WebElement inputElement1 = driver.findElement(By.xpath("//input[@type='submit']"));
            inputElement.sendKeys("Selenium");
            Actions action=new Actions(driver);
            action.click(inputElement1).build().perform();


}



























}