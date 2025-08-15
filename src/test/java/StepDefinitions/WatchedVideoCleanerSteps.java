package StepDefinitions;

import PageObjects.PreferencesPage;
import PageObjects.WatchedVideoCleanerPage;
import org.testng.Assert;
import io.cucumber.java.en.*;

import java.util.List;

public class WatchedVideoCleanerSteps {

    PreferencesPage prefPage;
    WatchedVideoCleanerPage cleanerPage;
      
    @Given("The user is on the SkyTube home page")
    public void the_user_is_on_the_sky_tube_home_page() {
        prefPage = new PreferencesPage();
        cleanerPage = new WatchedVideoCleanerPage();
        prefPage.changeinversionok();
    }

    @When("The user clicks the three-dot menu at the top-right corner and selects Watched Video Cleaner")
    public void the_user_clicks_the_three_dot_menu_and_selects() {
        prefPage.moreOptions(); 
        cleanerPage.clickWatchedVideocleaner();
    }

    @Then("The user should see the Watched Video Cleaner popup containing the options:")
    public void the_user_should_see_the_watched_video_cleaner_popup_containing_the_options(List<String> expectedOptions) {
        Assert.assertTrue(cleanerPage.isCleanerPopupDisplayed(),"Cleaner popup not displayed");
    }

    @Given("The user is in the Watched Video Cleaner popup")
    public void the_user_is_in_the_watched_video_cleaner_popup() throws Exception {
        prefPage = new PreferencesPage();
        cleanerPage = new WatchedVideoCleanerPage();
        prefPage.changeinversionok();
        // Setup: Ensure at least one bookmark & one download exist
        cleanerPage.downloadvideo();
        prefPage.notification();
        prefPage.notification();
        prefPage.notification();
        cleanerPage.bookmarkvideo();
        prefPage.moreOptions();
        cleanerPage.clickWatchedVideocleaner();
    }

    @When("The user clicks the CLEAN button without selecting any options")
    public void the_user_clicks_clean_without_selecting_any_options() {
    	cleanerPage.deselectRemoveWatchedDownloads();
        cleanerPage.deselectRemoveWatchedBookmarks();
    	cleanerPage.clickCleanButton();
    }

    @Then("The user should see the videos are present in the Downloads and Bookmark tab")
    public void the_user_should_see_the_videos_are_present_in_the_Downloads_and_Bookmark_tab() {
    	  // Open Downloads tab and check
        cleanerPage.openDownloadsTab();
        Assert.assertTrue(cleanerPage.areDownloadedVideosPresent(), 
            "No videos found in Downloads tab");

        // Open Bookmarks tab and check
        cleanerPage.BookmarksTab();
        Assert.assertTrue(cleanerPage.areBookmarkedVideosPresent(), 
            "No videos found in Bookmarks tab");
    }
    
    @When("The user selects {string} and clicks the CLEAN button")
    public void the_user_selects_option_and_clicks_clean(String option) throws Exception {
    	// Deselect all first
        cleanerPage.deselectRemoveWatchedDownloads();
        cleanerPage.deselectRemoveWatchedBookmarks();
        // Select the chosen option
        if (option.equalsIgnoreCase("Remove watched downloads")) {
            cleanerPage.selectRemoveWatchedDownloads();
        } else if (option.equalsIgnoreCase("Remove watched bookmarks")) {
            cleanerPage.selectRemoveWatchedBookmarks();
        }
        cleanerPage.clickCleanButton();
    }


    @Then("All watched videos should be removed from the DOWNLOADS tab")
    public void all_watched_videos_should_be_removed_from_downloads_tab() {
        cleanerPage.openDownloadsTab();
        cleanerPage.waitUntilDownloadsEmpty();
        Assert.assertFalse(cleanerPage.areDownloadedVideosPresent(), "Downloaded videos still present after cleaning");
    }

    @Then("All watched videos should be removed from the BOOKMARKS tab")
    public void all_watched_videos_should_be_removed_from_bookmarks_tab() {
        cleanerPage.openBookmarksTab();
        cleanerPage.waitUntilBookmarksEmpty();
        Assert.assertTrue(!cleanerPage.areBookmarkedVideosPresent(), "Bookmarked videos still present after cleaning");
    }

    @When("The user selects both {string} and {string} and clicks the CLEAN button")
    public void the_user_selects_both_options_and_clicks_clean(String opt1, String opt2) {
    	cleanerPage.clickCleanButton();
    }

    @Then("All watched videos should be removed from the DOWNLOADS and BOOKMARKS tabs")
    public void all_watched_videos_should_be_removed_from_both_tabs() {
        cleanerPage.openDownloadsTab();
        Assert.assertFalse(cleanerPage.areDownloadedVideosPresent(), "Downloaded videos still present after cleaning both");

        cleanerPage.BookmarksTab();
        Assert.assertFalse(cleanerPage.areBookmarkedVideosPresent(), "Bookmarked videos still present after cleaning both");
    }
}