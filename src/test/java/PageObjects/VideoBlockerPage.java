package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.drivermanager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class VideoBlockerPage {

    AndroidDriver driver;
    WebDriverWait wait;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Video Blocker\")") public WebElement videoBlockerButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Channel Filters\")") public WebElement channelFiltersOption;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Language and Region Filters\")") public WebElement languageRegionFiltersOption;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Other Filters\")") public WebElement otherFiltersOption;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/checkbox\").instance(0)") public WebElement videoBlockerCheckbox;
  
    public VideoBlockerPage() {
        this.driver = drivermanager.getdriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickVideoBlockerButton() {
        wait.until(ExpectedConditions.elementToBeClickable(videoBlockerButton)).click();
    }

    public void verifyVideoBlockerChecked() {
        wait.until(ExpectedConditions.visibilityOf(videoBlockerCheckbox));
        String isChecked = videoBlockerCheckbox.getAttribute("checked");
        Assert.assertEquals(isChecked, "true", "Video Blocker checkbox is NOT checked");
    }

    public void verifyChannelFiltersDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(channelFiltersOption));
        Assert.assertTrue(channelFiltersOption.isDisplayed(), "Channel Filters option is not displayed");
    }

    public void verifyLanguageRegionFiltersDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(languageRegionFiltersOption));
        Assert.assertTrue(languageRegionFiltersOption.isDisplayed(), "Language and Region Filters option is not displayed");
    }

    public void verifyOtherFiltersDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(otherFiltersOption));
        Assert.assertTrue(otherFiltersOption.isDisplayed(), "Other Filters option is not displayed");
    }
}
