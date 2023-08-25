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
import org.json.JSONArray;
import org.json.JSONObject;
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
        System.out.println("in upload function");
        String scriptPath = "./upload.sh";
        ProcessBuilder processBuilder = new ProcessBuilder(scriptPath);
        System.out.println("upload started");
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        String reportName = "https://storage.googleapis.com/selenium-output/" + App.reportName + ".html";
        System.out.println("Report name: https://storage.googleapis.com/selenium-output/" + App.reportName + ".html");
        mongoTransfer(reportName);
    }

    public void mongoTransfer(String reportName) throws IOException {
        String userId=App.reportName.split("_")[1];
        String url="http://g-codeeditor.el.r.appspot.com/editor?name="+userId;
        String filePath = "src/test/java/App.java";
        String classContent = readClassFileAsString(filePath);
        String escapedClassContent = classContent.replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
        try {
            URL getUrl = new URL("https://us-east-1.aws.data.mongodb-api.com/app/application-0-awqqz/endpoint/getSeleniumOutput"); // Replace with your actual GET API URL
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatusCode = getConnection.getResponseCode();
            if (getStatusCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    String responseData = response.toString();
                    JSONArray dataArray = new JSONArray(responseData); // Assuming the response is a JSON array

                    String currentUrl = url; // Replace with the URL you want to compare

                    boolean foundMatch = false;
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject item = dataArray.getJSONObject(i);
                        String entryUrl = item.getString("url");

                        if (entryUrl.equals(currentUrl)) {
                            foundMatch = true;

                            String apiUrl = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-awqqz/endpoint/updateSeleniumSubmission";
                            URL url1 = new URL(apiUrl);
                            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                            connection.setRequestMethod("PUT");
                            connection.setRequestProperty("Content-Type", "application/json");
                            connection.setDoOutput(true);

                            String putData = "{\n" +
                                    "    \"filter\": {\n" +
                                    "        \"url\": \"" + url + "\"\n" +
                                    "    },\n" +
                                    "    \"SubmittedCode\":\""+ escapedClassContent +"\",\n" +
                                    "    \"Output\":\"" + reportName + "\"\n" +
                                    "}";

                            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                                outputStream.writeBytes(putData);
                                outputStream.flush();
                            }
                            int statusCode = connection.getResponseCode();
                            String statusMessage = connection.getResponseMessage();
                            System.out.println("Status Code: " + statusCode);
                            System.out.println("Status Message: " + statusMessage);
                            break; // No need to continue the loop once a match is found
                        }
                    }

                    if (!foundMatch) {
                        String apiUrl = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-awqqz/endpoint/addSeleniumResult";
                        URL url2 = new URL(apiUrl);
                        HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
                        connection.setRequestMethod("PUT");
                        connection.setRequestProperty("Content-Type", "application/json");
                        connection.setDoOutput(true);
                        String putData1 = "{\n" +
                                "    \"filter\": {\n" +
                                "        \"url\": \"" + url + "\"\n" +
                                "    },\n" +
                                "    \"code\":\""+ escapedClassContent +"\",\n" +
                                "    \"output\":\"" + reportName + "\"\n" +
                                "}";


                        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                            outputStream.writeBytes(putData1);
                            outputStream.flush();
                        }
                        int statusCode = connection.getResponseCode();
                        String statusMessage = connection.getResponseMessage();
                        System.out.println("Status Code: " + statusCode);
                        System.out.println("Status Message: " + statusMessage);
                    }
                }
            } else {
                System.out.println("GET Request failed with status code " + getStatusCode);
            }

            getConnection.disconnect();
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
