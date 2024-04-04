1. https://rock-bonus-417312.de.r.appspot.com/

2. http://rock-bonus-417312.de.r.appspot.com/editor?name=14cea446558421655afe0dd016d9f2fc3b86642e900142f71587e599d7e043af

3. Sample Code for selenium

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
static String reportName="Report_1712236365_0";
  
    @Test

public void demo() {
        driver.get("https://www.google.com/");
        WebElement searchInput = driver.findElement(By.xpath("//textarea"));
        searchInput.sendKeys("selenium");
        searchInput.sendKeys(Keys.RETURN);
        WebElement title = driver.findElement(By.xpath("(//h3[text()='Selenium'])[1]"));
        String fetchedTitle = title.getText();
        System.out.println(fetchedTitle + " start2")
    }
}

