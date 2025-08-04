package PageObjects;

import java.time.Duration;

import org.apache.logging.log4j.core.util.SystemClock;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.drivermanager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Bookmarkspage {
	
	AndroidDriver driver ;
	WebDriverWait wait ;
	
	@AndroidFindBy(id = "android:id/button3") public WebElement changesInVersionOkButton;
	@AndroidFindBy(uiAutomator  = "new UiSelector().text(\"BOOKMARKS\")") public WebElement bookmarktab;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/noBookmarkedVideosText") public	WebElement emptybookmark;
	
	@AndroidFindBy(id = "free.rm.skytube.oss:id/menu_search") public WebElement searchbox;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.oss:id/options_button\").instance(0)") public WebElement videooptions;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Bookmark\")") public WebElement selectbookmark1;
	@AndroidFindBy(uiAutomator = "new UiSelector().description(\"Navigate up\")") public WebElement navigateback;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/title_text_view") public WebElement Bookmarkitemname; 
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"TRENDING (US)\")") public WebElement trendingpage;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Unbookmark\")") public WebElement unbookmark;
	
	@AndroidFindBy(id = "free.rm.skytube.oss:id/options_button") public WebElement moreoptions;
	
	public Bookmarkspage(){
		this.driver = drivermanager.getdriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		}
	
	public void changeinversionok() {
		wait.until(ExpectedConditions.visibilityOf(changesInVersionOkButton));
		changesInVersionOkButton.click();
	}
	
	public void bookmarkpage() {
		bookmarktab.click();
	}
	
	public String emptybookmarkcheck() {
		String emptytext = emptybookmark.getText();
		return emptytext;
	}
	
	public void bookmarkvideo() {
		searchbox.clear();
		searchbox.click();
		Actions action = new Actions(driver);
		action.sendKeys("zootopia2 trailer" + Keys.ENTER).perform();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(videooptions));
		videooptions.click();
		selectbookmark1.click();
	}
	
	public String checkbookmarkpage() {
		navigateback.click();
		bookmarktab.click();
		String videoname = Bookmarkitemname.getText();
		return videoname;
	}
	
	public void trendingpage() {
		trendingpage.click();
	}
	
	public void unbookmarkvideo() {
		searchbox.clear();
		searchbox.click();
		Actions action = new Actions(driver);
		action.sendKeys("zootopia2 trailer" + Keys.ENTER).perform();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(videooptions));
		videooptions.click();
		unbookmark.click();
	}
	
	public String checkunbookmarkpage() {
		navigateback.click();
		bookmarktab.click();
		String emptytext = emptybookmark.getText();
		return emptytext;
	}
	
	public void unbookmark_bookmarkpage() {
		navigateback.click();
		bookmarktab.click();
		moreoptions.click();
		unbookmark.click();	
	}
}
