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
driver.get("https://www.w3schools.com/html/html_elements.asp");
    WebElement inputElement = driver.findElement(By.xpath("//span[@class='color_h1']"));
    String elementText = inputElement.getText();
    System.out.println("Text of the span element: " + elementText);
    System.out.println(driver.getCurrentUrl());
    extentTest.log(Status.PASS,driver.getCurrentUrl(),captureScreenshot());
}




























}
