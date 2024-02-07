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
public void demo(){
        RestAssured.baseURI = "https://betaapi.gemecosystem.com/gemEcosystemDashboard/actuator/health";
        Response response = RestAssured.given().get().then().extract().response();

    
        response.prettyPrint();
    
}


























}
