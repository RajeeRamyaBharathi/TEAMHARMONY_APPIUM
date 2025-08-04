package PageObjects;

import java.time.Duration;

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

public class Downloadspage {
	
	AndroidDriver driver ;
	WebDriverWait wait ;
	
	@AndroidFindBy(id = "android:id/button3") public WebElement changesInVersionOkButton;
	@AndroidFindBy(uiAutomator  = "new UiSelector().text(\"DOWNLOADS\")") public WebElement downloadstab;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/noDownloadedVideosText") public	WebElement emptydownloads;
	
	@AndroidFindBy(id = "free.rm.skytube.oss:id/menu_search") public WebElement searchbox;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.oss:id/options_button\").instance(0)") public WebElement videooptions;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Download\")") public WebElement download;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().description(\"Navigate up\")") public WebElement navigateback;
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button") public WebElement denynotification;
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button") public WebElement allowaccessdevice;
	@AndroidFindBy(id = "free.rm.skytube.oss:id/title_text_view") public WebElement downloaditemname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"TRENDING (US)\")") public WebElement trendingpage;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Delete Download\")") public WebElement deletedownload;
	
	@AndroidFindBy(id = "free.rm.skytube.oss:id/options_button") public WebElement moreoptions;
	
	public Downloadspage(){
		this.driver = drivermanager.getdriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		}
	
	public void changeinversionok() {
		wait.until(ExpectedConditions.visibilityOf(changesInVersionOkButton));
		changesInVersionOkButton.click();
	}
	
	public void clickdownload() {
		downloadstab.click();
	}
	
	public String emptydownloadpage() {
		String emptytext = emptydownloads.getText();
		return emptytext;
	}
	
	public void downloadvideo(){
		searchbox.clear();
		searchbox.click();
		Actions action = new Actions(driver);
		action.sendKeys("rock squad barbie girls shorts" + Keys.ENTER).perform();
		wait.until(ExpectedConditions.visibilityOf(videooptions));
		videooptions.click();
		wait.until(ExpectedConditions.visibilityOf(download));
		download.click();
	}
	
	public String checkdownloadspage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(denynotification));
		denynotification.click();
		allowaccessdevice.click();
		Thread.sleep(10000);
		navigateback.click();
		downloadstab.click();
		String videoname = downloaditemname.getText();
		return videoname;
	}
	
	public void trendingpage() {
		trendingpage.click();
	}
	
	public void deletedownloadvideo() {
		searchbox.clear();
		searchbox.click();
		Actions action = new Actions(driver);
		action.sendKeys("rock squad barbie girls shorts" + Keys.ENTER).perform();
		wait.until(ExpectedConditions.visibilityOf(videooptions));
		videooptions.click();
		deletedownload.click();
	}
	
	public String checkdeletedownloadpage() {
		navigateback.click();
		downloadstab.click();
		String emptytext = emptydownloads.getText();
		return emptytext;
	}

	public void deletefromdownloadpage() {
		moreoptions.click();
		deletedownload.click();	
	}
}
