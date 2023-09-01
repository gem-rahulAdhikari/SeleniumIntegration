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
    static String reportName="Report_d6b40a0ba2ba035aa7172973fde9401b05667ce313bf8326cc218b0acda2c3f5_2";

    @Test
    public void demo(){
driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
            WebElement iframeElement = driver.findElement(By.xpath("//iframe[@id='iframeResult']")); 
            driver.switchTo().frame(iframeElement);
            WebElement inputElement = driver.findElement(By.xpath("//input[@id='fname']"));
            WebElement inputElement1 = driver.findElement(By.xpath("//input[@type='submit']"));
            inputElement.sendKeys("Selenium_test@sakshi");
            Actions action=new Actions(driver);
            action.click(inputElement1).build().perform();


}


}