package StepDefinitions;



import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import PageObjects.SearchChannel;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
			
		AndroidDriver driver;
		WebDriverWait wait;
		SearchChannel searchChannel;
        
		@Given("The user is in Skytube home page")
			public void the_user_is_in_skytube_home_page() {
			    searchChannel = new SearchChannel();
				searchChannel.clickOkButton();
				searchChannel.allowPermissionsIfPresent();
				searchChannel.skipIntroIfPresent();
			}

			@When("The user searches for a channel with the name {string}")
			public void the_user_searches_for_a_channel_with_the_name(String channelName){
				searchChannel.openSearch();
				searchChannel.enterSearchText(channelName);
				searchChannel.selectChannelFromResults(channelName);

			}

			@Then("The user should see the listed videos in the home page for {string}")
			public void the_user_should_see_the_listed_videos_in_the_home_page_for(String expectedText) {
				Assert.assertTrue(searchChannel.isSearchResultVisible(expectedText),
		                  "Expected search results to contain: " + expectedText);
			}

			@When("The user searches for a specific video with its link {string}")
			public void the_user_searches_for_a_specific_video_with_its_link(String videoLink){
				searchChannel.openSearch();
				searchChannel.enterSearchText(videoLink);
			}

			@Then("The user should see the video in the home page for {string}")
			public void the_user_should_see_the_video_in_the_home_page_for(String videoLink) {
				Assert.assertTrue(searchChannel.isSearchResultVisible(videoLink),
		                  "Expected to find video link in search results: " + videoLink);
		    }

			@When("The user types the special characters in the search tab {string}")
			public void the_user_types_the_special_characters_in_the_search_tab(String specialChars) {
				searchChannel.openSearch();
				searchChannel.enterSearchText(specialChars);
			}

			@Then("The user should see an empty column below the search tab")
			public void the_user_should_see_an_empty_column_below_the_search_tab() {
				boolean isEmpty = searchChannel.isSearchResultEmpty();
			    Assert.assertTrue(isEmpty, "Expected no results, but some videos were found.");
			}
	}
