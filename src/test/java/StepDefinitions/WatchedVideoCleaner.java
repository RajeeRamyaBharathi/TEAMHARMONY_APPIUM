package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WatchedVideoCleaner {
	
	@Given("The user clicks the three dot tab")
	public void the_user_clicks_the_three_dot_tab() {
	}
	@When("The user clicks the Watched Video Cleaner tab")
	public void the_user_clicks_the_watched_video_cleaner_tab() {
	}
	@Then("The user should see options for cleaning Watched Video Cleaner message")
	public void the_user_should_see_options_for_cleaning_watched_video_cleaner_message() {
	}

	@Given("The user clicks the Watched Video Cleaner button")
	public void the_user_clicks_the_watched_video_cleaner_button() {
	 	}
	@When("The user clicks the Remove watched downloads and clicks CLEAN button")
	public void the_user_clicks_the_remove_watched_downloads_and_clicks_clean_button() {
	 	}
	@Then("The user should see DOWNLOADS tab has no downloaded videos")
	public void the_user_should_see_downloads_tab_has_no_downloaded_videos() {
	}

	@When("The user clicks the Remove watched bookmarks and clicks CLEAN")
	public void the_user_clicks_the_remove_watched_bookmarks_and_clicks_clean() {
	  }
	@Then("The user should see DOWNLOADS tab has no bookmarked videos")
	public void the_user_should_see_downloads_tab_has_no_bookmarked_videos() {
	}

	@When("The user clicks both Remove watched downloads and Remove watched bookmarks")
	public void the_user_clicks_both_remove_watched_downloads_and_remove_watched_bookmarks() {
	}
	@Then("The user should see DOWNLOADS tab has no both downloaded and bookmarked videos")
	public void the_user_should_see_downloads_tab_has_no_both_downloaded_and_bookmarked_videos() {
	}
	
}
