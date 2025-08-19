package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.drivermanager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PreferencesPage {
	AndroidDriver driver ;
	WebDriverWait wait ;
	 
	@AndroidFindBy(id = "android:id/button3") public WebElement changesInVersionOkButton;
	@AndroidFindBy(accessibility = "More options") public WebElement moreOptions;
	@AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id=\"free.rm.skytube.oss:id/content\"])[3]") public WebElement preferences;
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button") public WebElement AllowNotifications;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Preferences\")")public WebElement PreferencesView;
	
	public PreferencesPage(){
		this.driver = drivermanager.getdriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		}
	
	public void changeinversionok() {
		wait.until(ExpectedConditions.visibilityOf(changesInVersionOkButton));
		changesInVersionOkButton.click();
	}
	
	public void moreOptions() {
		 wait.until(ExpectedConditions.elementToBeClickable(moreOptions)).click();
	}
	
	public void preferences() {
		 wait.until(ExpectedConditions.elementToBeClickable(preferences)).click();
	}
	
	public void notification() {
		  try {
	            if (AllowNotifications.isDisplayed()) {
	            	AllowNotifications.click();
	            }
	        } catch (Exception e) {
	            // Do nothing if permission dialog not shown
	        }
	}
	
	public void menuOption(String menuOption) {
	        By locator = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='" + menuOption + "']");
	        try {
	            WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	            Assert.assertTrue(item.isDisplayed(), "Menu item '" + menuOption + "' is not visible.");
	            item.click();
	        } catch (Exception e) {
	            Assert.fail("Menu item '" + menuOption + "' not found or not visible.",e);
	        }
	}
	        
	public boolean isPreferencesDisplayed() {
		return preferences.isDisplayed() && preferences.getText().equals("Preferences");
	}
	        
	public List<WebElement> getAllPreferencesMenuItems() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("android:id/title")));
	    return driver.findElements(By.id("android:id/title"));
	}

	public boolean isPreferenceContentDisplayed() {
		List<WebElement> textViews = driver.findElements(AppiumBy.className("android.widget.TextView"));
	    return !textViews.isEmpty();
	}

	public void goBack() {
		driver.navigate().back();
	}

	}
	
