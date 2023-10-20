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
    static String reportName="Report_d57bc5785be7dfc774f8b69c8f08e3556c4d94060006fd5f91c51b0feb82f742_0";

    @Test
public void demo(){  

driver.get("https://www.google.com/");
     WebElement searchInput = driver.findElement(By.xpath("//textarea"));
    searchInput.sendKeys("selenium");
    searchInput.sendKeys(Keys.RETURN);
     WebElement title = driver.findElement(By.xpath("(//h3[text()='Selenium'])[1]")); 
     String fetchedTitle=title.getText();
     if("Selenium".equals(fetchedTitle))
     {
         extentTest.log(Status.PASS,"text matched successfully.",captureScreenshot());
     }
    else
     {
         extentTest.log(Status.FAIL,"Failed to match text.",captureScreenshot());
     }

     extentTest.log(Status.PASS,driver.getCurrentUrl(),captureScreenshot());

}




























}