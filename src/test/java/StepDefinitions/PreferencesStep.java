package StepDefinitions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjects.PreferencesPage;
import Utilities.loggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PreferencesStep {
	PreferencesPage prefPage;
	
	
	@Given("the user is on the SkyTube home page")
	public void the_user_is_on_the_sky_tube_home_page() {
		prefPage = new PreferencesPage();	
		prefPage.changeinversionok();
		loggerLoad.info("Closing the pop up and navigating to the Trending page");
	}

	@When("the user clicks on the three-dot menu at the top-right corner and selects {string}")
	public void the_user_clicks_on_the_three_dot_menu_at_the_top_right_corner_and_selects(String string) {
		 	prefPage.moreOptions();
	        prefPage.preferences();
	        prefPage.notification();
	}

	@Then("the user is navigated to the Preferences tab")
	public void the_user_is_navigated_to_the_preferences_tab() {
		 	Assert.assertTrue(prefPage.PreferencesView.isDisplayed(), "Preferences view is not displayed");
	        Assert.assertEquals(prefPage.PreferencesView.getText(), "Preferences", "Preferences title mismatch");
	}

	@Given("the user is on the Preferences tab")
	public void the_user_is_on_the_preferences_tab() {
		prefPage = new PreferencesPage();	
		prefPage.changeinversionok();
		prefPage.moreOptions();
        prefPage.preferences();
        prefPage.notification();
	}

	@Then("the user should see the menu option {string} and click the menuOption")
	public void the_user_should_see_the_menu_option_and_click_the_menu_option(String menuOption) {
		prefPage.menuOption(menuOption);
		prefPage.goBack();
	}

	@When("the user clicks each Preferences menu item")
	public void the_user_clicks_each_preferences_menu_item() {
		int menuCount = prefPage.getAllPreferencesMenuItems().size();

	    for (int i = 0; i < menuCount; i++) {
	        // Re-fetch the menu items to avoid stale element reference
	        List<WebElement> menuItems = prefPage.getAllPreferencesMenuItems();
	        WebElement currentItem = menuItems.get(i);

	        String name = currentItem.getText();
	        loggerLoad.info("Clicking on menu item: " + name);
	        currentItem.click();

	        // Optional: Validate that the new screen or content appears here
	        Assert.assertTrue(prefPage.isPreferenceContentDisplayed(), "Content not displayed for " + name);

	        // Navigate back to Preferences page
	        prefPage.goBack();
	    }
		
		
	}

	@Then("the user should be able to view the content of each clicked Preferences menu item")
	public void the_user_should_be_able_to_view_the_content_of_each_clicked_preferences_menu_item() {
		Assert.assertTrue(prefPage.isPreferenceContentDisplayed(), "Preference content is not displayed.");

	}

}
