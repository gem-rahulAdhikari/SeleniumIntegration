import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Keys;

public class App {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
try{
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox");
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver(options);
System.out.println("Test1");
        // Navigate to a webpage
        driver.get("https://www.google.com");
System.out.println("Test2");

        // Find an input element and enter text
        WebElement inputElement = driver.findElement(By.xpath("//textarea"));
        inputElement.sendKeys("Selenium"+ Keys.ENTER);
String x=driver.findElement(By.xpath("(//span[text()='Selenium'])[3]")).getText();
System.out.println("Output: "+x);

        // Wait for the search results page to load
        // You might need to use explicit or implicit waits here

        // Find a link on the search results page and click it
        WebElement linkElement = driver.findElement(By.cssSelector("h3 a"));
        linkElement.click();

        // Perform other actions or assertions as needed
System.out.println("Test3");
        // Close the browser
        driver.quit();

//httpclient code 
System.out.println("Driver quit success");
System.out.println("HTTP Client: "+x);



 String apiUrl = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-awqqz/endpoint/addSeleniumResult";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
        
            // Set the content type and other headers (if needed)
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            // Enable output (to send data)
            connection.setDoOutput(true);

            // Data to send in the request body
            String postData = "{\"output1\": \"" + x + "\", \"output2\": \"Adhikari\"}";

            // Write the data to the connection's output stream
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(postData);
                outputStream.flush();
            }

            // Get the HTTP response code
            int statusCode = connection.getResponseCode();

            // Get the HTTP response status message
            String statusMessage = connection.getResponseMessage();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Status Message: " + statusMessage);

            if (statusCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the connection
               try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
 }
                    System.out.println("Response: " + response.toString());
                }
            } else {
                // Handle error response if needed
                System.out.println("Error Response: " + statusCode);
            }

            // Disconnect the connection
            connection.disconnect();

} catch (Exception e) {
            e.printStackTrace();
        }
    }
}

