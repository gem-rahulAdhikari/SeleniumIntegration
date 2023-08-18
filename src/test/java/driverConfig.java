import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public abstract class driverConfig extends WebdriverEventListener {
    public static WebDriver driver;
    static ThreadLocal<WebDriver> wDriver = new ThreadLocal<WebDriver>();

    @BeforeSuite
    public void reporter() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/" + App.reportName + ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentTest = extentReports.createTest(getClass().getSimpleName());
    }

    @BeforeMethod
    public void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        WebDriverListener listener = new WebdriverEventListener();
        wDriver.set(new ChromeDriver(options));
        WebDriver decorated = new EventFiringDecorator(listener).decorate(wDriver.get());
        wDriver.set(decorated);
        driver = wDriver.get();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        extentReports.flush();
    }

    @AfterSuite
    public void uploadReport() throws IOException, InterruptedException {
//        System.out.println("in upload function");
//        String scriptPath = "./upload.sh";
//        ProcessBuilder processBuilder = new ProcessBuilder(scriptPath);
//        System.out.println("upload started");
//        Process process = processBuilder.start();
//        int exitCode = process.waitFor();
        String reportName="https://storage.googleapis.com/selenium-output/" + App.reportName + ".html";
        System.out.println("Report name: https://storage.googleapis.com/selenium-output/" + App.reportName + ".html");
        mongoTransfer(reportName);
    }

    public void mongoTransfer(String reportName) throws IOException {
        String userId=App.reportName.split("_")[1];
        String url="http://127.0.0.1:5000/editor?name="+userId;
        String filePath = "src/test/java/App.java";
        String classContent = readClassFileAsString(filePath);
        try {
            // URL to send the PUT request
            String apiUrl = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-awqqz/endpoint/updateSeleniumSubmission";

            // Create a URL object
            URL url1 = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();

            // Set the request method to PUT
            connection.setRequestMethod("PUT");

            // Set the content type and other headers (if needed)
            connection.setRequestProperty("Content-Type", "application/json");
            // connection.setRequestProperty("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            // Enable output (to send data)
            connection.setDoOutput(true);
            String escapedClassContent = classContent.replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");

            String putData = "{\n" +
                    "    \"filter\": {\n" +
                    "        \"url\": \"" + url + "\"\n" +
                    "    },\n" +
                    "    \"SubmittedCode\":\""+ escapedClassContent +"\",\n" +
                    "    \"Output\":\"" + reportName + "\"\n" +
                    "}";


            // Write the data to the connection's output stream
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(putData);
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
                    System.out.println(putData);
                }
            } else {
                // Handle error response if needed
                System.out.println("Error Response: " + statusCode);
                System.out.println(putData);
            }

            // Disconnect the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }



//        RestAssured.baseURI = "https://us-east-1.aws.data.mongodb-api.com/";
//        String jsonBody = "{\n" +
//                "    \"filter\": {\n" +
//                "        \"url\": \"" + url + "\"\n" +
//                "    },\n" +
//                "    \"SubmittedCode\":\"" + classContent + "\",\n" +
//                "    \"Output\":\"" + reportName + "\"\n" +
//                "}";
//        Response response = RestAssured
//                .given()
//                .header("Content-type", "application/json")
//                .contentType(ContentType.JSON)
//                .body(jsonBody)
//                .put("app/application-0-awqqz/endpoint/updateSeleniumSubmission")
//                .then()
//                .extract().response();
    }
    public static String readClassFileAsString(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }
        }

        return content.toString();
    }
}
