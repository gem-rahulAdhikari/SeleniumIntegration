import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import java.lang.*;
import java.util.*;
import java.awt.*;
import java.math.*;
import java.time.*;

public class App extends driverConfig{
    
  @Test
    public void demo() {
        
        driver.get("https://www.google.com/");
        WebElement searchInput = driver.findElement(By.xpath("//textarea"));
        searchInput.sendKeys("selenium");
        searchInput.sendKeys(Keys.RETURN);
        WebElement title = driver.findElement(By.xpath("(//h3[text()='Selenium'])[1]"));
        String fetchedTitle = title.getText();
        System.out.println(fetchedTitle + " start2"); }
}
























}
