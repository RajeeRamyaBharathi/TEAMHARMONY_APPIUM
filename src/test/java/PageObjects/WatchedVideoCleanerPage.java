package PageObjects;

import java.time.Duration;
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

public class WatchedVideoCleanerPage {
	
    AndroidDriver driver;
    WebDriverWait wait;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Watched Video Cleaner\")")
    public WebElement watchedVideoCleanerMenuItem;

    @AndroidFindBy(id = "free.rm.skytube.oss:id/clean_watched_downloads")
    public WebElement removeWatchedDownloadsCheckbox;

    @AndroidFindBy(id = "free.rm.skytube.oss:id/clean_watched_bookmarks")
    public WebElement removeWatchedBookmarksCheckbox;

    @AndroidFindBy(id = "free.rm.skytube.oss:id/md_buttonDefaultPositive")
    public WebElement cleanButton;
    
    @AndroidFindBy(id = "free.rm.skytube.oss:id/md_buttonDefaultNegative")
    public WebElement CancelButton;
    
    @AndroidFindBy(id ="free.rm.skytube.oss:id/menu_search")
    public WebElement searchbox;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.oss:id/options_button\").instance(0)")
    public WebElement videoOptions;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.oss:id/content\").instance(3)")
    public WebElement selectbookmark1;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.oss:id/content\").instance(7)")
    public WebElement MarkasWatched;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Download\")") 
    public WebElement download;
   
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Navigate up\")")
    public WebElement Navigate;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"DOWNLOADS\")")
    public WebElement downloadsTab;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"BOOKMARKS\")")
    public WebElement bookmarksTab;

    public WatchedVideoCleanerPage() {
        this.driver = drivermanager.getdriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    public void clickWatchedVideocleaner() {
    	wait.until(ExpectedConditions.elementToBeClickable(watchedVideoCleanerMenuItem)).click();
    }

    public boolean isCleanerPopupDisplayed() {
        return removeWatchedDownloadsCheckbox.isDisplayed() && removeWatchedBookmarksCheckbox.isDisplayed();
    }

    // Select / Deselect methods
    public void selectRemoveWatchedDownloads() {
        if (!removeWatchedDownloadsCheckbox.isSelected()) removeWatchedDownloadsCheckbox.click();
    }
  
    public void selectRemoveWatchedBookmarks() {
        if (!removeWatchedBookmarksCheckbox.isSelected()) removeWatchedBookmarksCheckbox.click();
    }
    
    public void deselectRemoveWatchedDownloads() {
        removeWatchedDownloadsCheckbox.click();
    }
    
    public void deselectRemoveWatchedBookmarks() {
       removeWatchedBookmarksCheckbox.click();
    }

    public void clickCleanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cleanButton)).click();
    }

    public void openDownloadsTab() {
    	Navigate.click();
    	Navigate.click();
        wait.until(ExpectedConditions.elementToBeClickable(downloadsTab)).click();
    }
    
    public void BookmarksTab() {
    	 wait.until(ExpectedConditions.elementToBeClickable(bookmarksTab)).click();
    }
    
    public void openBookmarksTab() {
    	Navigate.click();
    	Navigate.click();
        wait.until(ExpectedConditions.elementToBeClickable(bookmarksTab)).click();
    }

    public boolean areBookmarkedVideosPresent() {
        try {
            return !driver.findElements(By.id("free.rm.skytube.oss:id/title_text_view")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areDownloadedVideosPresent() {
        try {
            return !driver.findElements(By.id("free.rm.skytube.oss:id/title_text_view")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilDownloadsEmpty() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(driver -> driver.findElements(By.id("free.rm.skytube.oss:id/downloaded_video_item")).isEmpty());
    }
    
    public void waitUntilBookmarksEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.findElements(By.id("free.rm.skytube.oss:id/bookmarked_video_item")).isEmpty());
    }
    
    public void bookmarkvideo() {
		searchbox.clear();
		searchbox.click();
		searchbox.clear();
		Actions action = new Actions(driver);
		action.sendKeys("zootopia2 trailer" + Keys.ENTER).perform();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(videoOptions));
		videoOptions.click();
		selectbookmark1.click();
		videoOptions.click();
		MarkasWatched.click();
	}
    
    public void downloadvideo() throws Exception{
		searchbox.clear();
		searchbox.click();
		Actions action = new Actions(driver);
		action.sendKeys("rock squad barbie girls shorts" + Keys.ENTER).perform();
		wait.until(ExpectedConditions.visibilityOf(videoOptions));
		videoOptions.click();
		wait.until(ExpectedConditions.visibilityOf(download));
		download.click();
		videoOptions.click();
		MarkasWatched.click();
		Thread.sleep(2000);
	}
	
}