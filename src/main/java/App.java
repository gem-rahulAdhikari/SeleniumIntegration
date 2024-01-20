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

public class App extends driverConfig {
    static String reportName="Report_1705568491_0";

    @Test
    public void demo() {
        // RestAssured.baseURI = "https://reqres.in";

        // // Example GET request to retrieve a user (user with ID 1)
        // Response response = RestAssured.get("/api/users/1");

        // // Print the response status code
        // System.out.println("Status Code: " + response.getStatusCode());

        // // Print the response body
        // System.out.println("Response Body: " + response.getBody().asString());
        
            
driver.get("https://www.google.com/");
     WebElement searchInput = driver.findElement(By.xpath("//textarea"));
    searchInput.sendKeys("selenium");
    searchInput.sendKeys(Keys.RETURN);
     WebElement title = driver.findElement(By.xpath("(//h3[text()='Selenium'])[1]"));
     String fetchedTitle=title.getText();
    System.out.println(fetchedTitle+" start2");      
    
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
