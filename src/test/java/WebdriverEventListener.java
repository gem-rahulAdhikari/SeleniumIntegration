import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebdriverEventListener extends driverConfig implements WebDriverListener {

//    @Override
//    public void afterGet(WebDriver driver, String url) {
//        extentTest.log(Status.PASS,"Launched Url Successfully : " + url,captureScreenshot());
//    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        extentTest.log(Status.PASS,"send keys");
    }
}
