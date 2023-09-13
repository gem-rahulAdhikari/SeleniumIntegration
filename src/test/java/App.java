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
    static String reportName="Report_4ceeeb2b5439002149ba0ea4cb0b9d3450030b68fe0a6d0a3d08247e9ab8d276_0";

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