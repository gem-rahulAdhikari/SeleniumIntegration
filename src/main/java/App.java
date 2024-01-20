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
        RestAssured.baseURI = "https://reqres.in";
        Response response = RestAssured.get("/api/users/1");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        
            

    }
}
