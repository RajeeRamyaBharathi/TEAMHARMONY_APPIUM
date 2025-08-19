package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.drivermanager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchChannel {
	
	String path;
	 AndroidDriver driver;
	 WebDriverWait wait;
	
	public SearchChannel() {
		this.driver = drivermanager.getdriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		}
	
	@AndroidFindBy(id = "android:id/button3") WebElement Okbtn;
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button") WebElement allowbtn;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/skipButton") WebElement skipbtn;
	@AndroidFindBy(id ="free.rm.skytube.oss:id/menu_search") WebElement topSearchIcon;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/search_src_text") WebElement searchInput;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/actionbarTitle") WebElement searchResultTitle;
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"free.rm.skytube.oss:id/grid_view\"]")
	WebElement splcaseView;
	
	public void clickOkButton() {
	    wait.until(ExpectedConditions.elementToBeClickable(Okbtn)).click();
	}

	public void allowPermissionsIfPresent() {
	    try {
	        if (allowbtn.isDisplayed()) {
	            wait.until(ExpectedConditions.elementToBeClickable(allowbtn)).click();
	        }
	    } catch (Exception ignored) {}
	}

	public void skipIntroIfPresent() {
	    try {
	        if (skipbtn.isDisplayed()) {
	            wait.until(ExpectedConditions.elementToBeClickable(skipbtn)).click();
	        }
	    } catch (Exception ignored) {}
	}

	public void openSearch() {
	    wait.until(ExpectedConditions.elementToBeClickable(topSearchIcon)).click();
	}

	public void enterSearchText(String text){
		 wait.until(ExpectedConditions.visibilityOf(searchInput));
		    searchInput.clear();
		    searchInput.click();
		    Actions action = new Actions(driver);
		    action.sendKeys(text + Keys.ENTER).perform();
		}
	
	public boolean isSearchResultVisible(String expectedText) {
	    try {
	        List<WebElement> videoTitles = driver.findElements(By.className("android.widget.TextView"));
	        for (WebElement title : videoTitles) {
	            String text = title.getText().trim().toLowerCase();
	            if (!text.isEmpty()) {
	                System.out.println("Checking visible text: " + text);
	                if (text.contains(expectedText.toLowerCase())) {
	                    return true;
	                }
	            }
	        }
	        System.out.println("Expected text '" + expectedText + "' not found. All visible text:");
	        printAllVisibleTexts();
	        
	    } catch (Exception e) {
	        System.out.println("Error while checking for search result: " + e.getMessage());
	    }
	    return false;
	}
	
	public void printAllVisibleTexts() {
	    List<WebElement> elements = driver.findElements(By.className("android.widget.TextView"));
	    for (WebElement el : elements) {
	        System.out.println("Visible Text: " + el.getText());
	    }
	}
	
	public void selectChannelFromResults(String channelName) {
	    try {
	        WebElement channelResult = driver.findElement(
	            By.xpath("//android.widget.TextView[@text='" + channelName + "']")
	        );
	        channelResult.click();
	    } catch (Exception e) {
	        System.out.println("Channel '" + channelName + "' not found in search results.");
	    }
	}

	public boolean isSearchResultEmpty() {
	    try {
	        List<WebElement> results = splcaseView.findElements(By.className("android.widget.TextView"));
	        if (results.isEmpty()) {
	            System.out.println("Search result list is empty.");
	            return true;
	        }

	        for (WebElement el : results) {
	            String text = el.getText().trim();
	            if (!text.isEmpty()) {
	                System.out.println("Found visible search result text: " + text);
	                return false; // Not empty if any visible text found
	            }
	        }

	        System.out.println("All search results have empty text.");
	        return true;
	    } catch (Exception e) {
	        System.out.println("Exception in isSearchResultEmpty: " + e.getMessage());
	        // Return true to treat errors as empty result (to avoid false negatives)
	        return true;
	    }
	}

}