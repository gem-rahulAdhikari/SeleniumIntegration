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
    static String reportName="Report_b092c8b27b1b4718bc1dfb4cc1eecdafadac8cee4cf9ceaca1a82699aa48db93_9";

    @Test
public void demo(){
System.out.println("1");
driver.get("https://www.google.com/");
    System.out.println("2");
    WebElement searchInput = driver.findElement(By.xpath("//textarea"));
    System.out.println("3");
    searchInput.sendKeys("selenium");
    searchInput.sendKeys(Keys.RETURN);
    System.out.println("4");
    WebElement title = driver.findElement(By.xpath("(//h3[text()='Selenium'])[1]")); 
    System.out.println("4");
    title.getText();
    System.out.println("5");
    extentTest.log(Status.PASS,driver.getCurrentUrl(),captureScreenshot());
}




























}
