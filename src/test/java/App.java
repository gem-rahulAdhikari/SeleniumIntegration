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
    static String reportName="Report_b092c8b27b1b4718bc1dfb4cc1eecdafadac8cee4cf9ceaca1a82699aa48db93_2";

    @Test
public void demo(){
driver.get("https://mis.geminisolutions.com/");
        System.out.println(driver.getCurrentUrl());
        extentTest.log(Status.PASS,driver.getCurrentUrl()+x,captureScreenshot());
}




























}
