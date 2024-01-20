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

public class App extends driverConfig {
    static String reportName="Report_1704714274_0";

    @Test
    public void demo() {
      RestAssured.baseURI = "https://reqres.in";

        // Send a GET request
        Response response = RestAssured.get("/api/users/1");

        // Get and print the status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        // Print the response body to the console
        String responseBody = response.getBody().asString();
        System.out.println("Response Body:\n" + responseBody);
}
