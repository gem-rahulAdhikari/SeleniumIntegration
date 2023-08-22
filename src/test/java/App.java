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
    static String reportName="Report_f51a60ea453780df5aef0b3c3cf6090a25ea1aa2216c0392a031be2811490ed8_0";

    @Test
public void demo(){
driver.get("https://www.google.com");
            WebElement inputElement = driver.findElement(By.xpath("//textarea"));
            inputElement.sendKeys("Selenium");
            Actions action=new Actions(driver);
            action.sendKeys(Keys.ENTER).build().perform();
            String x = driver.findElement(By.xpath("(//span[text()='Selenium'])[3]")).getText();
            System.out.println("Output: " + x);
            if (x.equalsIgnoreCase("selenium"))
                extentTest.log(Status.PASS,"output is: "+x,captureScreenshot());
            else
                extentTest.log(Status.FAIL,"output is: "+x,captureScreenshot());
}



























}