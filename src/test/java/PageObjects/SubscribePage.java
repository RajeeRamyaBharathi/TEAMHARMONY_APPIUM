package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.drivermanager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SubscribePage {

String path;	
AndroidDriver driver;
WebDriverWait wait;
	
	
	public SubscribePage() {
		this.driver = drivermanager.getdriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
	
	@AndroidFindBy(id = "android:id/button3") WebElement Okbtn;
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button") WebElement allowbtn;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/skipButton") WebElement skipbtn;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.oss:id/options_button\").instance(0)") WebElement dotaftersearch;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"free.rm.skytube.oss:id/title\" and @text=\"Channel...\"]")
	WebElement channelbtn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"free.rm.skytube.oss:id/title\" and @text=\"Subscribe\"]")
	WebElement subscribebtn;
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"SkyTube\"]")
	WebElement menuoption;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/sub_channel_name_text_view")
	public WebElement subchannel;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/channel_subscribe_button")
	public WebElement subbtnchannel;
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"free.rm.skytube.oss:id/title\" and @text=\"Unsubscribe\"]")
	WebElement dotUnsubscribe;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Copy URL\")")
	WebElement copyurlBtn;
	
	public void clickOkButton() {
	    Okbtn.click();
	}

	public void clickAllowButton() {
	    allowbtn.click();
	}

	public void clickSkipButton() {
	    skipbtn.click();
	}

	public void openChannelOptions() {
	    dotaftersearch.click();
	}

	public void selectChannel() {
	    channelbtn.click();
	}

	public void clickSubscribe() {
	    subscribebtn.click();
	}

	public void openMenu() {
	    menuoption.click();
	}

    public void toggleSubscriptionFromChannel() {
	    subbtnchannel.click();
	}
    
    public String getSubscribeToastMsg() {
		  WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Toast[contains(@text, 'Subscribed')]")));
		  String toastMessage = toast.getText();
	        System.out.println("Toast Message: " + toastMessage);
	        return toastMessage;
    }
    
    public String getUnsubscribeToastMessage() {
        try {
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.Toast[contains(@text, 'Unsubscribed')]")
            ));
            return toast.getText();
        } catch (Exception e) {
            System.out.println("Unsubscribe toast not found: " + e.getMessage());
            return null;
        }
    }

    
    public boolean isSubscribedChannelVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(subchannel));
            return subchannel.isDisplayed();
        } catch (Exception e) {
            System.out.println("Subscribed channel not visible: " + e.getMessage());
            return false;
        }
    }
    
    public boolean isUnsubscribeButtonVisibleAfterClickingSubscribedChannel() {
        try {
            subchannel.click();  // click on the subscribed channel
            wait.until(ExpectedConditions.visibilityOf(subbtnchannel));  // this.wait is already initialized
            return subbtnchannel.isDisplayed();
        } catch (Exception e) {
            System.out.println("Unsubscribe button not visible: " + e.getMessage());
            return false;
        }
    }
    
    public void openSubscribedChannel() {
        wait.until(ExpectedConditions.elementToBeClickable(subchannel)).click();
    }

}
