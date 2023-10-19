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
public void demo() throws InterruptedException {

driver.get("https://jewel.gemecosystem.com/");
    Thread.sleep(2000);
    WebElement loginButton = driver.findElement(By.xpath("//span[text()='Log in']"));
    Thread.sleep(2000);
    loginButton.click();
    Thread.sleep(2000);
    WebElement userName = driver.findElement(By.xpath("//input[@id='usernameField']")); 
    Thread.sleep(2000);
    userName.sendKeys("Rahul");
    extentTest.log(Status.PASS,driver.getCurrentUrl(),captureScreenshot());
}




























}
