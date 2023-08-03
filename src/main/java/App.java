        
      import org.openqa.selenium.By;
      import org.openqa.selenium.WebDriver;
      import org.openqa.selenium.WebElement;
      import org.openqa.selenium.chrome.ChromeDriver;
      import org.openqa.selenium.chrome.ChromeOptions;
      import org.openqa.selenium.Keys;

      public class App {
          public static void main(String[] args) {
             
              System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

              ChromeOptions options=new ChromeOptions();
              options.addArguments("--headless");
              options.addArguments("start-maximized"); 
              options.addArguments("disable-infobars");
              options.addArguments("--disable-extensions"); 
              options.addArguments("--disable-dev-shm-usage"); 
              options.addArguments("--no-sandbox");
              // Your editable code here
          
             
              System.out.print("hello world");
                 
            
              driver.quit();
          }
      }
  