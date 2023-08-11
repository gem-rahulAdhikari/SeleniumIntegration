        
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
            try {  
              System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
              ChromeOptions options=new ChromeOptions();
              options.addArguments("--headless");
              options.addArguments("start-maximized"); 
              options.addArguments("disable-infobars");
              options.addArguments("--disable-extensions"); 
              options.addArguments("--disable-dev-shm-usage"); 
              options.addArguments("--no-sandbox");
          
              System.out.print("hello");
              driver.quit();
              String apiUrl = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-awqqz/endpoint/addSeleniumResult";
              URL url = new URL(apiUrl);
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.setRequestMethod("POST");
              connection.setRequestProperty("Content-Type", "application/json");
              connection.setDoOutput(true);
              String postData = "{\"output1\": " + x + "}";
              try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                  outputStream.writeBytes(postData);
                  outputStream.flush();
              }
              int statusCode = connection.getResponseCode();
              String statusMessage = connection.getResponseMessage();
              System.out.println("Status Code: " + statusCode);
              System.out.println("Status Message: " + statusMessage);
              if (statusCode == HttpURLConnection.HTTP_OK) {
                  try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                      String line;
                      StringBuilder response = new StringBuilder();
                      while ((line = reader.readLine()) != null) {
                          response.append(line);
                      }
                      System.out.println("Response: " + response.toString());
                  }
              } else {
                  System.out.println("Error Response: " + statusCode);
              }
              connection.disconnect();
          }
          catch (Exception e) {
            e.printStackTrace();
        }
          }
      }
  
New content to add