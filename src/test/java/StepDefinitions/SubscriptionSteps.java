package StepDefinitions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import PageObjects.SubscribePage;
//import Utilities.drivermanager;
import Utilities.loggerLoad;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubscriptionSteps {
	
	AndroidDriver driver;
	WebDriverWait wait;
	private SubscribePage subscribePage;

    @Given("The user is on the Skytube home page")
	public void the_user_is_on_the_skytube_home_page() {
		subscribePage = new SubscribePage();
		subscribePage.clickOkButton();
		loggerLoad.info("Closing the pop up and navigating to the Trending page");
	}

	@When("The user plays a video and subscribes to the channel")
	public void the_user_plays_a_video_and_subscribes_to_the_channel() {
		 subscribePage.openChannelOptions();
	     subscribePage.selectChannel();
	     subscribePage.clickSubscribe();
		}

	@Then("The user should see a Subscribed confirmation message")
	public void the_user_should_see_a_subscribed_confirmation_message() {
		String toastMessage = subscribePage.getSubscribeToastMsg();
	    Assert.assertEquals(toastMessage, "Subscribed");
	}

	@When("The user navigates to the subscribed channels tab from the navigation menu")
	public void the_user_navigates_to_the_subscribed_channels_tab_from_the_navigation_menu() {
		subscribePage = new SubscribePage();
        subscribePage.openChannelOptions();
	    subscribePage.selectChannel();
	    subscribePage.clickSubscribe();
		subscribePage.openMenu();
	}

	@Then("The user should see the subscribed channel")
	public void the_user_should_see_the_subscribed_channel() {
		Assert.assertTrue(subscribePage.isSubscribedChannelVisible());
	   }

	@When("The user opens the subscribed channels tab from the navigation menu")
	public void the_user_opens_the_subscribed_channels_tab_from_the_navigation_menu() {
		subscribePage.openChannelOptions();
	    subscribePage.selectChannel();
	    subscribePage.clickSubscribe();
		subscribePage.openMenu();
	}

	@Then("The user should see the Unsubscribe button for the subscribed channel")
	public void the_user_should_see_the_unsubscribe_button_for_the_subscribed_channel() {
		Assert.assertTrue(subscribePage.isUnsubscribeButtonVisibleAfterClickingSubscribedChannel());
		}

	@Given("The user is on the subscribed channel page")
	public void the_user_is_on_the_subscribed_channel_page() {
		subscribePage = new SubscribePage();
		subscribePage.clickOkButton();
		subscribePage.openChannelOptions();
	    subscribePage.selectChannel();
	    subscribePage.clickSubscribe();
		subscribePage.openMenu();
		subscribePage.subchannel.click(); 
		}

	@When("The user clicks the Unsubscribe button")
	public void the_user_clicks_the_unsubscribe_button() {
		subscribePage.toggleSubscriptionFromChannel();
	}

	@Then("The user should see an Unsubscribed confirmation message")
	public void the_user_should_see_an_unsubscribed_confirmation_message() {
		 String toastMessage = subscribePage.getUnsubscribeToastMessage();
		    Assert.assertEquals("Unsubscribed", toastMessage);
          }

}
